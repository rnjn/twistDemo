package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.thoughtworks.twistexamples.pages.PaymentMethods;
import com.thoughtworks.twistexamples.pages.ShippingMethods;

import static junit.framework.Assert.*;

public class FinalCheckoutReview {
	private WebDriver browser;

	public FinalCheckoutReview(WebDriver browser) {
		this.browser = browser;
	}

	public void verifyThatIsIncludedInTheOrder(String string1) throws Exception {

		assertTrue(browser.findElements(By.partialLinkText(string1)).get(0)
				.isDisplayed());

	}

	public void verifyThatIsTheChosenPaymentMethod(String paymentOption)
			throws Exception {
		String paymentMethod = new PaymentMethods()
				.getPaymentMethodDescription(paymentOption);
		assertTrue(verifyAtleastOneListItemContains(paymentMethod));
	}

	public void verifyThatIsTheChosenShippingMethod(String shippingOption)
			throws Exception {
		String shippingMethodDescription = new ShippingMethods()
				.getShippingMethodDescription(shippingOption);
		assertTrue(verifyAtleastOneListItemContains(shippingMethodDescription));

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

	public void verifyThatIsSetAsTheGiftMessage(String string1)
			throws Exception {
		assertTrue(verifyAtleastOneListItemContains(string1));
	}

	public void verifyThatThisOrderIsAGift() throws Exception {
		assertTrue(verifyAtleastOneListItemContains("This order is a gift"));
	}

	public void submitVerifiedOrder() throws Exception {
		browser.findElement(By.name("processButton")).click();
	}

	public void verifyThatTheStatusOfTheOrderIs(String status) throws Exception {
		assertTrue(verifyAtleastOneListItemContains("Status " + status));
	}
}
