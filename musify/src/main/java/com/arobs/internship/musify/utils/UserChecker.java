package com.arobs.internship.musify.utils;

import com.arobs.internship.musify.model.Playlist;
import com.arobs.internship.musify.model.User;
import com.arobs.internship.musify.security.JwtUtils;

public class UserChecker {

    public static boolean isCurrentUserNotAdmin() {
        return !JwtUtils.getCurrentUserRole().equals("admin");
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

    public static boolean isCurrentUserNotOwnerOfPlaylist(Playlist playlist) {
        Integer userId = JwtUtils.getCurrentUserId();

        return (playlist.getOwnerUserId().intValue() != userId.intValue());
    }
}
