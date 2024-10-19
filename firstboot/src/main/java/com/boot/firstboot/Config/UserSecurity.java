package com.boot.firstboot.Config;

import com.boot.firstboot.Repository.UserRepository;

import com.boot.firstboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity implements UserDetailsService {
    @Lazy
   @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(username);
       if(user!=null){
    UserDetails userDetails =  org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
      return userDetails;
       }

       throw new UsernameNotFoundException("user not found exception "+username);
    }
}
