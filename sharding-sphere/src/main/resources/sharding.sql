-- 建库
create database ds1;
create database ds0;

-- 订单分表，按照年+月拆开表，建在ds0里面~
create table order_2201
(
    id          int auto_increment
        primary key,
    number      varchar(100) null comment '订单号',
    create_time datetime     null comment '创建日期'
);

create table order_2202
(
    id          int auto_increment
        primary key,
    number      varchar(100) null comment '订单号',
    create_time datetime     null comment '创建日期'
);

create table order_2203
(
    id          int auto_increment
        primary key,
    number      varchar(100) null comment '订单号',
    create_time datetime     null comment '创建日期'
);


