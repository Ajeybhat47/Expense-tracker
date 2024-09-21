# Expense Tracker Microservice

This is a **personalized microservice-based application** for farmers to efficiently manage and track work tasks, associated workers, and expenses. It helps monitor the day-to-day activities of workers, track completed tasks, and store expense information. The system is composed of several microservices, each responsible for distinct features such as day tracking, worker management, and configuration.

## Features

- **Microservice-based architecture**: Allows each service to be independently developed, deployed, and scaled.
- **Day Tracking Service**: Tracks the work done by a worker on a particular day.
- **Worker Management**: Manages the workers, their tasks, and performance.
- **Expense Tracking**: Provides the ability to log and monitor daily expenses for workers and tasks.
- **API Gateway**: Centralized entry point for handling API requests across microservices.
- **Config Server**: Centralized configuration management for all microservices.
- **Service Registry**: Dynamic discovery of services, ensuring smooth communication between microservices.
- **PostgreSQL integration**: All microservices use PostgreSQL as the database for data persistence.
- **Dockerized deployment**: Easy deployment with Docker, enabling each microservice to run in its container.

## Microservices Overview

1. **ConfigServer**: Provides centralized configuration management for the entire system.
2. **DayTrackingms**: Manages the records of workers and the tasks they perform on specific days.
3. **Workerms**: Handles the management of worker profiles, messaging, and tracking of assigned work.
4. **Workms**: Stores information about different work activities and provides APIs for creating, updating, and managing these activities.
5. **API Gateway**: Routes requests to appropriate microservices while ensuring load balancing and performance optimization.
6. **Service Registry**: Facilitates service discovery, allowing microservices to communicate seamlessly.
