--liquibase formatted sql
--changeset demo:query-02
CREATE TABLE IF NOT EXISTS order_api.cart_items (
    id bigserial PRIMARY KEY not null,
    cart_id bigint references cart(id) not null,
    product_id bigint not null,
    unit_number integer not null,
    unit_amount numeric(12, 2) not null,
    total_amount numeric(12, 2)
    );
-- rollback DROP TABLE cart_items;