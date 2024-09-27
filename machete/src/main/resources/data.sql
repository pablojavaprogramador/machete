
-- Insertar verbos en la tabla 'verbs'
INSERT INTO verbs (base_form, third_person, past, past_participle, gerund) VALUES
('load', 'loads', 'loaded', 'loaded', 'loading'),
('eat', 'eats', 'ate', 'eaten', 'eating'),
('run', 'runs', 'ran', 'run', 'running');

-- Insertar oraciones en la tabla 'sentences' asociadas al verbo 'load' (id = 1)
INSERT INTO sentences (tense, type, content, figurative_pronunciation, verb_id) VALUES
('Presente Simple', 'Afirmativo', 'I load the data every day.', '/ai lóud de deita evri dei/', 1),
('Presente Simple', 'Interrogativo', 'Do you load the data?', '/du iu lóud de deita?/', 1),
('Presente Simple', 'Negativo', 'I do not load the data.', '/ai du not lóud de deita/', 1),
('Presente Perfecto', 'Afirmativo', 'I have loaded the data.', '/ai hav lóuded de deita/', 1),
('Presente Perfecto', 'Interrogativo', 'Have you loaded the data?', '/hav iu lóuded de deita?/', 1),
('Presente Perfecto', 'Negativo', 'I have not loaded the data.', '/ai hav not lóuded de deita/', 1),
('Presente Continuo', 'Afirmativo', 'I am loading the data.', '/ai am lóuding de deita/', 1),
('Presente Continuo', 'Interrogativo', 'Am I loading the data?', '/am ai lóuding de deita?/', 1),
('Presente Continuo', 'Negativo', 'I am not loading the data.', '/ai am not lóuding de deita/', 1);

-- Insertar oraciones en la tabla 'sentences' asociadas al verbo 'eat' (id = 2)
INSERT INTO sentences (tense, type, content, figurative_pronunciation, verb_id) VALUES
('Presente Simple', 'Afirmativo', 'She eats an apple every morning.', '/shi eits an apl evri morning/', 2),
('Presente Simple', 'Interrogativo', 'Does he eat meat?', '/das hi eit mit?/', 2),
('Presente Simple', 'Negativo', 'They do not eat fish.', '/dey du not eit fish/', 2),
('Presente Perfecto', 'Afirmativo', 'We have eaten all the snacks.', '/wi hav eiten ol de snacks/', 2),
('Presente Perfecto', 'Interrogativo', 'Have you eaten lunch?', '/hav iu eiten lunch?/', 2),
('Presente Perfecto', 'Negativo', 'I have not eaten yet.', '/ai hav not eiten yet/', 2),
('Presente Continuo', 'Afirmativo', 'He is eating dinner.', '/hi is eiting dinner/', 2),
('Presente Continuo', 'Interrogativo', 'Are you eating now?', '/ar iu eiting nau?/', 2),
('Presente Continuo', 'Negativo', 'They are not eating.', '/dey ar not eiting/', 2);

-- Insertar oraciones en la tabla 'sentences' asociadas al verbo 'run' (id = 3)
INSERT INTO sentences (tense, type, content, figurative_pronunciation, verb_id) VALUES
('Presente Simple', 'Afirmativo', 'I run every morning.', '/ai ran evri morning/', 3),
('Presente Simple', 'Interrogativo', 'Do you run fast?', '/du iu ran fast?/', 3),
('Presente Simple', 'Negativo', 'She does not run.', '/shi das not ran/', 3),
('Presente Perfecto', 'Afirmativo', 'They have run the marathon.', '/dey hav ran de marathon/', 3),
('Presente Perfecto', 'Interrogativo', 'Have you run before?', '/hav iu ran bifor?/', 3),
('Presente Perfecto', 'Negativo', 'We have not run today.', '/wi hav not ran today/', 3),
('Presente Continuo', 'Afirmativo', 'He is running a new project.', '/hi is runing a new project/', 3),
('Presente Continuo', 'Interrogativo', 'Are you running in the park?', '/ar iu runing in de park?/', 3),
('Presente Continuo', 'Negativo', 'I am not running right now.', '/ai am not runing right nau/', 3);



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

INSERT INTO user_app (usuario, email, password, habilitado, ispremium, aceptacion_aviso_privacidad)
VALUES ('usuario1', 'usuario1@example.com', 'password123', true, false, true);