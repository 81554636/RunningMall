CREATE DATABASE `running` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

insert into rmall_station(displayName,city,createdate,username,password) values('象山','象山',CURRENT_TIMESTAMP,'STID011','Pass2010');
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

insert into rmall_product(id,displayName,description,place,createDate,lastUpdate,url,isValid) 
	values(1,'wine红酒-注册送好礼','正品佳酿,口感清爽','意大利',current_timestamp,current_timestamp,'http://www.metro.com.cn/~/media/CN-Metro/image/campaign-image/others/woty-2014-iv-940x200.jpg',false);
insert into rmall_product(id,displayName,description,place,createDate,lastUpdate,url,isValid) 
	values(2,'kiwi奇异果-注册送好礼','果肉鲜滑多汁','新西兰',current_timestamp,current_timestamp,'http://cdn.fruitday.com/up_images/1430200859.jpg',false);

insert into rmall_product(id,displayName,description,place,createDate,lastUpdate,url,isValid)
	values(1001,'wine红酒','正品佳酿,口感清爽','意大利',current_timestamp,current_timestamp,'http://121.40.58.21/imgs/wine_001.jpg',true);
insert into rmall_product(id,displayName,description,place,createDate,lastUpdate,url,isValid)
	values(1002,'kiwi奇异果','果肉鲜花多汁','新西兰',current_timestamp,current_timestamp,'http://121.40.58.21/imgs/kiwi_001.jpg',true);
insert into rmall_product(id,displayName,description,place,createDate,lastUpdate,url,isValid)
	values(1003,'rice稻花香米','原产,原制,原味','哈尔滨',current_timestamp,current_timestamp,'http://121.40.58.21/imgs/rice_001.jpg',true);

insert into rmall_specs(name,price,original,min,max,productID) values("4只/盒", 0, 20, 0, 1, 2);

insert into rmall_specs(name,price,original,min,max,productID) values("1KG/箱", 120, 145, 0, 255, 1002);
insert into rmall_specs(name,price,original,min,max,productID) values("2KG/箱", 220, 300, 0, 255, 1002);

insert into rmall_specs(name,price,original,min,max,productID) values("1L/瓶", 230, 300, 0, 255, 1001);
insert into rmall_specs(name,price,original,min,max,productID) values("4L/箱", 800, 1000, 0, 255, 1001);

insert into rmall_specs(name,price,original,min,max,productID) values("1KG/盒", 45, 60, 0, 255, 1003);

insert into rmall_product_imgs(product_id,index_,imgUrl) values(2,0,'http://121.40.58.21/imgs/kiwi.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(2,1,'http://121.40.58.21/imgs/kiwi.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1001,0,'http://121.40.58.21/imgs/wine.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1001,1,'http://121.40.58.21/imgs/wine.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1002,0,'http://121.40.58.21/imgs/kiwi.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1002,1,'http://121.40.58.21/imgs/kiwi.png');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1003,0,'http://121.40.58.21/imgs/rice_001.jpg');
insert into rmall_product_imgs(product_id,index_,imgUrl) values(1003,1,'http://121.40.58.21/imgs/rice_001.jpg');

insert into rmall_product_areas(product_id,area,index_) values(1,'象山',0);
insert into rmall_product_areas(product_id,area,index_) values(2,'象山',0);
insert into rmall_product_areas(product_id,area,index_) values(1001,'象山',0);
insert into rmall_product_areas(product_id,area,index_) values(1002,'象山',0);
insert into rmall_product_areas(product_id,area,index_) values(1003,'象山',0);

INSERT INTO `rmall_activity`(id,description,isValid,img,productID) VALUES (1,'跑跑上线, 注册送好礼',1,'http://121.40.58.21/imgs/activity_001.jpg',2);
--insert rmall_activity(id,description,isValid) values(1,'跑跑上线, 注册送好礼',true);
--insert into rmall_activityDetails(activityID,productID,`index`) values(1,1,0);
--insert into rmall_activityDetails(activityID,productID,`index`) values(1,2,1);

delete from rmall_shipDetails;
delete from rmall_orderDetails;
delete from rmall_orderItem;
delete from rmall_order;
delete from rmall_shipment;
delete from rmall_delivery;
delete from rmall_customer;

delete from rmall_station;
delete from rmall_activity;

delete from rmall_product_areas;
delete from rmall_product_imgs;
delete from rmall_specs;
delete from rmall_product;