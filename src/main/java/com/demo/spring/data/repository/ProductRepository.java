package com.demo.spring.data.repository;

import com.demo.spring.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {


}
