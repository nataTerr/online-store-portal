--liquibase formatted sql
--changeset demo:query-03 splitStatements:false
BEGIN;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'delivery') THEN
        create type delivery AS ENUM ('почтой', 'пункт выдачи заказа', 'курьером');
    END IF;
END
$$;

create table if not exists order_api.delivery_address (
    id bigserial PRIMARY KEY not null,
    order_delivery delivery not null,
    city varchar not null,
    street varchar not null,
    house varchar not null,
    flat integer
    );
-- rollback DROP TABLE delivery_address;