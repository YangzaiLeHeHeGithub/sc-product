package com.yz.git.sc.fund.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.yz.git.sc.fund.product","com.yz.git.sc.fund.product.*"})
public class ScFundProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScFundProductServiceApplication.class, args);
	}

}
