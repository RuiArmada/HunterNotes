
# Monster Hunter Inspired REST API

This project is a REST API built using Java, Maven, and Spring Boot. The API allows users to interact with a collection of monsters from the Monster Hunter universe. It provides CRUD functionality to manage and retrieve monsters.

## Features

- **Get All Monsters**: Retrieve a list of all monsters in the database.
- **Get Monster By Name**: Retrieve a monster's details by its name.
- **Create New Monster**: Add new monsters to the database.
- **Database Population**: Includes initial monster data (15 predefined entries) and allows adding new monsters via POST requests.

## Tech Stack

- **Java 17** or later
- **Spring Boot 2.x**
- **Maven** for build automation
- **H2 Database** (In-memory database for easy setup)
- **Postman** for API testing

## Installation

### Prerequisites

- Java 17 or later
- Maven 3.x
- IDE (Optional: IntelliJ IDEA, Eclipse, etc.)

### Steps to Run the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/monster-hunter-api.git
   cd monster-hunter-api
   ```

2. **Build the project with Maven**

   Run the following command to build the project:

   ```bash
   mvn clean install
   ```

3. **Run the Spring Boot application**

   Use Maven to run the application:

   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**

   Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints

### `GET /monsters`

Retrieves a list of all monsters.

**Response:**

```json
[
    {
        "id": 1,
        "name": "Rathalos",
        "type": "Flying Wyvern",
        "description": "A fire-breathing monster with red scales."
    },
    {
        "id": 2,
        "name": "Zinogre",
        "type": "Fanged Wyvern",
        "description": "A thunder-infused monster with great speed."
    }
]
```

### `GET /monsters/{name}`

Retrieves a monster's details by its name.

**Parameters:**

- `name` (Path parameter) - The name of the monster.

**Response:**

```json
{
    "id": 1,
    "name": "Rathalos",
    "type": "Flying Wyvern",
    "description": "A fire-breathing monster with red scales."
}
```

### `POST /monsters`

Adds a new monster to the database.

**Request Body:**

```json
{
    "name": "Magnamalo",
    "type": "Fanged Beast",
    "description": "A terrifying monster with a powerful explosive attack."
}
```

**Response:**

```json
{
    "id": 6,
    "name": "Magnamalo",
    "type": "Fanged Beast",
    "description": "A terrifying monster with a powerful explosive attack."
}
```

### Example POST Request (Using Postman)

To add 5 new monsters, send a `POST` request with a body like this:

```json
[
    {
        "name": "Apex Rathalos",
        "type": "Flying Wyvern",
        "description": "A more powerful variant of Rathalos with a fiery rage."
    },
    {
        "name": "Magnamalo",
        "type": "Fanged Beast",
        "description": "A terrifying monster with a powerful explosive attack."
    },
    {
        "name": "Valstrax",
        "type": "Elder Dragon",
        "description": "An Elder Dragon that flies through the air at immense speeds."
    },
    {
        "name": "Brachydios",
        "type": "Brute Wyvern",
        "description": "A brute wyvern with explosive slime capabilities."
    },
    {
        "name": "Kushala Daora (Prime)",
        "type": "Elder Dragon",
        "description": "A stronger version of Kushala Daora with enhanced wind control."
    }
]
```

### Database Population

The API is pre-populated with 15 monsters. The data can be modified, added, or deleted via API calls.

## Database Configuration

By default, this project uses **H2 Database** as an in-memory database. This means that the data is lost when the application is stopped. For production, you can switch to a more permanent database like MySQL, PostgreSQL, or any other supported database by changing the configuration in `application.properties`.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

## Testing the API

You can test the API using Postman or any other API testing tool. 

1. **Test the `GET /monsters` endpoint** by sending a `GET` request to `http://localhost:8080/monsters`.
2. **Test the `POST /monsters` endpoint** by sending a `POST` request with a JSON body to `http://localhost:8080/monsters`.

## Contribution

Feel free to fork this repository, create a pull request, or contribute to the project in any way.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
