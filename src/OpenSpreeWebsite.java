// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class OpenSpreeWebsite {

	private Browser browser;
	public static final String SPREE_DEMO_URL = "http://0.0.0.0:3000";
	public OpenSpreeWebsite(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		this.browser.navigateTo(SPREE_DEMO_URL);
	}

	public void tearDown() throws Exception {
		if(browser.link("Logout").exists())
			browser.link("Logout").click();
	}

}