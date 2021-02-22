package com.fenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: xiaguangyong
 * @ClassName: DiscoveryApplication
 * @Description:
 * @Date: 2021/2/22 19:45
 * @Version: 1.0
 * @Modify:
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }
}
