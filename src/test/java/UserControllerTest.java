import com.shop.common.Const;
import com.shop.common.ServerResponse;
import com.shop.controller.protal.UserController;
import com.shop.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/13 22:05
 * @ Modified By：
 */
@RunWith(SpringJUnit4ClassRunner.class) //这个必须使用junit4.9以上才有
@ContextConfiguration(locations = {"classpath:spring-test.xml","classpath:spring-mybatis.xml"})
@Transactional  //事务处理
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    //mock模拟session
    private MockHttpSession session;

    //mock模拟request
    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {

        this.session = new MockHttpSession();
        this.request = new MockHttpServletRequest();

    }

    @Test
    public void testShow() throws Exception {

        //创建参数
        User user = new User();
        user.setId(2);
        user.setUsername("122");
        user.setPassword("777");
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setEmail("515917203@qq.com");
        user.setPhone("22111155544");
        user.setQuestion("who im i");
        user.setAnswer("zlx");

        session.setAttribute(Const.CURRENT_USER,user);
        request.setSession(session);

        //构造controller
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        //ServerResponse<User> result = userController.login(user.getUsername(),user.getPassword(),session);
        ServerResponse<User> result = userController.update_information(session,user);
        System.out.println("返回值：" + result.getMsg());

    }
}