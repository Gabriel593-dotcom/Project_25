package com.example.project_25.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.project_25.entities.Category;
import com.example.project_25.entities.Order;
import com.example.project_25.entities.OrderItem;
import com.example.project_25.entities.Payment;
import com.example.project_25.entities.Product;
import com.example.project_25.entities.User;
import com.example.project_25.entities.enums.OrderStatus;
import com.example.project_25.repositories.CategoryRepository;
import com.example.project_25.repositories.OrderItemRepository;
import com.example.project_25.repositories.OrderRepository;
import com.example.project_25.repositories.ProductRepository;
import com.example.project_25.repositories.UserRepository;

@Configuration
@Profile("test")
public class DbTest implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		User user1 = new User(null, "Gabriel", "gabriel@test.com", "11971276935", "123");
		Order order1 = new Order(null, Instant.now(), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user1);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		userRepository.save(user1);
		orderRepository.saveAll(Arrays.asList(order1, order2));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		OrderItem oi1 = new OrderItem(order1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(order1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(order2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(order2, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		// Para associações 1 para 1, o save não será realizado pelo repository da
		// entidade dominada
		// e sim pela entidade dominante.
		Payment pay1 = new Payment(null, Instant.now(), order1);
		order1.setPayment(pay1);

		orderRepository.save(order1);
	}

}
