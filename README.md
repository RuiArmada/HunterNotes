# Monster Hunter Inspired REST API

This project is a REST API built using Java, Maven, and Spring Boot. The API allows users to interact with a collection of monsters from the Monster Hunter universe. It provides CRUD functionality to manage and retrieve monsters.

## Features

- **Get All Monsters**: Retrieve a list of all monsters in the database.
- **Get Monster By Name**: Retrieve a monster's details by its name.
- **Create New Monster**: Add new monsters to the database.
- **Database Integration**: Uses a MySQL database, provisioned using Docker Compose, for persistent storage.

## Tech Stack

- **Java 17** or later
- **Spring Boot 2.x**
- **Maven** for build automation
- **MySQL** (Dockerized for persistence)
- **Postman** for API testing

## Installation

### Prerequisites

- Java 17 or later
- Maven 3.x
- Docker and Docker Compose
- IDE (Optional: IntelliJ IDEA, Eclipse, etc.)

### Steps to Run the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/monster-hunter-api.git
   cd monster-hunter-api
   ```

2. **Setup MySQL using Docker Compose**

   A `docker-compose.yml` file is provided to spin up a MySQL database container. Run the following command to start the container:

   ```bash
   docker-compose up -d
   ```

   This will create a MySQL instance accessible at `localhost:3306` with the following credentials:

   - **Database Name**: `hunter_notes`
   - **Username**: `hunter_user`
   - **Password**: `hunterpassword`

   Example `docker-compose.yml`:

   ```yaml
   version: '3.8'
   services:
     db:
       image: mysql:8.0
       container_name: mysql-monsters
       restart: always
       environment:
         MYSQL_ROOT_PASSWORD: rootpassword
         MYSQL_DATABASE: hunter_notes
         MYSQL_USER: hunter_user
         MYSQL_PASSWORD: hunterpassword
       ports:
         - "3306:3306"
       volumes:
         - db_data:/var/lib/mysql
   volumes:
     db_data:
   ```

3. **Configure the application**

   The application is pre-configured with the following `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hunter_notes
   spring.datasource.username=hunter_user
   spring.datasource.password=hunterpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

   If you modify the `docker-compose.yml` file, update these properties accordingly.

4. **Build the project with Maven**

   Run the following command to build the project:

   ```bash
   mvn clean install
   ```

5. **Run the Spring Boot application**

   Use Maven to run the application:

   ```bash
   mvn spring-boot:run
   ```

6. **Access the API**

   Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints

### `GET /monsters`

Retrieves a list of all monsters.

**Response:**

```json
[
    {
        "name": "Rathalos",
        "type": "Flying Wyvern",
        "description": "A fire-breathing monster with red scales."
    },
    {
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
    "name": "Magnamalo",
    "type": "Fanged Beast",
    "description": "A terrifying monster with a powerful explosive attack."
}
```

### Example POST Request (Using Postman)

To add a new monster, send a `POST` request with a body like this:

```json
{
    "name": "Kushala Daora (Prime)",
    "type": "Elder Dragon",
    "description": "A stronger version of Kushala Daora with enhanced wind control."
}
```

## Database Configuration

The project uses a **MySQL database** provisioned using Docker Compose. Ensure the `docker-compose.yml` file is correctly set up and the database is running before starting the application.

### Default Docker Compose Configuration

```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    container_name: mysql-monsters
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: hunter_notes
      MYSQL_USER: hunter_user
      MYSQL_PASSWORD: hunterpassword
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql
volumes:
  db_data:
```

## Testing the API

You can test the API using Postman or any other API testing tool.

1. **Test the `GET /monsters` endpoint** by sending a `GET` request to `http://localhost:8080/monsters`.
2. **Test the `POST /monsters` endpoint** by sending a `POST` request with a JSON body to `http://localhost:8080/monsters`.

## Contribution

Feel free to fork this repository, create a pull request, or contribute to the project in any way.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
