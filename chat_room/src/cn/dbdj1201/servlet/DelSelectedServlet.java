package cn.dbdj1201.servlet;

import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yz1201
 * @date 2020-07-13 10:27
 **/
@WebServlet("/delSelected")
public class DelSelectedServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] uids = req.getParameterValues("uid");
        List<Integer> ids = Arrays.stream(uids).map(Integer::parseInt).collect(Collectors.toList());
        IUserService userService = new UserService();
        userService.delSelectedUsers(ids);
        resp.sendRedirect(req.getContextPath() + "/listUserPage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
