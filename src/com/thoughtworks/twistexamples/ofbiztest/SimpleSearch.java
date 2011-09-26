package com.thoughtworks.twistexamples.ofbiztest;

import static junit.framework.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimpleSearch {

	private WebDriver browser;

	public SimpleSearch(WebDriver browser) {
		this.browser = browser;
	}

	public void searchFor(String product) throws Exception {
		browser.findElement(By.name("SEARCH_STRING")).sendKeys(product);
		browser.findElement(By.id("SEARCH_OPERATOR_OR")).click();
		browser.findElements(By.className("button")).get(0).click();
	}

	public void verifyThatSearchResultsShow(String searchString)
			throws Exception {

		assertTrue(browser.findElements(By.linkText(searchString)).get(0)
				.isDisplayed());

	}

	public void verifyThatLastSearchesShows(String searchString)
			throws Exception {

		assertTrue(browser.findElements(By.tagName("LI")).get(42).getText()
				.toLowerCase().contains(searchString.toLowerCase()));

	}

	public void openProduct(String string1) throws Exception {
		browser.findElements(By.linkText(string1)).get(0).click();

	}

}