package com.asosyalbebe.samplespring.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminHome(Model model, HttpServletRequest request, HttpServletResponse response) {

	return "admin/admin";
    }
}
