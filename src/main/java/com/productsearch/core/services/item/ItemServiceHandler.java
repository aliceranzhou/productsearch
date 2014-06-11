package com.productsearch.core.services.item;

import java.util.*;

import com.productsearch.web.domain.Item;

public class ItemServiceHandler implements ItemService {

	// sorts List of items by increasing price
	@Override
	public List<Item> sortPrice(List<Item> items) {
		return mergesortPrice(items);
	}

	public List<Item> mergesortPrice(List<Item> items) {
		int m; // half of the length
		int n = items.size();
		if (n > 1) {
			// split array in two
			m = n / 2;
			List<Item> a = new ArrayList<Item>();
			List<Item> b = new ArrayList<Item>();
			int count = 0;
			for (Item item : items) {
				if (count < m)
					a.add(item);
				else
					b.add(item);
				count++;
			}
			// sort the two halves
			a = mergesortPrice(a);
			b = mergesortPrice(b);
			// merge the two sorted halves.
			items = mergePrice(a, b);
		}
		return items;
	}

	private List<Item> mergePrice(List<Item> a, List<Item> b) {
		List<Item> temp = new ArrayList<Item>(); // Allocate the temporary array
		int j = 0; // Number copied a
		int k = 0; // Number copied b
		int n = a.size();
		int m = b.size();
		// Merge by comparing lowest of each array
		while ((j < n) && (k < m)) {
			if (a.get(j).getPrice() < b.get(k).getPrice())
				temp.add(a.get(j++));
			else
				temp.add(b.get(k++));
		}

		// Copy any remaining entries
		while (j < n)
			temp.add(a.get(j++));
		while (k < m)
			temp.add(b.get(k++));
		return temp;
	}
}
