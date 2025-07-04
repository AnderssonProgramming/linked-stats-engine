# Linked Stats Engine

This project implements a statistics engine that calculates the mean and standard deviation of a set of real numbers using a custom linked list implementation that complies with Java's Collections API and displays the results both in the terminal and via a web interface.

## Features

- Custom LinkedList implementation compliant with Java's Collections API
- File reader to load data from a text file
- Statistical calculations (mean and standard deviation)
- Command-line output of statistical results
- Web interface for viewing results with charts and tables (chart.js, bootstrap, thymeleaf)

## Design Description

The project follows the Model-View-Controller (MVC) architecture pattern:

- **Model**: Contains the custom linked list implementation and statistics calculator.
- **Service**: Provides functionality to read data from files.
- **Controller**: Handles web requests and serves the user interface.
- **View**: HTML/CSS/JS interface for displaying results.

### Class Diagram

```plaintext
+------------------+     +-------------------+     +-------------------+
| CustomLinkedList |     | StatisticsCalc.   |     | DataReaderService |
+------------------+     +-------------------+     +-------------------+
| -first: Node     |     | +calculateMean()  |     | +readDataFromFile()|
| -last: Node      |     | +calculateStdDev()|     +-------------------+
| -size: int       |     +-------------------+             |
+------------------+             |                         |
| +add()           |             |                         |
| +size()          |             |                         |
| +listIterator()  |             |                         |
+------------------+             |                         |
                                 |                         |
                        +--------v---------+      +--------v---------+
                        | StatisticsContr. |      | CommandLineRunner|
                        +-----------------+       +-----------------+
                        | +getStatistics() |      | +run()          |
                        +-----------------+       +-----------------+
                                |
                                |
                        +-------v------+
                        | Web Interface|
                        +--------------+
                        | index.html   |
                        +--------------+
```

## How to Run

1. Clone the repository
2. Navigate to the project directory
3. Run the project using Maven:

```bash
mvn spring-boot:run 
or
java "-Dserver.port=8080" -jar target\linked-stats-engine-0.0.1-SNAPSHOT.jar
```

4. Open a web browser and go to: [http://localhost:8080/](http://localhost:8080/)

- Screenshot functionality

![Webserver functionality](src/site/resources/webserver.png)

## Tests

The project includes tests to verify:

- Basic operations of the custom linked list
- Calculation of mean and standard deviation
- Processing of data from the test file

Run the tests using:

```bash
mvn test
```

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
