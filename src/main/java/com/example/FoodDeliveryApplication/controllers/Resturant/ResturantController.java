package com.example.FoodDeliveryApplication.controllers.Resturant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.model.request.ResturantRequest;
import com.example.FoodDeliveryApplication.services.Resturant.ResturantService;


@RestController
public class ResturantController {
    @Autowired
    ResturantService resturantService;

    @GetMapping(path="/admin/allResturants")
    public ResponseEntity<List<Resturant>> getAllResutrants()
    {
        return new ResponseEntity<List<Resturant>>(resturantService.getAllResturants(), HttpStatus.OK);
    }

    @PostMapping(path="/resturant")
    public ResponseEntity<Resturant> createResturant(@RequestBody ResturantRequest resturantRequest)
    {
        return new ResponseEntity<Resturant>(resturantService.createResturant(resturantRequest), HttpStatus.CREATED);
    }
    
    @GetMapping(path="/resturant/getNearestAvailableResturantsByPincode/{pincode}")
    public ResponseEntity<List<Resturant>> getNearestAvailableResturantByPincode(@PathVariable String pincode)
    {
        return new ResponseEntity<List<Resturant>>(resturantService.getNearestAvailableResturantByPincode(pincode), HttpStatus.OK);
    }

    @GetMapping(path="/resturant/getResturantByPincode/{pincode}")
    public ResponseEntity<List<Resturant>> getResturantByPincode(@PathVariable String pincode)
    {
        return new ResponseEntity<List<Resturant>>(resturantService.getResturantByPincode(pincode), HttpStatus.OK);
    }

    @GetMapping(path="/resturant/{resturantId}")
    public ResponseEntity<Resturant> getResturantById(@PathVariable int resturantId)
    {
        return new ResponseEntity<Resturant>(resturantService.getResturantById(resturantId), HttpStatus.OK);
    }

    @PutMapping(path = "/resturant")
    public ResponseEntity<Resturant> updateResturant(@RequestBody Resturant resturant)
    {
        return new ResponseEntity<Resturant>(resturantService.updateResturant(resturant), HttpStatus.OK);
    }

    @PatchMapping(path = "/admin/resturant/block/{resturantId}")
    public ResponseEntity<Boolean> blockResturant(@PathVariable int resturantId)
    {
        return new ResponseEntity<Boolean>(resturantService.blockResturant(resturantId), HttpStatus.OK);
    }

    @PatchMapping(path = "/admin/resturant/unblock/{resturantId}")
    public ResponseEntity<Boolean> unblockResturant(@PathVariable int resturantId)
    {
        return new ResponseEntity<Boolean>(resturantService.unblockResturant(resturantId), HttpStatus.OK);
    }

    @PatchMapping(path = "resturant/updateBankDetails")
    public ResponseEntity<Boolean> updateBankDetails(@RequestParam int resturantId, @RequestParam MultipartFile file)
    {
        return new ResponseEntity<Boolean>(resturantService.updateBankDetailsOfResturant(resturantId, file), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "resturant/getBankDetails/{resturantId}")
    public ResponseEntity<InputStreamResource> getBankDetails(@PathVariable int resturantId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bankDetails.jpg");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.IMAGE_JPEG).body(resturantService.getBankDetailsOfResturant(resturantId));
    }
    
    @PatchMapping(path = "/resturant/updatePassword")
    public ResponseEntity<Boolean> updateResturantPassword(@RequestParam int resturantId, @RequestParam String password)
    {
        return new ResponseEntity<Boolean>(resturantService.updatePassword(resturantId, password), HttpStatus.OK);
    }

    @PatchMapping(path = "/resturant/updateMail")
    public ResponseEntity<Boolean> updateUserMail(@RequestParam int resturantId, @RequestParam String mail)
    {
        return new ResponseEntity<Boolean>(resturantService.updateMail(resturantId, mail), HttpStatus.OK);
    }
}
