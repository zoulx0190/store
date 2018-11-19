package com.shop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/17 23:08
 * @ Modified By：
 */
    public interface IFileService {
        String upload(MultipartFile file, String path);
    }

