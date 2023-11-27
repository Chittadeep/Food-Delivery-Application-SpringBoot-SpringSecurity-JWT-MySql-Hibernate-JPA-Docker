package com.example.FoodDeliveryApplication.services.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
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
        oldRider.setBankAccountInfo(rider.getBankAccountInfo());
        oldRider.setVehicleType(rider.getVehicleType());
        oldRider.setVehicle(rider.getVehicle());
        oldRider.setLatittude(rider.getLatittude());
        oldRider.setLongitude(rider.getLongitude());
        riderRepository.save(oldRider);
        //why cant we save the new rider directly when both old rider and new rider have their primary keys equal?
        return oldRider;
    }

    public boolean changeProfilePicture(int riderId, byte[] image)
    {
        return true;
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

}
