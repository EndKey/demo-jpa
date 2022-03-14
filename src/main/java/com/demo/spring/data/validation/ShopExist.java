package com.demo.spring.data.validation;

import com.demo.spring.data.validation.validator.ShopExistsByIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
    validatedBy = {ShopExistsByIdValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShopExist {

  String message() default "Shop does not exists!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}