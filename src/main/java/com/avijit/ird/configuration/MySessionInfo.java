package com.avijit.ird.configuration;

import com.avijit.ird.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionInfo {

    private User user;

    public User getCurrentUser() {
        user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}