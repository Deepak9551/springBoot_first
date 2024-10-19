//package com.boot.firstboot.Contoller;
//
//import com.boot.firstboot.Service.Service;
//import com.boot.firstboot.entity.MyData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class OldController {
//
//
//        @Autowired
//        private Service service ;
//
//        private HashMap<String , MyData> map = new HashMap<>();
//
//        @GetMapping
//        public ArrayList<MyData> sayHello(){
//
//            return new ArrayList<>(map.values());
//        }
//        @PostMapping
//        public String mypost(@RequestBody MyData user){
//            map.put(user.getId(), user);
//            return "Data added";
//        }
//
//        @GetMapping("/id/{myid}")
//        public MyData CheckId(@PathVariable int myid){
//            System.out.println(myid);
//            return     map.get(myid);
//
//        }
//
//        @DeleteMapping("/id/{myid}")
//        public String deleteUser(@PathVariable int myid){
//            map.remove(myid);
//            return "Delete user";
//        }
//        @PutMapping("/id/{myid}")
//        public String updateData(@RequestBody MyData user){
//            map.put(user.getId(),user);
//            return "Data update";
//        }
//    }
//
//
