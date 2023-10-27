CREATE TABLE customer(
    id            VARCHAR(36) PRIMARY KEY,
    full_name     VARCHAR(255),
    date_of_birth DATE,
    email         VARCHAR(255)
);

CREATE TABLE shop(
    id       VARCHAR(36) PRIMARY KEY,
    name     VARCHAR(255),
    location VARCHAR(255)
);

CREATE TABLE product(
    id      VARCHAR(36) PRIMARY KEY,
    name    VARCHAR(255),
    price   DECIMAL(10, 2),
    shop_id VARCHAR(255) REFERENCES shop (id)
);

CREATE TABLE purchase(
    id            VARCHAR(36) PRIMARY KEY,
    customer_id   VARCHAR(36) REFERENCES customer (id),
    product_id    VARCHAR(36) REFERENCES product (id),
    delivery_date DATE
);