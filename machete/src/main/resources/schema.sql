-- Crear tabla User
CREATE TABLE user_app (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    habilitado BOOLEAN NOT NULL,
    ispremium BOOLEAN NOT NULL,
    aceptacion_aviso_privacidad BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE chat_message_gpt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    response TEXT NOT NULL  -- Cambia VARCHAR(255) por TEXT o LONGTEXT
);


CREATE TABLE token_verificacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_expiracion TIMESTAMP,
    token VARCHAR(255),
    usuario_id BIGINT,
    CONSTRAINT CONSTRAINT_INDEX_3 UNIQUE (usuario_id)
);


-- Crear la tabla 'verbs'
CREATE TABLE verbs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_form VARCHAR(50) NOT NULL,
    third_person VARCHAR(50) NOT NULL,
    past VARCHAR(50) NOT NULL,
    past_participle VARCHAR(50) NOT NULL,
    gerund VARCHAR(50) NOT NULL
);

-- Crear la tabla 'sentences'
CREATE TABLE sentences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tense VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL, -- Afirmativo, Interrogativo, Negativo
    content VARCHAR(255) NOT NULL,
    figurative_pronunciation VARCHAR(100),
    verb_id BIGINT,
    FOREIGN KEY (verb_id) REFERENCES verbs(id) ON DELETE CASCADE
);



-- Crear tabla Community
CREATE TABLE community (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear tabla Learning
CREATE TABLE learning (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);


-- Crear tabla Notification con campos adicionales
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(255) DEFAULT NULL,
    user_id BIGINT NOT NULL,
    is_read TINYINT(1) DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    type VARCHAR(50) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES user_app(id) ON DELETE CASCADE  -- Vinculación con user_app
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Crear tabla Profile
CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    full_name VARCHAR(255),
    email VARCHAR(255) NOT NULL
);

