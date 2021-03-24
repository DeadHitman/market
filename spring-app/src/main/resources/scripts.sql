create table shop."Product"
(
    id serial not null,
    title text not null,
    price int not null
);

create unique index product_id_uindex
    on shop."Product" (id);

alter table shop."Product"
    add constraint product_pk
        primary key (id);

