# BestBuyExam
## Description

This demo uses Selenium Grid and the Page Obect Model to sastifies the interview question requirements.
Selenium Grid uses a central hub and one or more nodes to execute webdirver tests. For this demo I have 
a single node configured to run with the Chrome driver. A node can be also be confgured for multiple 
driver versions, and has default versions as wel.

The Page Object Model resolve the issue of having multiple test scripts witht the same code accessing the 
same html element. Now the code to access an element resides in a single class. As well, the logical 
abstraction of a page class allows for convenient test design flexibility.

####  System Details

 * OS X El Capitan 10.11.5
 * Intellij Community 17.1
 * Java JDK / JRE 1.8.0_121
 * Selenium server Standaloe 3.0.6
 * Chrome / Firefox (optional)
 * Maven 3.5.0

## Installation and Running Instructions

* clone or downlaod and unzip this repository
* cd into the top level directory 

### Start as selenium as the hub

 java -jar selenium-server-standalone-3.6.0.jar -role hub

### Register a node as chrome driver

java -Dwebdriver.chrome.driver="chromedriver" -jar selenium-server-standalone-3.6.0.jar -role webdriver -hub   http://localhost:4444/grid/register -port 5566

run
 mvn clean test -Dbrowser=“chrome"

#### To confirm go to the grid console
http://localhost:4444/grid/console


*** if you want to try firefox

Then stop the chrome node and restart twist the gecko driver

run 
java -Dwebdriver.chrome.geckodriver="geckodriver" -jar selenium-server-standalone-3.6.0.jar -role webdriver -hub http://localhost:4444/grid/gister -port 5566



mvn clean test -Dbrowser=“firefox"


## Design Decisions Explained:

The following is a list of the choices I made for this exam with justifications and possible alternatives where appropriate.

### Selenium Grid:

Flexibility, re-use and deployment potential. 

In my example I set the the drive nodes to Chrome. This is because I only have my local box to work with. And I wanted to ensure a working example for exam purposes.

In the real world we could design a system that set up Selenium grid with multiple nodes on separate boxes. Using json config files we can easily insatiate remote nodes to specific browser versions.

For Dev/QA/Prod environments there are pre-existing Docker images configurable as per our requirements.

Depending on cloud services availability, this could all be wrapped up in deployment scripts using Terraform (AWS) or Kubectl config files (Kubernetes on the Google Cloud Platform) along with a CI / CD system such as Jenkins or Travis CI.

https://github.com/SeleniumHQ/docker-selenium

### Page Object Model

Allows for flexibility and easy refactoring

The example code is meant to demonstrate this flexibility. 
It use the search box on the landing page.

It could easily be expanded to incorporate all of
the categories on the best buy landing page.

Similarly, all of the Sections in the left pane could be there own page.

Essentially we could have a parent “navigation” class
and derive from it several POM objects based on the different ways to navigate as the user drill down to a specific product.

 1.) by keyword
 2.) by category
 3.) Departments
 4.) Brands

### Maven: 

It is simple and well understood. A suitable alternative would be to use grade builds.

### JUnit:

Industry standard annotations and assertions. 

A more sophisticated approach would be to use TestNG annotations. 

Particularly to remove the need for static methods, and to make use of the reporting mechanisms available in TestNg.

## Issues:
// the cvv box appears in the browser, but its syntax has no height
I left it blank a it provide an exception




——-
register phantomjs to hub

"/path/phantomjs.exe --webdriver=5567--webdriver-selenium-grid-hub=http://127.0.0.1:4444"  - donot use host name , or "localhost" , use only IP

use same verison of jars for hub and node machines ( EX: 2.37)

then use

WebDriver driver = new RemoteWebDriver("http://IP of the node machine:5567/wd/hub",DesiredCapabilities.phantomjs());
