package cn.dbdj1201.servlet;

import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-07-08 8:43
 **/
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
//    private static final String PASSWORD = "test";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, Object> resultMap = new HashMap<>();

        IUserService userService = new UserService();
        boolean flag = userService.login(username, password);

        if (flag) {
            resultMap.put("success", true);
            resultMap.put("message", "登录成功");
            req.getSession().setAttribute("username", username);
        } else {
            resultMap.put("success", false);
            resultMap.put("message", "用户名或密码错误");
        }
        resp.getWriter().write(JSON.toJSONString(resultMap));
    }
}
