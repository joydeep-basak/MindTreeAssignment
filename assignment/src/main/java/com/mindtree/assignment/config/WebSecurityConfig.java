package com.mindtree.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity(debug = true) // when you want to see what filters are applied
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
	  http.cors().and()
	  	.csrf().disable().authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/images/**", "/static/**", "/**/favicon.ico").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers("/api/product/v1/*").permitAll()
        .antMatchers("/api/user/v1/*").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated();
  }
  
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
  }
}