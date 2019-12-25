package com.example.milktea.rest.v1;

import com.example.common.log.AutoLog;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.service.FileUploadService;
import com.example.milktea.vo.FileUploadVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/v1/file")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    private FileUploadService fileUploadService;

    @AutoLog(value = "调用文件上传接口")
    @PostMapping("/uploadFile")
    public ResponseEntity<JSONResultVO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("fileType") String fileType) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用文件上传接口");
        }
        JSONResultVO result = fileUploadService.uploadFile(file, username, fileType);
        return ResponseEntity.ok(result);
    }

    @AutoLog(value = "调用文件上传修改接口")
    @PostMapping("/updateFile")
    public ResponseEntity<JSONResultVO> updateFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("fileType") String fileType,
            @RequestParam("fileName") String fileName) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用文件上传修改接口");
        }
        JSONResultVO result = fileUploadService.updateFile(file, username, fileType, fileName);
        return ResponseEntity.ok(result);
    }

    @AutoLog(value = "调用文件删除接口")
    @PostMapping("/deleteFile")
    public ResponseEntity<JSONResultVO> deleteFile(@RequestBody FileUploadVO file) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用文件删除接口");
        }
        JSONResultVO result = fileUploadService.deleteFile(file.getFileName(), file.getUsername(), file.getFileType());
        return ResponseEntity.ok(result);
    }

    @AutoLog(value = "调用文件下载接口")
    @PostMapping("/downloadFile")
    public ResponseEntity<JSONResultVO> downloadFile(@RequestBody FileUploadVO file) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用文件下载接口");
        }
        JSONResultVO result = fileUploadService.downloadFile(file.getFileName(), file.getUsername(), file.getFileType(), file.getLocalPath());
        return ResponseEntity.ok(result);
    }
}

