# To Get Started

## Pre-requisites

- Install JDK and Maven and set environment path
- Chrome or Firefox browsers installed
- Text Editor(Optional) installed-->Sublime/Visual Studio Code/IntelliJ IDEA Ultimate

## Setup Scripts

- Clone the repository into a folder
- Go inside the folder and run following command from terminal/command prompt
- this should install all the dependencies from pom.xml(Need to have Admin rights)

```
mvn clean install test
```
## Execution

- Cucumber Default report gets generated under target -->Cucumber-reports

  ![extensionscreen](src/artifacts/Execution_Pass.png)

## Reports

- Cucumber Default report gets generated under target -->Cucumber-reports

  ![extensionscreen](src/artifacts/Cucumber_reports.png)
  
## Highlights
- Page Object model implementation./n
- Mutibrowser support
- BDD with cucumber
- Data driven testing
- Cucumber report

## Future Improvements
- Can be integrated to CI CD pipeline
- Screenshots can be added for test failures
- Headless browser options can be included to run the scripts
- Can be containerised to  docker containers for easy portability
- Secured  credential management can be added to integrate
- More reusable methods can be written for common functionalities
