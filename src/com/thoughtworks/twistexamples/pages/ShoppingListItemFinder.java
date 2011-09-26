package com.thoughtworks.twistexamples.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingListItemFinder {

	private final WebDriver browser;
	private final String productName;

	public ShoppingListItemFinder(WebDriver browser, String productName) {
		this.browser = browser;
		this.productName = productName;
	}

	public WebElement getProductLinkElement() {
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

	public WebElement getProductQuantityElement() {
		return getProductLinkElement().findElement(By.xpath(".."))
				.findElement(By.xpath("..")).findElement(By.xpath(".."))
				.findElement(By.name("quantity"));
	}

	private String constructAddToCartLinkText() {
		String productQuantity = getProductQuantityElement()
				.getAttribute("value");
		return "Add " + productQuantity + " To Cart";
	}

	public WebElement getAddToCartLink() {
		return getProductLinkElement().findElement(By.xpath(".."))
		.findElement(By.xpath("..")).findElement(By.xpath(".."))
		.findElement(By.linkText(constructAddToCartLinkText()));
	}
}
