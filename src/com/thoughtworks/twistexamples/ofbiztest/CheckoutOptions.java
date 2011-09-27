package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class CheckoutOptions {

	private WebDriver browser;

	public CheckoutOptions(WebDriver browser) {
		this.browser = browser;
	}

	public void selectDefaultShippingAddress() throws Exception {
		browser.findElements(By.name("shipping_contact_mech_id")).get(0).click();
	
	}

	public void navigateToCheckoutOptions() throws Exception {
		browser.findElements(By.linkText("Next")).get(0).click();
	
	}

	public void selectAsTheShippingOptions(String string1) throws Exception {
		browser.findElements(By.name("shipping_method")).get(14).click();
	
	}

	public void chooseToShipAsItemsBecomeAvailable() throws Exception {
		browser.findElements(By.name("may_split")).get(1).click();
	
	}

	public void markThisPurchaseAsAGift() throws Exception {
		browser.findElements(By.name("is_gift")).get(0).click();
	
	}

	public void moveNextToPaymentOptions() throws Exception {
		browser.findElements(By.linkText("Next")).get(0).click();
	
	}

}
