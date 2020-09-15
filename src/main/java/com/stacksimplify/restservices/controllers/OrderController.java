package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
//import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")

public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	//get all orders for a user given with userid
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException
	{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
		throw new UserNotFoundException("User Not found");
		
		return userOptional.get().getOrders();
	}
	
	
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException
	{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
		throw new UserNotFoundException("User Not found");
		
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	@GetMapping("{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid,@PathVariable Long orderid) throws UserNotFoundException, OrderNotFoundException
	{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException("User Not found");
		}
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		if(!optionalOrder.isPresent())
			throw new OrderNotFoundException("Order doesnot exist");
		
		    return optionalOrder.get();
		
	}	

		
	}

