package com.ZhuoYuxiang.week4;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//use @webservlet - no web.xml code
@WebServlet(
        urlPatterns = {"/jdbc"},
        initParams = {
                @WebInitParam(name="driver", value = "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
                @WebInitParam(name="url", value = "jdbc:sqlserver://localhost:1433;databaseName=userdb;encrypt=true;trustServerCertificate=true"),
                @WebInitParam(name="username", value = "sa"),
                @WebInitParam(name="password", value = "zyx345xyz"),
        },loadOnStartup = 1



)//end of webservlet
public class JDBCDemoServlet extends HttpServlet {
  Connection con=null; //class variable
  @Override
  public void init() throws ServletException {
//only one connection
    //String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //String url = "jdbc:sqlserver://localhost;databaseName=userdb;encrypt=true;trustServerCertificate=true";
    //String username = "sa";
    //String password = "zyx345xyz";


    //get servletconfig --> getInitParameters-- no change
    ServletConfig config=getServletConfig();
    String driver=config.getInitParameter("driver");// <param-name>driver</param-name>
    String url=config.getInitParameter("url");
    String username=config.getInitParameter("username");
    String password=config.getInitParameter("password");

    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, username, password);
      System.out.println("init()--> " +con);// ok
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    if (con == null) {
      throw new ServletException("Database connection is not initialized yet.");
    }

    //connection within do get-- many times - bad way
    System.out.println("i am in doGet()");// ok
    //we need to use con within doGet
    String sql="select * from usertable";
      try {
          ResultSet rs= con.createStatement().executeQuery(sql);
          while (rs.next()) {
            //get from rs - print - write into response

          }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }


  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


  }

  @Override
  public void destroy() {
    super.destroy();
      try {
          con.close();//when tomcat stop
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}