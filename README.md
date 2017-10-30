# BestBuyExam
## Description

This demo uses Selenium Grid and the Page Obect Model to sastify the interview question requirements.
Selenium Grid uses a central hub and one or more nodes to execute webdirver tests. For this demo I have 
a single node configured to run with the Chrome driver. A node can be also be confgured for multiple 
driver versions, and has default versions as well.

The Page Object Model resolves the issue of having multiple test scripts witht the same code accessing the 
same html element. Now the code to access an element resides in a single class. As well, the logical 
abstraction of a page class allows for test design flexibility.

####  System Details

 * OS X El Capitan 10.11.5
 * Intellij Community 17.1
 * Java JDK / JRE 1.8.0_121
 * Selenium server Standaloe 3.0.6
 * Chrome / Firefox (optional)
 * Maven 3.5.0

## Installation and Running Instructions

* clone or download and unzip this repository
* open 3 terminals into and cd into the top level directory 

### Start as selenium as the hub
In a terminal window run:

 java -jar selenium-server-standalone-3.6.0.jar -role hub

### Register a node as chrome driver
In a terminal window run:

java -Dwebdriver.chrome.driver="chromedriver" -jar selenium-server-standalone-3.6.0.jar -role webdriver -hub   http://localhost:4444/grid/register -port 5566

#### To confirm the setup go to the grid console
   http://localhost:4444/grid/console
   
### To run
In a terminal window run:

 mvn clean test -Dbrowser=“chrome"

#### If you want to try firefox:

Stop the chrome node and restart with the gecko driver and run: 

java -Dwebdriver.chrome.geckodriver="geckodriver" -jar selenium-server-standalone-3.6.0.jar -role webdriver -hub http://localhost:4444/grid/gister -port 5566

## Issues:
Selenium was unable to populate the credit card cvv input field. It does appear in browser and can be manually edited, but debugging via Chrome Dev. Tools, it appears that it has no height dimension.
Since it provides an appropriate exception for this test; i simply left it blank.

## Design Decisions Explained:

I based my choices on two factors.
* Industry Acceptance
* The Learning Curve

The tools I picked have well established histories and large install bases, with alot of documentation to support them. 

QA / Testing teams members typically have a wide range of skills. The tools in this demo all have relatively easy learning  curves. There are many sophisticated test frameworks available, and they are well worth using; but prior to choosing these,
a team must make sure it has the capacity and resources required to master them.

### Selenium Grid:

Flexibility, re-use and deployment potential. 

In my example I set the the drive nodes to Chrome. This is because I only have my local box to work with. And I wanted to ensure a working example for exam purposes.

In the real world we could design a system that set up Selenium grid with multiple nodes on separate boxes. Using json config files we can easily insatiate remote nodes to specific browser versions.

For Dev/QA/Prod environments there are pre-existing Docker images configurable as per our requirements.

Depending on cloud services availability, this could all be wrapped up in deployment scripts using Terraform (AWS) or Kubectl config files (Kubernetes on the Google Cloud Platform) along with a CI / CD system such as Jenkins or Travis CI.

https://github.com/SeleniumHQ/docker-selenium

### Page Object Model

Allows for flexibility and easy refactoring because the code to access a given element resides in a single class.
The example code is meant to demonstrate this flexibility.  It uses the search box on the landing page.
It could easily be expanded to incorporate all of the categories on the best buy landing page.
Similarly, all of the Sections in the left pane could be there own page.

Essentially we could have a parent “navigation” class and derive from it several POM objects based on the different ways to navigate; as the user drills down to a specific product.

* Keyword
* Category
* Departments
* Brands

### Maven: 

It is simple and well understood. A suitable alternative would be to use grade builds.

### JUnit:

Industry standard annotations and assertions. A more sophisticated approach would be to use TestNG annotations. 
Particularly to remove the need for static methods, and to make use of the reporting mechanisms available in TestNg.


