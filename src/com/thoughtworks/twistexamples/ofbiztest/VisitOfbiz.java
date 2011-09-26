package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import com.thoughtworks.twistexamples.ofbiztest.defaults.OfBizDefaultValues;

public class VisitOfbiz {

	private WebDriver browser;
	private final OfBizDefaultValues ofBizDefaults;

	public VisitOfbiz(WebDriver browser, OfBizDefaultValues defaults) {
		this.browser = browser;
		this.ofBizDefaults = defaults;
	}

	public void setUp() throws Exception {
		browser.get(ofBizDefaults.url);
	}

	public void tearDown() throws Exception {
		// this.browser.quit();
	}

}