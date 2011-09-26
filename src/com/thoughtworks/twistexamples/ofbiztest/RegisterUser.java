package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterUser {

	private WebDriver browser;

	public RegisterUser(WebDriver browser) {
		this.browser = browser;
	}

	public void navigateToTheUserRegistrationPage() throws Exception {
		browser.findElement(By.partialLinkText("Register")).click();
	}

	public void setNameAs(String string1, String string2, String string3)
			throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.id("USER_TITLE"))).selectByVisibleText(string1);
		browser.findElement(By.id("USER_FIRST_NAME")).sendKeys(string2);
		browser.findElement(By.id("USER_LAST_NAME")).sendKeys(string3);

	}

	public void setAddressAs(String string1, String string2, String string3)
			throws Exception {
		browser.findElement(By.id("CUSTOMER_ADDRESS1")).sendKeys(string1);
		browser.findElement(By.id("CUSTOMER_ADDRESS2")).sendKeys(string2);
		browser.findElement(By.id("CUSTOMER_CITY")).sendKeys(string3);

	}

	public void setPostalCodeAsAndCountryAs(String string1, String string2)
			throws Exception {
		browser.findElement(By.id("CUSTOMER_POSTAL_CODE")).sendKeys(string1);
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.id("customerCountry"))).selectByVisibleText(string2);

	}

	public void doNotAllowAddressSolicitation() throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.id("CUSTOMER_ADDRESS_ALLOW_SOL"))).selectByVisibleText("N");

	}

	public void setEmailAddressAsAndPasswordAs(String string1, String string2)
			throws Exception {
		browser.findElement(By.id("CUSTOMER_EMAIL")).sendKeys(string1);
		browser.findElement(By.id("PASSWORD")).sendKeys(string2);
		browser.findElement(By.id("CONFIRM_PASSWORD")).sendKeys(string2);

	}

	public void setUserNameWithPrefix(String string1) throws Exception {
		browser.findElement(By.id("USERNAME")).sendKeys(
				string1 + java.util.UUID.randomUUID());

	}

	public void setPasswordHintAs(String string1) throws Exception {
		browser.findElement(By.id("PASSWORD_HINT")).sendKeys(string1);

	}

	public void registerUser() throws Exception {
		browser.findElements(By.linkText("Save")).get(0).click();

	}

	public void verifyUserHasBeenCreated(String string1, String string2)
			throws Exception {
		assertTrue(browser.findElement(By.id("welcome-message")).getText()
				.contains(string1)
				&& browser.findElement(By.id("welcome-message")).getText()
						.contains(string2));

	}

}
