package com.ZhuoYuxiang.week2.demo;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.WebConnection;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.PrintWriter;
import java.io.Writer;

public class HelloWorldServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter Writer = response.getWriter();
        Writer.println("Name:Zhuo Yuxiang \nID:2022211001000427\nDate and Time Wed Mar 13 20:21:52 CST 2024"
                );
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}

