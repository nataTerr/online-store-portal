--liquibase formatted sql
--changeset demo:query-03
CREATE TABLE IF NOT EXISTS site_api.price (
    id bigserial PRIMARY KEY not null,
    product_id bigint references products(id) not null,
    price numeric check (price > 0::numeric) not null,
    date_from date not null,
    date_by date
    );
-- rollback DROP TABLE price;