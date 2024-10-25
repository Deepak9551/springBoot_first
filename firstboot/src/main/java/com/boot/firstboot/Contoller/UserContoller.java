package com.boot.firstboot.Contoller;

import com.boot.firstboot.Service.UserService;
import com.boot.firstboot.Service.WeatherService;
import com.boot.firstboot.entity.User;
import com.boot.firstboot.entity.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserContoller {
    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService ;
//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getuserAll();
//
//    }
//    @PostMapping
//    public void createUser(@RequestBody User user ){
//        userService.saveEntry(user);
//    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username =   authentication.getName();
        User userinDb = userService.findByUserName(username);
        if (userinDb != null){
            userinDb.setUserName(user.getUserName());
            userinDb.setPassword(user.getPassword());
            userService.saveEntry(userinDb);
        }
        return new ResponseEntity<>(userinDb, HttpStatus.NO_CONTENT);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  String name =  authentication.getName();
userService.deleteByUserName(name);
return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherEntity weather = weatherService.getWeather("Mumbai");
        String myweather  = "";
        if(weather!=null){

            myweather ="Weather feel like"+weather.getCurrent().getFeelslike();
            return new ResponseEntity<>("Hi " + authentication.getName() +" Weather feel like "+ myweather,HttpStatus.OK);
        }
return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
