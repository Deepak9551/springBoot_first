package com.boot.firstboot.entity;

import com.mongodb.lang.NonNull;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
@Builder
public class User {
    @Id

    private ObjectId id;
    @Indexed( unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;


    private List<MyData> myDataList = new ArrayList<>();
    private List<String> roles ;
}
