package com.shop.common;

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
}
