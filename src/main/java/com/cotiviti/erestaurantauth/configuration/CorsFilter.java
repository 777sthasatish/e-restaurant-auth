package com.cotiviti.erestaurantauth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Value("${allowedOrigins}")
    private String allowedOrigins;
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Authorize (allow) all domains to consume the content
        resp.addHeader("Access-Control-Allow-Origin", allowedOrigins);
        resp.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, PATCH, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "authorization, content-type");

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (servletRequest.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
