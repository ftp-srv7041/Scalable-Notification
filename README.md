# Scalable Notification System

This project is a scalable, modular backend system for sending Email,SMS,and In-App notifications. It is built using Spring Boot,RabbitMQ,and MySQL,and is structured 
with multiple independent modules for clean separation of concerns.

# Feature:

   Send notifications to users via Email,SMS, or In-App
   Asynchronous message processing using RabbitMQ
   Retry mechanism for failed notifications
   Modular consumer services (Email, SMS, Push)
   Priority-based processing (3 levels of queues)

# Modules Overview

| Module Name                     | Description                             |
|--------------------------------|-----------------------------------------|
|  notificationservice           | Main REST API gateway                   |
|  EmailConsumer                 | Consumes and sends email notifications |
|  SMSConsumer                   | Sends SMS using Twilio or similar API  |
|  PushNConsumer                 | Handles in-app or push notifications   |
|  NotificationProcessorPriority1/2/3  | Queue handlers for priority levels  |

# Tech Stack

* Java 17
* Spring Boot
* Maven
* RabbitMQ
* MySQL
* Docker

# Prerequisites

- MySQL server running with a database named `notification_db`
- RabbitMQ server running on port `5672` (management UI on `15672`)
- Java JDK 17 installed
- Optional: Postman to test APIs
