package com.arobs.internship.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();

        if (!request.getServletPath().equals("/counter")) {
            ServletContext context = event.getServletContext();
            AtomicInteger count = ((AtomicInteger) context.getAttribute("counter"));
            count.getAndIncrement();
            context.setAttribute("counter", count);
        }
    }
}
