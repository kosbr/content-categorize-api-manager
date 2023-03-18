CREATE TABLE tasks
(
    id              uuid not null,
    url             varchar(200) not null,
    status          int not null,
    categories      varchar(200),
    error           varchar(200),
    created         timestamp with time zone not null,
    updated         timestamp with time zone not null,
    primary key (id)
)