
-- 待办表
create table if not exists t_memorandum
(
    id      int primary key auto_increment,
    title   varchar null_to_default comment '标题',
    content varchar null_to_default  comment '内容'
);
