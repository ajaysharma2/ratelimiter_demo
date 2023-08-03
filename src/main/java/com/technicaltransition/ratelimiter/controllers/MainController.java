package com.technicaltransition.ratelimiter.controllers;

import com.technicaltransition.ratelimiter.repository.CustomerRepository;
import com.technicaltransition.ratelimiter.service.MessageService;
import com.technicaltransition.ratelimiter.utils.SiteCounter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private MessageService messageService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SiteCounter siteCounter;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String getTestData() {
        return "test";
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("messages", messageService.findAll());
        model.addAttribute("counter", siteCounter.incrementAndGetCount());
        return "/views/message";
    }

    @RequestMapping(value = "/message2", method = RequestMethod.GET)
    public ModelAndView messagesModel() {
        ModelAndView mav = new ModelAndView("message");
        mav.addObject("messages", messageService.findAll());
        mav.addObject("counter", siteCounter.incrementAndGetCount());
        return mav;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomers(Model model, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("customer");
        mav.addObject("customers", customerRepository.findAll());
        mav.addObject("counter", siteCounter.incrementAndGetCount());
        return mav;
    }

    @RequestMapping("/testjsp")
    public String getTestJsp() {
        return "testjsp";
    }

}
