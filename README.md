# Forum

This forum application was developed using Spring Boot and REST, you can register, update, search and delete users, posts and messages using MySQL Database.
Some unit tests were made too using JUnit.

# Maven Dependencies

To run this application, you will need these dependencies in your pom.xml:

    -org.springframework.boot
    -com.fasterxml.jackson.core
    -org.springframework.boot
    -org.projectlombok
    -com.h2database
    -junit
    -mysql
    
# Stack dependencies
   
    -mysql
    
# Docker

If you want to run MySQL database at docker, do as follow:
    -docker pull mysql
    -docker run --name mybanco -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql






