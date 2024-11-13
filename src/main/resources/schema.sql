-- 新增 Manager 表
create table Manager (
						 id identity,
						 userName varchar(20) unique not null,
						 password varchar(100) not null,
						 fullName varchar(50) not null,
						 email varchar(50) not null,
						 phoneNo varchar(15),
						 delete tinyint not null default 0
);

create table Commenter (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);

create table comment (
	id integer identity primary key,
	spitter integer not null,
	message varchar(2000) not null,
	postedTime datetime not null,
	ischecked boolean default false, -- 审核状态
	checker_id integer,               -- 审核人ID
	check_time datetime,              -- 审核时间
	foreign key (spitter) references spitter(id),
	foreign key (checker_id) references Manager(id)
);

-- 插入初始管理员数据
insert into Manager (userName, password, fullName, email, phoneNo, delete)
values ('admin', 'password', 'Administrator', 'admin@example.com', '1234567890', 0);

insert into Commenter (id, username, password, first_name, last_name, email)
values (1, 'JustinChen', '123456', 'Justin', 'Chen', 'cjwjustin@outlook.com');

insert into comment (spitter, message, postedTime)
values (1, 'Hello World!', '2018-01-01 00:00:00');
