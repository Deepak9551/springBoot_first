package com.boot.firstboot.Repository;

import com.boot.firstboot.entity.MyData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface Repository extends MongoRepository<MyData, ObjectId> {
MyData findById(String myid);
}
