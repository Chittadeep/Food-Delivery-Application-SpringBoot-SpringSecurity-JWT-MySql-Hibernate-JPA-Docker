package com.example.FoodDeliveryApplication.services.Rider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Enums.Role;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.exceptions.ImageRequestedDoesNotExistException;
import com.example.FoodDeliveryApplication.model.request.RiderRequest;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRepository;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;
import com.example.FoodDeliveryApplication.repository.globals.LoginDetailsRepository;

@Service
public class RiderService {
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public Rider createRider(RiderRequest riderRequest)
    {
        Rider rider = new Rider(riderRequest);
        String passwordEncoded = passwordEncoder.encode(riderRequest.getPassword());
        LoginDetails loginDetails = new LoginDetails(rider.getMail(), passwordEncoded, Role.RIDER);
        loginDetailsRepository.save(loginDetails);
        riderRepository.save(rider);
        return rider;
    }

    public List<Rider> getAllRiders()
    {
        return (List<Rider>) riderRepository.findAll();
    }

    public Rider getRider(int riderId)
    {
        validateRiderAndAdmin(riderId);
        return riderRepository.findById(riderId).orElseThrow(()-> new RuntimeException("Rider with this id does not exist"));
    }

    public Rider updateRider(Rider rider)
    {
        validateRider(rider.getRiderId());
        Rider oldRider = getRider(rider.getRiderId());
        oldRider.setName(rider.getName());
        oldRider.setCity(rider.getCity());
        oldRider.setAddress(rider.getAddress());
        oldRider.setDob(rider.getDob());
        oldRider.setState(rider.getState());
        oldRider.setPincode(rider.getPincode());
        oldRider.setPhoneNumber(rider.getPhoneNumber());
        oldRider.setVehicleType(rider.getVehicleType());
        oldRider.setVehicle(rider.getVehicle());
        oldRider.setLatitude(rider.getLatitude());
        oldRider.setLongitude(rider.getLongitude());
        riderRepository.save(oldRider);
        //why cant we save the new rider directly when both old rider and new rider have their primary keys equal?
        return oldRider;
    }

    public boolean changeProfilePicture(int riderId, MultipartFile file)
    {
        validateRider(riderId);
        Rider oldRider = getRider(riderId);
        try {
            oldRider.setImage(file.getBytes());
            riderRepository.save(oldRider);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean submitDl(int riderId, MultipartFile file)
    {
        validateRider(riderId);
        Rider oldRider = getRider(riderId);
        try{
            oldRider.setDl(file.getBytes());
            riderRepository.save(oldRider);
        }catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public InputStreamResource getProfilePicture(int riderId)
    {
        validateRiderAndAdmin(riderId);
        byte[] bytes = getRider(riderId).getImage();
        if(bytes==null) throw new RuntimeException("The image you requested does not exist");
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource = new InputStreamResource(stream);
        return resource;
    }

    public InputStreamResource getDl(int riderId)
    {
        validateRiderAndAdmin(riderId);
        byte[] bytes = getRider(riderId).getDl();
        if(bytes==null) throw new RuntimeException("The image you requested does not exist");
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource= new InputStreamResource(stream);
        return resource;
    }

    public boolean blockRider(int riderId)
    {
        Rider rider = getRider(riderId);
        rider.setAvailable(false);
        riderRepository.save(rider);
        return true;
    }

    public boolean unblockRider(int riderId)
    {
        Rider rider = getRider(riderId);
        rider.setAvailable(true);
        riderRepository.save(rider);
        return true;
    }

    public boolean updateRiderLocation(int riderId, String pincode, String address, String city, double latitude, double longitude)  {
        validateRiderAndAdmin(riderId);
        Rider rider = getRider(riderId);
        rider.setCity(city);
        rider.setAddress(address);
        rider.setPincode(pincode);
        rider.setLatitude(latitude);
        rider.setLongitude(longitude);
        riderRepository.save(rider);
        return true;
    }

    public boolean makeRiderAvailable(int riderId)
    {
        validateRiderAndAdmin(riderId);
        Rider rider = getRider(riderId);
        rider.setAvailable(true);
        riderRepository.save(rider);
        return true;
    }

    public boolean makeRiderUnavailable(int riderId)
    {
        validateRiderAndAdmin(riderId);
        Rider rider = getRider(riderId);
        rider.setAvailable(false);
        riderRepository.save(rider);
        return true;
    }
    
    public boolean updatePassword(int riderId, String password)
    {
        validateRiderAndAdmin(riderId);
        Rider rider = getRider(riderId);
        LoginDetails oldLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(rider.getMail());
        oldLoginDetails.setPassword(passwordEncoder.encode(password));
        loginDetailsRepository.save(oldLoginDetails);
        return true;
    }

    public boolean updateMail(int riderId, String mail)
    {
        validateRiderAndAdmin(riderId);
        Rider oldRider = getRider(riderId);
        LoginDetails oldLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(oldRider.getMail());
        oldLoginDetails.setUserName(mail);
        oldRider.setMail(mail);
        loginDetailsRepository.save(oldLoginDetails);
        riderRepository.save(oldRider);
        return true;
    }

    private void validateRider(int riderId)
    {
        UserDetails riderDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rider loggedInRider = riderRepository.getRiderByMail(riderDetail.getUsername());
        if(loggedInRider==null) throw new RuntimeException("any rider with the jwt token does not exist");
        if(loggedInRider.getRiderId()!=riderId) throw new RuntimeException("This rider cannot update other riders details");
    }

    private void validateRiderAndAdmin(int riderId)
    {
        UserDetails riderDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rider loggedInRider = riderRepository.getRiderByMail(riderDetail.getUsername());
        if(loggedInRider==null) 
        {
            User user = userRepository.getUserByMail(riderDetail.getUsername());
            if(user==null) throw new RuntimeException("Neither any rider nor any admin is registered with this JWT Token");
            if(user.isAdmin()) return;
        }
        if(loggedInRider.getRiderId()!=riderId) throw new RuntimeException("This rider cannot update other riders details");
    }
}
