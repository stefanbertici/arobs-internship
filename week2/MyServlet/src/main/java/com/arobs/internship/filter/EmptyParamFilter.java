package com.arobs.internship.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/login")
public class EmptyParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (httpRequest.getMethod().equalsIgnoreCase("POST")) { // only if http method is POST do we filter
            String name = servletRequest.getParameter("name");
            String password = servletRequest.getParameter("password");

            if (name != null && name.matches("[A-Za-z0-9]{1,15}") &&
                    password != null && password.matches("[A-Za-z0-9]{1,15}")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/wronginput.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
