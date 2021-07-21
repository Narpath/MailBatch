package com.narpath.batchmail;

public class Employee {
	
	private int id;
	
	private String name;
	
	private String designation;
	
	private String city;
	
	private String company;
	
	private String email;

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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee id=" + id + ",\n name=" + name + ",\n designation=" + designation + ",\n city=" + city
				+ ",\n company=" + company + ",\n email=" + email + "]";
	}
	

}
