package com.loginApp.spring_security_6.service;

import com.loginApp.spring_security_6.CustomUserDetails;
import com.loginApp.spring_security_6.entity.User;
import com.loginApp.spring_security_6.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(Objects.isNull(user)){
            System.out.println("User does not exist");
            throw new UsernameNotFoundException("User Not found");
        }
        return new CustomUserDetails(user);

    }
}
