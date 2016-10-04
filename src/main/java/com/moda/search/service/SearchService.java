package com.moda.search.service;

import java.io.IOException;



public interface SearchService {

	String indexEmployee(String data) throws IOException;
	String getName(String name);

}