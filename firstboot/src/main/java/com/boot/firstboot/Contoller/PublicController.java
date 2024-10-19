package com.boot.firstboot.Contoller;

import com.boot.firstboot.Service.UserService;
import com.boot.firstboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
public UserService userService;


    @GetMapping
    public List<User> getAllUser(){
        return userService.getuserAll();

    }

    @GetMapping("/heath-check")
    public String healthCheck(){return  "ok";}

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user ){
        userService.saveEntry(user);
    }
}
