package com.boot.firstboot.Service;

import com.boot.firstboot.entity.MyData;
import com.boot.firstboot.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveuserdata(User myuser);
    List<User> getuserAll();
    Optional<User> getuserdata(ObjectId id);
    void deleteuser(ObjectId  id);
    User findByUserName(String userName);
    void  saveEntry(User username);
    void deleteByUserName(String userName);
    void saveUser(User user);

    void saveAdmin(User user);
}
