--liquibase formatted sql
--changeset demo:data-02
CREATE TABLE IF NOT EXISTS site_api.products (
    id bigserial PRIMARY KEY not null,
    caption VARCHAR(255) unique not null,
    category_id integer references categories(id) not null,
    images varchar(255),
    description varchar,
    short_description varchar
    );
-- rollback DROP TABLE products;