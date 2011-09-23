package com.thoughtworks.twistexamples.ofbiztest;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import static junit.framework.Assert.*;

public class CompareProducts {

	private WebDriver browser;

	public CompareProducts(WebDriver browser) {
		this.browser = browser;
	}

	public void addToCompare(String string1) throws Exception {
		browser.findElements(By.linkText("Add To Compare")).get(0).click();

	}

	public void compareProducts() throws Exception {
		browser.findElements(By.linkText("Compare Products")).get(0).click();

	}

	public void verifyEmptyShoppingCart() throws Exception {
		assertEquals("You have no products to compare.",
				browser.findElements(By.tagName("DIV")).get(42).getText());

	}

	public void verifyThatAndAppearAreCompared(String string1, String string2)
			throws Exception {
		String title = browser.getTitle();
		WebDriver popup = browser.switchTo().window("compareProducts");
		assertTrue(popup
				.findElements(By.tagName("TD")).get(1).getText()
				.contains(string1));
		assertTrue(popup
				.findElements(By.tagName("TD")).get(2).getText()
				.contains(string2));
	
	}

}