package com.boot.firstboot.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
@Autowired
    private RedisTemplate redisTemplate;


@Test
public void testsendMail(){
  redisTemplate.opsForValue().set("email","12pcm.deepak@gmail.com");
String email = (String)   redisTemplate.opsForValue().get("email");
    System.out.println(email);
}

}
