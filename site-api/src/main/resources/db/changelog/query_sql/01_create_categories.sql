--liquibase formatted sql
--changeset demo:query-01
CREATE TABLE IF NOT EXISTS site_api.categories (
    id integer PRIMARY KEY unique not null,
    caption VARCHAR(255) unique not null,
    parent_id integer references categories(id)
    );
-- rollback DROP TABLE categories;