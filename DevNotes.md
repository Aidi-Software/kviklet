Run all containers:

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

## Dev Setup (MacOS)

### Run the app locally:

```
# frontend
cd frontend/
npm run start

# backend
cd backend/
./gradlew bootRun

# mysql
mysql.server start --port=3307

# postgresql
brew services start postgresql@16
```
