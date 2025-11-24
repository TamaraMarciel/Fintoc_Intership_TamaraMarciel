-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS fintoc_db;
USE fintoc_db;

-- Schema de Productos para E-commerce

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(200),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    foto LONGTEXT,
    ventas INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de categorías para relación de muchos a muchos
CREATE TABLE IF NOT EXISTS product_categories (
    product_id BIGINT NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    PRIMARY KEY (product_id, categoria),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejorar las búsquedas
CREATE INDEX idx_categoria ON product_categories(categoria);
CREATE INDEX idx_nombre ON products(nombre);

-- Insertar datos de ejemplo - Snacks y Bebidas
INSERT INTO products (nombre, descripcion, precio, stock, foto) VALUES
('Red Bull Sandía', 'Energética', 1700.00, 2, '/images/Red Bull Sandía.jpg'),
('Red Bull Açaí', 'Energética', 1700.00, 3, '/images/Red Bull Açaí.webp'),
('Barrita Nature Valley Salted Caramel', 'Barrita', 1200.00, 2, '/images/Barrita Nature Valley Saled Caramel.webp'),
('Test', 'Sirve para probar nuevas features en la pepestore', 1.00, 95666, '/images/test.jpg'),
('Red Bull Dragon Fruit', 'Energética', 1700.00, 1, '/images/Red Bull Dragon Fruit.jpeg'),
('Coca Cola Zero Lata', 'El segundo producto favorito del canal #vida-efimera', 900.00, 4, '/images/Coca Cola Zero Lata.jpeg'),
('Postre Protein Snack Caramelo', 'Postre', 900.00, 1, '/images/Postre Protein Snack Caramelo.jpg'),
('Postre Goodness Chocolate', 'Postre protein', 1000.00, 3, '/images/Postre goodnes Chocolate.webp'),
('RedBull Sin azúcar', 'Energética', 1700.00, 5, '/images/RedBull Sin azúcar.jpeg'),
('Postre Goodness Caramelo', 'Postre protein', 1000.00, 4, '/images/Postre goodnes Chocolate.webp'),
('Sprite Zero Lata', 'Bebida', 900.00, 3, '/images/Sprite Zero Lata.jpeg'),
('Barrita Wild Protein Caramelo', 'Barritas', 1500.00, 4, '/images/Barrita Wild Protein Caramelo.png'),
('Red Bull Zero', 'Enérgetica', 1700.00, 2, '/images/Red Bull Zero.jpg'),
('Red Bull', 'Energética', 1700.00, 3, '/images/Red Bull.webp');

-- Insertar categorías para cada producto (hasta 5 por producto)
INSERT INTO product_categories (product_id, categoria) VALUES
(1, 'energética'),
(1, 'bebestibles'),
(2, 'energética'),
(2, 'bebestibles'),
(3, 'barritas'),
(3, 'snacks'),
(4, 'test'),
(4, 'snacks'),
(5, 'energética'),
(5, 'bebestibles'),
(6, 'bebidas'),
(6, 'bebestibles'),
(7, 'postre'),
(7, 'snacks'),
(8, 'postre'),
(8, 'snacks'),
(9, 'energética'),
(9, 'bebestibles'),
(10, 'postre'),
(10, 'snacks'),
(11, 'bebidas'),
(11, 'bebestibles'),
(12, 'barritas'),
(12, 'snacks'),
(13, 'energética'),
(13, 'bebestibles'),
(14, 'energética'),
(14, 'bebestibles');


CREATE USER 'fintoc_user'@'localhost' IDENTIFIED BY 'fintoc_password';
GRANT ALL PRIVILEGES ON fintoc_db.* TO 'fintoc_user'@'localhost';
FLUSH PRIVILEGES;