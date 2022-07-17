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


### Get product from name

curl -X GET \
http://localhost:8080/products/Dining%20Chair \
-H 'Postman-Token: e7c044aa-1ad8-4749-bf03-c21fb89513b0' \
-H 'cache-control: no-cache'


### Sell product

curl -X PUT \
http://localhost:8080/products/Dining%20Chair \
-H ': ' \
-H 'Content-Type: application/json' \
-H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
-H 'cache-control: no-cache'


curl -X PUT \
http://localhost:8080/products/Dining%20Table \
-H ': ' \
-H 'Content-Type: application/json' \
-H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' \
-H 'cache-control: no-cache'


