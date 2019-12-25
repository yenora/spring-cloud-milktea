package com.example.milktea.service.impl;

import com.example.common.util.FtpUtil;
import com.example.common.util.IDUtils;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.service.FileUploadService;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${ftp.host:127.0.0.1}")
    private String host;

    @Value("${ftp.port:21}")
    private int port;

    @Value("${ftp.username:milktea}")
    private String loginName;

    @Value("${ftp.password:123456}")
    private String password;

    @Value("${ftp.basepath:/spring-cloud-milktea}")
    private String basepath;

    @Value("${nginx.path:http://127.0.0.1:5000}")
    private String nginxPath;

    @Value("${temp.filePath:F:\\github\\image\\}")
    private String tempFilePath;

    @Override
    @Transactional
    public JSONResultVO uploadFile(MultipartFile file, String username, String fileType) {
        if (file.isEmpty()) {
            return JSONResultVO.builder()
                    .code(-1)
                    .data("")
                    .msg("上传文件为空").build();
        }
        String originalFilename = file.getOriginalFilename();

        if (!"".equals(originalFilename)) {
            String uuid = IDUtils.genImageName();

            if (originalFilename.contains("\\")) {
                int index = originalFilename.lastIndexOf("\\");
                originalFilename = originalFilename.substring(index + 1);
            }

            if (originalFilename.contains(".")) {
                String[] fileNameSplitArray = originalFilename.split("\\.");
                originalFilename = uuid + "." + fileNameSplitArray[1];
            }

            if (!originalFilename.contains(".")) {
                originalFilename = uuid;
            }

            // 临时文件
            File dest = new File(tempFilePath + originalFilename);
            try {
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }

                file.transferTo(dest);

                /*
                    上传文件到FTP服务器
                 */
                String filePath = "/" + username + "/" + fileType + "/";
                FileInputStream in = new FileInputStream(dest);
                boolean flag = FtpUtil.uploadFile(host, port, loginName, password, basepath, filePath, originalFilename, in);

                if (flag) {
                    return JSONResultVO.builder()
                            .code(1)
                            .data(nginxPath + basepath + filePath + originalFilename)
                            .msg("上传成功").build();
                } else {
                    LOGGER.error("文件上传错误");
                    return JSONResultVO.builder()
                            .code(-2)
                            .data("")
                            .msg("上传失败").build();
                }
            } catch (Exception e) {
                LOGGER.error("文件上传异常");
                return JSONResultVO.builder()
                        .code(-2)
                        .data("")
                        .msg("上传失败").build();
            }
        } else {
            return JSONResultVO.builder()
                    .code(-2)
                    .data("")
                    .msg("上传失败").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO updateFile(MultipartFile file, String username, String fileType, String fileName) {
        if (((FileUploadService) AopContext.currentProxy()).deleteFile(fileName, username, fileType).getCode() < 0) {
            LOGGER.error("删除文件异常");
        }
        JSONResultVO uploadResult = ((FileUploadService) AopContext.currentProxy()).uploadFile(file, username, fileType);
        if (!"".equals(uploadResult.getData())) {
            return JSONResultVO.builder()
                    .code(1)
                    .data(uploadResult.getData())
                    .msg("修改文件成功").build();
        } else {
            return JSONResultVO.builder()
                    .code(-2)
                    .data("")
                    .msg("修改文件失败").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO deleteFile(String fileName, String username, String fileType) {
        String filePath = "/" + username + "/" + fileType + "/";
        if (FtpUtil.deleteFile(host, port, loginName, password, basepath, filePath,
                fileName.substring(fileName.lastIndexOf("/") + 1))) {
            return JSONResultVO.builder()
                    .code(1)
                    .data(fileName)
                    .msg("删除文件成功").build();
        } else {
            LOGGER.error("删除文件失败");
            return JSONResultVO.builder()
                    .code(-2)
                    .data("")
                    .msg("删除文件失败").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO downloadFile(String fileName, String username, String fileType, String localPath) {
        String remotePath = basepath + "/" + username + "/" + fileType + "/";
        if (FtpUtil.downloadFile(host, port, loginName, password, remotePath, fileName, localPath)) {
            return JSONResultVO.builder()
                    .code(1)
                    .data(fileName)
                    .msg("删除文件成功").build();
        } else {
            LOGGER.error("删除文件失败");
            return JSONResultVO.builder()
                    .code(-2)
                    .data("")
                    .msg("删除文件失败").build();
        }
    }
}

