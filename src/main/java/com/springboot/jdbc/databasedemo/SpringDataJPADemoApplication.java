package com.springboot.jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.jdbc.databasedemo.entity.Person;
import com.springboot.jdbc.databasedemo.springdata.IPersonSpringDataRepository;

//CommandLineRunner - Spring boot’s CommandLineRunner interface is used to run a code block only 
//once in application’s lifetime – after application is initialized or application context is initialized. 

@SpringBootApplication
public class SpringDataJPADemoApplication implements CommandLineRunner{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IPersonSpringDataRepository personSpringDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJPADemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("SpringDataJPARepository : User Details for ID 10001 from H2 DB -> {}" , personSpringDataRepository.findById(10001));
		logger.info("SpringDataJPARepository : Insert new person  -> {}" ,
				personSpringDataRepository.save(new Person("Tara", "Berlin", new Date())));
		logger.info("SpringDataJPARepository : Update 10003 -> {}", personSpringDataRepository.save(new
				Person(10003, "Pieter", "Utrecht", new Date())));
		personSpringDataRepository.deleteById(10002);
		logger.info("SpringDataJPARepository : All user from H2 DB -> {}" , personSpringDataRepository.findAll());
	}

}
