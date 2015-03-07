package com.asosyalbebe.samplespring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asosyalbebe.samplespring.utils.Logger;

@Controller
public class BaseController {
	private Logger log = Logger.getLogger(getClass());

	private static int counter = 0;
	private String VIEW_INDEX = "index";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		log.info("Test Logu by {0} {1}", "serdar", "kuzucu");
		return VIEW_INDEX;

	}
}
