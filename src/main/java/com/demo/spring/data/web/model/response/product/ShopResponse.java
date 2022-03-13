package com.demo.spring.data.web.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {
    private String id;
    private String name;

}
