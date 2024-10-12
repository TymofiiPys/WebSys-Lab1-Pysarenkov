package org.example.websyslab1_proj;

import org.example.websyslab1_proj.entity.Product;
import org.example.websyslab1_proj.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebSysLab1ProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSysLab1ProjApplication.class, args);
	}

	@Bean
	public CommandLineRunner initProducts(ProductRepository productRepository){
		return args -> {
			Product product1 = new Product(1L, "first_prod");
			Product product2 = new Product(2L, "second_prod");
			productRepository.saveAll(List.of(product1, product2));
		};
	}
}
