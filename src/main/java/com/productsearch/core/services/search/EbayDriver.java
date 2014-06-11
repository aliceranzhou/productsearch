package com.productsearch.core.services.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.productsearch.web.domain.Item;

public class EbayDriver {

	// needed for authentication
	public final static String EBAY_APP_ID = "AliceZho-a6ec-4dfb-aed1-33c7356e213c";
	public final static String EBAY_FINDING_SERVICE_URI = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME="
			+ "{operation}&SERVICE-VERSION={version}&SECURITY-APPNAME="
			+ "{applicationId}&GLOBAL-ID={globalId}&sortOrder={sortOrder}&keywords={keywords}"
			+ "&paginationInput.entriesPerPage={maxresults}";
	// basic parameters
	public static final String SERVICE_VERSION = "1.0.0";
	public static final String OPERATION_NAME = "findItemsByKeywords";
	public static final String GLOBAL_ID = "EBAY-ENCA";
	public final static int REQUEST_DELAY = 3000;

	private int numItems;
	private final static int DEF_NUMITEMS = 10;

	// variables
	private String postalCode = "null";
	private String keyWords;

	public EbayDriver() {
		this.numItems = DEF_NUMITEMS;
	}

	// getters and setters
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	// handles the search
	public List<Item> search() {
		// creates url
		String url = createUrl();
		List<Item> items = null;
		try {
			// calls XMLtoItem, which searches and parses from XMLtoItem
			items = XMLtoItem.parseItems(url, 1);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	public String createUrl() {
		// replaces the parameters in the basic url with the specified
		// parameters
		String url = EbayDriver.EBAY_FINDING_SERVICE_URI;
		url = url.replace("{version}", EbayDriver.SERVICE_VERSION);
		url = url.replace("{operation}", EbayDriver.OPERATION_NAME);
		url = url.replace("{globalId}", EbayDriver.GLOBAL_ID);
		url = url.replace("{applicationId}", EbayDriver.EBAY_APP_ID);
		url = url.replace("{sortOrder}", "BestMatch");

		// puts our variables in
		try {
			url = url.replace("{keywords}", URLEncoder.encode(keyWords, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		url = url.replace("{maxresults}", "" + this.numItems);
		if (!postalCode.equals("null")) {
			url += "&buyerPostalCode=" + this.postalCode;
		}
		return url;
	}
}