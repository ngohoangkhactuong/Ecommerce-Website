create DATABASE ASM

USE ASM
GO

INSERT users (email,fullname,[password],phonenumber,role,[status]) VALUES ('tuongvi@gmail.com','Nguyen Thi Tuong Vi','123','0347050842',0,1)
INSERT users (email,fullname,[password],phonenumber,role,[status]) VALUES ('tuongvi090902@gmail.com','Nguyen Thi Tuong Vi','123','0347050842',1,1)
GO
INSERT sellers VALUES('Tuong Vi',1)
INSERT sellers VALUES('Khac tuong',1)
GO
INSERT users (email,fullname,[password],phonenumber,role,seller_sellId,[status]) VALUES ('tuongvi210702@gmail.com','Nguyen Thi Tuong Vi','123','0347050842',0,1,1)
INSERT users (email,fullname,[password],phonenumber,role,seller_sellId,[status]) VALUES ('khactuong@gmail.com','Nguyen Thi Tuong Vi','123','0347050842',0,2,1)



INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://product.hstatic.net/1000026602/product/99e4c5417377bc29e566_f0807997e380472e97df9971b853ecab.jpg','Ao polo',200,20,100,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://bizweb.dktcdn.net/100/415/697/products/ahu2keci-1-iirh-hinh-mat-truoc-01.jpg?v=1657108771117','Ao polo1',200,99,2,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/436821/item/goods_00_436821.jpg?width=1600&impolicy=quality_75','Ao polo2',200,99,5,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://bizweb.dktcdn.net/thumb/1024x1024/100/415/697/products/1-af0c84b6-d733-4bef-8dcf-d8a7d6bc30b8.jpg','Ao polo3',200,99,5,1,1)


INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://cf.shopee.vn/file/591a0853b958dd13fd55092615b17cef','Ao kieu',200,99,5,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://cf.shopee.vn/file/78ba402852527d81a4eb22c195a3aacc','Ao kieu1',200,99,5,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://cf.shopee.vn/file/535b777152ed5e0d543905855e1dbcf5','Ao kieu2',200,99,5,1,1)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://cf.shopee.vn/file/ae4bb6a3d774ec8b62899794cd7cd409','Ao kieu3',200,99,5,1,1)

INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://i0.wp.com/lotuka.com/wp-content/uploads/2022/03/4-1.jpg?fit=760%2C760&ssl=1','Ao body suit',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://www.kidsplaza.vn/blog/wp-content/uploads/2017/07/body-be-trai-768x766.jpg','Ao body suit1',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIJjm2fLHqh1_CQ8Op-mXSku3XfmD7qWS1kVOqq5-UTHVOau1D74A_OCTwtr-mRvcc11M&usqp=CAU','Ao body suit2',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://concung.com/2021/11/53000-77789-large_mobile/bodysuit-be-trai-tam-giac-animo-b1121002-0-3m-nau.jpg','Ao body suit3',200,99,5,1,2)

INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://thegioithoitrangbaby.com/wp-content/uploads/2020/12/vay-be-gai-gam-cot-no-da175-2.jpg','Vay cho be',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://daubapkids.com.vn/uploads/images/san-pham/be-gai/vay-tho-be-gai-mau-kem-vang-hoa-nhi-6.jpg','Vay cho be 1',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://nasashop.vn/wp-content/uploads/2021/05/vay-be-gai-xanh-hoa.jpeg','Vay cho be 2',200,99,5,1,2)
INSERT products(brand,color,[image],name,price,amount,stock,[status],seller_sellId) VALUES ('Tuong-Vi','Black','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIepY-SMQCq9glTu0TN7cIddsULqYQYw5jzHTVluaEPR1hqoQe_vqVBM9GJNnGtO665jQ&usqp=CAU','Vay cho be 3',200,99,5,1,2)
GO

INSERT categories VALUES('aaa','Quan ao nam')
INSERT categories VALUES('aaa','Quan ao nu')
INSERT categories VALUES('aaa','Quan ao be trai')
INSERT categories VALUES('aaa','Quan ao be gai')
GO

INSERT products_categories VALUES(1,1)
INSERT products_categories VALUES(1,2)
INSERT products_categories VALUES(1,3)
INSERT products_categories VALUES(1,4)


INSERT products_categories VALUES(2,5)
INSERT products_categories VALUES(2,6)
INSERT products_categories VALUES(2,7)
INSERT products_categories VALUES(2,8)


INSERT products_categories VALUES(3,9)
INSERT products_categories VALUES(3,10)
INSERT products_categories VALUES(3,11)
INSERT products_categories VALUES(3,12)


INSERT products_categories VALUES(4,13)
INSERT products_categories VALUES(4,14)
INSERT products_categories VALUES(4,15)
INSERT products_categories VALUES(4,16)
GO


