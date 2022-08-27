create database prs;
use prs; 

create table admin(
                      id int primary key auto_increment,
                      name varchar(30),
                      pwd varchar(100),
                      addtime varchar(30)
);

create table user(
                     id int primary key auto_increment,
                     name varchar(30),
                     pwd varchar(100),
                     phone varchar(30),
                     mailbox varchar(30),
                     addtime varchar(30)
);

create table paperinfo(
                          id int primary key auto_increment,
                          classnum varchar(30), -- 分类号
                          title varchar(100), -- 论文标题
                          author varchar(30), -- 作者
                          abstracts varchar(500), -- 摘要
                          keyword varchar(50), -- 关键字
                          genre varchar(30), -- 期刊名称
                          type varchar(30), -- 论文类别
                          time varchar(30), -- 发表时间
                          download int, -- 下载次数
                          address varchar(50), -- 所在位置
                          addtime varchar(30) -- 添加时间
);

create table subtitle(
                     id int primary key auto_increment,
                     name varchar(30),
                     num int
);

insert into user values (1,'name1','0fc7a6d358ede1438e51944fc86e986403baf5fd2d8c6677d03b1734c6813334','123456789','123456789@qq.com','2021-09-23'); # 密码为123
insert into user values (2,'name2','9037b33762a228811f351c194817e38262e5fbfc2a6668cf7a29b6f38ecf82d6','987654321','987654321@qq.com','2021-09-23'); # 密码为1234
insert into user values (3,'name3','08c37205403196449be4c1d399c2f682fc9b0e36e53a54ba82e8a0b0d37e56fb','132123321','123321132@163.com','2021-09-23'); # 密码为12345

insert into admin values(1,'admin','ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270','2021-09-23'); # 密码为1234

insert into paperinfo values(1,'G354','智能检索模型研究','孔敬','提出了一个智能检索形式框架模型，论述了实例化该模型的建模技术、知识表示和检索算法，对30个智能信息检索系统进行了模型框架、知识表示和检索算法的统计分析，总结了三种类型的智能检索模型实例化方案。','智能检索模型；建模技术；知识表示；检索算法','现代图书情报技术','期刊文章','2004-10-28',0,'upload/智能检索模型研究_孔敬.pdf','2021-09-23');
insert into paperinfo values(2,'TP311.138','基于ASP_SQL_Server_省略_的Web文献检索系统及其查询优化','宋阳','介绍了基于ASP、SQL Server2000实现的Web文献检索系统及其查询优化。通过调试、运行,Web文献检索系统运行正常,查询效率高。基于ASP、SQL Server2000实现的Web文献检索系统简单、安全、稳定,维护方便。','ASP；SQL Server2000；Web文献检索系统；查询优化','计算机应用与软件','期刊文章','2006-10',0,'upload/基于ASP_SQL_Server_省略_的Web文献检索系统及其查询优化_宋阳.pdf','2021-09-23');
insert into paperinfo values(3,'TP393','国内实用网络文献检索系统','李卫','简要介绍和评价了目前国内几个实用的网络文献检索系统：CNKI 中国知网、万方数据资源系统、中国科技图书文献中心网和天元数据网，为在实际工作中选择适当的检索工具提高文献检索的效率和质量提出了很好的建议。','计算机网络；文献检索系统；应用系统；建议','铁路计算机应用','期刊文章','2004-09',0,'upload/国内实用网络文献检索系统_李卫.pdf','2021-09-23');
insert into paperinfo values(4,'TP274','学术期刊电子论文检索系统设计','蒋从文','设计了一种能将各个学术期刊网站上的电子论文信息采集到一个统一的数据库中并提供检索的系统。系统分为数据采集、数据分析和存储、数据检索3个模块。前两个模块负责将互联网上电子论文的内容结构化存储到本地数据库，最后一个模块负责对数据库内容生成索引并提供查询。目前，该系统已存有150万篇中文期刊论文。','数据采集；数据检索；Sphinx；全文索引','电子科技','期刊文章','2014-02',0,'upload/学术期刊电子论文检索系统设计_蒋从文.pdf','2021-09-23');

insert into subtitle values (1,'计算',0);
insert into subtitle values (2,'计算机',0);
insert into subtitle values (3,'计算机科学',0);
insert into subtitle values (4,'计算机网络',0);
insert into subtitle values (5,'计算机应用',0);
insert into subtitle values (6,'计算机视觉',0);
insert into subtitle values (7,'计算机系统',0);
insert into subtitle values (8,'计算机技术',0);
insert into subtitle values (9,'系统',0);
insert into subtitle values (10,'系统结构',0);
insert into subtitle values (11,'数学',0);
insert into subtitle values (12,'数学建模',0);
insert into subtitle values (13,'数学模型',0);
insert into subtitle values (14,'数学文化',0);
insert into subtitle values (15,'数据',0);
insert into subtitle values (16,'数据分析',0);
insert into subtitle values (17,'数据挖掘',0);
insert into subtitle values (18,'数据库',0);
insert into subtitle values (19,'数据可视化',0);
insert into subtitle values (20,'数据处理',0);
