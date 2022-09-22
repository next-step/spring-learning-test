CREATE TABLE payment
(
    id          bigint       not null auto_increment,
    member_name varchar(100) not null,
    amount      bigint       not null,
    primary key (id)
);

CREATE TABLE play
(
    id          bigint       not null auto_increment,
    member_name varchar(100) not null,
    datetime    timestamp    not null,
    primary key (id)
);