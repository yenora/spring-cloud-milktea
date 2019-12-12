package com.example.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: JSON包装对象
 * @Author: yaos
 * @Date: 2019-12-12 21:04
 * @Version：V1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JSONResultVO {
    private Integer code;
    private Object data;
    private String msg;
}
