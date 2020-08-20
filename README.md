Scripts For DB

INSERT INTO user (id, username, password, salary, age) VALUES (1, 'user1', '$2a$04$Ye7/lJoJin6.m9sOJZ9ujeTgHEVM4VXgI2Ingpsnf9gXyXEXf/IlW', 3456, 33);
INSERT INTO user (id, username, password, salary, age) VALUES (2, 'user2', '$2a$04$StghL1FYVyZLdi8/DIkAF./2rz61uiYPI3.MaAph5hUq03XKeflyW', 7823, 23);
INSERT INTO user (id, username, password, salary, age) VALUES (3, 'user3', '$2a$04$Lk4zqXHrHd82w5/tiMy8ru9RpAXhvFfmHOuqTmFPWQcUhBD8SSJ6W', 4234, 45);

INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);


Login API

localhost:8080/auth/login
POST
DATA OF USER LOGIN : {
    "username": "user2",
    "password": "password2"
}

DATA OF ADMIN LOGIN : {
    "username": "user1",
    "password": "password1"
}

User API

localhost:8080/user/getText
GET
Header : 
  Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdCI6MTU5NzkzMjk1MiwiZXhwIjoxNTk3OTUwOTUyfQ.bj41PnYt0YpavxZIPaiG-VtdF3uK4XPVeAvFkUqaRzw
  
Admin API

localhost:8080/admin/getText
GET
Header : 
  Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInNjb3BlcyI6IlJPTEVfVVNFUiIsImlhdCI6MTU5NzkzMjk1MiwiZXhwIjoxNTk3OTUwOTUyfQ.bj41PnYt0YpavxZIPaiG-VtdF3uK4XPVeAvFkUqaRzw
