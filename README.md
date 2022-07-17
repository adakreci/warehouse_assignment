## Prerequisites
Having installed java 11+, spring and maven 3.8.x


## Building the application
After cloning the git repository, build the project using the following
mvn clean install

## Run the application
Either run the application from Run Configurations or terminal with the command:
mvn spring-boot:run 

The server will run on port 8080.
The inventory and products will be loaded in the start.


### Get all available products

curl -X GET \
http://localhost:8080/products/ \
-H 'Postman-Token: e7c044aa-1ad8-4749-bf03-c21fb89513b0' \
-H 'cache-control: no-cache'


### Get all items in inventory

curl -X GET \
http://localhost:8080/inventory/ \
-H 'Postman-Token: e7c044aa-1ad8-4749-bf03-c21fb89513b0' \
-H 'cache-control: no-cache'