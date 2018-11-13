package com.shop.controller.backend;

import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/13 21:28
 * @ Modified By：
 */

@Controller
@RequestMapping("/manager/")
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            User user=response.getData();
            if(user.getRole()== Const.Role.ROLE_ADMIN){
                session.setAttribute(Const.CURRENT_Admin,user);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("该用户不是管理员，无法登录");
            }
        }
        return  response;
    }
}
