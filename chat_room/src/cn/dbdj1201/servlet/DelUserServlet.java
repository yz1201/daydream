package cn.dbdj1201.servlet;

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
 * @date 2020-07-13 9:26
 **/
@WebServlet("/delUser")
public class DelUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("id"));
        IUserService userService = new UserService();
        userService.deleteByUserId(userId);
        resp.sendRedirect(req.getContextPath() + "/listUserPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
