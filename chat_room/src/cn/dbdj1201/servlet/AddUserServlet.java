package cn.dbdj1201.servlet;

import cn.dbdj1201.entity.User;
import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author yz1201
 * @date 2020-07-13 8:53
 **/
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new User();
        IUserService userService = new UserService();
        try {
            BeanUtils.populate(newUser, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.addUser(newUser);
        resp.sendRedirect(req.getContextPath()+"/listUserPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
