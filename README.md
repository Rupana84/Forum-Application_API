# Forum Application API

A Spring Boot REST API for a chat forum service.

## Features
- Manage channels (create, delete, list)
- Post and retrieve messages in channels
- Simple error handling with HTTP status codes

## Installation
1. Clone the repository:
   git clone https://github.com/Rupana84/Forum-Application_API.git
   cd Forum-Application_API

2.	Configure environment variables:
    export MYSQL_USERNAME=root
    export MYSQL_PASSWORD=YourSecurePassword
    export MYSQL_URL=jdbc:mysql://localhost:3306/forum_db

3.	Build and run the project:
    mvn spring-boot:run

4.API Endpoints

Channels
	•	GET /channels/ – Get all channels
	•	POST /channels/ – Create a new channel
	•	DELETE /channels/{id} – Delete a channel
	•	PUT /channels/{id} – Add a message to a channel

Messages
	•	GET /messages/{id} – Get a specific message
	•	POST /messages/ – Create a message
	•	PUT /messages/{id} – Update a message
	•	DELETE /messages/{id} – Delete a message

Technologies Used
	•	Java 17
	•	Spring Boot 3
	•	MySQL
	•	JPA/Hibernate
	•	Maven

License

This project is open-source and available under the MIT License.
