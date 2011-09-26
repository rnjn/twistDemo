package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.*;

public class FinalCheckoutReview {

	private HashMap<String, String> paymentMethods = new HashMap<String, String>();
	private WebDriver browser;

	public FinalCheckoutReview(WebDriver browser) {
		populatePaymentMethods();
		this.browser = browser;
	}

	private void populatePaymentMethods() {
		paymentMethods.put("COD", "Cash On Delivery");
	}
	
	public void verifyThatIsIncludedInTheOrder(String string1) throws Exception {
		
		assertTrue(browser.findElements(By.partialLinkText(string1)).get(0)
				.isDisplayed());
		
	}

	public void verifyThatIsTheChosenPaymentMethod(String string1)
			throws Exception {
		assertTrue(verifyAtleastOneListItemContains(paymentMethods.get(string1)));
	}

	public void verifyThatIsTheChosenShoppingMethod(String string1)
			throws Exception {
		assertTrue(verifyAtleastOneListItemContains(string1));

	}

	private boolean verifyAtleastOneListItemContains(String query) {
		List<WebElement> listItems = browser.findElements(By.tagName("LI"));
		for (WebElement listItem : listItems) {
			if (listItem.getText().toLowerCase().contains(query.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void verifyThatAreSetAsSpecialInstructions(String string1)
			throws Exception {
		assertTrue(verifyAtleastOneListItemContains(string1));
	}

	public void verifyThatIsSetAsTheGiftMessage(String string1) throws Exception {
		assertTrue(verifyAtleastOneListItemContains(string1));
	}

	public void verifyThatThisOrderIsAGift() throws Exception {
		assertTrue(verifyAtleastOneListItemContains("This order is a gift"));
	}

	public void submitVerifiedOrder() throws Exception {
		browser.findElement(By.name("processButton")).click();
	}

	public void verifyThatTheStatusOfTheOrderIsApproved() throws Exception {
		assertTrue(verifyAtleastOneListItemContains("Status Approved"));
	}
}
