CREATE TABLE IF NOT EXISTS
    categories
(
    category_id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    promotions
(
    promotion_id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR,
    description
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    minimarkets
(
    minimarket_id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    deposits
(
    deposit_id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    trucks
(
    truck_id
    SERIAL
    PRIMARY
    KEY,
    serial_number
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    manufacturers
(
    manufacturer_id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
);

CREATE TABLE IF NOT EXISTS
    products
(
    product_id
    SERIAL,
    promotion_id
    INTEGER,
    category_id
    INTEGER,
    minimarket_id
    INTEGER,
    manufacturer_id
    INTEGER,
    name
    VARCHAR,
    price
    FLOAT,
    quantity
    INTEGER,
    icon_url
    VARCHAR,

    PRIMARY
    KEY
(
    product_id
),
    CONSTRAINT fk_promotion_id FOREIGN KEY
(
    promotion_id
) REFERENCES promotions
(
    promotion_id
),
    CONSTRAINT fk_category_id FOREIGN KEY
(
    category_id
) REFERENCES categories
(
    category_id
),
    CONSTRAINT fk_minimarket_id FOREIGN KEY
(
    minimarket_id
) REFERENCES minimarkets
(
    minimarket_id
),
    CONSTRAINT fk_manufacturer_id FOREIGN KEY
(
    manufacturer_id
) REFERENCES manufacturers
(
    manufacturer_id
)
    );
