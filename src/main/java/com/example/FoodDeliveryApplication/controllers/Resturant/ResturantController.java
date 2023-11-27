package com.example.FoodDeliveryApplication.controllers.Resturant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.services.Resturant.ResturantService;


@RestController
public class ResturantController {
    @Autowired
    ResturantService resturantService;

    @GetMapping(path="/allResturants")
    public ResponseEntity<List<Resturant>> getAllResutrants()
    {
        return new ResponseEntity<List<Resturant>>(resturantService.getAllResturants(), HttpStatus.OK);
    }

    @PostMapping(path="/Resturant")
    public ResponseEntity<Resturant> createResturant(@RequestBody Resturant resturant)
    {
        return new ResponseEntity<Resturant>(resturantService.createResturant(resturant), HttpStatus.CREATED);
    }

    @GetMapping(path="/Resturant/{id}")
    public ResponseEntity<Resturant> getResturantById(int resturantId)
    {
        return new ResponseEntity<Resturant>(resturantService.getResturantById(resturantId), HttpStatus.OK);
    }

    @PutMapping(path = "/Resturant")
    public ResponseEntity<Resturant> updateResturant(@RequestBody Resturant resturant)
    {
        return new ResponseEntity<Resturant>(resturantService.updateResturant(resturant), HttpStatus.OK);
    }

    @PatchMapping(path = "/Resturant/block/{resturantId}")
    public ResponseEntity<Boolean> blockResturant(@PathVariable int resturantId)
    {
        return new ResponseEntity<Boolean>(resturantService.blockResturant(resturantId), HttpStatus.OK);
    }

    @PatchMapping(path = "/Resturant/unblock/{resturantId}")
    public ResponseEntity<Boolean> unblockResturant(@PathVariable int resturantId)
    {
        return new ResponseEntity<Boolean>(resturantService.unblockResturant(resturantId), HttpStatus.OK);
    }
}
