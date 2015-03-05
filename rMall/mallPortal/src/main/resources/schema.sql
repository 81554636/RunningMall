insert into rmall_station(id,name,createdate) 
	values(1,'南京小站',CURRENT_TIMESTAMP),(2,'上海小站',CURRENT_TIMESTAMP),(3,'杭州小站',CURRENT_TIMESTAMP);
insert into rmall_customer(id, name,phone,address,createDate,lastUpdate) 
	values(1, '凌晓舟','18616834285','虹梅路1801号新业园B栋3楼',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
insert into rmall_product(id,displayName,price,createDate,lastUpdate) values(1,'wine红酒',230.00,current_timestamp,current_timestamp),(2,'kiwi奇异果',120.00,current_timestamp,current_timestamp);