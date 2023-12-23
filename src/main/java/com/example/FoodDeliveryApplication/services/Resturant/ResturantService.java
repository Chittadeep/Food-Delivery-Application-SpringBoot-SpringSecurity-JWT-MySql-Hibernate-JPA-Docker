package com.example.FoodDeliveryApplication.services.Resturant;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResturantService {
    @Autowired
    ResturantRepository resturantRepository;

    public Resturant createResturant(Resturant resturant)
    {
        resturantRepository.save(resturant);
        return resturant;
    }

    public List<Resturant> getAllResturants()
    {
        return (List<Resturant>) resturantRepository.findAll();
    }

    public Resturant getResturantById(int resturantId)
    {
        return resturantRepository.findById(resturantId).orElseThrow(()-> new EntityNotFoundException("Resturant with this id does not exist"));
    }

    public List<Resturant> getNearestAvailableResturantByPincode(String pincode)
    {
        return resturantRepository.getResturantByPincode(pincode);//get Nearest and Available change to be done
    }
    public List<Resturant> getResturantByPincode(String pincode)
    {
        return resturantRepository.getResturantByPincode(pincode);//get Nearest and Available change to be done
    }
    public Resturant updateResturant(Resturant resturant)
    {
        Resturant oldResturant= getResturantById(resturant.getResturantId());
        oldResturant.setAddress(resturant.getAddress());
        oldResturant.setResturantType(resturant.getResturantType());
        oldResturant.setEmail(resturant.getEmail());
        oldResturant.setCity(resturant.getCity());
        oldResturant.setPhoneNumber(resturant.getPhoneNumber());
        oldResturant.setFssaiStatus(resturant.getFssaiStatus());
        oldResturant.setBankDetails(resturant.getBankDetails());
        oldResturant.setGst(resturant.getGst());
        oldResturant.setLatitude(resturant.getLatitude());
        oldResturant.setLongitude(resturant.getLongitude());
        oldResturant.setPassword(resturant.getPassword());
        oldResturant.setName(resturant.getName());
        oldResturant.setWebsite(resturant.getWebsite());
        resturantRepository.save(oldResturant);
        return resturant;
    }

    public boolean blockResturant(int resturantId)
    {
        Resturant resturant = getResturantById(resturantId);
        resturant.setApproved(false);
        resturantRepository.save(resturant);
        return true;
    }

    public boolean unblockResturant(int resturantId)
    {        
        Resturant resturant = getResturantById(resturantId);
        resturant.setApproved(true);
        resturantRepository.save(resturant);
        return true;
    }

}
