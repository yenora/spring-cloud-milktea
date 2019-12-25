package com.example.milktea.service;

import com.example.common.vo.JSONResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService{
    /**
     * 上传文件
     *
     * @param file 二进制文件
     * @param username 用户名称
     * @param fileType 文件类型（文件夹）
     * @return JSONResultVO
     */
    JSONResultVO uploadFile(MultipartFile file, String username, String fileType);

    /**
     * 修改文件
     *
     * @param file 二进制文件
     * @param username 用户名称
     * @param fileType 文件类型（文件夹）
     * @param fileName 文件名称
     * @return JSONResultVO
     */
    JSONResultVO updateFile(MultipartFile file, String username, String fileType, String fileName);

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @param username 用户名称
     * @param fileType 文件类型（文件夹）
     * @return JSONResultVO
     */
    JSONResultVO deleteFile(String fileName, String username, String fileType);

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @param username 用户名称
     * @param fileType 文件类型（文件夹）
     * @param localPath 本地路径
     * @return JSONResultVO
     */
    JSONResultVO downloadFile(String fileName, String username, String fileType, String localPath);
}