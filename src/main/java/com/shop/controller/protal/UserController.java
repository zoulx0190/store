package com.shop.controller.protal;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import com.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        /**
         * create by    : zlx
         * create time  : 22:45 2018/11/12
         * description  :用户登录
         *
         * @Param: username
         * @Param: password
         * @Param: session
         * @return ServerResponse<User>
         */
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            int role = response.getData().getRole();
            String userRole=Const.CURRENT_USER;
            switch (role){
                case Const.Role.ROLE_CUSTOMER:
                    break;
                    case Const.Role.ROLE_ADMIN:
                        userRole=Const.CURRENT_Admin;
                        break;
            }
            session.setAttribute(userRole, response.getData());
        }
        return response;
    }

    /**
     * create by    : zlx
     * create time  : 23:52 2018/11/12
     * description  :用户登出
     *
     * @return
     * @Param: null
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * create by    : zlx
     * create time  : 23:57 2018/11/12
     * description  :用户注册
     *
     * @return
     * @Param: null
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * create by    : zlx
     * create time  : 0:14 2018/11/13
     * description  :
     *
     * @return
     * @Param: null
     */
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * create by    : zlx
     * create time  : 0:15 2018/11/13
     * description  :获取用户登录信息
     *
     * @return
     * @Param: null
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录");
    }
/**
 * create by    : zlx
 * create time  : 0:16 2018/11/13
 * description  :忘记密码，
 * 
 * @Param: null
 * @return 
 */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }
/**
 * create by    : zlx
 * create time  : 0:17 2018/11/13
 * description  :校验问题答案
 *
 * @Param: null
 * @return
 */
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    /**
     * create by    : zlx
     * create time  : 0:38 2018/11/13
     * description  :忘记密码重置密码
     *
     * @Param: null
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passowrdNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passowrdNew,forgetToken);
    }
    /**
     * create by    : zlx
     * create time  : 15:09 2018/11/13
     * description  :登录状态下重置密码
     *
     * @Param: null
     * @return
     */
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    /**
     * create by    : zlx
     * create time  : 19:10 2018/11/13
     * description  :更新个人信息
     *
     * @Param: null
     * @return
     */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
/**
 * create by    : zlx
 * create time  : 19:55 2018/11/13
 * description  :获取用户详细信息
 *
 * @Param: null
 * @return
 */
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录 status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }
}