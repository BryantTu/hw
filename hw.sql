drop database if exists hw;

create DATABASE hw;

use hw

drop table if exists tb_class;

create table tb_class 
(
id int primary key auto_increment,
name varchar(20) unique not null,
createTime date
);

insert into tb_class values (default, 'J111', '2014-10-1');
insert into tb_class values (default, 'J112', '2015-5-1');

drop table if exists tb_student;
create table tb_student
(
id int primary key,
name varchar(20) not null,
gender bit not null,
tel char(11),
class_id int references tb_class(id)
);

insert into tb_student values (1001, 'Âæê»', 1, '13345678900', 1); 
insert into tb_student values (1002, '¹ù¾¸', 1, '13345678900', 1);
insert into tb_student values (1003, 'ÕÅÈı·á', 1, '13345678900', 1);
insert into tb_student values (1122, 'ÀîÄª³î', 0, '13345678900', 1);
insert into tb_student values (1333, 'ÍõÖØÑô', 1, '13345678900', 1);
insert into tb_student values (1009, 'Çğ´¦»ú', 1, '13345678900', 1);
insert into tb_student values (1566, 'ÄÂÄî´È', 0, '13345678900', 1);
insert into tb_student values (1789, '¶ÎÓş', 1, '13345678900', 2);
insert into tb_student values (3345, '»ÆÈØ', 0, '13345678900', 2);