/*
package com.sweetitech.ird.configuration;

import com.sweetitech.ird.serviceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

*/
/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:38 PM
 * @project InternalResourcesDivision
 *//*





@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().logout()
                .logoutSuccessUrl("/").permitAll();
    }
}
*/
