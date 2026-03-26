CREATE TABLE products.product (
    id BIGSERIAL PRIMARY KEY,
    product_identifier VARCHAR NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    category_id BIGINT REFERENCES products.category(id)
);