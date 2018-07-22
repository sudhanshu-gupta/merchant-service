
create table if not exists merchant_offer
(
    id bigint not null auto_increment,
    description varchar2,
    offer_price float,
    currency tinyint,
    start_time timestamp default CURRENT_TIMESTAMP,
    end_time timestamp,
    state tinyint default 0,
    merchant_id bigint not null,
    creation_time timestamp default CURRENT_TIMESTAMP,
    INDEX (start_time),
    INDEX (end_time),
    INDEX (state)
) default charset=utf8;

