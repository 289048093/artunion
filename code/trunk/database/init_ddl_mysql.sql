/******创建数据库******/
create database if not exists oplab default character set utf8;

drop table if exists tb_user;

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   ID_                  bigint not null,
   EMP_ID_              bigint,
   USERNAME_            char(30) not null,
   REALNAME_            char(30) not null,
   PASSWORD_            char(30) not null,
   SEX_                 tinyint not null,
   EMAIL_               varchar(50),
   TELEPHONE_           char(20),
   MOBILE_PHONE_        char(20),
   CHAT_                bool not null,
   PIC_                 varchar(255),
   DESC_                varchar(255),
   STATUS_              tinyint not null,
   ADD_TIME_            date not null,
   LAST_LOGIN_TIME_     date not null,
   primary key (ID_)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;