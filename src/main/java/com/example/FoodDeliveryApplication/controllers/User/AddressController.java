package com.example.FoodDeliveryApplication.controllers.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.User.Address;
import com.example.FoodDeliveryApplication.services.User.AddressService;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping(path="/address")
    public ResponseEntity<List<Address>> getAllAddress()
    {
        return new ResponseEntity<List<Address>>(addressService.getAllAddress(), HttpStatus.OK);
    }

    @GetMapping(path = "/address/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable int addressId)
    {
        return new ResponseEntity<Address>(addressService.getAddressById(addressId), HttpStatus.OK);
    }

    @GetMapping(path = "/address/getAddressOfAnUser/{userId}")
    public ResponseEntity<List<Address>> getAdressOfAnUser(@PathVariable int userId)
    {
        return new ResponseEntity<List<Address>>(addressService.getAdressOfAnUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/address/getAddressesByCity/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable String city)
    {
        return new ResponseEntity<List<Address>>(addressService.getAddressesByCity(city), HttpStatus.OK);
    }

    @GetMapping(path = "/address/getAddressesByState/{state}")
    public ResponseEntity<List<Address>> getAddressesByState(@PathVariable String state)
    {
        return new ResponseEntity<List<Address>>(addressService.getAddressesByState(state), HttpStatus.OK);
    }

    @GetMapping(path = "/address/getAdressesByPincode/{pinCode}")
    public ResponseEntity<List<Address>> getAddressByPincode(@PathVariable String pinCode)
    {
        return new ResponseEntity<List<Address>>(addressService.getAddressByPincode(pinCode), HttpStatus.OK);
    }

    @PostMapping(path="/address")
    public ResponseEntity<Address> createAddress(@RequestBody Address address)
    {
        return new ResponseEntity<Address>(addressService.createAddress(address), HttpStatus.CREATED);
    }

    @PutMapping(path = "/address")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address)
    {
        return new ResponseEntity<Address>(addressService.updateAddress(address), HttpStatus.OK);
    }

    @DeleteMapping(path = "/address/{addressId}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable int addressId)
    {
        return new ResponseEntity<Boolean>(addressService.deleteAddress(addressId), HttpStatus.OK);
    }
}
