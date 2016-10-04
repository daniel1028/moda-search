package com.moda.search.repository;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;



public interface SearchRepo {
	public String getName(String name);
	String postEmpl(XContentBuilder employee, String docId) throws IOException;
}
