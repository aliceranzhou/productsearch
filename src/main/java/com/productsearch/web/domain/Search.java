package com.productsearch.web.domain;

public class Search {
	// fields
	private String keyWords;
	private String postalCode;

	// setters and getters for above fields
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCode() {
		return postalCode;
	}
}
