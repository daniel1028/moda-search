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

import com.moda.search.constants.Constants;
import com.moda.search.service.SearchService;

@Controller
@RequestMapping("/search/")
public class SearchController extends BaseController {

  private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
  @Autowired
  private SearchService searchService;

  @RequestMapping(value = "/employee/{id:.+}", method = RequestMethod.GET)
  public ModelAndView getEmployeeDetails(@PathVariable(Constants.ID) String id) {
    String val = searchService.getEmployee(id);
    return responseBuilder(val);
  }

  @RequestMapping(value = "/index/employee", method = RequestMethod.POST)
  public ModelAndView indexEmployeeDetails(@RequestBody String data) throws IOException {
    String result = searchService.indexEmployee(data);
    return responseBuilder(result);
  }

}