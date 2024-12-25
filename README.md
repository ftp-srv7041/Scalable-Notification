# Scalable Notification System

This repository contains a **scalable notification system** designed to handle email, SMS, and push notifications efficiently. The system prioritizes messages using **Apache Kafka** for event streaming, **Redis** for caching, and integrates with third-party providers such as **SendGrid** for email and **Twilio** for SMS.

![diagram-export-12-25-2024-3_06_45-PM](https://github.com/user-attachments/assets/78bd8e2b-84df-4dc0-ac5f-94df24c40e23)



## Introduction

Building a robust and scalable notification system is essential for modern applications to deliver real-time updates and improve user engagement. This project demonstrates an end-to-end notification system that processes messages with priority-based handling across multiple services. The design emphasizes:
- Scalability for high-throughput systems.
- Modularity with independent services for different notification types.

For a comprehensive breakdown of the system's architecture, design considerations, implementation and all the features of our system, please refer to blog post:
[Designing and Implementing a Scalable Notification System](https://puneetchhabra.hashnode.dev/designing-and-implementing-a-scalable-notification-system).

Additionally, you can check out the [**system design**](https://www.youtube.com/watch?v=OhnXFvw1Gl8) and [**project demonstration video**](https://www.youtube.com/watch?v=UB79MBRyXrQ) for a better understanding of the architecture and how the system operates in real-world scenarios.

---
## Features

- **Notification API**: Allows creating and managing notifications across multiple channels.
- **Multi-Channel Support**: Email, SMS, and Push Notifications, with the flexibility to add more.
- **Priority-Based Queuing**: Dynamically processes notifications based on priority levels (Transactional, Informational, Promotional).
- **User Preferences**: 
  - Opt-in/out of specific channels or categories (e.g., disable promotional SMS).
  - Set quiet hours for each channel.
- **Duplicate Prevention**: Ensures no duplicate notifications are sent using a `UNIQUE` DB constraint and a `message_hash` for consistency.
- **Templating**: Supports placeholders like `{name}` and `{otp}` for dynamic message personalization.
- **Rate Limiting**: Handles API rate limits for third-party vendors efficiently.
- **Scalability**: Designed for high-throughput systems with asynchronous processing to avoid blocking users.
---

## Configuration

### 1. Application Properties

Each service requires its own configuration file to connect to Kafka, Redis, and other resources. Update the [application.properties](http://application.properties) file in these services:
- **notificationservice**
- **NotificationProcessorPriority1**
- **NotificationProcessorPriority2**
- **NotificationProcessorPriority3**
- **EmailConsumer**
- **SmsConsumer**
- **PushNConsumer**

Ensure you provide the correct Kafka and Redis connection details for your environment.

---

### 2. Email Configuration

Update the following credentials in the file:
```
com/puneetchhabra/EmailConsumer/service/EmailService.java
```
- `SENDGRID_API_KEY`
- `your_sendgrid_verified_sender_email`

---

### 3. SMS Configuration

Update the following credentials in the file:
```
com/puneetchhabra/SMSConsumer/service/SmsService.java
```
- `ACCOUNT_SID`
- `AUTH_TOKEN`
- `TWILIO_PHONE_NUMBER`

---

### 4. Push Notifications

Push notifications currently return a mock response. The logic is implemented in the `PushNService` class. You can extend this to integrate with an external push notification provider.

---

## Database Configuration

This project uses **MySQL** running on `localhost` as the database. To set up the database:

1. Create a new SQL database for the project as per the schema design.
2. You can take help from the Sql Commands file.

**Note:** It is highly recommended to recheck the SQL commands to ensure they align with the current schema design before executing them.
![Schema Design](https://github.com/user-attachments/assets/4718a09d-3615-41c7-b333-23aa00c6b221)


---

## System Requirements

1. **Kafka Server**: This project uses Confluent Cloud for Kafka. Update configurations if you're using a different Kafka setup.
2. **Redis**: A managed Redis Cloud instance is used. Update configurations for your Redis instance.
3. Ensure all ports configured in the services are available.

---

## Getting Started

1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd NotificationSystem
   ```

2. Update configurations in `application.properties` and service classes as per your setup.

3. Build and run all services:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Monitor the system logs to verify the priority-based processing of messages across services.


