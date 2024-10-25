package com.boot.firstboot.Service;

import com.boot.firstboot.Schedular.UserSchedular;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SentimentTest {
    @Autowired
  private   UserSchedular userSchedular ;

    @Disabled
    @Test
    public void Sentiment(){
        userSchedular.fetchUsersAndSendMain();
    }
}
