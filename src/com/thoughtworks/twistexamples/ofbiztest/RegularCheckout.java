package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class RegularCheckout {

	private WebDriver browser;

	public RegularCheckout(WebDriver browser) {
		this.browser = browser;
	}

	public void checkoutShoppingCart() throws Exception {
		browser.findElements(By.linkText("Check out")).get(0).click();
	
	}

}
