use sample;

create table store(
barcode integer primary key,
productname varchar(30) not null,
price integer not null,
amount integer
)ENGINE=MyISAM Default charset=utf8;

/*현재 데이터베이스에 만들어진 테이블 확인*/
show tables;

insert into store(barcode, productname, price, amount) values(12345678,'크런키',1000,50);

select * from store;

drop table store;

create table store(
barcode varchar(10) primary key,
productname varchar(30) not null,
price integer not null,
amount integer
)ENGINE=MyISAM Default charset=utf8;

insert into store(barcode, productname, price, amount) values('1000000001','크런키',1000,50);

select * from store;

/*name을 포합하는 데이터를 찾아오는 SQL*/
select * from store where trim(lower(productname)) LIKE trim(lower('%가%'));