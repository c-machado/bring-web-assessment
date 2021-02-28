# bring-assessment
TestSuite for the search of a flight with the fare value with variable passengers and dates. 
This project has been built using Java, Cucumber, Selenium and TestNG.
 All the expected behaviours have been documented using the Gherkin language. 

## What do you need?
In order to run this suite you'll need:
1. Java (v.11.0.2)
1. Maven to build and run the suite (v.3.6.0)

If you're unsure which Java version you have in your machine, please run
the following command in your terminal: 
```
$ java -version 
```
Same goes for maven:
```
$ mvn -v 
```
If your Java or Maven installations are outdated, or you don't have them in your machine, 
you can always install or update them via Homebrew,
using the following commands:

```
$ brew cask install java 
$ brew install maven
```

## How to run this suite?
1. Clone this [repository](https://github.com/c-machado/bring-web-assessment) 
in your local machine, if you run into any issues about authentication try to clone it using
the [Http](https://github.com/c-machado/bring-web-assessment.git) option
1. Navigate to **bring-web-assessment** folder in your terminal
1. Run the following command:
```
mvn clean install test -DsuiteXmlFile=testng.xml 
```
1. If you've already run it for the first time, and want to run it again, you
just need to run this command:
```
mvn test -DsuiteXmlFile=testng.xml 
```


## Any troubles?

If you run into any issues while trying to execute this suite, please feel 
free to contact me at **dcmachadou@gmail.com**, I'll be happy to take a look at it.   

Thank you! 
:sunglasses:
