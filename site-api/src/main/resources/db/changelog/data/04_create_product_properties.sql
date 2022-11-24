--liquibase formatted sql
--changeset demo:data-04
CREATE TABLE IF NOT EXISTS site_api.product_properties (
    product_id bigint primary key references products(id) not null,
    caption varchar(255),
    value varchar(255)
    );
-- rollback DROP TABLE product_properties;