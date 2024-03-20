package com.ZhuoYuxiang.week3.demo;


import com.sun.net.httpserver.HttpServer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LifeCycleServlet extends HttpServlet {
    public  LifeCycleServlet(){
           System.out.println("i am in constructor --> LifeCycleServlet() ");// line1
    }
    @Override
    public void init(){
         System.out.println("i am in init() ");// line2

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("i am in service () --> doGet()");// line3
        //line4 -2nd request
        //line5 -3rd request
        //and so on --many times -for each request

}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



    }

    @Override
    public void destroy() {
        System.out.println("i am in destroy()");//when ?
    }
}
