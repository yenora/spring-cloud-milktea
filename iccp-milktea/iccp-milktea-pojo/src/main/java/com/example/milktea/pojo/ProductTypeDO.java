package com.example.milktea.pojo;

import org.hibernate.validator.constraints.Length;

public class ProductTypeDO {
    /** 产品种类ID*/
    private Long id;

    /** 产品种类名称*/
    @Length(max = 50, message = "产品种类名称最大长度为255")
    private String name;

    /** 产品种类描述*/
    @Length(max = 1000, message = "产品介绍最大长度为255")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}