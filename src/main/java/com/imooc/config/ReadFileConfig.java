package com.imooc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 用类的方法读取配置文件中的信息
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class ReadFileConfig {
    private String host;
    public void setHost(String host) {
        this.host = host;
    }
    public String getHost() {
        return host;
    }

    private String port;
    public void setPort(String port) {
        this.port = port;
    }
    public String getPort() {
        return port;
    }

    private String username;
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    private String password;
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
