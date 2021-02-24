-- Databases and their Users

CREATE
DATABASE projectx;
CREATE
USER projectx_admin with encrypted password 'changeme';
GRANT ALL PRIVILEGES ON DATABASE
projectx to projectx_admin;

CREATE
DATABASE keycloak;
CREATE
USER keycloak_admin with encrypted password 'changeme';
GRANT ALL PRIVILEGES ON DATABASE
keycloak to keycloak_admin;

-- Create Tables and Populate them

\c projectx projectx_admin

CREATE TABLE project
(
    id          SERIAL PRIMARY KEY,
    version     INT       DEFAULT 1     NOT NULL,
    created_on  TIMESTAMP DEFAULT now() NOT NULL,
    modified_on TIMESTAMP DEFAULT now() NOT NULL,
    created_by  VARCHAR(255)            NOT NULL,
    modified_by VARCHAR(255)            NOT NULL,
    name        VARCHAR(50)             NOT NULL,
    description VARCHAR(255)            NOT NULL,
    status      VARCHAR(1)              NOT NULL
);

INSERT INTO project(id, created_on, modified_on, created_by, modified_by, name, description, status)
VALUES (1, now(), now(), 'bootstrap', 'bootstrap', 'project 1-1', 'First test project', 'O');

INSERT INTO project(id, created_on, modified_on, created_by, modified_by, name, description, status)
VALUES (2, now(), now(), 'bootstrap', 'bootstrap', 'project 1-2', 'Second test project', 'N');
