package org.example.websyslab1_proj;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.example.websyslab1_proj.entity.Product;
import org.example.websyslab1_proj.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
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

	@Bean
	public CacheManager cacheManager(){
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		CaffeineCache bbCache = new CaffeineCache("breakingbadquote",
				Caffeine.newBuilder()
						.expireAfterWrite(1, TimeUnit.MINUTES)
						.maximumSize(20)
						.build());
		CaffeineCache bbCacheSlow = new CaffeineCache("slowerquote",
				Caffeine.newBuilder()
						.expireAfterWrite(2, TimeUnit.MINUTES)
						.expireAfterAccess(1, TimeUnit.MINUTES)
						.maximumSize(20)
						.build());
		cacheManager.setCaches(List.of(bbCache, bbCacheSlow));
		return cacheManager;
	}
}
