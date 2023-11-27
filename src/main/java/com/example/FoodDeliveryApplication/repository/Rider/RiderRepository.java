package com.example.FoodDeliveryApplication.repository.Rider;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Rider.Rider;

@Repository
public interface RiderRepository extends CrudRepository<Rider, Integer> {
}
