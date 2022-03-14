package com.demo.spring.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product",
    indexes = {
        @Index(name = "product_product_id_product_name_index",
            columnList = "product_id, product_name")
    })
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id")
    private String id;

    @Column(name = "product_name",length = 50)
    private String name;

    @Column
    private BigInteger price;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Version
    @Column
    private Long version;

}
