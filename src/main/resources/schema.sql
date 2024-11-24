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
    `coverImagine` varchar(255),
);

create table `Book`
(
    `iid` bigint primary key,
    `authors`      varchar(100),
    `publisher`    varchar(100),
    `introduction` varchar(512),
    foreign key (iid) references Item (iid)
);

create table `Movie`
(
    `iid` bigint primary key,
    `director`     varchar(100),
    `writers`      varchar(100),
    `cast`         varchar(255),
    `introduction` varchar(512),
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
    cdate date        not null,
    rating int        not null ,
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
            VALUES ('FuckGLP', '1', 'G', 'LP', '2000-02-29', 'Fuck@GLP.com', '123456789', 'UID1avatar.png');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (1, '菊花香', '2024-11-23', '[0,0,0,0,0]', 8.0, 'https://www.baidu.com');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUES(1,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUES(1,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(1,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (2, '美女视频', '2023-11-23', '[0,0,0,0,0]', 10.0, '1_OIP-C.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(2,'kobe man!','glp','菊花,耳朵','这个很好看');
INSERT INTO Category_Item(iid,`name`)
            VALUES(2,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(2,'热血');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (3, '奥特曼', '1993-11-23', '[0,0,0,0,0]', 10.0, 'https://www.baidu.com');
INSERT INTO `Book` (`iid`,`authors`,`publisher`,`introduction`)
            VALUES(3,'kobe man!','glp er','这个很好看');
INSERT INTO Category_Item(iid,`name`)
            VALUES(3,'唯美');
INSERT INTO Category_Item(iid,`name`)
            VALUES(3,'热血');
INSERT INTO Category_Item(iid,`name`)
            VALUES(3,'健康');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (4, '菊花香', '2024-11-20', '[0,0,0,0,0]', 8.0, 'IID1cover.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUES(4,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUES(4,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(4,'唯美');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (5, '菊花香', '2024-11-23', '[0,0,0,0,0]', 8.0, 'IID1cover.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUES(5,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUES(5,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(5,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (6, '菊花香', '2024-11-25', '[0,0,0,0,0]', 8.0, 'IID1cover.jpg');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUES(6,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUES(6,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(6,'唯美');

INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (7, '菊花香', '2024-11-23', '[0,0,0,0,0]', 8.0, 'https://www.baidu.com');
INSERT INTO `Music` (`iid`,`artists`,`lyrics`,`album`)
            VALUES(7,'jay zhou  man','菊花残，满地伤，你的菊花已泛黄','《菊花》');
INSERT INTO Category_Item(iid,`name`)
            VALUES(7,'鬼畜');
INSERT INTO Category_Item(iid,`name`)
            VALUES(7,'唯美');

INSERT INTO Comment VALUES (1,1,'2020-02-28',4,'TETysgccuwuwudhwudhwu');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (8, '再见爱人4', '2024-11-21', '[0,0,0,0,0]', 10.0, '1z.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(8,'刘乐','刘乐','胡彦斌 黄执中 黄圣依等','《再见爱人第四季》是芒果TV推出的婚姻纪实观察真人秀，胡彦斌、papi酱、黄执中、沈奕斐、侯佩岑、武艺组成观察团，黄圣依&杨子夫妇、麦琳&李行亮夫妇、葛夕&刘爽夫妇组成夫妻团 。该节目采用纪实手法记录三对面临婚姻危机的夫妻，观察他们踏上为期18天的“再爱之旅”，展露更多婚姻本相');
INSERT INTO Category_Item(iid,`name`)
            VALUES(8,'情感');
INSERT INTO Category_Item(iid,`name`)
            VALUES(8,'旅游');
INSERT INTO Category_Item(iid,`name`)
            VALUES(8,'真人秀');



INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (9, '浪客剑心', '2024-3-30', '[0,0,0,0,0]', 10.0, '2l.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(9,'大友启史','藤井清美、大友启史','佐藤健','该片改编自和月伸宏所著同名漫画，为庆祝《浪客剑心》的电视动画15周年而拍摄，以明治维新初期为背景，讲述了杀人不眨眼的剑客绯村剑心，在明治维新之后，发誓不再杀人，与各色人物因缘际会，展现他独特生活方式的故事 。该片在2012年获得锡切斯-加泰罗尼亚国际电影节最佳电影奖提名');
INSERT INTO Category_Item(iid,`name`)
            VALUES(9,'动作');
INSERT INTO Category_Item(iid,`name`)
            VALUES(9,'漫改');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (10, '肖申克的救赎', '2024-3-27', '[0,0,0,0,0]', 10.0, '3x.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(10,'弗兰克·德拉邦特','斯蒂芬·金、弗兰克·德拉邦特','蒂姆·罗宾斯、摩根·弗里曼','该片改编自斯蒂芬·埃德温·金1982年的中篇小说《肖申克的救赎》，主要讲述了银行家安迪因被误判为枪杀妻子及其情人的罪名入狱后，他不动声色、步步为营地谋划自我拯救并最终成功越狱，重获自由的故事 。1995年，该片获得第67届奥斯卡金像奖中包括最佳影片在内的七项提名 。该片累计票房约5830万美元 。');
INSERT INTO Category_Item(iid,`name`)
            VALUES(10,'犯罪');
INSERT INTO Category_Item(iid,`name`)
            VALUES(10,'剧情');
INSERT INTO Category_Item(iid,`name`)
            VALUES(10,'经典');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (11, '三傻大闹宝莱坞', '2024-3-20', '[0,0,0,0,0]', 10.0, '3s.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(11,'拉库马·希拉尼','维德胡·维诺德·乔普拉 、拉库马·希拉尼 、阿希贾特·乔希','阿米尔·汗、马德哈万、沙尔曼·乔什、卡琳娜·卡普','该片是根据奇坦·巴哈特的小说《五点人》改编而成，采用插叙的手法，讲述了法罕、拉加与兰彻在印度著名学府帝国工业大学中结下了深厚的友谊的故事。2014年，该片获得第37届日本电影学院奖最佳外语片提名。获得印度国际电影学院最佳影片大奖。截至2024年5月9日，该片在全球累计票房60,262,836美元。');
INSERT INTO Category_Item(iid,`name`)
            VALUES(11,'喜剧');
INSERT INTO Category_Item(iid,`name`)
            VALUES(11,'剧情');
INSERT INTO Category_Item(iid,`name`)
            VALUES(11,'爱情');
INSERT INTO Category_Item(iid,`name`)
            VALUES(11,'歌舞');




INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (12, '749局', '2024-10-31', '[0,0,0,0,0]', 10.0, '47.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(12,'陆川','陆川','王俊凯、苗苗、郑恺、任敏、辛柏青','该片灵感源于导演陆川军校毕业后，在749部队工作的一段经历，讲述了发生在2030年“近未来”故事，749局第三小队战士们应对巨大灾难时展现的少年成长故事');
INSERT INTO Category_Item(iid,`name`)
            VALUES(12,'冒险');
INSERT INTO Category_Item(iid,`name`)
            VALUES(12,'动作');
INSERT INTO Category_Item(iid,`name`)
            VALUES(12,'奇幻');



INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (13, '我不是药神', '2024-4-18', '[0,0,0,0,0]', 10.0, '5w.jpg');
INSERT INTO `Movie` (`iid`,`director`,`writers`,`cast`,`introduction`)
            VALUES(13,'文牧野','韩家女、钟伟','徐峥、周一围、王传君、谭卓、章宇、杨新鸣、王砚辉','改编自真实事件的影片《我不是药神》讲述了神油店老板程勇从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”独家代理商的故事。影片引起了国家层面对于抗癌药物的重视，促进了医疗制度、药品监管以及社会救助体系的更改。《我不是药神》是中国电影业的一次突破，是一部罕见的通过电影镜头反映社会意义的作品。该片获第15届精神文明建设“五个一工程”优秀作品奖、第35届大众电影百花奖优秀影片、第55届台湾电影金马奖最佳剧情长片等诸多奖项。首映当日获1.6亿元的中国票房，截止到2021年共获得4.51亿美元的全球票房。');
INSERT INTO Category_Item(iid,`name`)
            VALUES(13,'医疗');
INSERT INTO Category_Item(iid,`name`)
            VALUES(13,'剧情');
INSERT INTO Category_Item(iid,`name`)
            VALUES(13,'喜剧');





INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (14, '活着', '2023-12-24', '[0,0,0,0,0]', 10.0, 'huozhe.jpg');
INSERT INTO `Book` (`iid`,`authors`,`publisher`,`introduction`)
            VALUES(14,'余华','北京十月文艺出版社','《活着》是当代作家余华的代表作，讲述了一个人历尽世间沧桑和磨难的一生，亦将中国大半个世纪的社会变迁凝缩其间。《活着》还讲述了眼泪的宽广和丰富；讲述了绝望的不存在；讲述了人是为了活着本身而活着的，而不是为了活着之外的任何事物而活着。《活着》出版25年来打动了无数读者，经过时间的沉淀，已成为20世纪中国文学当之无愧的经典之作。到今天，已被译介至英、法、德、意、日、韩、俄等30多个国家和地区，获得国内外多个文学大奖，余华也凭借这部作品于2004年获得法兰西艺术和骑士文化勋章。');
INSERT INTO Category_Item(iid,`name`)
            VALUES(14,'社会');
INSERT INTO Category_Item(iid,`name`)
            VALUES(14,'小说');
INSERT INTO Category_Item(iid,`name`)
            VALUES(14,'现实主义');




INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (15, '三体', '2024-2-10', '[0,0,0,0,0]', 10.0, 'santi.jpg');
INSERT INTO `Book` (`iid`,`authors`,`publisher`,`introduction`)
            VALUES(15,'刘慈欣','重庆出版社','作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。《三体》的文本叙事在“后人类”的思考上有着重大突破，构建了科学与文学的互动范式，将道德内涵引入对科技的辩证思考中，并以文学手段在文化语境中对科技进行大胆假设和重构，但科技核心只是一个叙事跳板，是完成现实超越的重要媒介，也是人类命运共同体书写的重要工具。《三体》最吸引人的地方在于通过对人类中心主义的解构，继而完成对人与自然、动物之间的伦理反思与文学表达，最终指向去人类中心化的思想内核。其第一部经过刘宇昆翻译后获得了第73届雨果奖最佳长篇小说奖，第三部英文版获得2017年轨迹奖最佳长篇科幻小说奖。2019年，列入“新中国70年70部长篇小说典藏”。2022年9月，《三体》入选2021十大年度国家IP。');
INSERT INTO Category_Item(iid,`name`)
            VALUES(15,'科幻');
INSERT INTO Category_Item(iid,`name`)
            VALUES(15,'小说');


INSERT INTO `Item` (`iid`, `title`,`releaseDate`,`stars`,`rating`,`coverImagine`)
VALUES (16, '小王子', '2023-11-10', '[0,0,0,0,0]', 10.0, 'xiaowangzi.jpg');
INSERT INTO `Book` (`iid`,`authors`,`publisher`,`introduction`)
            VALUES(16,'安托万·德·圣-埃克苏佩里','译林出版社','主要讲述了“我”因飞机故障迫降到撒哈拉沙漠上,碰巧遇到一名来自外星的小人儿——小王子。通过与小王子的交流,“我”了解到他所居住的星球的奇特情况以及他在太空中的奇遇:他分别拜访了国王的、爱虚荣人的、酒鬼的、商人的、点灯人的、地理学家的星球,最后来到了巨大的地球。在地球上他又遇到了蛇、玫瑰花、狐狸、扳道工、商贩等等,由此他明白了生活的道理和生命的本质');
INSERT INTO Category_Item(iid,`name`)
            VALUES(16,'童话');
INSERT INTO Category_Item(iid,`name`)
            VALUES(16,'小说');
INSERT INTO Category_Item(iid,`name`)
            VALUES(16,'奇幻');






















