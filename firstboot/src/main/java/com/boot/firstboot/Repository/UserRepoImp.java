package com.boot.firstboot.Repository;

import com.boot.firstboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserRepoImp {

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User>getUserForSa(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("Deepak"));
//        query.addCriteria(Criteria.where("age").gt("20"));


//        query.addCriteria(Criteria.where("userName").nin("golu","papa"));

//        query.addCriteria(Criteria.where("Roles").in("User","admin"));


        // Add email regex criteria (corrected dot escaping)
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));

        // Add criteria for sentimentAnalysis being of boolean type
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        // Fetch and return the list of users matching the criteria
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }
    }

