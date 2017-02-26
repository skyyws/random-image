package wangs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WangS on 2017/2/26.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //处理注册请求之前，检测验证码是否有效
        String checkCode = request.getParameter("checkcode");

        String s_checkCode = (String) request.getSession().getAttribute("checkcode");

        if (checkCode!=null && s_checkCode!=null && checkCode.equalsIgnoreCase(s_checkCode)) {
            System.out.printf("处理注册请求");
        } else {
            System.out.printf("认证码错误");
        }

    }
}
