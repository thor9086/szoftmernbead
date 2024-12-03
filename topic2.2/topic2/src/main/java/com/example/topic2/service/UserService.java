package com.example.topic2.service;

import com.example.topic2.model.User;
import com.example.topic2.repository.UserRepository;
import com.example.topic2.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

//    public Long getCurrentUserId() {
//        // Lekérjük az aktuális bejelentkezett felhasználót
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Ha van bejelentkezett felhasználó, lekérjük az id-t
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String username = userDetails.getUsername();
//
//            // Lekérjük a felhasználó azonosítóját a felhasználói nevéből, például a UserRepository használatával
//            // Feltételezzük, hogy a User osztály tartalmaz egy 'username' és 'id' mezőt
//            return userRepository.findByUsername(username).getId();
//        }
//
//        return null; // Ha nincs bejelentkezve felhasználó, visszatérhetünk null-al
//    }


    public Long getCurrentUserId() {
        // Az aktuális felhasználó nevét lekérjük
        String username = UserUtils.getCurrentUserByName();
        if (username != null) {
            // Kikeressük az adatbázisból a felhasználót a felhasználóneve alapján
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                return user.get().getId(); // Visszatérünk az ID-val
            }
        }
        return null; // Ha nincs bejelentkezve, vagy a felhasználó nem található
    }

    public static String getCurrentUserByName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Ez visszaadja a felhasználó email címét (vagy felhasználónevét)
        }
        return null; // Ha nincs bejelentkezve
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}

