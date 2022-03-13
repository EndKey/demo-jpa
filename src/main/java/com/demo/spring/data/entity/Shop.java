package com.demo.spring.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop",
    indexes = {
        @Index(name = "shop_shop_id_shop_name_index",
            columnList = "shop_id, shop_name")
    })
public class Shop {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "shop_id")
    private String id;

    @Column(name = "shop_name",length = 50)
    private String name;

}
