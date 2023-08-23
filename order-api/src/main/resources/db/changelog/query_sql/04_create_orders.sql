--liquibase formatted sql
--changeset demo:query-04 splitStatements:false
BEGIN;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'stat') THEN
        create type stat AS ENUM ('оформлен', 'передан в доставку',
'в службе доставки', 'в пути', 'в пункте/у курьера', 'получен');
    END IF;
END
$$;

BEGIN;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'payment') THEN
        create type payment AS ENUM ('оплачен', 'не оплачен');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS order_api.orders (
    id bigserial PRIMARY KEY not null,
    client_id bigint not null,
    create_date timestamp without time zone not null,
    create_date_tz VARCHAR not null,
    update_date timestamp without time zone not null,
    amount numeric(12, 2) not null,
    delivery_address_id bigint references delivery_address(id) not null,
    payment_status payment not null,
    delivery_date timestamp without time zone not null,
    delivery_date_tz VARCHAR not null,
    status stat not null
    );
-- rollback DROP TABLE orders;