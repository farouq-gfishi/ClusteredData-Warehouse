# Clustered Data Warehouse for FX Deals at Bloomberg

## Table of Contents

1. [Project Overview](#project-overview)
2. [Request Logic](#request-logic)
3. [System Requirements](#system-requirements)
4. [Implementation Details](#implementation-details)
   - [Database Selection](#database-selection)
   - [Deployment](#deployment)
   - [Project Structure](#project-structure)
   - [Error Handling](#error-handling)
   - [Logging](#logging)
   - [Unit Testing](#unit-testing)
   - [Documentation](#documentation)
   - [Makefile](#makefile) 
5. [Steps to Run the Application](#steps-to-run-the-application)
6. [Conclusion](#conclusion)

---

## 1. Project Overview <a id="project-overview"></a>

The project involves developing a data warehouse system for Bloomberg to analyze FX deals. The system's primary task is to accept FX deal details and persist them into a database. The system should handle validation, prevent duplicate imports, and ensure all imported rows are saved in the database without rollback capabilities.

## 2. Request Logic <a id="request-logic"></a>

### Request Fields:
- Deal Unique Id
- From Currency ISO Code
- To Currency ISO Code
- Deal timestamp
- Deal Amount 

### Validation:
- Validate row structure for missing fields and type/format errors.

### Import Deduplication:
- Ensure the system does not import the same request twice.

### No Rollback:
- All imported rows must be saved in the database without rollback capabilities.

## 3. System Requirements <a id="system-requirements"></a>

- Use actual database (PostgreSQL, MySQL, or MongoDB).
- Docker Compose for sample deployment.
- Maven or Gradle project structure.
- Proper error/exception handling.
- Logging of system activities.
- Unit testing with sufficient coverage.
- Markdown documentation.
- GitHub repository with Makefile for streamlined application execution.

## 4. Implementation Details <a id="implementation-details"></a>

### Database Selection <a id="database-selection"></a>

MySQL will be used for its relational capabilities and robustness in handling transactions.

### Deployment <a id="deployment"></a>

Docker Compose will be utilized for containerized deployment of the application and database.

### Project Structure <a id="project-structure"></a>

The project will follow a standard Maven project structure with modules for service, persistence, and testing.

### Error Handling <a id="error-handling"></a>

Exceptions will be defined for different error scenarios.

### Logging <a id="logging"></a>

Logging will be implemented using SLF4J for capturing application events and errors.

### Unit Testing <a id="unit-testing"></a>

JUnit will be used for unit testing, ensuring comprehensive test coverage across service and persistence layers.

### Documentation <a id="documentation"></a>

Documentation will be provided in Markdown format, covering project overview, architecture, setup instructions.

### Makefile <a id="makefile"></a>
The Makefile provides a streamlined way to manage common tasks related to building, testing, and running the application. Here are the available targets:

- **build**: Cleans any previously compiled files, compiles the project, runs tests, and packages the application into a JAR file.
    
    ```bash
    make build
    ```

- **run**: Starts the application using Docker Compose, ensuring all necessary containers are up and running.
   
   ```bash
    make run
    ```
    
- *stop**: Stops the running containers.
    
    ```bash
    make stop
    ```

- **clean**: Cleans up artifacts generated during the build process. 
    
    ```bash
    make clean
    ```

- **test**: Runs unit tests to ensure the application behaves as expected.
    
    ```bash
    make test
    ```

- **help**: Displays a list of available Makefile targets for reference.
    
    ```bash
    make help
    ```

## 5. steps to Run the Application <a id="steps to Run the Application"></a>

To run the application: 
 **Using Markfile**: This command will clean any previously compiled files, compiles the project, runs tests, and packages the application into a JAR file. And then will start the application using Docker Compose, ensuring all necessary containers are up and running.

   ```bash
   make all
   ```

## 6. Conclusion <a id="conclusion"></a>

This document outlines the design and implementation plan for the clustered data warehouse project for Bloomberg FX deals analysis. By adhering to the outlined requirements and utilizing modern development practices, the project aims to deliver a robust and scalable solution.
