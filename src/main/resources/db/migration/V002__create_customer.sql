create table customer
(
    id varchar(36) not null,
    name varchar(255) not null,
    cpf varchar(11) not null,
    constraint customer_pk primary key (id),
    constraint customer_cpf_un unique (cpf)
);

