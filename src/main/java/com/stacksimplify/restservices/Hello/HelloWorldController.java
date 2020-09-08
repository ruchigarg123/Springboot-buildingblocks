package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.UserDetails;

//class
@RestController
public class HelloWorldController {

	
	//simple method 
	//URL-/helloworld
	//method-GET
	
	//@RequestMapping(method=RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld()
	{
		return "hello World..";
	}
	
	
	@GetMapping("/helloWorld-bean")
	public UserDetails helloWorldBean()
	{
		return new UserDetails("Kalyan","Reddy","Hyderabad");
	}
}
