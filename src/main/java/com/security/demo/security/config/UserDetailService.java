package com.security.demo.security.config;

import com.security.demo.entity.User;
import com.security.demo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author bypt-dev-laptop-9
 */
@Service
@Slf4j
public class UserDetailService implements UserDetailsService  {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new ApplicationUserDetail(user);
        } else {
            throw new RuntimeException("User Not Found");
        }
    }
    
}
