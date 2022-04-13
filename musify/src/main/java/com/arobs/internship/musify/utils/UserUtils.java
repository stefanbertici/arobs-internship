package com.arobs.internship.musify.utils;

import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.security.JwtUtils;

public class UserUtils {

    public static boolean isCurrentAdmin() {
        return JwtUtils.getCurrentUserRole().equals("admin");
    }

    public static boolean isAdmin(User user) {
        return user.getRole().equals("admin");
    }

    public static boolean isActive(User user) {
        return user.getStatus().equals("active");
    }

    public static boolean canLogin(User user, String encryptedPassword) {
        return user.getEncryptedPassword().equals(encryptedPassword);
    }

    public static boolean isOperationOnSelf(Integer id) {
        return (JwtUtils.getCurrentUserId().intValue() == id.intValue());
    }
}
