package com.boot.firstboot.Service;

import com.boot.firstboot.Config.UserSecurity;
import com.boot.firstboot.Repository.UserRepository;
import com.boot.firstboot.entity.User;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@Disabled
public class FetchUser {
    @Mock
    private UserRepository userRepository;

 @InjectMocks
    private UserSecurity userDetailsService;
 @BeforeEach
 void setup(){
     MockitoAnnotations.initMocks(this);
 }
    @Test
    public void TestUser(){
when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("Ram-password").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("Deepak");
        Assertions.assertNotNull(userDetails);
    }
}
