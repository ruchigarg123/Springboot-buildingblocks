package com.stacksimplify.restservices.Hello;

import org.springframework.stereotype.Component;


public class UserDetails {
	
	private String firstName;
	private String LastName;
	private String City;
	

	public UserDetails(String firstName, String lastName, String city) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		City = city;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}

	@Override
	public String toString() {
		return "UserDetails [firstName=" + firstName + ", LastName=" + LastName + ", City=" + City + "]";
	}	

}
