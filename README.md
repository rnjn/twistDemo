
Twist demo

This demo showcases automation testing features of Twist. The Application Under Test is a simple eCommerce website, which is created using the open source Apache Open for Business Project (ofbiz). I have used Selenium 2 for testing this website.

Prerequisites

Before you run the twist demo, you would need to install Apache ofbiz. The requirements for both Twist and ofbiz are
1. Java 1.6 JDK
2. Ant

Once you have downloaded ofbiz 10.04 from here (http://ofbiz.apache.org/download.html), just run the following commands in your favorite shell -

1. ant install
2. ant run-install-exttest
3. ant run

The third command above will start the website on http://localhost:8080/ecommerce by default. Please test if you are able to access this site before starting the demo.


Demo Setup

1. Clone the repo by running git clone https://github.com/rnjn/twistDemo.git <insert folder name>
2. Go inside the copied folder and run ant smoke 
3. If everything works, the setup for twistDemo is done - enjoy going through the example scenarios.
4. If some tests fail, its mostly a bad check-in, and I would be working on fixing it.
5. If all tests fail, there's something wrong with the setup.

Why Apache ofbiz?

Basically there are two main reasons for choosing Apache ofBiz -
1. Its really easy to install.
2. It works on the Windows, Ubuntu and Mac machines that I have tested on, without any noticeable differences.

Another added advantage is that metaphors used in the ecommerce world are fairly well known, so understanding the tests are not polluted by the domain under test.



 


