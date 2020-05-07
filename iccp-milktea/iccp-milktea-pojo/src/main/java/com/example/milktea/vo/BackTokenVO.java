package com.example.milktea.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BackTokenVO {
    /**
     * Token
     */
    private String token;

    /**
     * 过期时间
     */
    private Integer expiresIn;
}
