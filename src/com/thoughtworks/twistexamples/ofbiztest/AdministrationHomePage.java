package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import com.thoughtworks.twistexamples.ofbiztest.defaults.OfBizDefaultValues;

public class AdministrationHomePage {

	private WebDriver browser;
	private final OfBizDefaultValues defaults;

	public AdministrationHomePage(WebDriver browser, OfBizDefaultValues defaults) {
		this.browser = browser;
		this.defaults = defaults;
	}

	public void goToAdminHomePage() throws Exception {
		this.browser.get(defaults.adminUrl);
	}

}
