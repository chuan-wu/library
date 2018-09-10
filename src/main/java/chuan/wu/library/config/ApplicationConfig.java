/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.config;

import chuan.wu.library.filter.ThrottleFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class ApplicationConfig {

    @Bean
    public Filter throttleFilter() {
        return new ThrottleFilter();
    }
}
