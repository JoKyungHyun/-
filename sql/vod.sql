drop table members;
drop table vod;

create table members (
	ID  varchar2(50) not null PRIMARY KEY,
	name varchar2(50) not null,
    sex varchar2(20) not null,
    addr varchar2(50) not null,
	pw varchar2(50) not null,
    rpw varchar2(50) not null
);
create table vod (
	title  varchar2(50) not null PRIMARY KEY,
	genre varchar2(50) not null,
    director varchar2(50) not null,
    score INT not null
);
commit;