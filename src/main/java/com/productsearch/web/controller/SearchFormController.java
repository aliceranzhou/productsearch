package com.productsearch.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.productsearch.core.services.search.SearchService;
import com.productsearch.web.domain.Basket;
import com.productsearch.web.domain.Item;
import com.productsearch.web.domain.Search;

@Controller
@RequestMapping("/")
public class SearchFormController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private Search search;

	@Autowired
	private Basket basket;

	//returns the html view for the form
	@RequestMapping(method = RequestMethod.GET)
	public String show(@ModelAttribute("search") Search search) {
		return "/searchForm";
	}

	//searches, fills basket with items, and redirects to a view that shows the items
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("search") Search search,
			@ModelAttribute("basket") Basket basket, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/searchForm";
		}
		basket.clear();
		this.searchService.setSearch(search);
		for (Item item : this.searchService.Searcher()) {
			basket.add(item);
		}
		return "redirect:/showItems";
	}

	@ModelAttribute("search")
	public Search getSearch() {
		return new Search();
	}

	public void setSearch() {
		this.search = new Search();
	}

	@ModelAttribute("basket")
	public Basket getBasket() {
		return basket;
	}

}
