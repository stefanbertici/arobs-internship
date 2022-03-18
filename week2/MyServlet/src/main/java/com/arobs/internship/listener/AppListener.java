package com.arobs.internship.listener;

import com.arobs.internship.repository.InMemoryPeopleRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.setAttribute("counter", new AtomicInteger(0));
        context.setAttribute("people", new InMemoryPeopleRepository());
    }
}
