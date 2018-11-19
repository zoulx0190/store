package com.shop.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/12 23:36
 * @ Modified By：
 */
public class Const {
    public static final String CURRENT_USER="currentUser";
    public static final String CURRENT_Admin="currentAdmin";
    public static final String USERNAME ="username";
    public static final String EMAIL ="email" ;
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public enum ProductStatusEnum{
        NOT_SAL(0,"下架"),
        ON_SAL(1,"在线");

        private int code;
        private String value;

        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
}
