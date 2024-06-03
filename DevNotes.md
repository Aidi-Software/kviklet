Run all the containers:
```
docker-compose up -d
```

Only run the kviklet with postgres:
```
docker compose build --no-cache kviklet kviklet-postgres
docker-compose up -d kviklet kviklet-postgres 
```
#### Default login (with docker containers):
User: testUser@example.com
Password: testPassword

### Run frontend container:
```
docker build --no-cache -t frontend .
docker run --name frontend -d -p 8888:8888 frontend
```


### Run the app locally:
```
# frontend
cd frontend/
npm run start

# backend
cd backend/
./gradlew bootRun
```
