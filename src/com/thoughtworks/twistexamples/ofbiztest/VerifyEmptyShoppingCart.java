package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyEmptyShoppingCart {

	private WebDriver browser;

	public VerifyEmptyShoppingCart(WebDriver browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		assertEquals("You have no products to compare.",
				browser.findElements(By.tagName("DIV")).get(42).getText());
	}

	public void tearDown() throws Exception {
		// This method is executed after the scenario execution finishes.
	}

}
