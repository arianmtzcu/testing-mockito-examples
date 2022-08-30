# Testing Mockito Examples
This project contains examples of unit tests using JUnit 5 and Mockito. The purpose of this project is to demonstrate how to effectively use Mockito for mocking dependencies in your unit tests.

## Project Structure
The project is organized as follows:
- **AssessmentServiceBasicTest.java**: Demonstrates basic unit testing without dependency injection.
- **AssessmentServiceConstructorInjectionTest.java**: Demonstrates unit testing using constructor-based dependency injection.
- **AssessmentServiceMockitoExtensionTest.java**: Demonstrates unit testing using Mockito's JUnit 5 extension for dependency injection. This approach is recommended for modern testing practices.

## Technologies Used
- **Java 11**: The project is built using Java 11.
- **JUnit 5**: For writing unit tests.
- **Mockito**: For mocking dependencies in the unit tests.
- **Lombok**: Used to reduce boilerplate code in the project.

## Running the Tests
To run the tests, use the following Maven command:
```bash
mvn test
```
This command will execute all the tests under the src/test/java directory.

## How to Use
You can explore the test cases in the project to see different ways to use Mockito for mocking dependencies and verifying interactions. These examples can be adapted to fit the specific needs of your project.

## Contributions
This project is open to contributions. If you'd like to collaborate, please open an issue or send a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/license/MIT) file for more details.
