--liquibase formatted sql
--changeset demo:query-04
CREATE TABLE IF NOT EXISTS order_api.orders (
    id bigserial PRIMARY KEY not null,
    client_id bigint not null,
    create_date timestamp without time zone not null,
    create_date_tz VARCHAR not null,
    update_date timestamp without time zone not null,
    amount numeric(12, 2) not null,
    delivery_address_id bigint references delivery_address(id) not null,
    payment_status varchar not null,
    delivery_date timestamp without time zone not null,
    delivery_date_tz VARCHAR not null,
    status varchar not null
    );
-- rollback DROP TABLE orders;