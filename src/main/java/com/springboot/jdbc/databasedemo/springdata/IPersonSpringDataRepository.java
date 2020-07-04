package com.springboot.jdbc.databasedemo.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jdbc.databasedemo.entity.Person;

//Instead of creating JPA repository EntityManager, use default JpaRepository methods
public interface IPersonSpringDataRepository extends JpaRepository<Person, Integer>{

}
