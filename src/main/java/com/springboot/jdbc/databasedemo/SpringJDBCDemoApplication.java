package com.springboot.jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.jdbc.databasedemo.entity.Person;
import com.springboot.jdbc.databasedemo.jdbc.PersonJdbcDao;

//CommandLineRunner - Spring boot’s CommandLineRunner interface is used to run a code block only 
//once in application’s lifetime – after application is initialized or application context is initialized. 

//@SpringBootApplication
public class SpringJDBCDemoApplication implements CommandLineRunner{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
	PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJDBCDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("User Details for ID 10001 from H2 DB -> {}" , personJdbcDao.findByID(10001));
		logger.info("Deleting 10002 -> No of Rows Deleted -> {}" , personJdbcDao.deleteByID(10002));
		logger.info("Insert new person and show all person in Person table-> {}" , 
				personJdbcDao.insertPerson(new Person(10004, "Tara", "Berlin", new Date())));
		logger.info("Update 10003 -> {}", 
				personJdbcDao.updatePerson(new Person(10003, "Pieter", "Utrecht", new Date())));
		logger.info("All user from H2 DB -> {}" , personJdbcDao.findAll());
	}

}
