package cn.dbdj1201.annotation.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-07-04 13:42
 **/
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        resp.getWriter().write("hello");
//        System.out.println("hello ---> "+Thread.currentThread().getName());
//        while (true){
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("?");
//        }
        System.out.println("你喝多了吗");
        System.out.println("你喝多了吗");
        System.out.println("你喝多了吗");
        System.out.println("你喝多了吗");
        System.out.println("你喝多了吗");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
