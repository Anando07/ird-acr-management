package com.sweetitech.ird.SecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Avijit Barua
 * @created_on 1/1/19 at 3:53 PM
 * @project ird
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/app-assets/**").permitAll()
                .antMatchers("/invalidSession*").anonymous().
                anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin/acrlist",true)
                .failureUrl("/login?error=true")
                .permitAll()

                .and()
                .sessionManagement()
                .invalidSessionUrl("/login").and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login") ///logout.html?logSucc=true
                .deleteCookies("JSESSIONID")
                .permitAll()


        ;

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http
                .csrf().disable()

                .authorizeRequests()

                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_OPERATOR")

                .antMatchers("/","/login").permitAll()

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
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }


}
