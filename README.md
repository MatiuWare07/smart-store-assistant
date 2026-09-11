# Smart Store Assistant

A RESTful API for minimarket inventory management with an integrated AI agent.

## Tech Stack

- **Backend:** Java 17 + Spring Boot 3.2.5
- **Database:** PostgreSQL
- **AI Agent:** Groq API (llama)
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
| 2 | JWT Authentication & User model | ✅ Complete |
| 3 | AI Agent integration (Groq) | ✅ Complete |
| 4 | AWS Deployment | 🔄 In progress |

## API Endpoints

### Authentication
| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| POST | `/auth/register` | Register a new user | No |
| POST | `/auth/login` | Login and receive JWT token | No |

### Products
| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| GET | `/api/products` | Get all products | Yes |
| GET | `/api/products/{id}` | Get product by ID | Yes |
| POST | `/api/products` | Create new product | Yes |
| PUT | `/api/products/{id}` | Update product | Yes |
| DELETE | `/api/products/{id}` | Delete product | Yes |
| GET | `/api/products/expiring-soon` | Get products expiring within 7 days | Yes |

### AI Agent
| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| POST | `/ai/query` | Ask a natural language question about the inventory | Yes |

## Local Setup

1. Clone the repository
2. Make sure you have **Java 17+** installed
3. Create a `.env` file in the root directory:
```env
DB_PASSWORD=your_password
GROQ_API_KEY=your_groq_api_key
```
4. Make sure PostgreSQL is running on port `5433` with a database named `smart_store_db`
5. Run the application:
```bash
./mvnw spring-boot:run
```
6. Register your first user:
```json
POST /auth/register
{
    "username": "admin",
    "password": "your_password",
    "role": "ROLE_ADMIN"
}
```
7. Login to get your JWT token:
```json
POST /auth/login
{
    "username": "admin",
    "password": "your_password"
}
```
8. Use the token in the `Authorization` header for all protected endpoints:
```
Authorization: Bearer <your_token>
```
9. Ask the AI agent about your inventory:
```json
POST /ai/query
{
    "question": "What products are expiring soon?"
}
```

## Author

**Mateo Fitipaldi** — [GitHub](https://github.com/MatiuWare07)