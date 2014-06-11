package com.productsearch.web.domain;

public class Item {
	// fields
	private String name;
	private String description;
	private String manufacturer;
	private String productGroup;
	private String detailPageURL;
	private double price;
	private double shippingCost;
	private String reviewURL;
	private String condition;
	private String id;

	// getters and setters for above fields
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;

	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getProductGroup() {
		return this.productGroup;
	}

	public void setDetailPageURL(String detailPageURL) {
		this.detailPageURL = detailPageURL;
	}

	public String getDetailPageURL() {
		return this.detailPageURL;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getReviewURL() {
		return this.reviewURL;
	}

	public void setCondition(String condition) {
		this.condition = condition;

	}

	public String getCondition() {
		return this.condition;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public double getShippingCost() {
		return this.shippingCost;
	}

}
