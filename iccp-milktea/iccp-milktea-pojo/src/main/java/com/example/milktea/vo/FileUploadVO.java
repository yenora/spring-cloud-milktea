package com.example.milktea.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileUploadVO {
    /**
     * 文件
     */
    private MultipartFile file;
    /**
     * 名称
     */
    private String fileName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 本地地址
     */
    private String localPath;
}
