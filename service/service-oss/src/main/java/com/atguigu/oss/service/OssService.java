package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xudongzhou
 * @date 2022-07-03
 * @desc
 */
public interface OssService {
    /**
     * 文件上传至阿里云
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
