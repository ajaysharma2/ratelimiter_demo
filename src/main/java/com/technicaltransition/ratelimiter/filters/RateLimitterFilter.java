package com.technicaltransition.ratelimiter.filters;

import com.technicaltransition.ratelimiter.RateLimitterDemo;
import com.technicaltransition.ratelimiter.utils.SiteCounter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RateLimitterFilter extends GenericFilterBean {
    private static final Logger LOG = LoggerFactory.getLogger(RateLimitterFilter.class);

    @Autowired
    private RateLimitterDemo rateLimitterDemo;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private SiteCounter siteCounter;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Map<String, Object> errorDetails = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (rateLimitterDemo == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            rateLimitterDemo = webApplicationContext.getBean(RateLimitterDemo.class);
        }
        if (!rateLimitterDemo.rateLimit(principal.getUsername())) {
            LOG.info(
                    "Logging Request  {} : {} : {}", req.getMethod(),
                    req.getRequestURI(), "Limit exceeded please try after sometime....");
            errorDetails.put("errorMsg", "Limit exceeded, Please try after sometime....");
            res.setStatus(HttpStatus.FORBIDDEN.value());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(res.getWriter(), errorDetails);
            //chain.doFilter(request, response);
            return;
        }

        LOG.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        chain.doFilter(request, response);
        LOG.info(
                "Logging Response :{}",
                res.getContentType());
    }

}
