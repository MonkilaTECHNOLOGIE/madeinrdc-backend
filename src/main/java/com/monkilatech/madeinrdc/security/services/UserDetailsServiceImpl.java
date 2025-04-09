package com.monkilatech.madeinrdc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monkilatech.madeinrdc.models.User;
import com.monkilatech.madeinrdc.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email:" + email));

        return UserDetailsImpl.build(user);
    }

    // public UserDetails loadUserByPhone(String phone) throws
    // UsernameNotFoundException {
    // User user = userRepository.findByPhone(phone)
    // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone:
    // " + phone));

    // return UserDetailsImpl.build(user);
    // }

}
