package cn.dbdj1201.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yz1201
 * @date 2020-07-09 19:35
 **/
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
        System.out.println("你喝醉了吗？");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
