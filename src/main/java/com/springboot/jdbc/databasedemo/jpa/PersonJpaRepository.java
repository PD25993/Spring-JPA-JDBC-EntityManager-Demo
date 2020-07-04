package com.springboot.jdbc.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.springboot.jdbc.databasedemo.entity.Person;

//Repository and Transaction management for insert/del/update
@Repository
@Transactional
public class PersonJpaRepository {

	//connect to the DB. EntityManager is the interface of PersistenceContext. All the operation performed in the session
	//is maintained by EntityManager. 
	@PersistenceContext
	EntityManager entityManager;

	public Person findByID(int idFromMain){
		return entityManager.find(Person.class, idFromMain);
	}

	public List<Person> findAll(){ 
		TypedQuery<Person> namedQyery = entityManager.createNamedQuery("find_all_person",Person.class);
		return namedQyery.getResultList();
	}


	public Person insertPerson(Person person){
		return entityManager.merge(person);
	}

	public Person updatePerson(Person person){
		if(null == findByID(person.getId()))
		{
			return null;
		}
		return entityManager.merge(person);
	}

	public void deletePersonById(int deletedIdFromMain){
		Person person = findByID(deletedIdFromMain);
		entityManager.remove(person);
	}
}
