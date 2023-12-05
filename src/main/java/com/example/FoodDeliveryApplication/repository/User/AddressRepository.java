package com.example.FoodDeliveryApplication.repository.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.User.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
    
    //@Query("Select a from Address a where a.userId= :userId")
    public List<Address> getAddressByUserId(int userId);

    //@Query("Select a from Address a where a.city = :city")
    public List<Address> getAddressByCity(String city);

    //@Query("Select a from Address a where a.state = :state")
    public List<Address> getAddressByState(String state);
    
    //@Query("Select a from Address a where a.pinCode = :pincode")
    public List<Address> getAddressByPincode(String pincode);
}
