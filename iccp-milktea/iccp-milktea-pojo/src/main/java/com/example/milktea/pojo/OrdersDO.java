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
public class OrdersDO {
    /** */
    private Long id;

    /** 订单唯一id*/
    private String orderId;

    /** 顾客id*/
    private Long memberId;

    /** 总金额*/
    private BigDecimal amount;

    /** 备注*/
    @Length(max = 255, message = "备注最大长度为255")
    private String notes;

    /** 生成时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)
    private LocalDateTime createTime;

    /** 客户*/
    @VOAttribute
    private MemberDO member;

    /** 查询订单创建时间的开始时间 */
    @VOAttribute
    private LocalDateTime queryCreateTimeBegin;

    /** 查询订单创建时间的结束时间 */
    @VOAttribute
    private LocalDateTime queryCreateTimeEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public MemberDO getMember() {
        return member;
    }

    public void setMember(MemberDO member) {
        this.member = member;
    }

    public LocalDateTime getQueryCreateTimeBegin() {
        return queryCreateTimeBegin;
    }

    public void setQueryCreateTimeBegin(LocalDateTime queryCreateTimeBegin) {
        this.queryCreateTimeBegin = queryCreateTimeBegin;
    }

    public LocalDateTime getQueryCreateTimeEnd() {
        return queryCreateTimeEnd;
    }

    public void setQueryCreateTimeEnd(LocalDateTime queryCreateTimeEnd) {
        this.queryCreateTimeEnd = queryCreateTimeEnd;
    }
}