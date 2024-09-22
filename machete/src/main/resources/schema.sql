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




CREATE TABLE token_verificacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_expiracion TIMESTAMP,
    token VARCHAR(255),
    usuario_id BIGINT,
    CONSTRAINT CONSTRAINT_INDEX_3 UNIQUE (usuario_id)
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

-- Crear tabla Notification
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL
);

-- Crear tabla Profile
CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    full_name VARCHAR(255),
    email VARCHAR(255) NOT NULL
);

