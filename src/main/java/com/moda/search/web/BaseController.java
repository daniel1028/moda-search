package com.moda.search.web;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
	public <M> ModelAndView responseBuilder(Object data) {
		ModelAndView model = new ModelAndView("content");
		model.addObject("content", data);
		return model;
	}
}
