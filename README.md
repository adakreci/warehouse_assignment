## Prerequisites
Having installed java 11+, spring and maven 3.8.x


## Building and running the application
After cloning the git repository, build the project using the following (in the root of the project from terminal)
mvn clean install

## Run the application
Either run the application from Run Configurations or terminal with the command:
mvn spring-boot:run 

The server will run on port 8080.
The inventory and products will be loaded in the start.

### ------------------------------------------------------------------------------------------------------------------------------

### Get all available products

curl -X GET \
http://localhost:8080/products/ \
-H 'cache-control: no-cache'


### Get all items in inventory

curl -X GET \
http://localhost:8080/inventory/ \
-H 'cache-control: no-cache'


### Get product from name

curl -X GET \
http://localhost:8080/products/Dining%20Chair \
-H 'cache-control: no-cache'


### Sell product

curl -X PUT \
http://localhost:8080/products/Dining%20Chair \
-H 'Content-Type: application/json' \
-H 'cache-control: no-cache'


curl -X PUT \
http://localhost:8080/products/Dinning%20Table \
-H 'Content-Type: application/json' \
-H 'cache-control: no-cache'


