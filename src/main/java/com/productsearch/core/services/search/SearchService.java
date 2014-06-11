package com.productsearch.core.services.search;

import java.util.*;

import com.productsearch.web.domain.Item;
import com.productsearch.web.domain.Search;

public interface SearchService {
	public void setSearch(Search search);
	public Search getSearch();
	public List<Item> Searcher();
}
