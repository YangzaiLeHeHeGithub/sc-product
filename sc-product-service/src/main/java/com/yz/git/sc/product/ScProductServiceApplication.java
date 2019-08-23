package com.yz.git.sc.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yz.git.sc.product.dao")
@ComponentScan(basePackages = {"com.yz.git.sc.product","com.yz.git.sc.product.*"})
public class ScProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScProductServiceApplication.class, args);
	}

}
