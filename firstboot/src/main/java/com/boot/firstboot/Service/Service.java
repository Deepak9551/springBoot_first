package com.boot.firstboot.Service;

import com.boot.firstboot.entity.MyData;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface Service {
    void saveuserdata(MyData myData, String user);
    void saveuserdata(MyData myData);
    List<MyData> getuserAll();
    Optional<MyData> getuserdata(ObjectId id);
    Boolean deleteuser(ObjectId  id, String username);
    Optional<MyData> findById(ObjectId myid);

}
