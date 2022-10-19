package com.unq.desa.criptoP2P;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableFeignClients
public class CriptoP2PApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptoP2PApplication.class, args);
	}

}
