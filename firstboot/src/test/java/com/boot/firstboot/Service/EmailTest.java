package com.boot.firstboot.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class EmailTest {
    @Autowired
    private EmailService emailService;
    @Test
    @Disabled
public void email(){
emailService.sendEmail("suresh9936872548@gmail.com","Good morning","hi me........................................");
}
}
