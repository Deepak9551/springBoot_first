package com.boot.firstboot.Service;

import com.boot.firstboot.Repository.UserRepoImp;
import com.boot.firstboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Repo {
    @Autowired
    private UserRepoImp userRepoImp;
    @Test
    public void testSaveUser(){
        List<User> userForSa = userRepoImp.getUserForSa();
        System.out.println(userForSa);
    }
}
