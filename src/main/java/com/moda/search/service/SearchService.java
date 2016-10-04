package com.moda.search.service;

import java.io.IOException;

public interface SearchService {

  String indexEmployee(String empInfo) throws IOException;

  String getEmployee(String empId);

}