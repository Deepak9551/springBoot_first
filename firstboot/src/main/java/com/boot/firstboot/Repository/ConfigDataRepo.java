package com.boot.firstboot.Repository;


import com.boot.firstboot.entity.ConfigData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigDataRepo extends MongoRepository<ConfigData, ObjectId> {
}
