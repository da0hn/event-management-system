create table event
(
    id varchar(36) not null,
    description varchar(255) null,
    published boolean default false not null,
    date date not null,
    total_spots int default 0 not null,
    total_spots_reserved int default 0 not null,
    partner_id varchar(36) not null,
    constraint event_pk primary key (id),
    constraint event_partner_id_fk foreign key (partner_id)
        references partner (id)
);

create table event_section
(
    id varchar(36) not null,
    name varchar(255) not null,
    description varchar(255) null,
    published boolean default false not null,
    price decimal not null,
    total_spots int default 0 not null,
    total_spots_reserved int default 0 not null,
    event_id varchar(36) not null,
    constraint event_section_pk primary key (id),
    constraint event_section_event_id_fk foreign key (event_id)
        references event (id)
);

create table event_spot
(
    id varchar(36) not null,
    reserved boolean default false not null,
    published boolean default false not null,
    location varchar(255) null,
    section_id varchar(36) not null,
    constraint event_spot_pk primary key (id),
    constraint event_spot_event_section_id_fk foreign key (section_id)
        references event_section (id)
);
