package com.example.ibes.demo;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class DemoApplication {

  	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
