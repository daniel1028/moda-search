package com.moda.search.repository;

import java.io.IOException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.moda.search.constants.IndexConstants;
import com.moda.search.constants.IndexTypeConstants;
import com.moda.search.datasource.infra.ElasticsearchClusterClient;
import com.moda.search.web.SearchController;

@Repository
public class SearchRepoImpl implements SearchRepo {

  private ElasticsearchClusterClient elsClient = ElasticsearchClusterClient.instance();
  private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
  private final Gson gson = new Gson();

  @Override
  public String getEmployee(String empdId) {
    GetResponse empInfo = elsClient.getElsClient().prepareGet(IndexConstants.EMPLOYEE_DETAILS, IndexTypeConstants.EMPLOYEE, empdId).get();
    String json = gson.toJson(empInfo.getSource());
    LOG.debug("json format {} :", json);
    return json;
  }

  @Override
  public String indexEmployee(XContentBuilder empInfo, String docId) throws IOException {

    IndexResponse response =
            elsClient.getElsClient().prepareIndex(IndexConstants.EMPLOYEE_DETAILS, IndexTypeConstants.EMPLOYEE, docId).setSource(empInfo).get();
    String indexvalue = gson.toJson(response);
    LOG.debug("json format {} :", indexvalue);
    return indexvalue;

  }

}
