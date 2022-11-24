--liquibase formatted sql
--changeset demo:data-03
CREATE TABLE IF NOT EXISTS site_api.price (
    product_id bigint primary key references products(id) not null,
    price money check (price > 0::money) not null,
    date_from timestamp without time zone not null,
    date_by timestamp without time zone
    );
-- rollback DROP TABLE price;