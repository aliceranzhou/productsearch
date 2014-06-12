package com.productsearch.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.productsearch.core.services.item.ItemService;
import com.productsearch.web.domain.Basket;

@Controller
public class ItemCommandController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ItemCommandController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private Basket basket;

	// redirects searchAgain to the search form
	@RequestMapping(value = "/searchAgain", method = RequestMethod.POST)
	public String searchAgain() {
		return "redirect:/";
	}

	// sorts the basket and redirects to /showItems
	@RequestMapping(value = "/sortPriceUp", method = RequestMethod.POST)
	public String sortPriceUp(@ModelAttribute("basket") Basket basket) {
		LOG.debug("items to be sorted");
		basket.reset(itemService.sort(basket.getItems(), "priceUp"));
		return "redirect:/showItems";
	}

	// sorts the basket and redirects to /showItems
	@RequestMapping(value = "/sortPriceDown", method = RequestMethod.POST)
	public String sortPriceDown(@ModelAttribute("basket") Basket basket) {
		LOG.debug("items to be sorted");
		basket.reset(itemService.sort(basket.getItems(), "priceDown"));
		return "redirect:/showItems";
	}

	// sorts the basket and redirects to /showItems
	@RequestMapping(value = "/sortAlpha", method = RequestMethod.POST)
	public String sortAlpha(@ModelAttribute("basket") Basket basket) {
		LOG.debug("items to be sorted");
		basket.reset(itemService.sort(basket.getItems(), "alpha"));
		return "redirect:/showItems";
	}

	@ModelAttribute("basket")
	public Basket getBasket() {
		return basket;
	}

}
