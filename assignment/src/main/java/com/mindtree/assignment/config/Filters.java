package com.mindtree.assignment.config;
import java.util.Collections;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindtree.assignment.filter.RestFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Filters {

//  @Bean
//  public FilterRegistrationBean loginRegistrationBean() {
//    log.info("Setting up loginRegistrationBean");
//    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//    filterRegistrationBean.setFilter(new LoginFilter());
//    filterRegistrationBean.setUrlPatterns(Collections.singletonList("/login/*"));
//    return filterRegistrationBean;
//  }

  @Bean
  public FilterRegistrationBean<RestFilter> restRegistrationBean() {
    log.info("Setting up restRegistrationBean");
    FilterRegistrationBean<RestFilter> filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new RestFilter());
    filterRegistrationBean.setUrlPatterns(Collections.singletonList("/api/product/v1/*"));
    return filterRegistrationBean;
  }
}