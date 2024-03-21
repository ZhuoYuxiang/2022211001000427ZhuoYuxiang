package com.ZhuoYuxiang.week4;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name = "name1", value = "ZhuoYuxiang"),
                @WebInitParam(name = "studentId1", value = "2022211001000427")
        })
public class ConfigDemoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取Servlet配置参数
        ServletConfig config = getServletConfig();
        String name = config.getInitParameter("name1");
        String studentId = config.getInitParameter("studentId1");

        // 将参数写入响应
        response.setContentType("text/plain");
        response.getWriter().write("Name: " + name + "\nStudent ID: " + studentId);
    }
}