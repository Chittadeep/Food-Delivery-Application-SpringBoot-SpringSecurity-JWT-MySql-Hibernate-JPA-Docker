package com.example.FoodDeliveryApplication.services.Rider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.exceptions.ImageRequestedDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRepository;

@Service
public class RiderService {
    @Autowired
    private RiderRepository riderRepository;

    public Rider createRider(Rider rider)
    {
        riderRepository.save(rider);
        return rider;
    }

    public List<Rider> getAllRiders()
    {
        return (List<Rider>) riderRepository.findAll();
    }

    public Rider getRider(int riderId)
    {
        return riderRepository.findById(riderId).orElseThrow(()-> new EntityDoesNotExistException("Rider with this id does not exist"));
    }

    public Rider updateRider(Rider rider)
    {
        Rider oldRider = getRider(rider.getRiderId());
        oldRider.setName(rider.getName());
        oldRider.setCity(rider.getCity());
        oldRider.setAddress(rider.getAddress());
        oldRider.setDob(rider.getDob());
        oldRider.setState(rider.getState());
        oldRider.setPincode(rider.getPincode());
        oldRider.setPhoneNumber(rider.getPhoneNumber());
        oldRider.setImage(rider.getImage());
        oldRider.setMail(rider.getMail());
        oldRider.setPassword(rider.getPassword());
        //oldRider.setBankAccountInfo(rider.getBankAccountInfo());
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
        byte[] bytes = getRider(riderId).getImage();
        if(bytes==null) throw new ImageRequestedDoesNotExistException();
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource = new InputStreamResource(stream);
        return resource;
    }

    public InputStreamResource getDl(int riderId)
    {
        byte[] bytes = getRider(riderId).getDl();
        if(bytes==null) throw new ImageRequestedDoesNotExistException();
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

    public boolean updateRiderLocation(int riderId, String pincode, double latitude, double longitude)  {
        Rider rider = getRider(riderId);
        rider.setPincode(pincode);
        rider.setLatitude(latitude);
        rider.setLongitude(longitude);
        riderRepository.save(rider);
        return true;
    }

    public boolean makeRiderAvailable(int riderId)
    {
        Rider rider = getRider(riderId);
        rider.setAvailable(true);
        riderRepository.save(rider);
        return true;
    }

    public boolean makeRiderUnavailable(int riderId)
    {
        Rider rider = getRider(riderId);
        rider.setAvailable(false);
        riderRepository.save(rider);
        return true;
    }

}
