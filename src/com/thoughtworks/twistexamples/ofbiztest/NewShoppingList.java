package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class NewShoppingList {

	private WebDriver browser;

	public NewShoppingList(WebDriver browser) {
		this.browser = browser;
	}

	public void visit(String string1) throws Exception {
		browser.findElements(By.linkText(string1)).get(0).click();
	
	}

	public void createANewShoppingList() throws Exception {
		browser.findElements(By.linkText("Create New")).get(1).click();
	}

	public void setListNameTo(String string1) throws Exception {
		browser.findElement(By.name("listName")).clear();
		browser.findElement(By.name("listName")).sendKeys(string1);
	
	}

	public void setListDescriptionTo(String string1) throws Exception {
		browser.findElement(By.name("description")).clear();
		browser.findElement(By.name("description")).sendKeys(string1);
	
	}

	public void setListTypeTo(String string1) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("shoppingListTypeId"))).selectByVisibleText(string1);
	
	}

	public void setThisListAsAPublicList() throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("isPublic"))).selectByVisibleText("Y");
	
	}

	public void saveNewShoppingList() throws Exception {
		browser.findElement(By.name("updateList")).findElement(By.linkText("Save")).click();
	
	}

}
