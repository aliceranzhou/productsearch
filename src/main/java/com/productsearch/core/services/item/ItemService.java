package com.productsearch.core.services.item;

import java.util.*;

import com.productsearch.web.domain.Item;

//interface for service
public interface ItemService {

	public List<Item> sort(List<Item> items, String type);
}
