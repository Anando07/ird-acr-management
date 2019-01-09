package com.sweetitech.ird.configuration;

import com.sweetitech.ird.common.Util.PasswordUtil;
import com.sweetitech.ird.serviceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
*
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:40 PM
 * @project InternalResourcesDivision
*/

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        passwordEncoder = PasswordUtil.getBCryptPasswordEncoder();
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        System.out.println("at the first stage ");
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .authenticationProvider(authenticationProvider())
                .jdbcAuthentication();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("inside configure with resource ");

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**","/upload/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_OPERATOR")
                .antMatchers("/","/login","/logout","/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/admin/acrlist",true)
                .permitAll();

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }

}
