package com.example.topic2.service;

import com.example.topic2.model.User;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public static String getCurrentUser() {
        String email = UserUtils.getCurrentUserEmail();
        if (email != null) {
            return email;
        } else {
            return "No user is logged in.";
        }
    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(email);
        }
    }
}

