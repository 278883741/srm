package com.srm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public MyShiroRealm myShiroRealm() {
        return null;
    }
}
