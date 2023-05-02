-- user
drop table if exists `user`;
create table user
(
    id          bigint             not null comment '主键id' primary key,
    username    varchar(50)        not null comment '用户名',
    password    varchar(64)        not null comment '密码',
    email       varchar(64) null comment '邮箱',
    phone       varchar(11) null comment '手机号',
    type        int      default 0 not null comment '类别',
    status      int      default 0 not null comment '状态',
    create_time datetime default current_timestamp null,
    update_time datetime default current_timestamp null on update current_timestamp comment '更新时间',
    is_delete   tinyint  default 0 null comment '是否删除'
);


