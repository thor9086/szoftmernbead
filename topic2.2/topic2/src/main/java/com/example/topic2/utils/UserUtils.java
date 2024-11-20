package com.example.topic2.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Ez visszaadja a felhasználó email címét (vagy felhasználónevét)
        }
        return null; // Ha nincs bejelentkezve
    }
}

