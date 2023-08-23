--liquibase formatted sql
--changeset demo:query-05
CREATE TABLE IF NOT EXISTS order_api.order_items (
    id bigserial PRIMARY KEY not null,
    order_id bigint references orders(id) not null,
    product_id bigint not null,
    quantity integer not null,
    unit_amount numeric(12, 2) not null,
    total_amount numeric(12, 2)
    );
-- rollback DROP TABLE order_items;