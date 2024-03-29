package com.example.FoodDeliveryApplication.controllers.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.model.request.RiderRequest;
import com.example.FoodDeliveryApplication.services.Rider.RiderService;


@RestController
public class RiderController {
    @Autowired
    private RiderService riderService;

    @GetMapping(path="/rider")
    public ResponseEntity<List<Rider>> getAllRiders()
    {
        return new ResponseEntity<List<Rider>>(riderService.getAllRiders(), HttpStatus.OK);
    }

    @GetMapping(path="/rider/{riderId}")
    public ResponseEntity<Rider> getRider(@PathVariable int riderId)
    {
        return new ResponseEntity<Rider>(riderService.getRider(riderId), HttpStatus.OK);
    }

    @PostMapping(path = "/rider")
    public ResponseEntity<Rider> createRider(@RequestBody RiderRequest riderRequest)
    {
        return new ResponseEntity<Rider>(riderService.createRider(riderRequest), HttpStatus.OK);
    }

    @PutMapping(path = "/rider")
    public ResponseEntity<Rider> updateRider(@RequestBody Rider rider)
    {
        return new ResponseEntity<Rider>(riderService.updateRider(rider), HttpStatus.OK);
    }

    @PatchMapping(path="/rider/block/{riderId}")
    public ResponseEntity<Boolean> blockRider(@PathVariable int riderId)
    {
        return new ResponseEntity<Boolean>(riderService.blockRider(riderId), HttpStatus.OK);
    }

    @PatchMapping(path="/rider/unblock/{riderId}")
    public ResponseEntity<Boolean> unblockRider(@PathVariable int riderId)
    {
        return new ResponseEntity<Boolean>(riderService.unblockRider(riderId), HttpStatus.OK);
    }


    @PatchMapping(path="/rider/{riderId}/changeProfilePicture")
    public ResponseEntity<Boolean> changeProfilePicture(@PathVariable int riderId, @RequestParam("file") MultipartFile file)
    {
        return new ResponseEntity<Boolean>(riderService.changeProfilePicture(riderId, file), HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/rider/{riderId}/getProfilePicture")
    public ResponseEntity<InputStreamResource> getProfilePicture(@PathVariable int riderId)
    {    
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=riderProfilePic.jpg");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.IMAGE_JPEG).body(riderService.getProfilePicture(riderId));
    } 

    @PatchMapping(path = "/rider/updateLocation")
    public ResponseEntity<Boolean> updateRiderLocation(@RequestParam int riderId,@RequestParam String address, @RequestParam String city, @RequestParam String pincode, @RequestParam double latitude, @RequestParam double longitude)
    {
        return new ResponseEntity<Boolean>(riderService.updateRiderLocation(riderId, pincode, address, city, latitude, longitude), HttpStatus.OK);
    }

    @PatchMapping(path = "/rider/{riderId}/makeRiderAvailable")
    public ResponseEntity<Boolean> makeRiderAvailable(@PathVariable int riderId)
    {
        return new ResponseEntity<Boolean>(riderService.makeRiderAvailable(riderId), HttpStatus.OK);
    }
    
    @PatchMapping(path = "/rider/{riderId}/makeRiderUnAvailable")
    public ResponseEntity<Boolean> makeRiderUnavailable(@PathVariable int riderId)
    {
        return new ResponseEntity<Boolean>(riderService.makeRiderUnavailable(riderId), HttpStatus.OK);
    }

    @PatchMapping(path = "/rider/submitDl/{riderId}")
    public ResponseEntity<Boolean> submitRiderDl(@PathVariable int riderId, @RequestParam("file") MultipartFile file)
    {
        return new ResponseEntity<Boolean>(riderService.submitDl(riderId, file), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/rider/{riderId}/getDl")
    public ResponseEntity<InputStreamResource> getDl(@PathVariable int riderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RiderDl.jpg");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.IMAGE_JPEG).body(riderService.getDl(riderId));
    }

    @PatchMapping(path = "/rider/updatePassword")
    public ResponseEntity<Boolean> updateRiderPassword(@RequestParam int riderId, @RequestParam String password)
    {
        return new ResponseEntity<Boolean>(riderService.updatePassword(riderId, password), HttpStatus.OK);
    }
    
    @PatchMapping(path = "/rider/updateMail")
    public ResponseEntity<Boolean> updateRiderMail(@RequestParam int riderId, @RequestParam String mail)
    {
        return new ResponseEntity<Boolean>(riderService.updateMail(riderId, mail), HttpStatus.OK);
    }
}
