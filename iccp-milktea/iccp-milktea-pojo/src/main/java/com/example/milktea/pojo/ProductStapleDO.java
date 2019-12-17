package com.example.milktea.pojo;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProductStapleDO {
    /** 产品原料ID*/
    private Long id;

    /** 产品原料名称*/
    @Length(max = 50, message = "产品原料名称最大长度为255")
    private String name;

    /** 产品原料价格*/
    private BigDecimal price;

    /** 产品原料库存量*/
    private Long stocks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }
}