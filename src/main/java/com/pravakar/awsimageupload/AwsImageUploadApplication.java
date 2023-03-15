package com.pravakar.awsimageupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class AwsImageUploadApplication {

	public static void main(String[] args) {

		SpringApplication.run(AwsImageUploadApplication.class, args);
	}


}
