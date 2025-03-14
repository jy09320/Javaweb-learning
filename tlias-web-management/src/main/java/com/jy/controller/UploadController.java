package com.jy.controller;

import com.jy.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequestMapping("/upload")
@RestController
public class UploadController {
    @PostMapping
    public Result handleFileUpload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数:{},{},{}",name,age,file);
        //保存文件
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename = UUID.randomUUID().toString() + extension;
        file.transferTo(new File("D:/我的练习/" + originalFilename));
        return Result.success();
    }
}
