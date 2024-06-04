package com.capstone.realmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
@EnableFeignClients
public class RealmenApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealmenApplication.class, args);
	}

}
