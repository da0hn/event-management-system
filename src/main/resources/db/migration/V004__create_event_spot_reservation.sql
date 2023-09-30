create table event_spot_reservation
(
    spot_id varchar(36) not null,
    customer_id varchar(36) not null,
    reservation_date date not null,
    constraint event_spot_reservation_pk primary key (SPOT_ID),
    constraint event_spot_reservation_customer_id_fk foreign key (CUSTOMER_ID)
        references customer (id),
    constraint event_spot_reservation_event_spot_id_fk foreign key (spot_id)
        references event_spot (id)
);

