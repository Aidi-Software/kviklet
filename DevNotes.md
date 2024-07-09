## Dev Setup (MacOS)

### Run the app in containers:

```
docker compose build --no-cache kviklet
docker-compose up -d kviklet kviklet-postgres mysql
```

#### Steps to create new request

1. Go to localhost and login with default Admin user:
   User: testUser@example.com
   Password: testPassword

2. Create new user via the Settings
3. Create new database connection
   Note: The Hostname should be the mysql container's IP
   Get container IP: docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <container_id>

### Run frontend container:

```
docker build --no-cache -t frontend .
docker run --name frontend -d -p 8888:8888 frontend
```

### Run the app locally:

```
# Start a mysql server
mysql.server start --port=3307

# Start a postgresql that serves kviklet on port 5432
brew services start postgresql@16

# frontend
cd frontend/
npm run start

# backend
cd backend/
./gradlew bootRun
```
