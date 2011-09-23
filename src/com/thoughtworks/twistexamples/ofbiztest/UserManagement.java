package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

import com.thoughtworks.twistexamples.ofbiztest.defaults.OfBizDefaultValues;
import static junit.framework.Assert.*;

public class UserManagement {

	private WebDriver browser;
	private final OfBizDefaultValues defaults;

	public UserManagement(WebDriver browser, OfBizDefaultValues defaults) {
		this.browser = browser;
		this.defaults = defaults;
	}

	public void loginAsAdministrator() throws Exception {
		browser.findElement(By.name("USERNAME")).sendKeys(defaults.adminUser);
		browser.findElement(By.name("PASSWORD")).sendKeys(defaults.adminPassword);
		browser.findElements(By.tagName("INPUT")).get(2).click();
	
	}

	public void goToPartyManager() throws Exception {
		browser.findElements(By.className("contracted")).get(1).click();
		browser.findElements(By.linkText("PARTY")).get(0).click();
	
	}

	public void searchForUserByUserID(String string1) throws Exception {
		browser.findElement(By.name("userLoginId")).sendKeys(string1);
		browser.findElements(By.tagName("INPUT")).get(14).click();
	
	}

	public void verifyThatUserShowsUpInTheSearchResultsPage(String string1)
			throws Exception {
		assertTrue(browser.findElements(By.linkText(string1)).get(0).getText()
				.contains(string1));
		assertTrue(com.thoughtworks.webdriver.Utils.exists(browser,
				By.linkText(string1), 0));
	
	}
}
