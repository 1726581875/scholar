DROP DATABASE IF EXISTS scholat;

CREATE DATABASE scholat;

--  使用数据库
use scholat

-- 创建用户登录表
CREATE TABLE `user_login`(
`user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
`user_phone` char(11) NOT NULL UNIQUE COMMENT '用户手机号',
`user_mail` varchar(32) NOT NULL UNIQUE COMMENT '用户邮箱',
`password` varchar(64) NOT NULL COMMENT '登录密码',
`role` int NOT NULL DEFAULT 1 COMMENT '用户权限:1普通用户,0系统管理员',
`login_time` DATETIME COMMENT  '登录时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';


-- 创建用户详情表
CREATE TABLE `user_detail`(
`user_id` int NOT NULL COMMENT '用户id',
`user_image`  varchar(128) NOT NULL COMMENT '用户头像',
`user_name` varchar(64) NOT NULL UNIQUE COMMENT '用户名字',
`user_sex` char(3) DEFAULT NULL COMMENT '性别,默认是null',
`user_age` int DEFAULT 0 COMMENT '年龄,0',
`user_major` varchar(64) DEFAULT NULL  COMMENT '专业',
`user_field` varchar(64) DEFAULT NULL  COMMENT '研究领域',
`user_type` varchar(10) DEFAULT NULL COMMENT '学生，或老师',
`user_school` varchar(32) DEFAULT NULL  COMMENT '学校',
`description` varchar(128) DEFAULT NULL  COMMENT '个人描述',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详情表';

-- 创建课程表
CREATE TABLE `course`(
`course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
`course_image` varchar(128) DEFAULT '/image/default.png' COMMENT '课程图标',
`course_name` varchar(36)  NOT NULL COMMENT '课程名称',
`user_id` int NOT NULL COMMENT '创建课程的人的id',
`course_desc` varchar(1024) NOT NULL COMMENT '课程简介',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`course_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';


-- 创建课程和用户关联表
CREATE TABLE `course_user`(
`course_id` int NOT NULL COMMENT '课程id',
`user_id` int NOT NULL COMMENT '该课程的用户id',
`class_name` varchar(36) default '外包2' COMMENT '班级',
`sno` varchar(36)  NOT NULL COMMENT '学号',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`course_id`,`user_id`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程用户表';


-- 创建问题表
CREATE TABLE `question`(
`question_id` int NOT NULL AUTO_INCREMENT COMMENT '问题id',
`course_id` int NOT NULL COMMENT '对应课程id',
`user_id` int NOT NULL COMMENT '提出问题的人的id',
`question_content` varchar(2048) NOT NULL COMMENT '问题内容',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(`question_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题表';


-- 创建问题回复表
CREATE TABLE `reply`(
`reply_id` bigint NOT NULL AUTO_INCREMENT COMMENT '回复表主键',
`question_id` int NOT NULL COMMENT '对应的评论id',
`user_id` int NOT NULL COMMENT '回复者id',
`reply_content` varchar(2048) NOT NULL COMMENT '评论内容',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(`reply_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论回复表';



-- 创建任务表，用于存老师发表的作业任务
CREATE TABLE `task`(
`task_id` int NOT NULL AUTO_INCREMENT COMMENT '任务id',
`course_id` int NOT NULL  COMMENT '所属课程Id',
`task_title` char(11) NOT NULL UNIQUE COMMENT '任务标题',
`task_content` varchar(2048) NOT NULL COMMENT '任务内容',
`annex_path` varchar(64) COMMENT '附件路径',
`end_time` DATETIME COMMENT  '截止时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`isDelaySubmit` int NOT NULL default 1 COMMENT '0表示可延迟提交，1表示不可延迟提交'
PRIMARY KEY (`task_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表表';


insert into task(course_id,task_title,task_content,annex_path)
values
(1,"作业一","随便写就行","E:\\fujian\\1\\"),
(1,"作业二","也是随便写就行","E:\\fujian\\2\\");


-- 创建作业表,存储学生提交的作业
CREATE TABLE `homework`(
`work_id` int NOT NULL AUTO_INCREMENT COMMENT '作业id',
`task_id` int NOT NULL  COMMENT '任务Id',
`user_id` int NOT NULL COMMENT '用户id',
`work_path` varchar(64) COMMENT '附件路径',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`status` int NOT NULL default 0 COMMENT '0表示未提交，1表示已提交',
`remark` varchar(100) COMMENT '教师评语',
'annex_path' varchar(64) COMMENT '教师回复的附件'
PRIMARY KEY (`work_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';


-- 创建通知表
CREATE TABLE `notice`(
`notice_id` int NOT NULL AUTO_INCREMENT COMMENT '通知id',
`send_id` int NOT NULL  COMMENT '发送方的用户Id',
`accept_id` int NOT NULL COMMENT '接收方的用户id或者班级id',
`notice_content` varchar(64)  COMMENT '通知内容',
`refuse_url` varchar(128)  COMMENT '拒绝',
`accept_url` varchar(128)  COMMENT '接受',
`notice_type` int NOT NULL COMMENT '1表示班级信息，2.个人信息，3.选择类通知',
`notice_flag` int NOT NULL DEFAULT 0 COMMENT '0表示没有查看，1表示已看，3表示已拒绝，4表示已同意',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送通知的时间',
PRIMARY KEY (`notice_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';


insert into notice(send_id ,accept_id,notice_content,notice_type,notice_flag,refuse_url,accept_url)
values
(1 , 2, 'springcloud从入门到放弃，要交作业啦',1,0,"http://localhost:9003/addCourse?courseId=3&userId=5&className=外包4&sno=2017764446&noticeId=1",""),
(1 , 2, '1111111',2,0,"",""),
(1 , 3, 'springcloud从入门到放弃，要交作业啦',1,0,"",""),
(1 , 1, '1111111',2,0,"",""),
(1 , 3, 'springcloud从入门到放弃，要交作业啦',1,0,"",""),
(1 , 4, '1111111',2,0,"","");



insert into homework(task_id,user_id,work_path)
values
(1,1,"E:\\fujian\\1\\2017764433-叶汝铿.docx"),
(1,2,"E:\\fujian\\2\\2017764446-肖明章.docx"),
(1,3,"E:\\fujian\\1\\2017764445-区淑燕.docx"),
(1,4,"E:\\fujian\\2\\2017764437-蓝淑贤.docx");


--  user_login插入数据、、密码是123
insert into user_login(user_phone,user_mail,password)
values
("12345678910","123@qq.com","123"),
("12345678911","124@qq.com","123"),
("12345678912","125@qq.com","123"),
("12345678913","126@qq.com","123"),
("12345678914","127@qq.com","123"),
("12345678915","128@qq.com","123"),
("12345678916","129@qq.com","123"),
("12345678917","1210@qq.com","123");

--  user_detail插入数据
insert into user_detail(user_id,user_image,user_name,user_sex,
user_major,user_field,user_type,user_school,description)
values
(1,'/image/default.png','root','男','软件工程','spring cloud','教师','岭南师范大学','我是老师'),
(2,'/image/default.png','小明','男','外包','spring cloud','学生','岭南师范大学','我是学生'),
(3,'/image/default.png','小李','女','心理','spring cloud','学生','岭南师范大学','我是学生'),
(4,'/image/default.png','小张','女','教师','spring cloud','学生','岭南师范大学','我是学生'),
(5,'/image/default.png','小黄','女','软件工程','spring cloud','教师','岭南师范大学','我是老师'),
(6,'/image/default.png','小绿','男','软件工程','spring cloud','教师','岭南师范大学','我是老师'),
(7,'/image/default.png','小蓝','男','软件工程','spring cloud','学生','岭南师范大学','我是学生'),
(8,'/image/default.png','小红','男','软件工程','spring cloud','学生','岭南师范大学','我是学生');



-- 课程表插入数据
insert into course(course_name,user_id,course_desc)
VALUES
("springcloud从入门到放弃","1","Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。Spring Cloud并没有重复制造轮子，它只是将各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。"),
("计算机组成原理","1","该书介绍了计算机的基本组成原理和内部工作机制。全书共分8章，主要内容分成两个部分：第1、2章介绍了计算机的基础知识；第3-8章介绍了计算机的各子系统（包括运算器、存储器、控制器、外部设备和输入输出子系统等）的基本组成原理、设计方法、相互关系以及各子系统互相连接构成整机系统的技术。"),
("hadoop大数据","2","Hadoop是一个由Apache基金会所开发的分布式系统基础架构。用户可以在不了解分布式底层细节的情况下，开发分布式程序。充分利用集群的威力进行高速运算和存储。Hadoop实现了一个分布式文件系统（Hadoop Distributed File System），简称HDFS。HDFS有高容错性的特点，并且设计用来部署在低廉的（low-cost）硬件上；而且它提供高吞吐量（high throughput）来访问应用程序的数据，适合那些有着超大数据集（large data set）的应用程序。HDFS放宽了（relax）POSIX的要求，可以以流的形式访问（streaming access）文件系统中的数据。Hadoop的框架最核心的设计就是：HDFS和MapReduce。HDFS为海量的数据提供了存储，而MapReduce则为海量的数据提供了计算。"),
("JAVA语言基础","1","Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程。"),
("springboot","1","Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。Spring Cloud并没有重复制造轮子，它只是将各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。"),
("mySql","1","该书介绍了计算机的基本组成原理和内部工作机制。全书共分8章，主要内容分成两个部分：第1、2章介绍了计算机的基础知识；第3-8章介绍了计算机的各子系统（包括运算器、存储器、控制器、外部设备和输入输出子系统等）的基本组成原理、设计方法、相互关系以及各子系统互相连接构成整机系统的技术。"),
("C语言","2","Hadoop是一个由Apache基金会所开发的分布式系统基础架构。用户可以在不了解分布式底层细节的情况下，开发分布式程序。充分利用集群的威力进行高速运算和存储。Hadoop实现了一个分布式文件系统（Hadoop Distributed File System），简称HDFS。HDFS有高容错性的特点，并且设计用来部署在低廉的（low-cost）硬件上；而且它提供高吞吐量（high throughput）来访问应用程序的数据，适合那些有着超大数据集（large data set）的应用程序。HDFS放宽了（relax）POSIX的要求，可以以流的形式访问（streaming access）文件系统中的数据。Hadoop的框架最核心的设计就是：HDFS和MapReduce。HDFS为海量的数据提供了存储，而MapReduce则为海量的数据提供了计算。"),
("养生","1","Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程。");


-- 课程_用户表插入数据
insert into course_user(course_id,user_id,class_name,sno)
values
(1,2,'外包1','00001'),
(1,3,'外包1','00002'),
(1,4,'外包1','00003'),
(1,5,'外包2','10001'),
(1,6,'外包2','10002'),
(1,7,'外包2','10003'),

(2,2,'外包2','00001'),
(2,3,'外包2','00002'),
(2,4,'外包2','00003'),
(2,5,'外包2','00004'),


(1,1,'外包2','00011'),
(2,1,'外包2','00011'),
(3,1,'外包2','00011'),
(4,1,'外包2','00011'),
(5,1,'外包2','00011'),
(6,1,'外包2','00011'),
(7,1,'外包2','00011'),
(8,1,'外包2','00011');

-- 问题表插入数据
insert into question(course_id,user_id,question_content) 
values
(1,1,'如何学习springcloud？'),
(1,2,'springcloud能干嘛？'),
(2,3,'你穷极一生最求啥？');

-- 问题回复表插入数据
insert into reply(question_id,user_id,reply_content)
values
(1,2,'多练习，多做项目，多请教大神。'),
(1,4,'渗透法，佛系法，量子法可以快速学习springcloud'),
(2,4,'springcloud可以帮助你掉头发'),
(2,5,'springcloud好像没啥用'),
(3,5,'这是个哲学问题'),
(3,4,'我大学数学老师说，人的一生都在追求快乐和逃避痛苦'),
(3,3,'吃饭、睡觉、发呆'),
(3,2,'电影说：要么忙着生，要么忙着死'),
(3,6,'铿哥说：世界那么大我想去看看');



