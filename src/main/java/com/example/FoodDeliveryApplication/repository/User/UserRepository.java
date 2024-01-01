package com.example.FoodDeliveryApplication.repository.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.FoodDeliveryApplication.entities.User.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User getUserByMail(String mail);

    @Query("Select u from User u where u.isAdmin = true")
    public List<User> getAllAdmins();
}
