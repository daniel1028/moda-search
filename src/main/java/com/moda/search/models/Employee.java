package com.moda.search.models;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * @author ajay
	 */
	private static final long serialVersionUID = 6379967963885764628L;

	private String id;
	
	private String name;
	
	private String role;
	
	private float salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	

}
