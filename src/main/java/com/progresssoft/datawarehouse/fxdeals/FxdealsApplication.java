package com.progresssoft.datawarehouse.fxdeals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class FxdealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxdealsApplication.class, args);
	}

}
