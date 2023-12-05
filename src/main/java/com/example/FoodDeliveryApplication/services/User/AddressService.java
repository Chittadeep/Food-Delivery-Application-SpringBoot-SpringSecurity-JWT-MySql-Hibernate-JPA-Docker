package com.example.FoodDeliveryApplication.services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.User.Address;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.User.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address)
    {
        addressRepository.save(address);
        return address;
    }

    public List<Address> getAllAddress()
    {
        return (List<Address>) addressRepository.findAll();
    }

    public Address getAddressById(int addressId)
    {
        return addressRepository.findById(addressId).orElseThrow(()-> new EntityDoesNotExistException("Adress with this addressId does not exist"));
    }

    public List<Address> getAdressOfAnUser(int userId)
    {
        return addressRepository.getAddressByUserId(userId);
    }

    public List<Address> getAddressesByCity(String city)
    {
        return addressRepository.getAddressByCity(city);
    }

    public List<Address> getAddressesByState(String state)
    {
        return addressRepository.getAddressByState(state);
    }
    
    public List<Address> getAddressByPincode(String pinCode)
    {
        return  addressRepository.getAddressByPincode(pinCode);
    }

    public Address updateAddress(Address address)
    {
        Address oldAddress = getAddressById(address.getAddressId());
        oldAddress.setAddress(address.getAddress());
        oldAddress.setCity(address.getCity());
        oldAddress.setState(address.getState());
        oldAddress.setPincode(address.getPincode());
        addressRepository.save(address);
        return oldAddress;
    }

    public boolean deleteAddress(int addressId)
    {
        addressRepository.deleteById(addressId);
        return true;
    }
}
