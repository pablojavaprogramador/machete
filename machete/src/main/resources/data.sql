-- Insertar datos en Community
INSERT INTO community (name) VALUES ('Tech Enthusiasts');
INSERT INTO community (name) VALUES ('Language Learners');

-- Insertar datos en Learning
INSERT INTO learning (title) VALUES ('Introduction to Java');
INSERT INTO learning (title) VALUES ('Advanced Spring Boot');

-- Insertar datos en Notification
INSERT INTO notification (message) VALUES ('Welcome to the Machete app!');
INSERT INTO notification (message) VALUES ('Your profile has been updated.');

-- Insertar datos en Profile
INSERT INTO profile (username, full_name, email) VALUES ('john_doe', 'John Doe', 'john.doe@example.com');
INSERT INTO profile (username, full_name, email) VALUES ('jane_smith', 'Jane Smith', 'jane.smith@example.com');

-- Inserta un usuario de ejemplo
INSERT INTO user_app (usuario, email, password, habilitado, aceptacion_aviso_privacidad)
VALUES ('testuser', 'testuser@example.com', 'passwordhashed', true, true);
