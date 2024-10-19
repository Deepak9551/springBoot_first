package com.boot.firstboot.Service;

import com.boot.firstboot.Repository.Repository;
import com.boot.firstboot.Repository.UserRepository;
import com.boot.firstboot.entity.MyData;
import com.boot.firstboot.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userrepository ;

    private static final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
    private static  final Logger  looger = LoggerFactory.getLogger(UserServiceImp.class);

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userrepository.save(user);
        looger.info("entry save hogi");
    }
    public void saveUser(User user){
    userrepository.save(user);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User ","Admin"));
        userrepository.save(user);
    }

    @Override
    public void deleteByUserName(String userName) {
        userrepository.deleteByUserName(userName);
    }

    @Override
    public void saveuserdata(User myuser){
        userrepository.save(myuser);
    }
    @Override
    public List<User> getuserAll(){

        return userrepository.findAll();
    }

    @Override
    public Optional<User> getuserdata(ObjectId id) {

        return userrepository.findById(id);
    }

    @Override
    public void deleteuser(ObjectId id) {
        userrepository.deleteById(id);
    }

public  User findByUserName(String userName){
        return userrepository.findByUserName(userName);
}

}
