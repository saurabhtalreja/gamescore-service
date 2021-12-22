### Overview
* This service fetches topScorers in a game.
* In-Memory DB H2 is used
* Seeding is done through data.sql in resources folder
* Global Exception Handler catches all exception but can be extended
* 8 Testcases- 1 Integration and several others are written in test folder

### API DEFINITION

* GET **/rest/api/topScorers?count=4** fetches by default 5 top scorers, otherwise can be specified with count query param
* GET **/rest/api/score/{name}** fetches score by name
* POST **/rest/api/createUpdateScore** create user and its score, or if user present updates score
* DELETE **/rest/api/deleteUserScore/{name}** deletes user & its score by name


### Running

This project requires docker to run, please consult the docker documentation on installing 
to your operating system. After docker has been installed, proceed with the following commands:

**Build the spring boot artifact first** `./mvnw clean package`

**Build the docker images** `docker-compose build`

**Launch the docker images** `docker-compose up`