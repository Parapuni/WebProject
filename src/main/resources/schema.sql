--需要插入原始数据

create table `User`
(
    `uid` identity primary key,
    `nickname`  varchar(30) unique not null,
    `password`  varchar(30)        not null,
    `firstName` varchar(50),
    `lastName`  varchar(50),
    `birthday`  date,
    `email`     varchar(50) unique not null,
    `number`    varchar(20) unique,
    `avatar`    varchar(255)
);

create table `Admin`
(
    `aid` identity primary key,
    `adminName` varchar(30) unique not null,
    `password`  varchar(30)        not null,
    `email`     varchar(50) unique not null,
    `number`    varchar(20) unique,
    `avatar`    varchar(255)
);

create table `Item`
(
    `iid` identity primary key,
    `title`        varchar(30),
    `releaseDate`  date,
    `stars`        varchar(80),
    `rating` double,
    `coverImagine` varchar(255)
);

create table `Book`
(
    `iid` bigint primary key,
    `authors`      varchar(100),
    `publisher`    varchar(100),
    `introduction` varchar(255),
    foreign key (iid) references Item (iid)
);

create table `Movie`
(
    `iid` bigint primary key,
    `director`     varchar(100),
    `writers`      varchar(100),
    `cast`         varchar(255),
    `introduction` varchar(255),
    foreign key (iid) references Item (iid)
);

create table `Music`
(
    `iid`   bigint primary key,
    `artists` varchar(100),
    `lyrics`  varchar(1000),
    `album` varchar(100),
    foreign key (iid) references Item (iid)
);

create table `Comment`
(
    `iid` bigint,
    `uid` bigint,
    uname varchar(30) not null,
    cdate date        not null,
    `content` varchar(255),
    primary key (uid, iid),
    foreign key (uid) references `User` (uid),
    foreign key (iid) references Item (iid)
);

create table Category_Item
(
    iid bigint,
    `name` varchar(20),
    primary key (iid, `name`),
    foreign key (iid) references Item (iid)
);

INSERT INTO `User` (`nickname`, `password`, `firstName`, `lastName`, `birthday`, `email`, `number`, `avatar`)
            VALUES ('FuckGLP', '1', 'G', 'LP', '2000-02-29', 'Fuck@GLP.com', '123456789', 'https://www.baidu.com');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(1,'菊花香',DATE('2024-11-23'),'[0,0,0,0,0]',8.0,'/imagines/book1.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUE(1,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUE(1,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(1,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(2,'美女视频',DATE('2023-11-23'),'[0,0,0,0,0]',10.0,'/imagines/book1.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUE(2,'kobe man!','glp','菊花,耳朵','这个很好看');
INSERT INTO Category_Item(iid,`name`)
            VALUE(2,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(2,'热血');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(3,'奥特曼',DATE('1993-11-23'),'[0,0,0,0,0]',10.0,'/imagines/book1.jpg');
INSERT INTO `Book` (`iid`,`authors`,`publisher`,`introduction`)
            VALUE(3,'kobe man!','glp er','这个很好看');
INSERT INTO Category_Item(iid,`name`)
            VALUE(3,'唯美');
INSERT INTO Category_Item(iid,`name`)
            VALUE(3,'热血');
INSERT INTO Category_Item(iid,`name`)
            VALUE(3,'健康');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(4,'菊花香',DATE('2024-11-23'),'[0,0,0,0,0]',8.0,'/imagines/book1.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUE(4,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUE(4,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(4,'唯美');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(5,'菊花香',DATE('2024-11-23'),'[0,0,0,0,0]',8.0,'/imagines/book1.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUE(5,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUE(5,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(5,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(6,'菊花香',DATE('2024-11-23'),'[0,0,0,0,0]',8.0,'/imagines/book1.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUE(6,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUE(6,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(6,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
            VALUE(7,'菊花香',DATE('2024-11-23'),'[0,0,0,0,0]',8.0,'/imagines/book1.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUE(7,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUE(7,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUE(7,'唯美');
