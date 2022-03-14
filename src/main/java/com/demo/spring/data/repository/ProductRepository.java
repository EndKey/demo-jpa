package com.demo.spring.data.repository;

import com.demo.spring.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>, ProductCustomRepository {

  Page<Product> findByName(String name, Pageable pageable);

}
