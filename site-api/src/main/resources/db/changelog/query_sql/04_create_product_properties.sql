--liquibase formatted sql
--changeset demo:query-04
--validCheckSum: 8:3d02dad128b08a64b7545c484dd74e0d
create table if not exists site_api.product_properties (
    product_id bigint references products(id) not null,
    caption varchar(255),
    val varchar(255)
    );
-- rollback DROP TABLE product_properties;