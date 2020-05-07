package com.example.milktea.pojo;

import com.example.common.annotation.VOAttribute;
import com.example.milktea.dto.ProductStapleDTO;
import com.example.milktea.dto.ProductTypeDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDO {
    /** 产品ID*/
    private Long id;

    /** 产品种类ID*/
    @NotNull
    private Long typeId;

    /** 产品原料*/
    @Length(max = 255, message = "产品原料最大长度为255")
    private String staples;

    /** 产品名称*/
    @Length(max = 255, message = "产品原料最大长度为255")
    private String name;

    /** 产品价格*/
    private BigDecimal price;

    /** 产品图片*/
    private String pic;

    /** 推荐指数*/
    private Integer recommend;

    /** 销售量*/
    private Long sales;

    /** 创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)
    private LocalDateTime createTime;

    /** 产品介绍*/
    private String description;

    @VOAttribute
    private ProductTypeDTO productTypeDTO;

    @VOAttribute
    private ProductStapleDTO productStapleDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getStaples() {
        return staples;
    }

    public void setStaples(String staples) {
        this.staples = staples;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductTypeDTO getProductTypeDTO() {
        return productTypeDTO;
    }

    public void setProductTypeDTO(ProductTypeDTO productTypeDTO) {
        this.productTypeDTO = productTypeDTO;
    }

    public ProductStapleDTO getProductStapleDTO() {
        return productStapleDTO;
    }

    public void setProductStapleDTO(ProductStapleDTO productStapleDTO) {
        this.productStapleDTO = productStapleDTO;
    }
}