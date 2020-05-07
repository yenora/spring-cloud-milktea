package com.example.common.vo;

import lombok.Builder;

/**
 * @Description: JSON包装对象
 * @Author: yaos
 * @Date: 2019-12-12 21:04
 * @Version：V1.0
 **/
@Builder
public class JSONResultVO {

    // 成功的状态码
    public static final Integer CODE_SUCCESS = 20000;
    // 失败的状态码
    public static final Integer CODE_ERROR = -50001;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 提示信息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
