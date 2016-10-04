package com.moda.search.service;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.UUID;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.moda.search.constants.Constants;
import com.moda.search.models.Employee;
import com.moda.search.repository.SearchRepo;

@Service
public class SearchServiceImpl implements SearchService {

  private static final Logger LOG = LoggerFactory.getLogger(SearchService.class);
  private final Gson gson = new Gson();
  
  @Autowired
  private SearchRepo searchRepo;

  @Override
  public String getEmployee(String name) {
    return searchRepo.getEmployee(name);
  }

  @Override
  public String indexEmployee(String empInfo) throws IOException {
    Employee empObj = gson.fromJson(empInfo, Employee.class);

    if (empObj.getId() == null) {
      empObj.setId(UUID.randomUUID().toString());
    }
    XContentBuilder empObjBuilder = jsonBuilder().startObject().field(Constants.ID, empObj.getId()).field(Constants.USER, empObj.getName())
            .field(Constants.ROLE, empObj.getRole()).field(Constants.SALARY, empObj.getSalary()).endObject();
    return searchRepo.indexEmployee(empObjBuilder, empObj.getId());
  }

}
