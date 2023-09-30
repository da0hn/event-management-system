create table `order`
(
    id varchar(36) not null,
    customer_id varchar(36) not null,
    amount decimal(18, 2) not null,
    spot_id varchar(36) not null,
    status varchar(30) not null
);

