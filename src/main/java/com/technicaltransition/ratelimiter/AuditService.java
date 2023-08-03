package com.technicaltransition.ratelimiter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.util.Hashtable;

public class AuditService {

    Hashtable history = new Hashtable<String, String>();

    public void track(HttpServletRequest request, String msg) {

        history.put(((User) request.getServletContext().getContext("user")).getUsername(), msg);

    }
}
