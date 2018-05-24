package com.campusnum.reseausocialsb;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mysql.fabric.xmlrpc.base.Array;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ReseausocialSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReseausocialSbApplication.class, args);
	}
	
}
