package com.yz.git.sc.fund.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScFundProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScFundProductServiceApplication.class, args);
	}

}
