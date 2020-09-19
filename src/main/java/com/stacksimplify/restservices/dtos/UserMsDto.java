package com.stacksimplify.restservices.dtos;

public class UserMsDto {

	private Long id;
	private String username;
	private String emailaddress;
	
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	// constructor using fields
	public UserMsDto(Long id, String username, String emailaddress) {
		super();
		this.id = id;
		this.username = username;
		this.emailaddress = emailaddress;
	}
	
	// constructor without argument
	public UserMsDto() {
		
	}
	
}
