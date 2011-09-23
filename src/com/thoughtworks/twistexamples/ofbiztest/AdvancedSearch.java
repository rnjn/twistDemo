package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import static junit.framework.Assert.*;

public class AdvancedSearch {

	private WebDriver browser;

	public AdvancedSearch(WebDriver browser) {
		this.browser = browser;
	}

	public void goToAdvancedSearch() throws Exception {
		browser.findElements(By.className("button")).get(1).click();
	
	}

	public void setKeywordTo(String string1) throws Exception {
		browser.findElement(By.name("advtokeywordsearchform")).findElement(By.name("SEARCH_STRING")).sendKeys(string1);
	
	}

	public void setColorTo(String string1) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("pft_COLOR"))).selectByVisibleText(string1);
	
	}

	public void setSizeTo(String string1) throws Exception {
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("pft_SIZE"))).selectByVisibleText(string1);
	
	}

	public void setLicenseTo(String string1) throws Exception {
		
		new org.openqa.selenium.support.ui.Select(browser.findElement(By
				.name("pft_LICENSE"))).selectByVisibleText(string1);
	
	}

	public void performSearch() throws Exception {
		browser.findElements(By.linkText("Find")).get(0).click();
	
	}

	public void verifyThatNoResultsAreFound() throws Exception {
		assertTrue(browser.findElements(By.tagName("H2"))
				.get(0).getText().contains("No results found."));
	
	}

	public void removeConstraintsFor(String string1) throws Exception {
		List<WebElement> linkButtons = browser.findElements(By.className("buttontext"));
		for (WebElement webElement : linkButtons) {
			if(webElement.findElement(By.xpath("..")).getText().contains(string1)){
				webElement.click();
				return;
			}
		}
	}

}
