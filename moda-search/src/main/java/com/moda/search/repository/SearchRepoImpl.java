package com.moda.search.repository;

import org.springframework.stereotype.Repository;


@Repository
public class SearchRepoImpl implements SearchRepo {

	@Override
	public String getName(String name) {
		return name;
	}

	

}
