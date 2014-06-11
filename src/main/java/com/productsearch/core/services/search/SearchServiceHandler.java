package com.productsearch.core.services.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productsearch.web.domain.Item;
import com.productsearch.web.domain.Search;

@Service
public class SearchServiceHandler implements SearchService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServiceHandler.class);

	private Search search;

	@Autowired
	private AmazonDriver amazonDriver;

	@Autowired
	private EbayDriver ebayDriver;

	public SearchServiceHandler() {
		LOG.debug("created service");
	}

	// getters and setters for private search
	@Override
	public void setSearch(Search search) {
		this.search = search;
		LOG.debug("finished setting search");
	}

	@Override
	public Search getSearch() {
		return this.search;
	}

	// handles the search
	@Override
	public List<Item> Searcher() {
		LOG.debug("initializing drivers");
		if (search != null) {
			// gives necessary info to the drivers
			amazonDriver.setKeyWords(this.search.getKeyWords());

			ebayDriver.setKeyWords(this.search.getKeyWords());
			ebayDriver.setPostalCode(this.search.getPostalCode());
		} else {
			LOG.debug("EXCEPTION! NO SEARCH HAS BEEN GIVEN");
		}
		LOG.debug("going to search");
		//searches amazon and ebay
		List<Item> i = amazonDriver.search();
		List<Item> j = ebayDriver.search();
		//concatenates the two arrays
		for (int c = 0; c < j.size(); c++) {
			i.add(j.get(c));
		}
		//returns the array of found items
		return i;
	}

}
