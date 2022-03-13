package com.demo.spring.data.repository;

import com.demo.spring.data.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {


}
