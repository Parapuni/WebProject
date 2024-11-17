--需要插入原始数据

create table User{
    uid identity primary key,
    nickname varchar
(
    30
) unique not null,
    password varchar
(
    30
) not null,
    firstName varchar
(
    50
),
    lastName varchar
(
    50
),
    birthday date,
    email varchar
(
    50
) unique not null,
    `number` varchar
(
    20
) unique,
    avatar varchar
(
    255
),
    };

create table `Admin`{
    aid identity primary key,
    adminName varchar
(
    30
) unique not null,
    password varchar
(
    30
) not null,
    email varchar
(
    50
) unique not null,
    `number` varchar
(
    20
) unique,
    avatar varchar
(
    255
),
    };

create table Item{
    iid identity primary key,
    title varchar
(
    30
),
    releaseDate date,
    stars varchar
(
    80
),
    rating double,
    coverImagine varchar
(
    255
)
    };

create table Book {
    iid int primary key foreign key references to Item
(
    iid
),
    authors varchar
(
    100
),
    publisher varchar
(
    100
),
    introduction varchar
(
    255
)
    };

create table Movie {
    iid int primary key foreign key references to Item
(
    iid
),
    director varchar
(
    100
),
    writers varchar
(
    100
),
    `cast` varchar
(
    255
),
    introduction varchar
(
    255
)
    };

create table Music {
    iid int primary key foreign key references to Item
(
    iid
),
    artists varchar
(
    100
),
    lyrics varchar
(
    1000
)
    };

create table Comment{
    uid int,
    iid int,
    content varchar
(
    255
),
    primary key
(
    uid,
    iid
),
    foreign key
(
    uid
) references to `User`
(
    uid
),
    foreign key
(
    iid
) references to Item
(
    iid
)
    };

create table Category_Item{
    iid integer,
    `name` varchar
(
    20
),
    primary key
(
    iid,
    `name`
),
    foreign key
(
    iid
) references to Item
(
    iid
)
    };

