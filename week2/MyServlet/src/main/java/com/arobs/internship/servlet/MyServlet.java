package com.arobs.internship.servlet;

import com.arobs.internship.repository.InMemoryPeopleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class MyServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(" \"/login\" doGet was called by " + req.getRemoteAddr());
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        printLoginForm(out);
    }

    private void printLoginForm(PrintWriter out) {
        out.println("""
                <html>
                    <h1>Hello from GET! Enter your name and the secret password.</h1>
                                    
                    <form name="loginForm" action="./login" method="POST">
                        Your name:       <input type="text" name="name"/> <br/>
                        Secret password: <input type="password" name="password"/> <br/>
                        <input type="submit" value="Submit"/>
                    </form>
                </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        ServletContext context = req.getServletContext();
        InMemoryPeopleRepository peopleRepository = (InMemoryPeopleRepository) context.getAttribute("people");

        loginResult(out, req, peopleRepository);
    }

    private void loginResult(PrintWriter out, HttpServletRequest req, InMemoryPeopleRepository peopleRepository) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String successfulOutput = ("""
                <h1>Hello %s from POST!</h1>
                <h2>You know the password! Have a cookie :)</h2>
                """).formatted(name);
        String failedOutput = ("""
                <h1>Hello %s from POST!</h1>
                <h2>You don't know the password! No cookie for you this time :(</h2>
                """).formatted(name);

        boolean isSuccessful = !name.isBlank() && password.equals("HelloWorld");
        if (isSuccessful) {
            out.println(successfulOutput);
            peopleRepository.add(name);
        } else {
            out.println(failedOutput);
        }

        printCookiesHallOfFame(out, peopleRepository.getAll());
    }

    private void printCookiesHallOfFame(PrintWriter out, Map<String, Integer> people) {
        out.println("""
                <html>
                    <h2>Cookies hall of fame:</h2>
                     <ul>""");

        people.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> out.println("<li>" + e.getKey() + " got " + e.getValue() + "</li>"));

        out.println("""
                    </ul>
                </html>
                    """);
    }
}
