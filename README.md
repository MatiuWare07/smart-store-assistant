# Smart Store Assistant

A RESTful API for minimarket inventory management with an integrated AI agent.

## Tech Stack

- **Backend:** Java 17 + Spring Boot 3.2.5
- **Database:** PostgreSQL
- **AI Agent:** Groq API
- **Authentication:** JWT + BCrypt
- **Deployment:** AWS EC2

## Requirements

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

## Project Phases

| Phase | Description | Status |
|-------|-------------|--------|
| 1 | Core REST API — Product CRUD & expiry alerts | ✅ Complete |
| 2 | JWT Authentication & User model | 🔄 In progress |
| 3 | AI Agent integration (Groq) | ⏳ Pending |
| 4 | AWS Deployment | ⏳ Pending |

## API Endpoints

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create new product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |
| GET | `/api/products/expiring-soon` | Get products expiring within 7 days |

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT token |

## Local Setup

1. Clone the repository
2. Make sure you have **Java 17+** installed
3. Create a `.env` file in the root directory:
```env
DB_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key
```
4. Make sure PostgreSQL is running on port `5433` with a database named `smart_store_db`
5. Run the application:
```bash
./mvnw spring-boot:run
```
6. Use the `/auth/register` endpoint to create your first user, then `/auth/login` to get your JWT token

## Author

**Mateo Fitipaldi** — [GitHub](https://github.com/MatiuWare07)