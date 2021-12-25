CREATE TABLE IF NOT EXISTS
    type_pay
(
    id
    SERIAL,
    type
    VARCHAR,
    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    type_status
(
    id
    SERIAL,
    type
    VARCHAR,
    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    shelf_category
(
    id
    SERIAL,
    name
    VARCHAR,
    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    minishop_category
(
    id
    SERIAL,
    name
    VARCHAR,
    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    products
(
    id
    SERIAL,
    name
    VARCHAR,
    PRIMARY
    KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    promotions
(
    id SERIAL,
    description VARCHAR,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS
    shelves
(
    id
    SERIAL,
    category_id
    SERIAL,
    product_id
    SERIAL,
    PRIMARY
    KEY
(
    id
),
    CONSTRAINT fk_category_id FOREIGN KEY
(
    category_id
) REFERENCES shelf_category
(
    id
),
    CONSTRAINT fk_product_id FOREIGN KEY
(
    product_id
) REFERENCES products
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    minishops
(
    id
    SERIAL,
    category_id
    SERIAL,
    product_id
    SERIAL,
    PRIMARY
    KEY
(
    id
),
    CONSTRAINT fk_category_id FOREIGN KEY
(
    category_id
) REFERENCES minishop_category
(
    id
),
    CONSTRAINT fk_product_id FOREIGN KEY
(
    product_id
) REFERENCES products
(
    id
)
    );

CREATE TABLE IF NOT EXISTS
    cash_register
(
    id
    SERIAL,
    cash_register_number
    SERIAL,
    local_money_cash_register
    FLOAT,
    type_pay_id
    SERIAL,
    type_status_id
    SERIAL,
    PRIMARY
    KEY
(
    id
),
    CONSTRAINT fk_type_pay FOREIGN KEY
(
    type_pay_id
) REFERENCES type_pay
(
    id
),
    CONSTRAINT fk_type_status FOREIGN KEY
(
    type_status_id
) REFERENCES type_status
(
    id
)
    );

INSERT INTO type_pay(type)
VALUES ('CASH');
INSERT INTO type_pay(type)
VALUES ('CARD');
INSERT INTO type_pay(type)
VALUES ('AMPRENTA');

INSERT INTO type_status(type)
VALUES ('BLOCATA');
INSERT INTO type_status(type)
VALUES ('FUNCTIONALA');
INSERT INTO type_status(type)
VALUES ('ASTEAPTA_OPERATOR');

INSERT INTO cash_register(cash_register_number, local_money_cash_register)
VALUES (1, 100);
INSERT INTO cash_register(cash_register_number, local_money_cash_register)
VALUES (2, 200);
INSERT INTO cash_register(cash_register_number, local_money_cash_register)
VALUES (3, 300);

INSERT INTO products(name)
VALUES ('Piper');
INSERT INTO products(name)
VALUES ('Boia');
INSERT INTO products(name)
VALUES ('Patrunjel Uscat');
INSERT INTO products(name)
VALUES ('Marar Uscat');

INSERT INTO products(name)
VALUES ('Portocale');
INSERT INTO products(name)
VALUES ('Cartofi');
INSERT INTO products(name)
VALUES ('Morcovi');

INSERT INTO shelf_category(name)
VALUES ('Produse 100% Naturale');
INSERT INTO shelf_category(name)
VALUES ('Condimente');
INSERT INTO shelf_category(name)
VALUES ('Bauturi Alcoolice');
INSERT INTO shelf_category(name)
VALUES ('Apa');
INSERT INTO shelf_category(name)
VALUES ('Sucuri');
INSERT INTO shelf_category(name)
VALUES ('Haine');
INSERT INTO shelf_category(name)
VALUES ('Branzeturi si lactate');
INSERT INTO shelf_category(name)
VALUES ('Produse de baie');
INSERT INTO shelf_category(name)
VALUES ('Produse de curatat');
INSERT INTO shelf_category(name)
VALUES ('Produse pentru corp');
INSERT INTO shelf_category(name)
VALUES ('Hartie igienica');
INSERT INTO shelf_category(name)
VALUES ('Dulciuri');
INSERT INTO shelf_category(name)
VALUES ('Gustari');
INSERT INTO shelf_category(name)
VALUES ('Uleiuri');
INSERT INTO shelf_category(name)
VALUES ('Peste');
INSERT INTO shelf_category(name)
VALUES ('Cereale');
INSERT INTO shelf_category(name)
VALUES ('Produse agricole fine');
INSERT INTO shelf_category(name)
VALUES ('Conserve');
INSERT INTO shelf_category(name)
VALUES ('Becuri');
INSERT INTO shelf_category(name)
VALUES ('Baterii');
INSERT INTO shelf_category(name)
VALUES ('Produse pentru scoala');

INSERT INTO minishop_category(name)
VALUES ('Carnagerie');
INSERT INTO minishop_category(name)
VALUES ('Brutarie');
INSERT INTO minishop_category(name)
VALUES ('Produse din peste');
INSERT INTO minishop_category(name)
VALUES ('Carnagerie');
INSERT INTO minishop_category(name)
VALUES ('Haine');
INSERT INTO minishop_category(name)
VALUES ('Fructe si legume');

INSERT INTO shelves(category_id, product_id)
SELECT shelf_category.id, products.id
FROM shelf_category
         INNER JOIN products ON products.name = 'Piper'
WHERE shelf_category.name = 'Condimente';

INSERT INTO shelves(category_id, product_id)
SELECT shelf_category.id, products.id
FROM shelf_category
         INNER JOIN products ON products.name = 'Boia'
WHERE shelf_category.name = 'Condimente';

INSERT INTO shelves(category_id, product_id)
SELECT shelf_category.id, products.id
FROM shelf_category
         INNER JOIN products ON products.name = 'Patrunjel Uscat'
WHERE shelf_category.name = 'Condimente';

INSERT INTO shelves(category_id, product_id)
SELECT shelf_category.id, products.id
FROM shelf_category
         INNER JOIN products ON products.name = 'Marar Uscat'
WHERE shelf_category.name = 'Condimente';



INSERT INTO minishops(product_id, category_id)
SELECT products.id, minishop_category.id
FROM products
         INNER JOIN minishop_category ON minishop_category.name = 'Fructe si legume'
WHERE products.name = 'Cartofi';

INSERT INTO minishops(product_id, category_id)
SELECT products.id, minishop_category.id
FROM products
         INNER JOIN minishop_category ON minishop_category.name = 'Fructe si legume'
WHERE products.name = 'Morcovi';

INSERT INTO minishops(product_id, category_id)
SELECT products.id, minishop_category.id
FROM products
         INNER JOIN minishop_category ON minishop_category.name = 'Fructe si legume'
WHERE products.name = 'Portocale';

INSERT INTO promotions(description)
VALUES ('Cumpara 2 paini si primesti una gratuit!');