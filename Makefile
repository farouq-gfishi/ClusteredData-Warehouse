# Default target
all: build run

# Target to clean and build the project using Maven
build:
	mvn clean install

# Target to start the application using Docker Compose
run:
	docker-compose up

# Target to stop the running containers
stop:
	docker-compose down

# Target to clean up artifacts
clean:
	mvn clean

# Target to run unit tests
test:
	mvn test

# Target to show help (list available targets)
help:
	@echo "Available targets:"
	@echo "  build         : Clean and build the project using Maven"
	@echo "  run           : Start the application using Docker Compose"
	@echo "  stop          : Stop the running containers"
	@echo "  clean         : Clean up artifacts"
	@echo "  test          : Run unit tests"
	@echo "  help          : Show this help message"

