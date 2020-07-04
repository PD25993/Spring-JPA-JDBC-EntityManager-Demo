package com.springboot.jdbc.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.jdbc.databasedemo.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Create custom result set mapper
	
	class PersonRowMapper implements RowMapper<Person>{
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}

	public List<Person> findAll(){
		//BeanPropertyRowMapper - Result set mapping with Person class row
		//return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	public Person findByID(int idFromMain){
		//BeanPropertyRowMapper - Result set mapping with Person class row
		try {
			/*
			 * return jdbcTemplate.queryForObject("select * from person where id=?", new
			 * Object[] { idFromMain }, new BeanPropertyRowMapper<Person>(Person.class));
			 */
			
			return jdbcTemplate.queryForObject("select * from person where id=?",
					new Object[] { idFromMain }, new PersonRowMapper());
		}
		catch(EmptyResultDataAccessException e){
			//System.out.println("Exception : " + e);
			return null;
		}
	}

	public List<Person> insertPerson(Person person){
		jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
				new Object[] { person.getId(),person.getName(),person.getLocation(),person.getBirthDate()});
		return findAll();
	}

	public Person updatePerson(Person person){
		//BeanPropertyRowMapper - Result set mapping with Person class row
		try {
			if(jdbcTemplate.update("update person " + " set name = ?, location = ?, birth_date = ? " + " where id = ?",
					new Object[] {person.getName(),person.getLocation(),person.getBirthDate(), person.getId()}) == 0 )
			{
				return null;
			}else
			{
				return findByID(person.getId());
			}
		}catch(EmptyResultDataAccessException e) {
			System.out.println("Exception : " + e);
			return null;
		}
	}

	public int deleteByID(int deletedIdFromMain){
		Person personDetailsByID= findByID(deletedIdFromMain);
		if(null == personDetailsByID)
		{
			return 0;
		}else{
			return jdbcTemplate.update("delete from person where id=?",
					new Object[] { deletedIdFromMain });
		}
	}

}
