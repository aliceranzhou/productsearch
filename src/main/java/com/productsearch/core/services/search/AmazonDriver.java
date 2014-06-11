package com.productsearch.core.services.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.util.List;

import com.productsearch.web.domain.Item;

public class AmazonDriver {

	// needed for authentication
	private static final String SECRET_KEY = "2K5Xzgn3rs0/fau93xFSoQTehACL0AdHHoFDO4Ma";
	private static final String AWS_KEY = "AKIAJ6OVP2LCPWTZOBRQ";
	private static final String ASSOCIATE_TAG = "idongservice-20";
	private String keyWords;

	// constructor
	public AmazonDriver() {
	}

	// getter and setters for keyWords
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	// handles the search
	public List<Item> search() {
		// initializes the Amazon signed request helper
		SignedRequestsHelper helper = null;
		try {
			// declares the helper
			helper = SignedRequestsHelper.getInstance("ecs.amazonaws.com",
					AWS_KEY, SECRET_KEY);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// hash map for storing the values
		Map<String, String> params = new HashMap<String, String>();
		// filling the hashmap
		params.put("AssociateTag", ASSOCIATE_TAG);
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2011-08-01");

		params.put("Operation", "ItemSearch");
		params.put("ResponseGroup",
				"Small, EditorialReview, OfferSummary, Reviews");
		params.put("SearchIndex", "All");
		// entering the keywords
		params.put("Keywords", this.keyWords);
		// helper signs the url using the parameters in the hashmap
		String url = helper.sign(params);

		// initializes list of items
		List<Item> items = null;
		try {
			// XMLtoItem searches with the created URL, then parses the returned
			// XML into a list of Items
			items = XMLtoItem.parseItems(url, 0);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return items;
	}
}