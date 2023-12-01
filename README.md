

# Spring Email Authentication System

This repository contains a robust email authentication system built with the Spring framework. The system ensures secure user authentication by sending a unique verification link to the user's email address. Leveraging Spring services, it provides a seamless and reliable authentication process.

## Features:

- **Email Verification**: Users receive a unique verification link via email for secure account activation.
- **Spring Security**: Utilizes Spring Security for handling authentication and authorization aspects.
- **Thymeleaf Templates**: Implements Thymeleaf templates for dynamic and visually appealing email content.
- **Service-Oriented Architecture**: Utilizes Spring services to modularize and manage different aspects of the authentication process.

## Project Structure:

- `src/main/java...`: Java source code for Spring components, controllers, services, and security configuration.
- `src/main/resources`: Configuration files, Thymeleaf templates(can be added), and application properties.
- `pom.xml`: Maven project configuration file.

## Getting Started:

1. Clone the repository: `git clone https://github.com/ztype764/Email_REST_Authernticator.git`
2. Configure the application.properties file with your email service provider details.
3. Build and run the project: `mvn clean install && java -jar target/your-app.jar`
4. Access the application at `http://localhost:8080`

## Usage:

1. Register a new account and provide your email address.
2. Check your email for the verification link and click to activate your account.
3. Login with your verified credentials.

Feel free to explore, modify, and integrate this Spring-based email authentication system into your projects. Contributions and feedback are highly encouraged!
