package com.shop.service;

import com.shop.common.ServerResponse;
import com.shop.pojo.User;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/12 22:23
 * @ Modified By：
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer id);

}
