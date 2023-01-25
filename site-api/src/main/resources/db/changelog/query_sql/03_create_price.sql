--liquibase formatted sql
--changeset demo:query-03
CREATE TABLE IF NOT EXISTS site_api.price (
    product_id bigint primary key references products(id) not null,
    price money check (price > 0::money) not null,
    date_from date not null,
    date_by date
    );
-- rollback DROP TABLE price;