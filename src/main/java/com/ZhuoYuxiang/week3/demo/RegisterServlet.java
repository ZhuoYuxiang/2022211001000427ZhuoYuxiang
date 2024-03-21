package com.ZhuoYuxiang.week3.demo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/register", "/registered-users"})
public class RegisterServlet extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            ServletContext servletContext = config.getServletContext();
            Context initCtx = new InitialContext();
            dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/UserDatabase");
        } catch (NamingException e) {
            throw new ServletException("Failed to lookup DataSource", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");

        String insertSql = "INSERT INTO usertable (id, username, password, email, gender, birthdate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setString(5, gender);
            pstmt.setString(6, birthdate);
            pstmt.executeUpdate();

            // 可能需要重定向或响应成功消息
            response.sendRedirect(request.getContextPath() + "/success.html"); // 假设有一个成功提示页面
        } catch (SQLException ex) {
            // Handle exception
            throw new ServletException("Error inserting user data", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String selectSql = "SELECT * FROM usertable";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {

            out.println("<html><body><table border='1'>");
            while (rs.next()) {
                ((PrintWriter) out).println("<tr>");
                out.println("<td>" + rs.getString("id") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("password") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("birthdate") + "</td>");
                // Add other columns as needed
                out.println("</tr>");
            }
            out.println("</table></body></html>");
        }  catch (SQLException ex) {
            throw new ServletException("Error executing SQL query to retrieve registered users: " + selectSql, ex);
        }
    }
}