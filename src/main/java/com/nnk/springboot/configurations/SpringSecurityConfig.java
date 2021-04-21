package com.nnk.springboot.configurations;

import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/user/*").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and().formLogin().permitAll()
        .defaultSuccessUrl("/bidList/list")
        .and().logout().permitAll()
        .logoutUrl("/app-logout")
        .logoutSuccessUrl("/login")
        .and().exceptionHandling()
        .accessDeniedPage("/app/error");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }
}
