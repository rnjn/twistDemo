package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class AddItemsToCart {

	private WebDriver browser;

	public AddItemsToCart(WebDriver browser) {
		this.browser = browser;
	}

	public void addToShoppingCart(String integer1, String string2)
			throws Exception {
		WebElement quantityInput = browser.findElements(By.name("quantity"))
				.get(0);
		quantityInput.clear();
		quantityInput.sendKeys(integer1);
		quantityInput.findElement(By.xpath("..")).findElement(By.tagName("a"))
				.click();

	}

	public void navigateToShoppingCart() throws Exception {
		browser.findElements(By.linkText("View Cart")).get(0).click();
	}

	public void verifyThatWereAddedToTheShoppingCart(String string1,
			String string2) throws Exception {

		WebElement product = browser.findElement(By.partialLinkText(string2));
		assertNotNull(product);

		WebElement parentRow = product.findElement(By.xpath("..")).findElement(
				By.xpath(".."));
		WebElement findElement = parentRow
				.findElement(By.className("inputBox"));
		assertEquals(string1, findElement.getAttribute("value"));
	}

}
