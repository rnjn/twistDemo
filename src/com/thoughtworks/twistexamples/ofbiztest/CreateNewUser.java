package com.thoughtworks.twistexamples.ofbiztest;

// JUnit Assert framework can be used for verification

import org.openqa.selenium.WebDriver;

public class CreateNewUser {

	private WebDriver browser;

	public CreateNewUser(WebDriver browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		RegisterUser registerUserWorkflow = new RegisterUser(browser);
		registerUserWorkflow.navigateToTheUserRegistrationPage();
		registerUserWorkflow.setAddressAs("one", "two", "three");
		registerUserWorkflow.setEmailAddressAsAndPasswordAs("uriah@heep.com","password");
		registerUserWorkflow.setNameAs("Mr.", "Uriah", "Heep");
		registerUserWorkflow.setPostalCodeAsAndCountryAs("90210", "United Kingdom");
		registerUserWorkflow.setPasswordHintAs("magician");
		registerUserWorkflow.setUserNameWithPrefix("uriah");
		registerUserWorkflow.registerUser();
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
