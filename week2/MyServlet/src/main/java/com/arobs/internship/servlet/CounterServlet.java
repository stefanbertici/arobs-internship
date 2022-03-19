package com.arobs.internship.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "counterServlet", urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CounterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info(" \"/counter\" doGet was called by " + req.getRemoteAddr());
        PrintWriter out = resp.getWriter();
        out.println("Request counter: " + req.getServletContext().getAttribute("counter"));
    }
}
