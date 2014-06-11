package com.productsearch.core.services.search;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import com.productsearch.web.domain.Item;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//gets xml and converts to Items using SAXParser
public class XMLtoItem {
	public static ArrayList<Item> parseItems(String sourceUrl, int i)
			throws ParserConfigurationException, SAXException,
			MalformedURLException, IOException {
		// initiating SAXParser
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser();
		SAXHandler handler = null;
		// i==0 means that the results are from amazon, and i==1, from ebay.
		// different handlers must be used because the two websites have
		// different labels
		if (i == 0) {
			handler = new AmazonHandler();
		} else if (i == 1) {
			handler = new EbayHandler();
		}
		// parse using the handler we've specified
		parser.parse(new InputSource(new URL(sourceUrl).openStream()), handler);
		// return list
		return handler.itemList;
	}
}

class SAXHandler extends DefaultHandler {
	// our SAXHandler has a list of items, a current item, and a string to hold
	// the current content
	ArrayList<Item> itemList = new ArrayList<>();
	Item item = null;
	String content = null;

	// gets the content
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}
}

// for Amazon; extends SAXHandler, meaning that it also has the same fields and
// methods as SAXHandler
class AmazonHandler extends SAXHandler {
	// overriding the default handler startElement.
	// starts with an 'Item' label, and creates an item
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case "Item":
			item = new Item();
			break;
		}
	}

	// if the endElement is Item, then add the filled Item to the list
	// if it is any of the others, then fill the attributes of the Item.
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {
		case "Item":
			itemList.add(item);
			break;
		case "ASIN":
			item.setId(content);
			break;
		case "Title":
			item.setName(content);
			break;
		case "Manufacturer":
			item.setManufacturer(content);
			break;
		case "DetailPageURL":
			item.setDetailPageURL(content);
			break;
		case "ProductGroup":
			item.setProductGroup(content);
			break;
		case "FormattedPrice":
			if (!content.equalsIgnoreCase("too low to display")) {
				String s = content.substring(1);
				s = s.replaceAll("(,)", "");
				item.setPrice(Double.parseDouble(s));
			}
			break;
		case "IFrameURL":
			item.setReviewURL(content);
			break;
		case "Content":
			item.setDescription(content);
			break;
		}
	}

}

// for Ebay; extends SAXHandler, meaning that it also has the same fields and
// methods as SAXHandler
class EbayHandler extends SAXHandler {

	// overriding the default handler startElement.
	// starts with an 'item' label, and creates an item
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case "item":
			item = new Item();
			break;
		}
	}

	// if the endElement is Item, then add the filled Item to the list
	// if it is any of the others, then fill the attributes of the Item.
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {
		case "item":
			itemList.add(item);
			break;
		case "itemId":
			item.setId(content);
			break;
		case "title":
			item.setName(content);
			break;
		case "categoryName":
			item.setProductGroup(content);
			break;
		case "viewItemURL":
			item.setDetailPageURL(content);
			break;
		case "convertedCurrentPrice":
			String s = content;
			s = s.replaceAll("(,)", "");
			item.setPrice(Double.parseDouble(s));
			break;
		case "conditionDisplayName":
			item.setCondition(content);
			break;
		case "shippingServiceCost":
			String t = content;
			t = t.replaceAll("(,)", "");
			item.setShippingCost(Double.parseDouble(t));
			break;
		}
	}

}
