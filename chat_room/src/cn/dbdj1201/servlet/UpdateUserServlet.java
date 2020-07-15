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
 * @date 2020-07-13 9:46
 **/
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService userService = new UserService();

        User updateUser = new User();

        try {
            BeanUtils.populate(updateUser, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.updateUser(updateUser);

        resp.sendRedirect(req.getContextPath() + "/listUserPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
