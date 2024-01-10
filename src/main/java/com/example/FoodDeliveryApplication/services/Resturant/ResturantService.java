package com.example.FoodDeliveryApplication.services.Resturant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Enums.Role;
import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;
import com.example.FoodDeliveryApplication.model.request.ResturantRequest;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantRepository;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;
import com.example.FoodDeliveryApplication.repository.globals.LoginDetailsRepository;
import com.example.FoodDeliveryApplication.services.helpers.ResturantHelper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResturantService extends ResturantHelper {
    
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Resturant createResturant(ResturantRequest resturantRequest)
    {
        Resturant resturant = new Resturant(resturantRequest);
        resturantRepository.save(resturant);
        String passwordEncoded = passwordEncoder.encode(resturantRequest.getPassword());
        LoginDetails loginDetails = new LoginDetails(resturant.getEmail(), passwordEncoded, Role.RESTURANT);
        loginDetailsRepository.save(loginDetails);
        return resturant;
    }

    public List<Resturant> getAllResturants()
    {
        return (List<Resturant>) resturantRepository.findAll();
    }

    public Resturant getResturantById(int resturantId)
    {
        validateResturantAndAdmin(resturantId);
        return resturantRepository.findById(resturantId).orElseThrow(()-> new EntityNotFoundException("Resturant with this id does not exist"));
    }

    public List<Resturant> getNearestAvailableResturantByPincode(String pincode)
    {
        return resturantRepository.getResturantByPincode(pincode);//get Nearest and Available change to be done
    }

    public List<Resturant> getNearestAvailableResturantByPincodeAndApproved(String pincode)
    {
        return resturantRepository.getResturantByPincodeAndApproved(pincode, true);//get Nearest and Available change to be done
    }

    public List<Resturant> getResturantByPincode(String pincode)
    {
        return resturantRepository.getResturantByPincode(pincode);//get Nearest and Available change to be done
    }

    public boolean updateBankDetailsOfResturant(int resturantId, MultipartFile file)
    {
        validateResturant(resturantId);
        Resturant oldResturant = getResturantById(resturantId);
        try{
            oldResturant.setBankDetails(file.getBytes());
            resturantRepository.save(oldResturant);
            return true;
        }catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public InputStreamResource getBankDetailsOfResturant(int resturantId)
    {
        validateResturantAndAdmin(resturantId);
        byte[] bytes = getResturantById(resturantId).getBankDetails();
        if(bytes==null) throw new RuntimeException("The image you requested does not exist");
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource = new InputStreamResource(stream);
        return resource;
    }

    public Resturant updateResturant(Resturant resturant)
    {
        validateResturant(resturant.getResturantId());
        Resturant oldResturant= getResturantById(resturant.getResturantId());
        oldResturant.setAddress(resturant.getAddress());
        oldResturant.setResturantType(resturant.getResturantType());
        oldResturant.setCity(resturant.getCity());
        oldResturant.setPhoneNumber(resturant.getPhoneNumber());
        oldResturant.setFssaiStatus(resturant.getFssaiStatus());
        oldResturant.setBankDetails(resturant.getBankDetails());
        oldResturant.setGst(resturant.getGst());
        oldResturant.setLatitude(resturant.getLatitude());
        oldResturant.setLongitude(resturant.getLongitude());
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

    public boolean updatePassword(int resturantId, String password)
    {
        validateResturantAndAdmin(resturantId);
        Resturant resturant = getResturantById(resturantId);
        LoginDetails resturantLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(resturant.getEmail());
        resturantLoginDetails.setPassword(passwordEncoder.encode(password));
        loginDetailsRepository.save(resturantLoginDetails);
        return true;
    }

    public boolean updateMail(int resturantId, String mail)
    {
        validateResturantAndAdmin(resturantId);
        Resturant resturant = getResturantById(resturantId);
        LoginDetails resturantLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(resturant.getEmail());
        resturantLoginDetails.setUserName(mail);
        resturant.setEmail(mail);
        resturantRepository.save(resturant);
        loginDetailsRepository.save(resturantLoginDetails);
        return true;
    }

}
