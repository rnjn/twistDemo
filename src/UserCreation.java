// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class UserCreation {

	
	private Browser browser;

	public UserCreation(Browser browser) {
		this.browser = browser;
	}


	public void createUserWithPassword(String userEmail, String password)
			throws Exception {
		browser.link("Log In").click();
		browser.link("Create a new account").click();
		browser.textbox("user[email]").setValue(userEmail);
		browser.password("user[password]").setValue(password);
		browser.password("user[password_confirmation]").setValue(password);
		browser.submit("Create").click();
	}

}