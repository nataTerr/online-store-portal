--liquibase formatted sql
--changeset demo:query-04
--validCheckSum: 8:6c5fe4eaa5e8af1bdd1796b2333fa016
create table if not exists site_api.product_properties (
    product_id bigint references products(id) not null,
    caption varchar(255),
    value varchar(255)
    );
-- rollback DROP TABLE product_properties;