CREATE TABLE products.product (
    id BIGSERIAL PRIMARY KEY,
    product_identifier VARCHAR NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR NOT NULL,
    price FLOAT NOT NULL,
    category_id BIGINT REFERENCES products.category(id)
);