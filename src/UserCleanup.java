// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class UserCleanup {

	private Browser browser;

	public UserCleanup(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		
	}

	public void tearDown() throws Exception {
		if(browser.link("Logout").exists())
			browser.link("Logout").click();
		browser.link("Log In").click();
		browser.textbox("user[email]").setValue("spree@example.com");
		browser.password("user[password]").setValue("spree123");
		browser.submit("Log In").click();
		browser.navigateTo(OpenSpreeWebsite.SPREE_DEMO_URL + "/admin");
		browser.link("Users").click();
		browser.textbox("search[email_contains]").setValue("spode");
		browser.submit("Search").click();
		browser.link("Delete").click();
		browser.button(" OK ").click();
	}
}