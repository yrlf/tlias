package com.itheima.tliaswebmanagement.utils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.File;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {

    @Autowired
    private AliOSSProperties aliOSSProperties;


    // 1 @Value 外部属性注入,不要 hard code在这里
    // 2 Bean对象 + YML 配置

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException, ClientException {

        // @Value("${aliyun.oss.endpoint}")
//        String endpoint = aliOSSProperties.getEndpoint();
//
//        // @Value("${aliyun.oss.accessKeyId}")
//        String  accessKeyId = aliOSSProperties.getAccessKeyId();
//
//        // @Value("${aliyun.oss.accessKeySecret}")
//        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
//
//        // @Value("${aliyun.oss.bucketName}")
//        String bucketName = aliOSSProperties.getBucketName();

        // 避免 github 泄露 , 改为环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        String endpoint="https://oss-ap-southeast-2.aliyuncs.com";
        String bucketName ="web-tlias-yz5896";

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
