--liquibase formatted sql
--changeset demo:query-04
create table if not exists site_api.product_properties (
    id bigserial primary key not null,
    product_id bigint references products(id) not null,
    caption varchar(255),
    value varchar(255)
    );
-- rollback DROP TABLE product_properties;