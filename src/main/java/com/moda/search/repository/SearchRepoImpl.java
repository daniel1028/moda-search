package com.moda.search.repository;
import java.io.IOException;
import java.util.UUID;

import org.elasticsearch.action.get.GetResponse;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.moda.search.datasource.infra.ElasticsearchClusterClient;
import com.moda.search.models.Employee;
import com.moda.search.web.SearchController;


@Repository
public class SearchRepoImpl implements SearchRepo {
	
	private ElasticsearchClusterClient elsClient = ElasticsearchClusterClient.instance();
	private final Logger LOG = LoggerFactory.getLogger(SearchController.class);
	private final Gson gson = new Gson();
	
	@Override
	public String getName(String name) {
		GetResponse response = elsClient.getElsClient().prepareGet("twitter", "tweet", "1").get();
		String json = gson.toJson(response.getSource());
		LOG.debug("json format {} :", json);
		return json;
	}

	@Override
	public String postEmpl(XContentBuilder employee,  String docId) throws IOException {
		
		IndexResponse response = elsClient.getElsClient().prepareIndex("employee_details", "employee" ,docId)
		        .setSource(employee)
		        .get();
		String indexvalue = gson.toJson(response);
		LOG.debug("json format {} :", indexvalue);
		return indexvalue;

	}


}
