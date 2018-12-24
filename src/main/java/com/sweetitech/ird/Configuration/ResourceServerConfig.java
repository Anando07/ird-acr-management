package com.sweetitech.ird.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:38 PM
 * @project InternalResourcesDivision
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll();
//                .antMatchers("/users/admin/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/**").authenticated();

    }
}