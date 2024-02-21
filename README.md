# GitHub Repositories API Wrapper

## Overview

This Spring Boot application serves as a wrapper for the GitHub API. It allows users to list all non-fork GitHub repositories for a given user, providing details such as the repository name, owner login, and branch information including branch names and the last commit SHA for each branch.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 11 or newer
- Gradle (for building the project)

## Setup Instructions

1. **Clone the Repository**

```bash
git clone https://github.com/Retardeded/githuboriginal.git
```
```bash
cd githuboriginal
```

2. **Build the Project**

```bash
./gradlew build
```

3. **Run the Application**
```bash
./gradlew bootRun
```

4. **Running Tests**
```bash
./gradlew test
```

5. **Enviroment variables**

The application uses environment variables for configuration, allowing easy adjustments for different deployment environments. Default values are provided in the `application.properties` file for running locally, but you may need to override them depending on your deployment setup.

### Default Environment Variables

```properties
# URL of the GitHub API
github.api.url=${GITHUB_API_URL:https://api.github.com}

# Server port
server.port=${SERVER_PORT:8080}
```

## API Usage

1. **List User Repositories Endpoint**

```bash
GET /api/repositories/{username}
```
Returns a list of non-fork repositories for the specified GitHub username, including repository name, owner login, and branches with their last commit SHA.

Example Request
```bash
curl -X GET "http://localhost:8080/api/repositories/<username>" -H "accept: application/json"
```

Example Success Response
```bash
[
  {
    "repositoryName": "SampleRepo",
    "ownerLogin": "Retardeded",
    "branches": [
      {
        "branchName": "main",
        "lastCommitSha": "abcdef1234567890abcdef1234567890abcdef12"
      }
    ]
  }
]
```

2. **Error Handling**
The application gracefully handles errors, such as a non-existing GitHub user, and returns appropriate HTTP status codes with descriptive messages.

Example Error Response
```bash
{
  "status": 404,
  "message": "User not found"
}
```

## This application meets the specified acceptance criteria by:

1. **Listing all GitHub repositories for a given user that are not forks.**
2. **Including the repository name, owner login, and detailed branch information.**
3. **Handling non-existing GitHub users by returning a 404 status code with a clear message.**
4. **Utilizing external configuration for the GitHub API URL and server port.**
