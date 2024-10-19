package com.boot.firstboot.Contoller;

import com.boot.firstboot.Service.Service;
import com.boot.firstboot.Service.UserService;
import com.boot.firstboot.entity.MyData;
import com.boot.firstboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {
    @Autowired
    private Service service;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> GetAlluser() {
        System.out.println("here");
        String message = "Users not exists";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =   authentication.getName();
    User user = userService.findByUserName(username);
     List<MyData> myDataList = user.getMyDataList();

        if (myDataList != null && !myDataList.isEmpty()) {
            log.info("empty");
            return new ResponseEntity<>(myDataList,HttpStatus.OK);
        }
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> mypost(@RequestBody MyData myData ) {
    String message = "Record added Successfully";
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =   authentication.getName();

//        myData.setDate(LocalDateTime.now());
        service.saveuserdata(myData,username);
        return new ResponseEntity<>(myData,HttpStatus.CREATED);
    }catch (Exception e){
return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<?> CheckId(@PathVariable ObjectId myid){
//        Optional<MyData> getuserdata = service.getuserdata(myid);
//        return getuserdata.map(myData -> new ResponseEntity<>(myData, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    String message = " User not Found";
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username =   authentication.getName();
    User user  = userService.findByUserName(username);
    List<MyData> collect = user.getMyDataList().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
    if(!collect.isEmpty()){
        Optional<MyData> data = service.findById(myid);
        return new ResponseEntity<>(data.get(),HttpStatus.OK);
    }
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId myid ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =   authentication.getName();
        String message = "Record deleted Successfully";
       boolean deleteuser = service.deleteuser(myid, username);
        if (deleteuser ) {
            return new ResponseEntity<>(deleteuser,HttpStatus.NO_CONTENT);
        }
      return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?>  updateData(@PathVariable ObjectId myid, @RequestBody MyData newuser ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =   authentication.getName();
        User user  = userService.findByUserName(username);
        List<MyData> collect = user.getMyDataList().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        String message = "Record Updated Successfully";
        if(!collect.isEmpty()){
            Optional<MyData> data = service.findById(myid);

            if (data.isPresent() ) {

            MyData old = data.get();

                old.setTitle(newuser.getTitle() != null && !newuser.getTitle().equals("") ? newuser.getTitle() : old.getTitle());
                old.setContent(newuser.getContent() != null && !newuser.getContent().equals("") ? newuser.getContent() : old.getContent());
                service.saveuserdata(old);

                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}

