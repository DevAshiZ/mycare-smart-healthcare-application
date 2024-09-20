### Frontend Configurations

#### Install dependencies

- First install the dependencies for the web-client using the following command

``` bash
    cd web-client
    npm install
```

- To start the web-client in development mode, use the following command

``` bash
    npm run dev
```

- To start the web-client, use the following command

``` bash
    npm start
```

### Backend Configurations

- Create a new database in PostgreSQL
- Update the application.yml file with the following configurations
- Replace the <<DATABASE_NAME>> with the name of the database you created
- Replace the <<PASSWORD>> with the password of the database
- Replace the <<SECRET_KEY>> with a secret key of your choice **(it should be 256-bit key for HS256)**

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/<<DATABASE_NAME>>
    username: postgres
    password: <<PASSWORD>>
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect



jwt:
  secret:
    key: <<SECRET_KEY>>
```