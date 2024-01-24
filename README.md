# Gamer Directory Service

A service to manage directory of games, gamers and their credits to individual games.

## Technologies used
- Java 21
- Spring Boot 3.2.2
- Spring Data JPA
- PostgreSQL 16.1
- Maven 3.9.5
- Lombok
- MapStruct 1.5.5.Final
- Swagger
- Docker Engine 24.0.6 & Docker Compose 2.23.0

## Deployment Instruction
The easiest way to deploy and run the application is to:
- Install `Docker` on the host machine.
- Clone the repository and navigate to that cloned directory.
- Run '`docker compose up -d`' from inside the directory.
- Wait for the completion of the above instruction.
- Go to the [Swagger UI](http://localhost:8080/swagger-ui/index.html) and navigate the APIs as per the description provided. **The database is initialized with a sample dataset when the application starts**. So, the Query APIs should return some valid data to get started with. The descriptions of the APIs are also made to have sufficient information so that the test can be performed easily.
- The postgres database can also be browsed via the [`adminer` GUI](http://localhost:8888). Use the following values to login to the `adminer` application:
   - System: PostgreSQL
   - Server: postgres
   - Username: admin
   - Password: admin
   - Database: gamers

**NOTE:** The application container is built and run on the host port `8080` (container port `8080` is mapped to the same on host) automatically. `Postgres 16.1` database container will also run on host port `5432`. Adminer container is also added so that the database can be navigated via its GUI. Its port is mapped on the host at `8888`.

## Assumptions made
- Max Credit API needs to return Games with Top Scorer (i.e. gamers with maximum credit) based on their interest level. Two things are noteworthy here:
   - No credits may be awarded to a particular game at all.
   - A gamer can be awarded credits for a game in which s/he didn't show any interest during enrollment.
  
  Because of this, the API will not return any games that have not received any credits. The credits that have been awarded to a gamer without their interests will be considered and categorized under an `UNKNOWN` interest level for the game.

### Improvements to be done
- Unit/Integration Tests
- Performance Tests
- Database Migration Tools (`Flyway`/`Liquibase`)
- Logging
- Gamers' interest levels and geography are currently hard coded. We should move these into the database and expose APIs to manage these.
