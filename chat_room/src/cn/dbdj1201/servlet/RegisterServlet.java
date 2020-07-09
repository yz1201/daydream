package cn.dbdj1201.servlet;

import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;
import com.wf.captcha.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yz1201
 * @date 2020-07-08 19:37
 **/
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String verifyCode = req.getParameter("verifyCode");
        if (CaptchaUtil.ver(verifyCode,req)){
            IUserService userService = new UserService();
            System.out.println(username + " - " + password);
            boolean flag = userService.register(username, password);
            if (!flag) {
                resp.getWriter().write("gun, this name is mine");
            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }else {
            CaptchaUtil.clear(req);
//            resp.getWriter().write("");
            resp.getWriter().write("verifyCode is error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
