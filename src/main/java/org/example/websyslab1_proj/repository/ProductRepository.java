package org.example.websyslab1_proj.repository;

import org.example.websyslab1_proj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
