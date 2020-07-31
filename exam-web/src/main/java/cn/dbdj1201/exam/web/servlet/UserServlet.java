package cn.dbdj1201.exam.web.servlet;

import cn.dbdj1201.exam.entity.User;
import cn.dbdj1201.exam.service.IUserService;
import cn.dbdj1201.exam.service.impl.UserServiceImpl;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yz1201
 * @date 2020-07-31 18:18
 **/
@WebServlet("/login")
public class UserServlet extends HttpServlet {

    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json;charset=utf8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!StrUtil.isEmpty(username) && !StrUtil.isEmpty(password)) {
            User loginUser = this.userService.login(username, password);

            if (loginUser == null) {
                resp.sendRedirect(req.getContextPath() + "/error.html");
            } else {
                Cookie username1 = new Cookie("username", username);
                Cookie password1 = new Cookie("password", password);

//                Cookie cookie = new Cookie("user", new ObjectMapper().writeValueAsString(loginUser));
                resp.addCookie(username1);
                resp.addCookie(password1);
                req.getRequestDispatcher("success.html").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
