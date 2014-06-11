package com.productsearch.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.productsearch.web.domain.Basket;

@Controller
public class ItemQueryController {


	@Autowired
	private Basket basket;

	//shows Items
	@RequestMapping(value = "/showItems", method = RequestMethod.GET)
	public String show(@ModelAttribute("basket") Basket basket) {
		return "/popuptrial";
	}

	@ModelAttribute("basket")
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

}
