package com.demo.spring.data.service.impl;

import com.demo.spring.data.entity.Product;
import com.demo.spring.data.entity.Shop;
import com.demo.spring.data.repository.ProductRepository;
import com.demo.spring.data.repository.ShopRepository;
import com.demo.spring.data.service.ProductService;
import com.demo.spring.data.web.model.request.product.CreateProductRequest;
import com.demo.spring.data.web.model.request.product.FilterProductRequest;
import com.demo.spring.data.web.model.request.product.UpdateProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository shopRepository;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Product create(CreateProductRequest request) {
        Shop shop = shopRepository.getById(request.getShopId());

        Product product = Product.builder()
                .shop(shop)
                .build();
        BeanUtils.copyProperties(request, product);

        return productRepository.save(product);
//        if (true) {
//            throw new RuntimeException("Test Rollback");
//        }
//        else return null;// Untuk testing transaction.

    }

    @Override
    public Product findById(String id) {
        return productRepository.getById(id);
    }

    @Override
    public Page<Product> findByFilter(FilterProductRequest request) {
        return productRepository.findByFilter(request);
    }

    @Override
    public Product update(String id, UpdateProductRequest request) {
        Product product = productRepository.getById(id);
        BeanUtils.copyProperties(request, product);
        String shopId = request.getShopId();
        if (!shopId.equals(product.getShop().getId())){
            Shop shop = shopRepository.getById(shopId);
            product.setShop(shop);
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
