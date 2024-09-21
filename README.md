# Expense Tracker Microservice

This project is an **Expense Tracker** microservice that helps manage and monitor personal finances efficiently. It is designed using a microservices architecture and includes RESTful APIs for data interaction. The microservices communicate with each other through a service registry, and an API Gateway is configured for load balancing and API request management.

## Features

- **Microservice-based architecture**: Each component is a standalone service, promoting scalability and flexibility.
- **Expense tracking**: Provides a set of RESTful APIs to create, read, update, and delete expense records.
- **API Gateway**: Manages API requests and optimizes performance with load balancing.
- **Service registry**: Ensures efficient communication between microservices.
- **PostgreSQL integration**: Uses PostgreSQL for persistent and efficient data storage.
- **Dockerized deployment**: Each service is containerized with Docker for easy deployment and scaling.

## Tools & Technologies

- **Spring Boot**: Microservice framework for building RESTful APIs.
- **PostgreSQL**: Database for storing and managing expense data.
- **Docker**: Containerization of services for simplified deployment.
- **API Gateway**: Centralized entry point for managing and routing API requests.
- **Service Registry (Eureka/Consul)**: Manages microservice discovery and communication.
- **Load Balancing**: Distributes incoming API requests for improved performance and reliability.

## Architecture

This project is composed of multiple microservices working together:

1. **Expense Service**: Manages expense-related operations, such as creating, updating, and deleting expenses.
2. **API Gateway**: Routes API requests to the correct microservice and handles load balancing.
3. **Service Registry**: Maintains a list of active services for dynamic discovery and communication.

Each microservice is deployed within its own Docker container, ensuring they are isolated and scalable independently.

## Installation

Follow these steps to set up the Expense Tracker Microservice:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/expense-tracker-microservice.git
   cd expense-tracker-microservice
