package com.example.FoodDeliveryApplication.services.order;

import java.sql.Timestamp;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Enums.ModeOfPayment;
import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Order.OrderItem;
import com.example.FoodDeliveryApplication.entities.Resturant.Menu;
import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.entities.Resturant.ResturantPayment;
import com.example.FoodDeliveryApplication.entities.Resturant.ResturantRating;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;
import com.example.FoodDeliveryApplication.entities.User.Address;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.User.UserPayment;
import com.example.FoodDeliveryApplication.entities.globals.Globals;
import com.example.FoodDeliveryApplication.exceptions.AddressIsNotOfUserException;
import com.example.FoodDeliveryApplication.exceptions.MenuNotAvailableInAnyResturantException;
import com.example.FoodDeliveryApplication.exceptions.OrderAlreadyCancelledException;
import com.example.FoodDeliveryApplication.exceptions.ResturantAndUserAddressTooFarException;
import com.example.FoodDeliveryApplication.model.response.OrderResponse;
import com.example.FoodDeliveryApplication.repository.Order.OrderRepository;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantRatingRepository;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRatingRepositiory;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRepository;
import com.example.FoodDeliveryApplication.repository.User.UserPaymentRepository;
import com.example.FoodDeliveryApplication.services.AssignRiderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserPaymentRepository userPaymentRepository;
    private RiderRepository riderRepository;
    private RiderRatingRepositiory riderRatingRepositiory;
    private ResturantRatingRepository resturantRatingRepository;
    
    @Autowired
    public OrderService(OrderRepository orderRepository, EntityManagerFactory entityManagerFactory,
        EntityManager entityManager, UserPaymentRepository userPaymentRepository, RiderRepository riderRepository, RiderRatingRepositiory riderRatingRepositiory, ResturantRatingRepository resturantRatingRepository) {
        this.orderRepository = orderRepository;
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
        this.userPaymentRepository = userPaymentRepository;
        this.riderRepository = riderRepository;
        this.riderRatingRepositiory = riderRatingRepositiory;
        this.resturantRatingRepository = resturantRatingRepository;
        new Thread(new AssignRiderService(orderRepository, riderRepository), "AssignRiderThread").start();
    }

    @Transactional
    public OrderResponse createOrder(OrderCustom order)
    {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = entityManager.unwrap(Session.class);
        //session.beginTransaction();
        Resturant resturant = session.get(Resturant.class, order.getResturant().getResturantId());
        User user = session.get(User.class, order.getUser().getUserId());
        Address address = session.get(Address.class, order.getAddress().getAddressId());
        System.out.println(address.getPincode());
        System.out.println(resturant.getPincode());
        if(address.getUserId()!=user.getUserId()) throw new RuntimeException("Address is not of the user given");
        if(!address.getPincode().equals(resturant.getPincode())) throw new RuntimeException("Address and Resturant too far");
        List<OrderItem> items =  updatePrice(order.getOrderItems(), session);
        items.stream().forEach((item)->{
            item.setOrder(order);});
        order.setOrderStatus(OrderStatus.INITIATED); 
        int id =(Integer) session.save(order);
        session.flush();
        OrderCustom orderCustom = session.get(OrderCustom.class, id);
        //session.getTransaction().commit();
        //sessionFactory.getCurrentSession().flush();

        //create order items repository to save and repository save all 
        // when order gets created the user payment should also get created
        UserPayment userPayment = new UserPayment();
        userPayment.setBaseAmount(getBasePrice(items));
        userPayment.setUserPaymentId(orderCustom.getUser().getUserId());
        userPayment.setOrderId(orderCustom.getOrderId());
        userPayment.setGstAmount((Globals.getGstPercentage()*userPayment.getBaseAmount())/100);
        userPayment.setMiscellaneousFee((Globals.getMiscellaneousFee()*userPayment.getBaseAmount())/100);
        userPayment.setDeliveryFee(Globals.getDeliveryFeePerKm()*1);
        userPayment.setTip(20);
        userPayment.setTotalAmount(userPayment.getBaseAmount()+userPayment.getDeliveryFee()+userPayment.getGstAmount()+userPayment.getTip());
        userPayment.setPaid(false);
        userPayment.setModeOfPayment(ModeOfPayment.INITIATED);
        userPayment.setTransactionId(new Timestamp(System.currentTimeMillis()).toString());//currently since for implementing new TransactionId we have to use microservices for integrating with actual payment gateways 
        //getting the user payment after saving
        userPayment = userPaymentRepository.save(userPayment);
        //resetting the user payment in the order custom for response
        orderCustom.setUserPayment(userPayment);

        //For the order rider should be available
        orderRepository.save(orderCustom);
        OrderResponse orderResponse = new OrderResponse(orderCustom);

        return orderResponse;
        }
        
    private List<OrderItem> updatePrice(List<OrderItem> orderItems, Session session)
    {
        for(OrderItem orderItem : orderItems)
        {
            if(orderItem.getMenu()==null) throw new RuntimeException("The price menu you want to update does not exist for any resturant");
            orderItem.setMenu(session.get(Menu.class, orderItem.getMenu().getMenuId()));
            orderItem.setPrice(orderItem.getQuantity()*orderItem.getMenu().getPrice());
        }
        return orderItems;
    }
    
    private double getBasePrice(List<OrderItem> orderItems)
    {
        double tot = 0;
        for(OrderItem orderItem : orderItems)
        {
            tot+=orderItem.getPrice();
        }
        return tot;
    }

    public OrderResponse updateOrderStatus(int id, OrderStatus status)
    {
        OrderCustom order = getOrder(id);
        order.setOrderStatus(status);
        if(order.getOrderStatus()==OrderStatus.CANCELLED) throw new RuntimeException("Order is already cancelled so the order cannot be changed");
        if(order.getOrderStatus()==OrderStatus.DELIVERED) throw new RuntimeException("Order is already delivered so the order status cannot be changed");
        if(status.equals(OrderStatus.INITIATED))
        {
            order.setOrderInitiatedTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        else if(status.equals(OrderStatus.PLACED))
        {
            order.setOrderPlacedTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        else if(status.equals(OrderStatus.ACCEPTED))
        {
            order.setOrderAcceptedTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        else if(status.equals(OrderStatus.READY_FOR_PICKUP))
        {
            if(!status.equals(OrderStatus.ACCEPTED)) throw new RuntimeException("Order is not accepted then how is it ready for pickup");
            order.setOrderReadyForPickupTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        else if(status.equals(OrderStatus.ON_THE_WAY))
        {
            order.setOrderOnTheWayTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            ResturantRating resturantRating = new ResturantRating();
            resturantRating.setUser(order.getUser());
            resturantRating.setResturant(order.getResturant());
            resturantRating.setCompleted(false);
            resturantRatingRepository.save(resturantRating);
            
            RiderRating riderRating = new RiderRating();
            riderRating.setUser(order.getUser());
            riderRating.setRider(order.getRider());
            riderRating.setCompleted(false);
            riderRatingRepositiory.save(riderRating);
            order.setOrderDeliveredTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        return new OrderResponse(orderRepository.save(order));
    }

    public OrderCustom getOrder(int id)
    {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("no such id available for order"));
    }

    public List<OrderCustom> getAllOrderForAdmin()
    {
        return (ArrayList<OrderCustom>) orderRepository.findAll();
    }

    public List<OrderResponse> getOrderCustomByUserId(int userId)
    {
        List<OrderCustom> results = orderRepository.getOrderCustomByUser_UserId(userId);
        
        return results.stream().map(OrderResponse::new).toList();
    }

    public List<OrderResponse> getOrderCustomByResturantId(int resturantId)
    {
        return orderRepository.getOrderCustomByResturant_ResturantId(resturantId).stream().map(OrderResponse::new).toList();
    }

    public List<OrderResponse> getOrderCustomByRiderId(int riderId)
    {
        return orderRepository.getOrderCustomByRider_RiderId(riderId).stream().map(OrderResponse::new).toList();
    }

    public List<OrderCustom> getOrderCustomByResturantIdAndOrderStatus(int resturantId, OrderStatus orderStatus)
    {
        return orderRepository.getOrderCustomByResturant_ResturantIdAndOrderStatus(resturantId, orderStatus);
    }

    public List<OrderResponse> getOrderCustomPendingByResturantId(int resturantId)
    {
        /*
        List<OrderResponse> results = new ArrayList<>();
        for(OrderCustom order: getOrderCustomByResturantIdAndOrderStatus(resturantId, OrderStatus.INITIATED))
        {
            results.add(new OrderResponse(order));
        }
        for(OrderCustom order : getOrderCustomByResturantIdAndOrderStatus(resturantId, OrderStatus.PLACED))
        {
            results.add(new OrderResponse(order));
        }
        return results;
        */
        return orderRepository.getPendingOrdersForResturant(resturantId).stream().map(OrderResponse::new).toList();
        //return orderRepository.getPendingOrdersForResturant(resturantId);
    }

}
