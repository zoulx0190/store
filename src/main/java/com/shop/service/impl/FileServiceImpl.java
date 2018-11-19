package com.shop.service.impl;

import com.google.common.collect.Lists;
import com.shop.service.IFileService;
import com.shop.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/17 23:54
 * @ Modified By：
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {
    Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public String upload(MultipartFile file, String path) {
        //获取文件的全名
        String fileName=file.getOriginalFilename();
        //得到文件扩展名
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        //新文件名
        String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        //文件上传的路径
        File fileDir=new File(path);
        //文件夹不存在,根据路径创建多级文件夹
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        //上传后文件存放的路径
        File targetFile = new File(path,uploadFileName);

        try {
            //将新图片替换旧图片
            file.transferTo(targetFile);
            //上传到文件服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
           // targetFile.delete();
        } catch (Exception e) {
            logger.error("上传文件异常");
            return null;
        }

        return targetFile.getName();
    }
}
