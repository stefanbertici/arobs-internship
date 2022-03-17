package com.arobs.internship.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("""
                    <h1>Hello from GET! Please log in.</h1>
                    
                    <form name="loginForm" action="./login" method="POST">
                        Name: <input type="text" name="name"/> <br/>
                        Password: <input type="password" name="password"/> <br/>
                        <input type="submit" value="Submit"/>
                    </form>
                    """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        resp.setContentType("text/html");

        boolean isSuccessful = !name.isEmpty() && password.equals("HelloWorld");

        PrintWriter out = resp.getWriter();
        out.println("<h1>Hello from POST!</h1>\n" +
                    "<h2>Logged in = " + isSuccessful + "</h2>");
    }
}
