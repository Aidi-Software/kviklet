Run all the containers:
```
docker-compose up -d
```

### Run frontend:
```
docker build --no-cache -t frontend .
```
### Run backend:
```
./gradlew clean build
./gradlew bootRun
```