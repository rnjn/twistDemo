package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddItemsToCart {

	private WebDriver browser;

	public AddItemsToCart(WebDriver browser) {
		this.browser = browser;
	}

	public void addToShoppingCart(String quantity, String productName)
			throws Exception {
		List<WebElement> quantities = browser.findElement(
				By.id("productdetail")).findElements(By.name("quantity"));

		for (WebElement quantityInput : quantities) {
			if (quantityInput.isDisplayed()) {
				quantityInput.clear();
				quantityInput.sendKeys(quantity);
				quantityInput.findElement(By.xpath("..")).findElement(By.xpath(".."))
						.findElement(By.tagName("a")).click();

				return;
			}
		}
	}

	public void navigateToShoppingCart() throws Exception {
		browser.findElements(By.linkText("View Cart")).get(0).click();
	}

	public void verifyThatWereAddedToTheShoppingCart(String quantity,
			String productName) throws Exception {

		WebElement product = verifyAndGetProductLinkElement(productName);
		WebElement parentRow = getLinkElementParent(product);
		WebElement findElement = parentRow
				.findElement(By.className("inputBox"));
		assertEquals(quantity, findElement.getAttribute("value"));
	}

	public void verifyThatWereAddedToTheShoppingCartFromShoppingList(
			String quantity, String productName) throws Exception {

		WebElement product = verifyAndGetProductLinkElement(productName);
		WebElement parentRow = getLinkElementParent(product);
		List<WebElement> cells = parentRow.findElements(By.tagName("td"));
		for (WebElement cell : cells) {
			if (!cell.getText().equals(quantity))
				continue;
			else
				return;
		}
		Assert.fail("Quantity is not displayed");
	}
	

	private WebElement getLinkElementParent(WebElement product) {
		WebElement parentRow = product.findElement(By.xpath("..")).findElement(
				By.xpath(".."));
		return parentRow;
	}

	private WebElement verifyAndGetProductLinkElement(String productName) {
		WebElement product = browser.findElement(By
				.partialLinkText(productName));
		assertNotNull(product);
		return product;
	}

}
