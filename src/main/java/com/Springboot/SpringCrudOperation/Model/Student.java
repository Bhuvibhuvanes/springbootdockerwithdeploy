package com.Springboot.SpringCrudOperation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private  String name;
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Student() {
		super();
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
	public String toString() {
		return "Student [id="+id+",name="+name+"]";
	}

}
