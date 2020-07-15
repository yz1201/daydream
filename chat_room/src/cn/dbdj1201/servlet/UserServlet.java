package cn.dbdj1201.servlet;

import cn.dbdj1201.entity.User;
import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yz1201
 * @date 2020-07-12 18:44
 **/
@WebServlet("/listUser")
public class UserServlet extends HttpServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询全部用户信息,重定向到展示页面
        req.setAttribute("users", this.userService.findAll());
        req.getRequestDispatcher("my-list.jsp").forward(req, resp);
    }
}
