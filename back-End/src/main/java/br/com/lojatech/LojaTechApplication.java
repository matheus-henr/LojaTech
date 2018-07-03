package br.com.lojatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.lojatech.resources.exception"})
public class LojaTechApplication{
	

	public static void main(String[] args) {
		SpringApplication.run(LojaTechApplication.class, args);
	}


}
