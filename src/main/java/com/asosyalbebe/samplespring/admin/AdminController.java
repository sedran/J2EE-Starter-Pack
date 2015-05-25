package com.asosyalbebe.samplespring.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asosyalbebe.samplespring.user.model.User;
import com.asosyalbebe.samplespring.user.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminHome(Model model, HttpServletRequest request, HttpServletResponse response) {

	return "adminHome.view";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userManagement(Model model, HttpServletRequest request, HttpServletResponse response) {

	return "adminUsers.view";
    }

    @RequestMapping(value = "/users/getUsers", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getUsers(HttpServletRequest request, HttpServletResponse response) {
	Map<String, Object> body = new HashMap<String, Object>();

	String firstResultStr = request.getParameter("firstResult");
	String maxResultsStr = request.getParameter("maxResults");

	int firstResult = Integer.parseInt(firstResultStr);
	int maxResults = Integer.parseInt(maxResultsStr);

	List<User> users = userService.getUsers(firstResult, maxResults);
	List<Object> usersJson = new ArrayList<Object>(users.size());
	for (User user : users) {
	    Map<String, Object> userJson = new HashMap<String, Object>();
	    userJson.put("id", user.getId());
	    userJson.put("email", user.getEmail());
	    userJson.put("name", user.getName());
	    userJson.put("surname", user.getSurname());
	    userJson.put("username", user.getUsername());
	    userJson.put("createDate", user.getCreateDate().getTime());
	    usersJson.add(userJson);
	}

	body.put("users", usersJson);
	return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
    }

}
