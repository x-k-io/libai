package com.kite.libai.provider.tripartite.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.kite.libai.core.utils.DigestUtils;
import com.kite.libai.provider.account.utils.PictureUtils;
import com.kite.libai.boot.properties.AliYunProperties;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AliOssService {

    private AliYunProperties aliOssProperties;

    /**
     * 上传
     *
     * @param data data
     * @param key  path
     * @return String
     */
    public String upload(byte[] data, String key) {
        // 创建OSSClient实例。
        OSS ossClient = getOssClient();
        PutObjectResult result =
                ossClient.putObject(aliOssProperties.getBucketName(), key, new ByteArrayInputStream(data));
        // 关闭client
        ossClient.shutdown();
        return aliOssProperties.getBucketUrl() + "/" + key;
    }

    private OSS getOssClient() {
        // 创建OSSClient实例。
        return new OSSClientBuilder()
                .build(aliOssProperties.getEndpoint(),
                        aliOssProperties.getAccessKeyId(),
                        aliOssProperties.getAccessKeySecret());
    }


    /**
     * 根据姓名生成头像
     *
     * @param name name
     * @return 头像地址
     */
    public String genAvatar(String name) {
        byte[] data = new byte[0];
        try {
            data = PictureUtils.generateImage(name);
        } catch (IOException e) {
            log.error("生成头像失败");
            e.printStackTrace();
        }
        String key = DigestUtils.md5Hex(data) + ".png";
        return upload(data, key);
    }
}
