ALTER TABLE promotions
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(product_id);
