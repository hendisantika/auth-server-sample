# OAuth2 Authorization Server Sample

## Overview

This project is a sample implementation of an OAuth2 Authorization Server using Spring Boot. It demonstrates how to set
up a secure authentication and authorization server with custom realm validation for multi-tenant applications.

## Technologies

- Java 21
- Spring Boot 3.5.3
- Spring Security OAuth2 Authorization Server
- Gradle
- Lombok

## Features

- OAuth2 Authorization Server implementation
- Custom realm validation for multi-tenant support
- JWT token customization with realm claims
- PKCE (Proof Key for Code Exchange) support for enhanced security
- Form-based authentication for user login

## Prerequisites

- JDK 21
- Gradle

## Getting Started

### Building the Project

```bash
./gradlew build
```

### Running the Application

```bash
./gradlew bootRun
```

The server will start on port 8080.

### Configuration

The application is configured with:

- Default user: linh@company.com / pw
- OAuth2 client: pkce-client
- Supported realms: invoice-realm, order-realm, product-realm

## Usage Examples

### Authorization Request with Realm

```
GET /oauth2/authorize?response_type=code&client_id=pkce-client&scope=openid profile&redirect_uri=http://127.0.0.1:8081/login/oauth2/code/pkce-client&code_challenge=CHALLENGE&code_challenge_method=S256&realm=invoice-realm
```

## Project Structure

- `OAuth2ServerConfiguration.java`: Configures the OAuth2 authorization server
- `RealmValidator.java`: Implements custom realm validation
- `SessionBasedConfiguration.java`: Configures form-based authentication

## Additional Resources

- [Spring Authorization Server Documentation](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/)
- [OAuth 2.0 Specification](https://oauth.net/2/)

## Author

- Hendi Santika
- Contact: hendisantika@yahoo.co.id
- Telegram: @hendisantika34
