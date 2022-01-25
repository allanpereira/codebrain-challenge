# CodeBrain Challenge - API

---
This project contains the API to CodeBrain Challenge.

## Running tests

---

```shell
./gradlew test # Or `gradlew.bat test` on Windows
```

## Running application

---

```shell
./gradlew run # Or `gradlew.bat run` on Windows
```


## API endpoints

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