package com.productsearch.core.services.item;

import java.util.*;

import com.productsearch.web.domain.Item;

public class ItemServiceHandler implements ItemService {

	// sorts List of items by increasing price
	@Override
	public List<Item> sort(List<Item> items, String type) {
		return mergesort(items, type);
	}

	public List<Item> mergesort(List<Item> items, String type) {
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
			a = mergesort(a, type);
			b = mergesort(b, type);
			// merge the two sorted halves.
			if (type.equalsIgnoreCase("priceup")) {
				items = mergePriceUp(a, b);
			} else if (type.equalsIgnoreCase("pricedown")) {
				items = mergePriceDown(a, b);
			} else if (type.equalsIgnoreCase("alpha")) {
				items = mergeAlpha(a, b);
			}

		}
		return items;
	}

	private List<Item> mergePriceUp(List<Item> a, List<Item> b) {
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

	private List<Item> mergePriceDown(List<Item> a, List<Item> b) {
		List<Item> temp = new ArrayList<Item>(); // Allocate the temporary array
		int j = 0; // Number copied a
		int k = 0; // Number copied b
		int n = a.size();
		int m = b.size();
		// Merge by comparing lowest of each array
		while ((j < n) && (k < m)) {
			if (a.get(j).getPrice() > b.get(k).getPrice())
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

	private List<Item> mergeAlpha(List<Item> a, List<Item> b) {
		List<Item> temp = new ArrayList<Item>(); // Allocate the temporary array
		int j = 0; // Number copied a
		int k = 0; // Number copied b
		int n = a.size();
		int m = b.size();
		// Merge by comparing lowest of each array
		while ((j < n) && (k < m)) {
			if ((a.get(j).getName().compareToIgnoreCase(b.get(k).getName())) < 0)
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
