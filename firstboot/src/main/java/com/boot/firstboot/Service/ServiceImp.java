package com.boot.firstboot.Service;

import com.boot.firstboot.Repository.Repository;
import com.boot.firstboot.entity.MyData;
import com.boot.firstboot.entity.User;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImp implements Service{
    @Autowired
    private Repository repository ;

    @Autowired
    private UserService userService;



    @Override
    @Transactional
    public void saveuserdata(MyData myData , String username){
        try {
        User user =  userService.findByUserName(username);
        myData.setDate(LocalDateTime.now());
        System.out.println(myData);
        MyData savedata =   repository.save(myData);
        System.out.println(savedata);
        user.getMyDataList().add(savedata);
        userService.saveUser(user);
        System.out.println("data saved");

            }catch (Exception e){
        System.out.println("error in save data");
    }

    }
    public void saveuserdata(MyData myData ){

    repository.save(myData);

    }
    @Override
    public List<MyData> getuserAll(){

        return repository.findAll();
    }

    @Override
    public Optional<MyData> getuserdata(ObjectId id) {

        return repository.findById(id);
    }

    @Override
    @Transactional
    public Boolean deleteuser(ObjectId id, String username) {
        boolean removed =  false;
   try {
       User user =  userService.findByUserName(username);
        removed = user.getMyDataList().removeIf(x -> x.getId().equals(id));
       if (removed ) {
           userService.saveUser(user);
           repository.deleteById(id);

       }
       return removed;
   }catch (Exception e){
       System.out.println("error in delete");
   }

        return null;
    }

    @Override
    public Optional<MyData> findById(ObjectId myid) {
        return repository.findById(myid);
    }


}
