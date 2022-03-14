package com.demo.spring.data.service;

import com.demo.spring.data.entity.Product;
import com.demo.spring.data.web.model.request.product.CreateProductRequest;
import com.demo.spring.data.web.model.request.product.FilterProductRequest;
import com.demo.spring.data.web.model.request.product.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {

    Product create(CreateProductRequest request);

    Product findById(String id);

    Page<Product> findByFilter(FilterProductRequest request);

    Product update(String id, UpdateProductRequest request);

    void delete(String id);

}
