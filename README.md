Busy Call Notifier
Project Overview

Busy Call Notifier is a Spring Boot backend system designed to detect and notify users when multiple callers attempt to reach the same person simultaneously, preventing call conflicts. Unlike traditional busy signals, this system notifies the receiver who tried calling, ensuring no missed information.

Key Features

Notify the receiver about all callers who tried calling while they were busy.

Handle simultaneous call attempts between multiple users.

Only active ringing users see current callers.

Supports clearing state after a call to prevent memory issues.

Built with Spring Boot, REST APIs, H2 in-memory database, and OOP principles.

Tech Stack

Java 21

Spring Boot 3.x

Spring Data JPA for ORM

H2 Database for in-memory storage

RESTful APIs for call registration and notifications

Architecture
Caller (UserA/UserB)
       |
       v
   -------------------
   |    UserC        |
   |  State: RINGING |
   -------------------
       ^
       | Notifications of parallel attempts
       |
  Other Callers

User.java – Stores name, phone number, and state.

UserState.java (enum) – FREE, RINGING, IN_CALL.

CallService.java – Handles call logic, active calls, and notifications.

CallController.java – REST APIs: /call, /notifications, /clear.

API Endpoints
Endpoint	Method	Description	Params
/api/call	POST	Make a call from one user to another	callerName, callerNumber, receiverName, receiverNumber
/api/notifications	GET	Get list of users who tried to call while receiver was busy	receiverName, receiverNumber
/api/clear	POST	Clear active call and notifications	receiverName, receiverNumber
Usage Example

UserA calls UserC:

POST http://localhost:8080/api/call?callerName=UserA&callerNumber=111&receiverName=UserC&receiverNumber=333
Response: UserC is now ringing by UserA

UserB calls UserC while UserA is ringing:

POST http://localhost:8080/api/call?callerName=UserB&callerNumber=222&receiverName=UserC&receiverNumber=333
Response: UserC is busy. Attempt recorded from UserB

UserC checks notifications:

GET http://localhost:8080/api/notifications?receiverName=UserC&receiverNumber=333
Response: [{ "name": "UserB", "phoneNumber": "222", "state": "FREE" }]

Clear call after completion:

POST http://localhost:8080/api/clear?receiverName=UserC&receiverNumber=333
Response: Cleared notifications and active call for UserC
Design Decisions

Used ConcurrentHashMap for thread-safe active calls.

Used HashSet for unique parallel callers.

Enum for user states ensures type safety.

Separation of Controller and Service for modularity.

Future Improvements

Add real-time notifications using WebSocket.

Persist logs in production-grade database (MySQL/Postgres).

Build a frontend for live call simulation.
