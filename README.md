# Nova Futur Code Challenge - Samuel Catalano
Technical Challenge

### How to compile application:
> `mvn clean install`

### Stack:
| Technology | Version |
|--|--|
| **Java** | "13.0.2" 2020-01-14 |
| **Spring Boot** | 2.2.5.RELEASE |
| **Project Lombok** | Stable |
| **JUnit** | 4/5 |

### How to run the application:
> Run via IDE (IntelliJ, Eclipse, NetBeans):
- Importing the project as Maven project on your favourite IDE.
- Build project using Java 11
- Run/Debug project from Main Application Class :: NovaFuturApplication

> Run via terminal:
- `mvn spring-boot:run`

> Run Tests:
- `mvn test`

### APIs:
> Collatz Conjecture - Iterative Version
* GET: http://localhost:8080/collatz/iterative?number=10

> Collatz Conjecture - Recursive Version
* GET: http://localhost:8080/collatz/recursive?number=10

> Graphics Point - Dot Product
* POST: http://localhost:8080/graphics-point/dot-product
> Body example

```javascript{
{
  "n": 3,
  "vectorA": [33,-5,74],
  "vectorB": [22,16,45]
}
```
> Feedback Calculation - Memory Cache Simulation
* GET: http://localhost:8080/feedback?number=123459876

**Docker**

Exists a Dockerfile prepared to download a OpenJDK 11 Slim and install the application.

- Run the command: `docker build -t novafutur/interview-test:release .`
- Run the command: `docker run -p port:port <IMG_TAG>`
- Example: `docker run -p 8080:8080 8fb870f41548`
- Or download the image `docker pull samueldnc/samuelcatalano:novafutur`
