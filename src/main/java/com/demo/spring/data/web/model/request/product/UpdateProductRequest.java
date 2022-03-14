package com.demo.spring.data.web.model.request.product;

import com.demo.spring.data.validation.ShopExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotBlank(message = "Blank")
    @Length(min = 2, max = 50, message = "Length_2_50")
    private String name;

    @Min(value = 0, message = "Min 0")
    private BigInteger price;

    @ShopExist
    private String shopId;

}
