package com.example.FoodDeliveryApplication.services.order;

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
import com.example.FoodDeliveryApplication.entities.User.Address;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.User.UserPayment;
import com.example.FoodDeliveryApplication.entities.globals.Globals;
import com.example.FoodDeliveryApplication.model.response.OrderResponse;
import com.example.FoodDeliveryApplication.repository.Order.OrderRepository;
import com.example.FoodDeliveryApplication.repository.User.UserPaymentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserPaymentRepository userPaymentRepository;
    
    @Transactional
    public OrderResponse createOrder(OrderCustom order)
    {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = entityManager.unwrap(Session.class);
        //session.beginTransaction();
        //OrderCustom orderCustom =  orderRepository.save(order);
        Resturant resturant = session.get(Resturant.class, order.getResturant().getResturantId());
        User user = session.get(User.class, order.getUser().getUserId());
        Address address = session.get(Address.class, order.getAddress().getAddressId());
        List<OrderItem> items =  updatePrice(order.getOrderItems(), session);
        items.stream().forEach(item->item.setOrder(order));
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
        userPayment.setDeliveryFee(id);
        userPayment.setPaid(false);
        userPayment.setModeOfPayment(ModeOfPayment.INITIATED);
        userPayment.setTransactionId("abcd");
        //getting the user payment after saving
        userPayment = userPaymentRepository.save(userPayment);
        //resetting the user payment in the order custom for response
        orderCustom.setUserPayment(userPayment);
        OrderResponse orderResponse = new OrderResponse(orderCustom);

        return orderResponse;
    }

    private List<OrderItem> updatePrice(List<OrderItem> orderItems, Session session)
    {
        for(OrderItem orderItem : orderItems)
        {
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

    public OrderCustom updateOrderStatus(int id, OrderStatus status)
    {
        OrderCustom order = getOrder(id);
        order.setOrderStatus(status);
        return orderRepository.save(order);
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

    public List<OrderCustom> getOrderCustomByResturantId(int resturantId)
    {
        return orderRepository.getOrderCustomByResturant_ResturantId(resturantId);
    }

    public List<OrderCustom> getOrderCustomByRiderId(int riderId)
    {
        return orderRepository.getOrderCustomByRider_RiderId(riderId);
    }

    public List<OrderCustom> getOrderCustomByResturantIdAndOrderStatus(int resturantId, OrderStatus orderStatus)
    {
        return orderRepository.getOrderCustomByResturant_ResturantIdAndOrderStatus(resturantId, orderStatus);
    }
}
