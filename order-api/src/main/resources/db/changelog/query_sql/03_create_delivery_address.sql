--liquibase formatted sql
--changeset demo:query-03
create table if not exists order_api.delivery_address (
    id bigserial PRIMARY KEY not null,
    order_delivery varchar not null,
    city varchar not null,
    street varchar not null,
    house varchar not null,
    flat integer
    );
-- rollback DROP TABLE delivery_address;