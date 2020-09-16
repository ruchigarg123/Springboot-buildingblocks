package com.stacksimplify.restservices.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//class
@RestController
public class HelloWorldController {

	
	//simple method 
	//URL-/helloworld
	//method-GET
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	
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
	
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale)
	{
		return messageSource.getMessage("label.Hello",null , new java.util.Locale(locale));
	}
	
	//Alternative way of writing above method
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2()
	{
		return messageSource.getMessage("label.Hello",null , LocaleContextHolder.getLocale());
	}
}
