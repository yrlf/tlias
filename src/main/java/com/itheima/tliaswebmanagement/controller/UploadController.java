package com.itheima.tliaswebmanagement.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController

public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping("/upload")
//    public Result upload2(String username, Integer age, MultipartFile image) throws IOException {
//        // 1. 存在本地:
//        // 获取文件的原始文件名
//        String fileName = image.getOriginalFilename();
//        // image.transferTo(new File("/Users/yangz/Documents/projects/tlias-web-management/images/"+fileName));
//        // 1.1 构造唯一文件名, UUID + 文件原始名的拓展名
//        int index = fileName.lastIndexOf(".");
//        String extname = fileName.substring(index);
//        String uuidFileName = UUID.randomUUID().toString()+extname;
//        log.info("文件上传: {}, {}, {}, {}", username, age, image, uuidFileName);
//        image.transferTo(new File("/Users/yangz/Documents/projects/tlias-web-management/images/" + uuidFileName));
//        return Result.success();
//    }


    // 2. 阿里云 OSS 存储文件
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {

        log.info("文件上传, 文件名: {}", image.getOriginalFilename());
        // 调用 OSS 工具类来上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成, 文件访问的 url: {} ", url);
        return Result.success(url);
    }
}
