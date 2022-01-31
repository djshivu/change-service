# change-service

## Reference Documentation

### BitBucket URL: https://github.com/djshivu/change-service.git

### Service Documentation(Swagger URL) : http://localhost:8080/change-service/swagger-ui.html

### Endpoint: http://localhost:8080/change-service/change

### Softwares/Tools/Plugins/Libraries Used:
###Java 11
###Spring Boot 2.6.3
###Maven 3.8.4
###SonarLint(Eclipse Plugin) - For code quality
###EclEmmaEclipse Plugin) - For code coverage
###Logback - For Logging
###OpenAPI/Swagger - Documenting the service 
###PostMan - To test Rest API's

### Needs to be improved
1) Adding headers to the service. RequestId, AuthTokens etc..
2) Adding security to the service
3) Logic to get the change should be improved for better performance.
4) Code coverage needs to improved to meet > 80%
5) Yet to resolve few SonarLint code quality issues.
6) Need to use database for maintaining the coin availability status and to handle the transactional status.

### Assignment
Create a Spring Boot service that exposes a REST API that allows a user to request change for a given bill. The service should assume there are a finite number of coins. 

Requirements:
• Available bills are (1, 2, 5, 10, 20, 50, 100)
• Available coins are (0.01, 0.05, 0.10, 0.25)
• Start with 100 coins of each type
• Change should be made by utilizing the least amount of coins
• API should validate bad input and respond accordingly
• Service should respond with an appropriate message if it does not have enough coins to make change
• The service should maintain the state of the coins throughout the transactions
• Deliver the code with test cases
• Upload your code to Github and come to interview prepared to walk through code

Bonus:
• Allow for number of coins to be configurable and easily changed
• Allow for the user to request for the most amount of coins to make change
