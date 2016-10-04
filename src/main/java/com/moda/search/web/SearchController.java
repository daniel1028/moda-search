package com.moda.search.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moda.search.service.SearchService;

@Controller
@RequestMapping("/search/")
public class SearchController extends BaseController{

	private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	private  SearchService searchService;
	

	@RequestMapping(value = "/employee/{id:.+}", method = RequestMethod.GET)
	public ModelAndView getEmpoloyeeDetails(@PathVariable("id") String id) {
		LOG.debug("search is executed - for Query :", id);
		String val = searchService.getName(id);
		LOG.debug("search response :", val);
		return getModel(val);

	}

	@RequestMapping(value = "/index/employee", method = RequestMethod.POST)
	public ModelAndView postemployee(@RequestBody String data) throws IOException {
		LOG.debug("search is executed - for Query :", data);
		
		String result = searchService.postEmpl(data);
		LOG.debug("Succesfully created :", result);
		return getModel("Indices Sucessfully created ");

	}

	

	

}