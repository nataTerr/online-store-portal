--liquibase formatted sql
--changeset demo:query-01
CREATE TABLE IF NOT EXISTS order_api.cart (
    id bigserial PRIMARY KEY not null,
    client_id bigint not null,
    items_number integer not null,
    amount numeric(12, 2) not null
    );
-- rollback DROP TABLE cart;