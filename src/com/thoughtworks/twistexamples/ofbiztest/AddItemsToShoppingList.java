package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

public class AddItemsToShoppingList {

	private WebDriver browser;

	public AddItemsToShoppingList(WebDriver browser) {
		this.browser = browser;
	}

	public void addNumbersOfTheSaidItemTo(String quantity,
			String shoppingListName) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("shoppingListId"))).selectByVisibleText(shoppingListName);

		List<WebElement> quantities = browser.findElement(
				By.name("addToShoppingList")).findElements(By.name("quantity"));

		for (WebElement quantityInput : quantities) {
			if (quantityInput.isDisplayed()) {
				quantityInput.clear();
				quantityInput.sendKeys(quantity);
				quantityInput.findElement(By.xpath(".."))
						.findElement(By.tagName("a")).click();

				return;
			}
		}

	}

	public void verifyThatNumbersOfAreAddedToShoppingList(String quantity,
			String productName) throws Exception {

		WebElement productLink = findProductLink(productName);
		assertNotNull(productName + " not displayed", productLink);

		List<WebElement> quantityInputs = productLink
				.findElement(By.xpath("..")).findElement(By.xpath(".."))
				.findElement(By.xpath("..")).findElements(By.name("quantity"));

		
		WebElement quantityInput = productLink
				.findElement(By.xpath("..")).findElement(By.xpath(".."))
				.findElement(By.xpath("..")).findElement(By.name("quantity"));
		assertTrue(productName + " has " + quantityInput.getAttribute("value") + " instead of "
				+ quantity + " numbers added to the shopping list",
				quantityInput.getAttribute("value").equals(quantity));
		
	}

	private WebElement findProductLink(String productName) {
		List<WebElement> buttonTexts = browser.findElements(By
				.className("buttontext"));
		for (WebElement buttonText : buttonTexts) {
			if (buttonText.getText().toLowerCase()
					.contains(productName.toLowerCase())) {
				return buttonText;
			}
		}
		return null;
	}

	public void editShoppingList(String shoppingListName) throws Exception {
		browser.findElements(By.linkText("Shopping Lists")).get(0).click();
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("shoppingListId"))).selectByVisibleText(shoppingListName);

		browser.findElement(By.name("selectShoppingList"))
				.findElement(By.linkText("Edit")).click();

	}

}
