-- Create Tables
CREATE TABLE raw_material (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock_quantity INTEGER NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value DECIMAL(10, 2) NOT NULL
);

CREATE TABLE product_composition (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    raw_material_id INTEGER NOT NULL,
    required_quantity INTEGER NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (raw_material_id) REFERENCES raw_material(id)
);