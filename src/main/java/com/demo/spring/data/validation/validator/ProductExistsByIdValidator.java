package com.demo.spring.data.validation.validator;

import com.demo.spring.data.repository.ProductRepository;
import com.demo.spring.data.validation.ProductExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductExistsByIdValidator implements ConstraintValidator<ProductExist, String> {

  @Autowired
  ProductRepository productRepository;

  @Override
  public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
    return productRepository.existsById(id);
  }
}
