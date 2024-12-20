package com.tigo.ahp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AhpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhpApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") 
                .allowedOrigins("http://localhost:3000") // Replace with your frontend's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") 
                .allowedHeaders("Authorization", "Content-Type"); 
			}
		};
	}

}
