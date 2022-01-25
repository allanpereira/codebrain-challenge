# CodeBrain Challenge

---

This project contains the API and the frontend application to CodeBrain Challenge.

## Requirements

---

- Docker - with docker compose installed

**OR**

- Java 11+
- MySQL 8+
- NodeJS

## Running

---
You can run this project using Docker containers - started with a single command using Docker Compose - or using a
development machine with Java, MySQL and NodeJS installed.

### Using Docker compose

```shell
docker-compose up
```

### Using a development machine

#### Start API

```shell
cd api
./gradlew run # Or `gradlew.bat run` on Windows
```

#### Start frontend

```shell
cd front
npm i
npm run dev
```

## API endpoints

---

### Salespeople

#### Create Salesperson

```shell
curl --request POST \
  --url http://localhost:8080/salespeople \
  --header 'Content-Type: application/json' \
  --data '{
    "name": "John Doe",
    "registration": "RG-85410"
  }'
```

#### Update Salesperson

```shell
curl --request PUT \
  --url http://localhost:8080/salespeople/:id \
  --header 'Content-Type: application/json' \
  --data '{
    "name": "Jane Doe",
    "registration": "RG-85411"
  }'
```

#### Remove Salesperson

```shell
curl --request DELETE \
  --url http://localhost:8080/salespeople/:id \
  --header 'Content-Type: application/json'
```

#### Get Salesperson

```shell
curl --request GET \
  --url http://localhost:8080/salespeople/:id \
  --header 'Content-Type: application/json'
```


#### Find Salesperson By Registration

```shell
curl --request GET \
  --url http://localhost:8080/salespeople?registration=:registration \
  --header 'Content-Type: application/json'
```


### Products

#### Create Product

```shell
curl --request POST \
  --url http://localhost:8080/products \
  --header 'Content-Type: application/json' \
  --data '{
    "name": "Test Product",
    "price": "999",
    "imageUrl": "https://via.placeholder.com/150"
  }'
```

#### Update Product

```shell
curl --request PUT \
  --url http://localhost:8080/products/:id \
  --header 'Content-Type: application/json' \
  --data '{
    "name": "Test Product Updated",
    "price": "444",
    "imageUrl": "https://via.placeholder.com/150"
  }'
```

#### Remove Product

```shell
curl --request DELETE \
  --url http://localhost:8080/products/:id \
  --header 'Content-Type: application/json'
```

#### Get Product

```shell
curl --request GET \
  --url http://localhost:8080/products/:id \
  --header 'Content-Type: application/json'
```


#### Find Product By Id

```shell
curl --request GET \
  --url http://localhost:8080/products?id=:id \
  --header 'Content-Type: application/json'
```


#### Find Product By Name

```shell
curl --request GET \
  --url http://localhost:8080/products?name=:name \
  --header 'Content-Type: application/json'
```


### Sales

#### Create a Sale

```shell
curl --request POST \
  --url http://localhost:8080/sales \
  --header 'Content-Type: application/json' \
  --data '{
      "salespersonId": 2,
      "products": [
        {
          "id": 1,
          "quantity": 1
        },
        {
          "id": 2,
          "quantity": 2
        }
      ]
    }'
```