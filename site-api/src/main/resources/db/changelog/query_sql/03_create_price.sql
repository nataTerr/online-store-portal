--liquibase formatted sql
--changeset demo:query-03
CREATE TABLE IF NOT EXISTS site_api.price (
    product_id bigint primary key references products(id) not null,
    price numeric(12,2) check (price > 0) not null
    );
-- rollback DROP TABLE price;
