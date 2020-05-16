package com.example.milktea.pojo;

import com.example.common.annotation.VOAttribute;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDO {
    /** 产品ID*/
    private Long id;

    /** 产品种类ID*/
    private Long typeId;

    /** 产品原料*/
    @Length(max = 255, message = "产品原料最大长度为255")
    private String stapleIds;

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

   /** 接收前台传来的原料id数组*/
    @VOAttribute
    private Long[] stapleId;

    /** 前台展示用*/
    @VOAttribute
    private String type;

    /** 前台展示用*/
    @VOAttribute
    private String staple;

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

    public String getStapleIds() {
        return stapleIds;
    }

    public void setStapleIds(String stapleIds) {
        this.stapleIds = stapleIds;
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
        this.description = description == null ? null : description.trim();
    }

    public Long[] getStapleId() {
        return stapleId;
    }

    public void setStapleId(Long[] stapleId) {
        this.stapleId = stapleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStaple() {
        return staple;
    }

    public void setStaple(String staple) {
        this.staple = staple;
    }
}