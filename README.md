# About the project:
SwvlNotificationService is a microservice spring-boot application 
which is responsible for sending notifications for SWVL customers.
It is exposing two APIs one for sending personalized notification and the other one for grouped notification
- **personalized notification API:** It receives requestDTO and it builds up the final message 
that should be sent to the customer, then it sends it to one of the 
Apache Kafka topics: PERSONALIZED_PUSH_NOTIFICATION , SMS_NOTIFICATION.
The messages sent to SMS_NOTIFICATION topic will be **partially** consumed by the application **periodically** 
per the **configured time limit per SMS provider**

- **grouped notification API:** It pretty much does the same as personalized notification API
except for it receives user groups ids and it retrieves the related users then it sends it to one of the 
Apache Kafka topics: GROUPED_PUSH_NOTIFICATION , SMS_NOTIFICATION.     

`Note: if the SMS provider limited to receive 60 requests per minute:
 our consumer will pull messages every 30 seconds and will consume only **60/2/number Of Nudes** messages`

# Used Technologies: 
- **Spring-Boot:** For exposing APIs and modifying the DB 
- **H2** For in-memory DB 
- **Apache Kafka** For messaging 

# ERD: 
![Alt text](ERD.PNG?raw=true "ERD")

# Exposed APIs: 
- **Path:** /send-notification/personalized 

     - **Method:** Post 
 
     - **Description:** this method is responsible for sending specific users
     
     - **body:** ``` {
                         "templateId": "1", // id of notification template
                         "typeId": "1", // id of notification type : sms or pushed-notification
                         "templateVars":  { // valus for vars in the notification template
                                           "{promo-code}": 123456
                                         },
                         "receivers":[1,2] // ids for users that will recive the notification
                     }``` 
                     
- **Path:** /send-notification/personalized   
     - **body:** ``` {
                         "templateId": "2", // id of notification template
                         "typeId": "2", // id of notification type : sms or pushed-notification
                         "receivers":[1,2] // ids for groups that will recive the notification
                     }``` 
                     

# Putting Project on rails:
For running the project
- download the repo
- run the command  : **mvnw clean package -DskipTests && docker-compose up**

~~you can run: mvnw clean package && docker-compose up for not skipping unit test~~

