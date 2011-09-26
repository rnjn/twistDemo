package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver browser;

	public HomePage(WebDriver browser) {
		this.browser = browser;
	}

	public void goToHomePage() throws Exception {
		browser.findElement(By.linkText("Main")).click();

	}

}
