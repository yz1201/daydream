package cn.dbdj1201.annotation.servlet;

import cn.dbdj1201.annotation.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yz1201
 * @date 2020-07-09 18:02
 **/
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("你今天吃了吗");
        String filename = req.getParameter("filename");
        ServletContext context = req.getServletContext();
        resp.setHeader("content-type",
                context.getMimeType(filename));
        String path = context.getRealPath("/resources/") + filename;

        filename = DownLoadUtils.getFileName(req.getHeader("user-agent"), filename);
        resp.setHeader("content-disposition", "attachment;filename=" + filename);

//        File file = new File(context.getRealPath("/resources/") + filename);
//        System.out.println("file is exist ? " + file.exists());
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(path));
             BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream())) {
            byte[] bs = new byte[1024 * Runtime.getRuntime().availableProcessors()];
            int b;
            while ((b = bis.read(bs)) != -1) {
                bos.write(bs, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("传输完成");
//        BufferedInputStream bis = new BufferedInputStream(
//                new FileInputStream(context.getRealPath("/resources") + filename));
//        BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
