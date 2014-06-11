package com.productsearch.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket implements Serializable {
	// to store data
	private static final long serialVersionUID = 3666631636634006849L;
	// list of items
	private List<Item> items = new ArrayList<Item>();

	// constructor
	public Basket() {

	}

	// constructor given list
	public Basket(List<Item> items) {
		this.items = items;
	}

	// adds item
	public void add(Item item) {
		items.add(item);
	}

	// returns itemList
	public List<Item> getItems() {
		return this.items;
	}

	// gets size of list
	public int getSize() {
		return items.size();
	}

	// clears basket
	public void clear() {
		items = new ArrayList<Item>();
	}

	// clears basket and replaces with new items
	public void reset(List<Item> items) {
		this.clear();
		for (Item item : items) {
			this.add(item);
		}
	}

	// prints basket contents for testing purposes
	public void print() {
		for (Item item : items) {
			System.out.println(item.getPrice());
		}

	}
}
