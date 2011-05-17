/**
 * Sahi - Web Automation and Test Tool
 *
 * Copyright  2006  V Narayan Raman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.sahi.command;

import java.io.IOException;
import net.sf.sahi.issue.JiraIssueCreator;
import net.sf.sahi.report.HtmlReporter;
import net.sf.sahi.report.JunitReporter;
import net.sf.sahi.report.TM6Reporter;
import net.sf.sahi.request.HttpRequest;
import net.sf.sahi.response.HttpResponse;
import net.sf.sahi.response.NoCacheHttpResponse;
import net.sf.sahi.session.Session;
import net.sf.sahi.session.Status;
import net.sf.sahi.test.SahiTestSuite;
import net.sf.sahi.util.BrowserType;
import net.sf.sahi.util.BrowserTypesLoader;

public class Suite {
	
    public void start(final HttpRequest request) {
    	if (request.getParameter("browserType") != null) {
    		startPreconfiguredBrowser(request);
    		return;
    	}
        Session session = request.session();
        String suitePath = request.getParameter("suite");
        String base = request.getParameter("base");
        String browser = request.getParameter("browser");
        String browserOption = request.getParameter("browserOption");
        String browserProcessName = request.getParameter("browserProcessName");
        String threads = request.getParameter("threads");
        String systemProxy = request.getParameter("useSystemProxy");
        Boolean useSystemProxy = false;
        if(systemProxy != null){
        	useSystemProxy = ("true".equals(systemProxy)) ? true : false;
        }
        launch(suitePath, base, browser, session.id(), 
        		browserOption, browserProcessName, threads, useSystemProxy, request);
        
    }
    
    public void startPreconfiguredBrowser(final HttpRequest request){
    	BrowserTypesLoader browserLoader = new BrowserTypesLoader();
    	BrowserType browserType = browserLoader.getBrowserType(request);
    	Session session = request.session();
        String suitePath = request.getParameter("suite");
        String base = request.getParameter("base");
        final int threads = getThreads(request.getParameter("threads"), browserType.capacity());
        
        // launches browser with pre configured browser settings
        if(browserType != null){
	        launch(suitePath, base, browserType.path(), session.id(), browserType.options(), 
	        		browserType.processName(), ""+threads, browserType.useSystemProxy(), request);
        }
    }

	private int getThreads(String threadsStr, int capacity) {
		int threads = 1;
        try {
        	threads = Integer.parseInt(threadsStr);
        } catch (Exception e) {
        }
        return (threads < capacity) ? threads : capacity;
	}
    
    private void launch(String suitePath, String base, String browser, 
    		String sessionId, String browserOption, String browserProcessName, 
    		String threadCapacity, boolean useSystemProxy, HttpRequest request){
    	final SahiTestSuite suite = new SahiTestSuite(net.sf.sahi.config.Configuration.getAbsoluteUserPath(suitePath),
    			base, browser, sessionId, browserOption, browserProcessName);
        int threads = 1;
        try {
            threads = Integer.parseInt(threadCapacity);
        } catch (Exception e) {
        }
        suite.setAvailableThreads(threads);
        suite.setUseSystemProxy(useSystemProxy);
        try {
			net.sf.sahi.config.Configuration.copyProfiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
        suite.setExtraInfo(request.getParameter("extraInfo"));
        suite.setInitJS(request.getParameter("initJS"));
        setReporters(suite, request);
        setIssueCreators(suite, request);
        new Thread(){
        	@Override
        	public void run() {
        		suite.run();
        	}
        }.start();
    }
    
    private void setIssueCreators(final SahiTestSuite suite, final HttpRequest request) {
        String propFile = request.getParameter("jira");
        if (propFile != null) {
            suite.addIssueCreator(new JiraIssueCreator(propFile));
        }
    }

    public HttpResponse status(final HttpRequest request) {
        Session session = request.session();
        Status status = session.getStatus();
        if (status == null) status = Status.FAILURE;
        return new NoCacheHttpResponse(status.getName());
    }

    private void setReporters(final SahiTestSuite suite, final HttpRequest request) {
        String logDir = request.getParameter("junit");
        if (logDir != null) {
        	if (!logDir.equals("")) logDir = net.sf.sahi.config.Configuration.getAbsoluteUserPath(logDir);
            suite.addReporter(new JunitReporter(logDir));
        }
        logDir = request.getParameter("html");
        if (logDir != null) {
        	if (!logDir.equals("")) logDir = net.sf.sahi.config.Configuration.getAbsoluteUserPath(logDir);
            suite.addReporter(new HtmlReporter(logDir));
        }
        logDir = request.getParameter("tm6");
        if (logDir != null) {
        	if (!logDir.equals("")) logDir = net.sf.sahi.config.Configuration.getAbsoluteUserPath(logDir);
            suite.addReporter(new TM6Reporter(logDir));
        }
    }

    public void kill(final HttpRequest request) {
        Session session = request.session();
        SahiTestSuite suite = SahiTestSuite.getSuite(session.id());
        if (suite != null) suite.kill();
    }
}
