package com.springboot.jdbc.databasedemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//When we use in memory DB, spring JPA is automatically create schema for the @Entity class. 
//So no need of Data.sql file to create table. If we not removed then getting below error :
//Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "PERSON" already exists; SQL statement:
//create table person ( id integer not null, name varchar(255) not null, location varchar(255), birth_date timestamp, primary key(id) ) [42101-200]
@Entity
@Table(name="person")
//Create Named Query to execute based on entity not the table. Use JPQA - Java Persistance Query Language. It use entity not table
@NamedQuery(name="find_all_person", query="select p from Person p")
public class Person {
	@Id
	@GeneratedValue //Using sequencing for creating ID - Hibernate: create sequence hibernate_sequence start with 1 increment by 1
	private int id;
	private String name;
	private String location;
	private Date birthDate;
	
	//By default it should come but we use parameter constructor so no default constructor created by java and we are getting 
	//error : "Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.springboot.jdbc.databasedemo.entity.Person]: No default constructor found; 
	//nested exception is java.lang.NoSuchMethodException: com.springboot.jdbc.databasedemo.entity.Person.<init>()"
	public Person() {
		super();
	}
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	//For JPA/Hibernate need to create constructor without ID as its taken care by JPA
	public Person( String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	

}
