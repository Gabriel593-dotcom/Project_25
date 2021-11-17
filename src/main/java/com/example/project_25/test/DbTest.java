package com.example.project_25.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.project_25.entities.Order;
import com.example.project_25.entities.User;
import com.example.project_25.entities.enums.OrderStatus;
import com.example.project_25.repositories.OrderRepository;
import com.example.project_25.repositories.UserRepository;

@Configuration
@Profile("test")
public class DbTest implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	User user1 = new User(null, "Gabriel", "gabriel@test.com", "11971276935", "123");
	Order order1 = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user1);
	Order order2 = new Order(null, Instant.now(), OrderStatus.DELIVERED, user1);

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(user1);
		orderRepository.saveAll(Arrays.asList(order1, order2));
	}

}
