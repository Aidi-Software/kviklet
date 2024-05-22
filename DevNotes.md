Run all the containers:
```
docker-compose up -d
```

Only run the kviklet:
```
docker compose build --no-cache kviklet kviklet-postgres
docker-compose up -d kviklet kviklet-postgres 
```

#### Default login:
User: testUser@example.com
Password: testPassword

### Run frontend:
```
docker build --no-cache -t frontend .
docker run --name frontend -d -p 8888:8888 frontend

```
### Run backend:
```

./gradlew bootRun
```
