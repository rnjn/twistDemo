package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class PaymentOptions {

	private WebDriver browser;

	public PaymentOptions(WebDriver browser) {
		this.browser = browser;
	}

	public void chooseToPayWith(String paymentOption) throws Exception {
		browser.findElement(By.linkText(paymentOption)).click();

	}

	public void setBillingAddressAs(String address1, String city)
			throws Exception {
		browser.findElement(By.name("address1")).sendKeys(address1);
		browser.findElement(By.name("city")).sendKeys(city);

	}

	public void setBillingAddressCountryTo(String country) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("countryGeoId"))).selectByVisibleText(country);

	}

	public void setBillingAddressStateToAndPostalCodeTo(String state,
			String postalCode) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("stateProvinceGeoId"))).selectByVisibleText(state);
		browser.findElement(By.name("postalCode")).sendKeys(postalCode);

	}

	public void setCreditCardPersonalInformationNameFor(String firstNameOnCard,
			String lastNameOnCard) throws Exception {
		browser.findElement(By.name("firstNameOnCard")).sendKeys(
				firstNameOnCard);
		browser.findElement(By.name("lastNameOnCard")).sendKeys(lastNameOnCard);

	}

	public void setCreditCardToToExpireOn(String cardType, String cardNumber,
			String expiryMonth, String expiryYear) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("cardType"))).selectByVisibleText(cardType);
		browser.findElement(By.name("cardNumber")).sendKeys(
				cardNumber.toString());
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("expMonth"))).selectByVisibleText(expiryMonth);
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("expYear"))).selectByVisibleText(expiryYear);

	}

	public void continueToOrderReview() throws Exception {
		browser.findElements(By.className("smallsubmit")).get(0).click();
	}

}
