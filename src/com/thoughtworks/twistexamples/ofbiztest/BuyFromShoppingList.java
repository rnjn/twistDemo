package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.thoughtworks.twistexamples.pages.ShoppingListItemFinder;

public class BuyFromShoppingList {

	private WebDriver browser;

	public BuyFromShoppingList(WebDriver browser) {
		this.browser = browser;
	}

	public void addToShoppingCart(String productName) throws Exception {
		ShoppingListItemFinder shoppingListItemFinder = new ShoppingListItemFinder(browser,productName);
		WebElement addToCartLink = shoppingListItemFinder.getAddToCartLink();
		addToCartLink.click();
	}

}
