package com.stacksimplify.restservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
//import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class SpringbootBuildingBlocksApplication {
//
	//--
	// Love yourself and enjoy what you do!! even if u dont ,...contact me hahaha..
	public static void main(String[] args) {
	 SpringApplication.run(SpringbootBuildingBlocksApplication.class, args);
		
		
	}
	
	@Bean
  public LocaleResolver localeResolver()
  {
	  AcceptHeaderLocaleResolver localeResolver = new  AcceptHeaderLocaleResolver();
	  localeResolver.setDefaultLocale(Locale.US);
	  return localeResolver;
  }

	@Bean
	public ResourceBundleMessageSource  messageSource()
	{
		 ResourceBundleMessageSource  messageSource = new  ResourceBundleMessageSource();
		 messageSource.setBasename("messages");
		 return messageSource;
	}
}
