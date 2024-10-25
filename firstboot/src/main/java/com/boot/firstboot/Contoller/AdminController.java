package com.boot.firstboot.Contoller;

import com.boot.firstboot.Service.AppCache;
import com.boot.firstboot.Service.UserService;
import com.boot.firstboot.Service.UserServiceImp;
import com.boot.firstboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService ;
    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllusers(){
        String message = "Users not found ";
        List<User> users = userService.getuserAll();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users , HttpStatus.OK);
        }
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        String message = "New Admin is Created";
    userService.saveAdmin(user);
    return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @GetMapping("/clearcache")
    public void appclearcache(){
        appCache.init();
    }
}
