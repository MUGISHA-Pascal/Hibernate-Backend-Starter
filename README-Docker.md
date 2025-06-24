# Docker Setup for Hibernate Project

This document explains how to use Docker with the Hibernate Spring Boot application.

## Prerequisites

- Docker installed on your system
- Docker Compose installed on your system

## Quick Start

### Production Mode

1. **Build and run the entire stack:**
   ```bash
   docker-compose up --build
   ```

2. **Run in background:**
   ```bash
   docker-compose up -d --build
   ```

3. **Stop the services:**
   ```bash
   docker-compose down
   ```

### Development Mode

1. **Start development environment:**
   ```bash
   docker-compose -f docker-compose.dev.yml up --build
   ```

2. **Access the services:**
   - Application: http://localhost:8080
   - PostgreSQL: localhost:5432
   - pgAdmin: http://localhost:5050 (admin@admin.com / admin)

## Services

### Application (Spring Boot)
- **Port:** 8080
- **Debug Port:** 5005 (development mode only)
- **Main Class:** `com.hibernate.hibernate.HibernateApplication`

### PostgreSQL Database
- **Port:** 5432
- **Database:** studentdb
- **Username:** postgres
- **Password:** postgres

### pgAdmin (Development only)
- **Port:** 5050
- **Email:** admin@admin.com
- **Password:** admin

## Docker Files Explained

### `Dockerfile`
- Multi-stage build for production
- Uses OpenJDK 17
- Optimized for production deployment

### `Dockerfile.dev`
- Single-stage build for development
- Includes debug support
- Supports hot reload with mounted volumes

### `docker-compose.yml`
- Production configuration
- Includes health checks
- Persistent data volumes

### `docker-compose.dev.yml`
- Development configuration
- Includes pgAdmin for database management
- Source code mounting for hot reload
- Debug port exposure

## Environment Variables

The application uses the following environment variables:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/studentdb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
```

## Useful Commands

### View logs
```bash
# All services
docker-compose logs

# Specific service
docker-compose logs app
docker-compose logs postgres

# Follow logs
docker-compose logs -f app
```

### Access containers
```bash
# Access application container
docker-compose exec app bash

# Access database
docker-compose exec postgres psql -U postgres -d studentdb
```

### Clean up
```bash
# Stop and remove containers, networks
docker-compose down

# Stop and remove containers, networks, and volumes
docker-compose down -v

# Remove all unused containers, networks, images
docker system prune -a
```

## Troubleshooting

### Port conflicts
If you get port conflicts, you can modify the ports in the docker-compose files:
```yaml
ports:
  - "8081:8080"  # Use port 8081 on host instead of 8080
```

### Database connection issues
1. Ensure PostgreSQL container is healthy:
   ```bash
   docker-compose ps
   ```

2. Check database logs:
   ```bash
   docker-compose logs postgres
   ```

3. Wait for health check to pass before starting the application

### Build issues
1. Clean Docker cache:
   ```bash
   docker system prune -a
   ```

2. Rebuild without cache:
   ```bash
   docker-compose build --no-cache
   ```

## Development Workflow

1. **Start development environment:**
   ```bash
   docker-compose -f docker-compose.dev.yml up --build
   ```

2. **Make code changes** - The application will automatically reload

3. **Debug** - Attach your IDE to localhost:5005

4. **Database management** - Use pgAdmin at http://localhost:5050

5. **Stop development:**
   ```bash
   docker-compose -f docker-compose.dev.yml down
   ```

## Production Deployment

For production deployment, use the production docker-compose file:

```bash
docker-compose up -d --build
```

The production setup includes:
- Multi-stage Docker build for smaller image size
- Health checks for reliable orchestration
- Persistent data volumes
- Optimized JVM settings 