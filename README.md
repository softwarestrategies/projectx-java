# ProjectX-Java

## About

This project is both for learning/practicing new tech and acting as a template for (potential) future projects. It is a
perpetual work-in-progress (and sometimes, kinda broken) and not a know-all end-all.

**Technologies used:**

- Java
- Spring Boot (including Spring Cloud, Spring Data, Spring Security, Spring WebFlux)
- Docker
- PostgreSQL (with R2DBC)
- Keycloak (as an OAuth 2.0 Authorization Server)
- Testing (JUnit 5 and Testcontainers)
- Simple UI (Thymeleaf, Spring WebMVC)

## Initial Setup

### 1) Docker

The infrastructure for the system is setup using docker-compose and the components/values are in the file **
docker-compose.yml**. So, startup Docker from the project root directory and then confirm that all of the required
containers have started up:

    /projectx-java %  docker-compose up -d
    /projectx-java %  docker ps

        You should see all of the containers specified in the docker-compose.yml file "Up"

**NOTE:**  After any Dev work is done, you should stop Docker containers setup here

    /projectx-java %  docker-compose up -d

### 2) PostgreSQL

For this, you'll need some PostgreSQL client installed so that you can access & setup the database. I have PostgreSQL
installed on my machine and am thus using **psql** from the commandline. From the commandline, log into psql to check
that the tables were created and data was inserted.

    /projectx-java %  psql -h localhost -p 5432 -d projectx -U projectx_admin --password

        projectx=> select * from project;
    
            which should show no projects,  but not fail either

### 3) Keycloak

Though there are ways to set things up via Keycloak's Admin REST API, I will do it via the UI available when the container is up & running.  There is a lot more to Keycloak, but you'll have to learn that on your own.  This is just intended to get you started with something.

With your browser, go to this URL and login using the Keycloak credential in the docker-compose.yml file:   **localhost:8484/auth**

#### A) Create a Realm

Create a Realm named **"projectx"**

    (NOTE: It is not obvious how.  What you do is float your mouse up near "Master" in the left column and the option becomes apparent)

#### B) Create Role(s)

Create these 2 Roles:  **"ADMIN"** and **"USER"**.

#### C) Create User(s)

Create a User named **"admin.test@softwarestrategies.io"**, assigning it the two roles **"USER"** and **"ADMIN"** (from
the "Role Mappings" tab) and setting its password (from the "Credentials" tab) to **"admin"**

Create a User named **"user.test@softwarestrategies.io"**, assigning it the one role **"USER"** and setting its
password to **"user"**

Create a User named **"monitor.test@softwarestrategies.io"**, assigning it the one role **"USER"** and setting its
password to **"monitor"**

#### D) Create Client Scope(s)

Create these 2 Roles:  **"read"** and **"write"**.

#### E) Create a Client

Create a Client named **"projectx-ui"**

For the "Valid Redirect URIs" value on the Settings tab, set it to this:  ** http://localhost:8080/ui-client/* **

On the "Client Scopes" tab, add to "Default Client Scopes" the 2 scopes created above:  **read** and **write**

Finally, you want to add a Mapper so that the JWT Access Token has the "preferred_username" value, as specified in our app.  From the "Mappers" tab, choose "Add Builtin" and select the one named "username"

#### F) Testing that All was Setup OK

Either with an app (like Postman) or from the commandline with an app (like "curl"), you will want to make a REST API
call into Keycloak.

    POST http://localhost:8484/auth/realms/projectx/protocol/openid-connect/token

    grant_type    password
    client_id     projectx-ui
    username      admin.test@softwarestrategies.io
    password      changeme

        NOTE: this can be done with cURL -> curl -X POST -d 'grant_type=password' -d 'client_id=project-ui' -d 'username=admin.test@softwarestrategies.io' -d 'password=admin' http://localhost:8484/auth/realms/projectx/protocol/openid-connect/token

If it worked, the call should return with a JSON object that contains a property named **"access_token"**

One final test is to see that the roles created above are in the created JWT Access Token, important because roles can
be used by Spring Security to control/limit access based on them.

Copy the value of "access_token", go to **jwt.io** and paste the access_token value into the **"Encoded"** textarea.
Then look at the **"Decoded"** textarea and you should see the decoded contents of the JWT Access Token. Read down aways
and you should see a section (like the following) the two roles assigned to the Admin user.

    "roles": [
        "offline_access",
        "uma_authorization",
        "ADMIN",
        "USER"
    ]
