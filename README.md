<div align="center">
<h2 align="center">CVQS Car Defect App</h2>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#running-locally">Running Locally</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

CVQS App is a backend application created for Toyota to facilitate defect recording
by terminal operators for detecting potential issues in vehicles manufactured in factories,
and to streamline the listing of these defects by team leaders. This project is built using
Spring Boot, Spring Security with JWT Authentication, Spring Data JPA, and Hibernate with
PostgreSQL. Additionally, Docker Compose is used to run the project.

<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

* [Java 17](https://www.java.com/tr/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Hibernate](https://hibernate.org/)
* [Log4j2](https://logging.apache.org/log4j/2.x/)
* [Docker](https://www.docker.com/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Running Locally

This project is a [Spring Boot](https://spring.io/projects/spring-boot) application
built using [Maven](https://spring.io/guides/gs/maven/). You can clone it and run 
it locally on your machine as shown below or using an IDE like Intellij idea.

   ```sh
   $ git clone https://github.com/tastekinselcuk/32bit_CVQS_App
   $ cd springApp
   $ ./mvnw spring-boot:run
   ```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

You can try it locally or on postman.

***Here are some of the endpoints:***

### {POST} Register `/api/auth/register` 
* Send `username` and `password` as `Json`.
* Return type is json containing JWT token.
### {POST} Login `/api/auth/authenticate`
* Send `email`, `password`, `name`, `surname`  as `Json`.
* Return type is json containing JWT token.


<p align="right">(<a href="#top">back to top</a>)</p>
