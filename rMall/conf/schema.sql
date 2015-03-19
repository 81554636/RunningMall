CREATE DATABASE `runningMall` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

insert into rmall_station(displayName,city,createdate,username,password) values('余姚','余姚',CURRENT_TIMESTAMP,'STID001','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('慈溪','慈溪',CURRENT_TIMESTAMP,'STID002','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('北仑','北仑',CURRENT_TIMESTAMP,'STID003','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('镇海','镇海',CURRENT_TIMESTAMP,'STID004','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('鄞州','鄞州',CURRENT_TIMESTAMP,'STID005','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('江东','江东',CURRENT_TIMESTAMP,'STID006','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('江北','江北',CURRENT_TIMESTAMP,'STID007','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('海曙','海曙',CURRENT_TIMESTAMP,'STID008','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('奉化','奉化',CURRENT_TIMESTAMP,'STID009','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('宁海','宁海',CURRENT_TIMESTAMP,'STID010','Pass2010');
insert into rmall_station(displayName,city,createdate,username,password) values('象山','象山',CURRENT_TIMESTAMP,'STID011','Pass2010');
	
insert into rmall_product(id,displayName,price,createDate,lastUpdate,url) values(1,'wine红酒',230.00,current_timestamp,current_timestamp,'http://121.40.58.21/imgs/wine.png');
insert into rmall_product(id,displayName,price,createDate,lastUpdate,url) values(2,'kiwi奇异果',120.00,current_timestamp,current_timestamp,'http://121.40.58.21/imgs/kiwi.png');
