package com.moda.search.repository;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;



public interface SearchRepo {
	public String getEmployee(String empInfo);
	String indexEmployee(XContentBuilder empInfo, String docId) throws IOException;
}
