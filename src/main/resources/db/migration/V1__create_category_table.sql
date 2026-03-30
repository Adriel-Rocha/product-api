CREATE SCHEMA IF NOT EXISTS products;

CREATE TABLE products.category (
    id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);