package com.demo.spring.data.validation.validator;

import com.demo.spring.data.repository.ShopRepository;
import com.demo.spring.data.validation.ShopExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShopExistsByIdValidator implements ConstraintValidator<ShopExist, String> {

  @Autowired
  ShopRepository shopRepository;

  @Override
  public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
    return shopRepository.existsById(id);
  }
}
