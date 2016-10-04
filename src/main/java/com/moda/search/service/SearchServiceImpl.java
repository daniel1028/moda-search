package com.moda.search.service;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.UUID;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.moda.search.models.Employee;
import com.moda.search.repository.SearchRepo;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger LOG = LoggerFactory.getLogger(SearchService.class);
	private final Gson gson = new Gson();
	@Autowired
	private SearchRepo searchRepo;

	
	@Override
	public String getName(String name){
		return searchRepo.getName(name);
	}
	@Override
	public String postEmpl(String data) throws IOException{
		Employee employeeObject = gson.fromJson(data, Employee.class);
		
		
		if(employeeObject.getId() == null){
			employeeObject.setId(UUID.randomUUID().toString());
		}
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("id", employeeObject.getId())
			        .field("user",employeeObject.getName())
			        .field("role", employeeObject.getRole())
			        .field("salary",employeeObject.getSalary())
			        .endObject();
		return searchRepo.postEmpl(builder,employeeObject.getId());
	}


}
