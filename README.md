# Bill Manager

#### this is a simple application to manage bills and products. It also has an authentication service to authenticate the users and uses jwt to generate the tokens

## How to run the application with docker

* use mvn clean package to generate the jar files
* use docker-compose up -d build to run the application

## How to test the application

* enter to the url http://localhost:8585/Login with the user sotoleal and the password sotoleal123 just like this:
{
    "userName": "sotoleal",
    "password": "sotoleal123"
}
* then use the token to access to the userservice

