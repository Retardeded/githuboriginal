package com.pl.githuboriginal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GithubOriginalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubOriginalApplication.class, args);
	}

}
