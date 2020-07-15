package cn.dbdj1201.servlet;

import cn.dbdj1201.entity.PageBean;
import cn.dbdj1201.entity.User;
import cn.dbdj1201.service.IUserService;
import cn.dbdj1201.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author yz1201
 * @date 2020-07-13 10:44
 **/
@WebServlet("/listUserPage")
public class UserPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        Map<String, String[]> condition = req.getParameterMap();
        IUserService userService = new UserService();
        req.setAttribute("pb", userService.findByPagesConditional(Integer.parseInt(currentPage),
                Integer.parseInt(rows), condition));
        req.setAttribute("condition", condition);
        req.getRequestDispatcher("my-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
