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

alter table shop.cart drop constraint fkdebwvad6pp1ekiqy5jtixqbaj;

alter table shop.cart
    add constraint fkdebwvad6pp1ekiqy5jtixqbaj
        foreign key (customer_id) references shop.customer
            on update cascade on delete cascade;

alter table shop.cart drop constraint fk3d704slv66tw6x5hmbm6p2x3u;

alter table shop.cart
    add constraint fk3d704slv66tw6x5hmbm6p2x3u
        foreign key (product_id) references shop.product
            on update cascade on delete cascade;

