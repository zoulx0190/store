package com.shop.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/17 0:51
 * @ Modified By：
 */
public class PropertiesUtil {
    private static Logger logger=LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName = "shop.properties";

        props=new Properties();
        try{
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        }catch (IOException e){
            logger.error("读取配置文件异常");
        }
    }

    public static String getProperty(String key){
        String value=props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){
        String value=props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value=defaultValue.trim();
        }
        return value.trim();
    }

}
