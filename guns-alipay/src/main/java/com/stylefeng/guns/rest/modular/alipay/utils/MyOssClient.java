package com.stylefeng.guns.rest.modular.alipay.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@ConfigurationProperties(prefix = "myoss")
@Component
public class MyOssClient {
    String bucket;
    String endpoint;
    String accessKey;
    String secret;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void ossFileUpload(File myfile, String desFileName) throws IOException {
        InputStream inputStream = new FileInputStream(myfile);
        OSSClient ossClient = new OSSClient(endpoint, accessKey, secret);
        ossClient.putObject(bucket, desFileName, inputStream);
       // 关闭OSSClient。//ossClient.shutdown();
    }

    /*public static void main(String[] args) throws FileNotFoundException {

        File targetFile = new File("D:/tmp", "qr-267cdea131974d108fc6038616265a3d.png");
        InputStream inputStream = new FileInputStream(targetFile);
        OSSClient ossClient = new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAI6oEddMf9eVfL", "HHbUz1F4mo183rIdzxXk9GSkWeTHgl");
        ossClient.putObject("hao6666", "qr-267cdea131974d108fc6038616265a3d.png", inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }*/

}
