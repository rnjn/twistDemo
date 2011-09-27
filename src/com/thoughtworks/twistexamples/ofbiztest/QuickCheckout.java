package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.thoughtworks.twistexamples.pages.PaymentMethods;
import com.thoughtworks.twistexamples.pages.ShippingMethods;

public class QuickCheckout {

	private WebDriver browser;

	public QuickCheckout(WebDriver browser) {
		this.browser = browser;
	}

	public void chooseQuickCheckout() throws Exception {
		browser.findElements(By.linkText("Quick Checkout")).get(0).click();

	}

	public void selectAsTheMethodOfPayment(String paymentOption)
			throws Exception {

		List<WebElement> paymentMethods = browser.findElements(By
				.name("checkOutPaymentId"));
		String paymentMethodCode = new PaymentMethods()
				.getPaymentMethod(paymentOption);

		for (WebElement paymentMethod : paymentMethods) {
			if (paymentMethod.getAttribute("value").equals(paymentMethodCode)) {
				paymentMethod.click();
				return;
			}
		}
	}

	public void selectFirstStoredAddressAsShippingAddress() throws Exception {
		browser.findElements(By.name("shipping_contact_mech_id")).get(0)
				.click();

	}

	public void selectAsTheShippingMethod(String shippingOption) throws Exception {
		List<WebElement> shippingMethods = browser.findElements(By
				.name("shipping_method"));
		
		String shippingMethodCode = new ShippingMethods().getShippingMethod(shippingOption);
		
		for (WebElement shippingMethod : shippingMethods) {
			if (shippingMethod.getAttribute("value").equals(shippingMethodCode)) {
				shippingMethod.click();
				return;
			}
		}
	}

	public void chooseToShipTheOrderOnlyWhenAllTheItemsInTheOrderAreReady()
			throws Exception {
		browser.findElements(By.name("may_split")).get(0).click();

	}

	public void addAsSpecialInstructions(String string1) throws Exception {
		browser.findElement(By.name("shipping_instructions")).sendKeys(string1);

	}

	public void markThisOrderAsAGift() throws Exception {
		browser.findElements(By.name("is_gift")).get(0).click();

	}

	public void addAsTheGiftMessage(String string1) throws Exception {
		browser.findElement(By.name("gift_message")).sendKeys(string1);

	}

	public void continueToFinalOrderReview() throws Exception {
		browser.findElements(By.linkText("Continue to Final Order Review"))
				.get(0).click();

	}

}
