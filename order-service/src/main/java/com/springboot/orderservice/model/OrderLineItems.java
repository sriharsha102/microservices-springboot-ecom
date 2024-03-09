package com.springboot.orderservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_orderLineItems")
@Getter
@Setter

public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;



}
