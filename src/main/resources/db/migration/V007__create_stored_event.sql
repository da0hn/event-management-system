create table stored_event
(
    id varchar(36) not null,
    body json not null,
    occurred_on datetime not null,
    type_name varchar(50) not null,
    constraint stored_event_pk
        primary key (id)
);
