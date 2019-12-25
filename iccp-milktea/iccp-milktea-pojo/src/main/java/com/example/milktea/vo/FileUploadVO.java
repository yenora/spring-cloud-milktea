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
    private MultipartFile file;
    private String fileName;
    private String username;
    private String fileType;
    private String localPath;
}
