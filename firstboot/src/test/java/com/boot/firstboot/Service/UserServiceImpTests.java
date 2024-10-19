package com.boot.firstboot.Service;

import com.boot.firstboot.Repository.UserRepository;
import com.boot.firstboot.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
@ActiveProfiles("dev")
public class UserServiceImpTests {

@Autowired

private UserRepository userRepository;
    @ParameterizedTest
    @ValueSource (strings = {
            "Deepak",
            "new user",
            "hero"
    })
    public void testAdd(String name){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("Deepak") , "Failed for "+name);
//        assertTrue(2<5);
    }

    @Disabled

    @ParameterizedTest
@ArgumentsSource(UserArgumentsProvider.class)
    public void testUser(User user){

        assertNotNull(userRepository.save(user) );
//        assertTrue(2<5);
    }

    @Test
    @Disabled
    public  void CheckUserData(){
     User user =   userRepository.findByUserName("Deepak");
    assertTrue(!user.getMyDataList().isEmpty());
    }


    @ParameterizedTest
    @Disabled
    @CsvSource({
            "10,5,5",
            "20,10,5",
            "5,3,2"
    })
    public void addNumber(int expected , int a , int b){
        assertEquals(expected,a+b ,"This is number is fail"+a +" "+b);
    }
}
