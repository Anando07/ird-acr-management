package com.sweetitech.ird.configuration;

import com.sweetitech.ird.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionInfo {

    private User user;

    public User getCurrentUser() {

        /*if (user == null) {

        }*/
        user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}