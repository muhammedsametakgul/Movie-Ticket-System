# Movie Ticket Booking System

## Overview

The Movie Ticket Booking System is a web application developed using Spring Boot. This project aims to provide users with an easy way to book movie tickets online. It includes features like user authentication, ticket booking, email notifications, and more.

## Technologies Used

- **Spring Boot**: Framework for building Java-based applications.
- **Spring Security**: For securing the application.
- **Maven**: Dependency management.
- **Postman**: For API testing.
- **Docker**: Containerization of the application.
- **Redis**: Caching mechanism.
- **RabbitMQ**: Message broker for asynchronous communication.
- **MySQL**: Database management.
- **Email Sender**: For sending email notifications.
- **PDF Converter**: For generating PDF tickets.
- **Liquibase**: Database schema management.

## To check redis cache
For Redis Cache : <br>
docker run --name redis-cache -d -p 6379:6379 redis <br>
docker exec -it redis-cache redis-cli<br>
keys *

## Setup and Installation

### Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

### Clone the Repository

```bash
git clone https://github.com/muhammedsametakgul/Movie-Ticket-System.git
cd movie-ticket-booking-system
