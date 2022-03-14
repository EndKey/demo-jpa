package com.demo.spring.data.repository;

import com.demo.spring.data.entity.Product;
import com.demo.spring.data.web.model.request.product.FilterProductRequest;
import org.springframework.data.domain.Page;

public interface ProductCustomRepository {

  Page<Product> findByFilter(FilterProductRequest request);

}
