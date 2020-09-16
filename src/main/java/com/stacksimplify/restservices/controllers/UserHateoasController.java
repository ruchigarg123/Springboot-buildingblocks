package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilderFactory;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException
	{
		List<User> allusers = userService.getAllUsers();
		for(User user : allusers)
		{
			//selfLink
			Long userid = user.getId();
			
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			
			//relationship link with getAllorders
			
			CollectionModel<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
			Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
		    user.add(ordersLink);
		}
		
		Link selflinkgetAllusers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResource = new CollectionModel(allusers,selflinkgetAllusers);
		return finalResource;
		
		
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
		Optional<User> userOptional = userService.getUserById(id);
		User user = userOptional.get();
		Long userid = user.getId();
		Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
		user.add(selfLink);
		EntityModel<User> finalResource = new EntityModel<User>(user);
		return finalResource;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage() );
			
		}
		
	}
	
	
}
