package com.demo.spring.data.web.controller;

import com.demo.spring.data.entity.Product;
import com.demo.spring.data.entity.Shop;
import com.demo.spring.data.service.ProductService;
import com.demo.spring.data.validation.ProductExist;
import com.demo.spring.data.web.model.request.product.CreateProductRequest;
import com.demo.spring.data.web.model.request.product.FilterProductRequest;
import com.demo.spring.data.web.model.request.product.UpdateProductRequest;
import com.demo.spring.data.web.model.response.Response;
import com.demo.spring.data.web.model.response.product.ProductResponse;
import com.demo.spring.data.web.model.response.product.ShopResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("Create new product")
    @PostMapping(path = "api/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ProductResponse> createNewProduct (@Valid @RequestBody CreateProductRequest request) {
        Product product = productService.create(request);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @ApiOperation("find product")
    @GetMapping(path = "api/product/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ProductResponse> findProduct (@ProductExist @PathVariable String id) {
        Product product = productService.findById(id);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @ApiOperation("Filter Product")
    @PostMapping(
        path = "/api/products/filter",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<ProductResponse>> filter(@RequestBody FilterProductRequest request) {
        Page<Product> productPage = productService.findByFilter(request);
        List<ProductResponse> productResponseList = productPage.map(this::toResponse).getContent();
        return Response.<List<ProductResponse>>builder()
            .status(HttpStatus.OK.value())
            .data(productResponseList)
            .pagination(toResponse(productPage))
            .build();
    }

    @ApiOperation("update product")
    @PutMapping(path = "api/product/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ProductResponse> updateProduct (@ProductExist @PathVariable String id,
                                          @Valid @RequestBody UpdateProductRequest request) {
        Product product = productService.update(id, request);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @ApiOperation("delete product")
    @DeleteMapping(path = "api/product/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> updateProduct (@ProductExist @PathVariable String id) {
        productService.delete(id);
        return Response.<Void>builder()
                .status(HttpStatus.OK.value())
                .build();
    }

    private ProductResponse toResponse (Product product) {
        ProductResponse response = ProductResponse.builder()
                .shop(toResponse(product.getShop())).build();
        BeanUtils.copyProperties(product, response);
        return response;
    }

    private ShopResponse toResponse (Shop shop) {
        ShopResponse shopResponse = ShopResponse.builder().build();
        BeanUtils.copyProperties(shop, shopResponse);
        return shopResponse;
    }

    private Response.Pagination toResponse(Page<?> page) {
        return Response.Pagination.builder()
            .page(page.getNumber())
            .size((long) page.getSize())
            .totalItems(page.getTotalElements())
            .build();
    }
}
