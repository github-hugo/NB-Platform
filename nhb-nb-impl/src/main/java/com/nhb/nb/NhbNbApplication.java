package com.nhb.nb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrix
@ComponentScan("com.nhb")
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
public class NhbNbApplication {

	public static void main(String[] args) {
		// 启动服务
		SpringApplication.run(NhbNbApplication.class, args);
	}

}
