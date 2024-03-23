
create database food_test11
use food_test11

CREATE TABLE [Role](
	[role_id] int not null identity(1,1) primary key,
	[role_name] nvarchar(32),
);

CREATE TABLE Account (
	[acc_id] INT IDENTITY(1,1) PRIMARY KEY,
	[username] NVARCHAR(32),  -- max length name is 32
	[password] NVARCHAR(60),  -- ...
	[role_id] INT,   -- user: 1 (default), seller: 2, admin: 3
	[is_block] INT,   --  block = 1
	[coin] INT,                 
	[create_time] DATE,   -- date create account
	[status] INT,
	FOREIGN KEY (role_id) REFERENCES [Role](role_id)
)

CREATE TABLE Account_Details (
	acc_id int,
    nickname NVARCHAR(255),
	[image] NVARCHAR(MAX), 
    email NVARCHAR(120),
    phone NVARCHAR(15),
    gender INT,  
    dateOfBirth DATE,
    -- Định nghĩa khóa ngoại để liên kết với bảng ACCOUNT
    FOREIGN KEY (acc_id) REFERENCES Account (acc_id)
)


CREATE TABLE Account_Address (
    acc_id int,
    nickname NVARCHAR(MAX),
    phone_addr NVARCHAR(15),           -- phone to receive order
    [address] NVARCHAR(MAX),			
    note NVARCHAR(MAX),
    [status] NVARCHAR(50),			   --
    -- Tạo khóa ngoại để liên kết Account_Address với Account
     FOREIGN KEY (acc_id) REFERENCES Account (acc_id)
);

CREATE TABLE Category (
    id INT PRIMARY KEY IDENTITY(1,1),
    [name] NVARCHAR(255)
);

CREATE TABLE Seller (
	seller_id INT PRIMARY KEY IDENTITY(1,1),
	acc_id INT,
	phone_store NVARCHAR(20),
	email_store NVARCHAR(120),
	[address_store] NVARCHAR(MAX),
	store_name NVARCHAR(40),
	number_of_foods INT,    -- number of product that seller have
	store_opentime DATE,
	rating_store float,             -- rate = average of all rating product
	follower INT,			      -- Số lượng người theo dõi cửa hàng 
	is_active INT,                -- 0 chưa được xác nhận, 1 đã xác nhận bởi admin
	image_store NVARCHAR(MAX),
	note NVARCHAR(MAX),          -- admin send contact to seller (hide/inactive/...)
	CONSTRAINT FK_Seller_Account FOREIGN KEY (acc_id)
	REFERENCES Account (acc_id)
);

CREATE TABLE Product (
    pid INT PRIMARY KEY IDENTITY(1,1),
    category_id INT,
	seller_id INT,
    [image] NVARCHAR(MAX),
    title NVARCHAR(MAX),   
    old_price INT,
    current_price INT,
    amount_of_sold INT,          
    [number_in_stock] INT,       -- number
    [status] NVARCHAR(MAX),      -- yeu thich/ het hang/ khuyen mai/ sale...
    describe NVARCHAR(MAX),		 	
	rating FLOAT,

	-- Tạo khóa ngoại để liên kết Product với Category
	CONSTRAINT FK_Product_Category FOREIGN KEY (category_id)
	REFERENCES Category (id),
	-- Tạo khóa ngoại để liên kết Product với Seller
	CONSTRAINT FK_Product_Seller FOREIGN KEY (seller_id)
	REFERENCES Seller (seller_id)
);


CREATE TABLE Orders (           -- Đơn hàng
    order_id INT PRIMARY KEY IDENTITY(1,1),
	acc_id INT,
	seller_id INT,
    nickname NVARCHAR(32),      -- Tên khách hàng
	phone NVARCHAR(20),
	email NVARCHAR(60),
	[address] NVARCHAR(MAX),
	note NVARCHAR(MAX),
	is_shipped INT,     -- Phương thức giao hàng (ví dụ:  0 giao tận nơi, 1 đến nhận hàng)
	is_delivered INT,   -- Trạng thái giao hàng (ví dụ:  0 chưa giao, 1 đang giao, 2 đã giao)
	is_accepted INT,    -- Trạng thái chấp nhận đơn đặt hàng (ví dụ: -2 đã hủy by seller, -1 đã hủy by user, 0 chưa chấp nhận, 1 đã chấp nhận)
	is_purchased INT,   -- Trạng thái đã thanh toán đơn hàng hay chưa ( có thể dùng cho thanh toán = ví ) 
	order_date DATETIME,    -- Ngày đặt hàng
	discount INT,       -- Giảm giá (nếu có)
	total_price INT,
    -- Tạo khóa ngoại để liên kết Orders với Account
    CONSTRAINT FK_Orders_Account FOREIGN KEY (acc_id)
    REFERENCES Account (acc_id),
	CONSTRAINT FK_Order_Seller FOREIGN KEY (seller_id)
	REFERENCES Seller (seller_id)
);

CREATE TABLE OrderDetail (
	detail_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT, -- ID đơn đặt hàng
	product_id INT,     -- ID sản phẩm
    product_title NVARCHAR(MAX), -- title sản phẩm
	product_image NVARCHAR(MAX),
	product_catename NVARCHAR(MAX),
	product_storename NVARCHAR(MAX),
	product_status NVARCHAR(MAX),
	product_oldprice INT,
	product_currentprice INT,
    quantity INT, -- Số lượng sản phẩm đặt hàng
    total_price INT, -- Tổng tiền
    is_feedback INT,    -- Đánh giá đơn đặt hàng (ví dụ:  0 chưa đánh giá , 1 đã đánh giá)

    -- Tạo khóa ngoại để liên kết OrderDetail với Orders
    CONSTRAINT FK_OrderDetail_Orders FOREIGN KEY (order_id)
    REFERENCES Orders (order_id),
    -- Tạo khóa ngoại để liên kết OrderDetail với Product
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (product_id)
    REFERENCES Product (pid),
);

CREATE TABLE FeedBack (
	feed_id INT PRIMARY KEY IDENTITY(1,1),
	product_id INT,
	cate_name NVARCHAR(255),
	acc_id INT,
	username NVARCHAR(255),   -- để hiện thị tên của user nào đã đánh giá
	feed_date NVARCHAR(120),  -- ngày đánh giá đơn
	rate INT,
	describe NVARCHAR(255),       -- có đúng sản phẩm đã đặt ko 
	[delivery] NVARCHAR(255),	  -- 
	[standard] NVARCHAR(255),     -- hương vị
	content NVARCHAR(MAX),        -- nội dung chi tiết feedback
	[image] NVARCHAR(255), 
	incognito INT,                  -- hiện thị tên khi đánh giá
	[status] int,                 -- hiện thị feedback hay ko 
	FOREIGN KEY (product_id) REFERENCES Product(pid),
	FOREIGN KEY (acc_id) REFERENCES Account(acc_id),
);

CREATE TABLE Notifications (
    notify_id INT PRIMARY KEY IDENTITY(1,1),
    acc_id INT,  -- ID của người dùng nhận thông báo
	[image] NVARCHAR(MAX),
	[title] NVARCHAR(MAX),
    [message] NVARCHAR(MAX),  -- Nội dung thông báo
    [timestamp] DATETIME,  -- Thời gian gửi thông báo
    [url] NVARCHAR(MAX),
    is_read INT,  -- Trạng thái đã đọc hay chưa
	is_hide INT,  
    CONSTRAINT FK_Notifications_Account FOREIGN KEY (acc_id)
    REFERENCES Account (acc_id)
);
/*
INSERT INTO Notifications (acc_id, [image], [title], [message], [timestamp], [is_read], [is_hide], [url])
VALUES (, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)
*/

CREATE TABLE SearchHistory (
    his_search_id INT PRIMARY KEY IDENTITY(1,1),
    acc_id INT,  -- ID của người dùng thực hiện tìm kiếm
    search_query NVARCHAR(MAX),  -- Truy vấn tìm kiếm
    search_timestamp DATETIME,  -- Thời điểm thực hiện tìm kiếm
    CONSTRAINT FK_SearchHistory_Account FOREIGN KEY (acc_id)
    REFERENCES Account (acc_id)
);

CREATE TABLE Menu (
    menu_id INT PRIMARY KEY IDENTITY(1,1),
    seller_id INT,
	[status] int,
    menu_date DATE,
	menu_name NVARCHAR(MAX),
	 is_sale int,  -- boolean
	 sale_off int,  -- %sale
    FOREIGN KEY (seller_id) REFERENCES Seller(seller_id)
);

CREATE TABLE Menu_Item (
	menu_item_id INT PRIMARY KEY IDENTITY(1,1),
    menu_id INT,
    product_id INT,
	FOREIGN KEY (product_id) REFERENCES Product(pid),
    FOREIGN KEY (menu_id) REFERENCES Menu(menu_id),
);



CREATE TABLE Report_Types (
    report_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    report_name NVARCHAR(255),
);

CREATE TABLE Report_Requests (
    request_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    acc_id INT,  -- ID of the user requesting the report
    report_id INT,  -- ID of the requested report type
	reportedthing_id Int,
    report_description NVARCHAR(MAX),
    request_timestamp NVARCHAR(255),
    [status] INT,  -- Status of the report request (e.g., pending, accepted, rejected)
    proof NVARCHAR(MAX),  -- Proof associated with the report request
	[type] INT, -- 0: Report seller, 1: Report Product of that seller,
    approver_id INT,  -- ID of the user who approved the report request


    -- Add other relevant fields as needed
    FOREIGN KEY (acc_id) REFERENCES Account(acc_id),
    FOREIGN KEY (report_id) REFERENCES Report_Types(report_id),
);

CREATE TABLE Payment_History(
    payment_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	order_id INT,
	payment_code VARCHAR(100),
	amount INT,
	info VARCHAR(MAX),
	error_code VARCHAR(10),
	vnpayQRCode VARCHAR(100),
	bankCode VARCHAR(100),
	payDate VARCHAR(100),
	[status] NVARCHAR(100)

	FOREIGN KEY (order_id) REFERENCES Orders(order_id),
);

INSERT INTO [Role] VALUES ('user'), ('seller'),('admin')

INSERT INTO [Account] ([username], [password], [role_id], [is_block], [create_time], coin, [status]) VALUES 
('admin', '1', 3, 0, GETDATE(), 200, 0),   -- acc_id = 1
('jisoo', '1', 1, 0, GETDATE(), 200, 0),   --        = 2
('seller', '1', 2, 0, GETDATE(), 200, 0),	--		  = 3
('jennie', '1', 2, 0, GETDATE(), 200, 0),
('lisa', '1', 2, 0, GETDATE(), 200, 0),	
('seller2', '1', 2, 0, GETDATE(), 200, 0),
('rose', '1', 2, 0, GETDATE(),200, 0),
(N'Phạm Hoan', '1', 1, 0, GETDATE(),200, 0),
('user', '1', 1, 0, GETDATE(), 200, 0);

INSERT INTO SearchHistory (acc_id, search_query, search_timestamp) VALUES (1, 'com', GETDATE())

INSERT INTO [Category] ([name]) VALUES
(N'Cơm Rang / Thường'),
(N'Bún / Phở'),
(N'Đồ Uống'),
(N'Đồ Ăn Nhanh'),
(N'Bánh Bao / Bánh Mỳ / Xôi')


INSERT INTO [Seller] (acc_id, phone_store, email_store, address_store, store_name, number_of_foods, store_opentime, rating_store, follower, is_active, image_store, note) VALUES 
(3, '0326644123', 'hoan@123', N'Quán ăn Thứ 1, Hà Nội Phố, Canteen số 1', N'HanoiPho', 5, '2023-05-23', 4.9, 120, 1, '', ''),
(4, '0326415882', 'hoan@123', N'Quán ăn Thứ 2, Quán Simdo, Canteen số 1', N'Simdo', 5, '2021-11-23', 4.7, 120, 1, '', ''),
(5, '0325545454', 'hoan@123', N' Quán ăn Thứ 3, Quán Quang Anh Canteen, Canteen số 1', N'Quang Anh Canteen', 5, '2023-08-23', 4.6, 120, 1, '',''),
(6, '0325454545', 'hoan@123', N' Quán ăn Thứ 4, Quán Daily Kebal Haus, Canteen số 1', N'Daily Kebal Haus', 5, '2023-12-23', 4.4, 120, 1, '',''),
(7, '0325454545', 'hoan@123', N' Quán ăn Thứ 5, Quán Mango, Canteen số 2', N'Mango', 5, '2022-11-23', 4.8, 100, 1, '', '');


INSERT INTO [dbo].[Product] ([category_id] ,[seller_id],[image], [title],[old_price] ,[current_price] ,[amount_of_sold] ,[number_in_stock] ,[status] ,[describe] ,[rating]) VALUES
    (1, 1, 'https://cdn.tgdd.vn/2021/06/CookProduct/1200-1200x675.jpg',N'Cơm Rang Thập Cẩm', 45000, 35000, 0, 20, N'Yêu thích','', 4.9  ),
    (1, 1, 'https://static.vinwonders.com/production/com-rang-dua-bo-ha-noi-1.jpg',N'Cơm Rang Dưa Bò', 45000, 40000, 0, 20, N'Yêu thích','', 4.9  ),
    (2, 1, 'https://cdn.tgdd.vn/Files/2018/04/01/1078873/nau-bun-bo-hue-cuc-de-tai-nha-tu-vien-gia-vi-co-san-202109161718049940.jpg',N'Bún Bò Huế', 65000, 52000, 0, 20, N'Giảm giá','', 4.9  ),
    (2, 1, 'https://cdn.tgdd.vn/2021/04/CookProduct/1-1200x676-21.jpg',N'Bún Bò Cay Nhẹ', 48000, 42000, 0, 20, N'Yêu thích','', 4.8 ),
    (2, 1, 'https://daynauan.info.vn/wp-content/uploads/2020/04/bun-thai.jpg',N'Bún Bò Hải Sản Siêu Cay', 64000, 58000, 0, 20, N'Yêu thích','', 4.4 ),
    (2, 1, 'https://cdn.tgdd.vn/Files/2022/01/25/1412805/cach-nau-pho-bo-nam-dinh-chuan-vi-thom-ngon-nhu-hang-quan-202201250230038502.jpg',N'Phở Bò Nam Định', 42000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 1, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20,N'Yêu thích','', 4.3 ),
    (5, 1, 'https://images.getrecipekit.com/20230813061131-andy-20cooks-20-20roast-20pork-20banh-20mi.jpg?aspect_ratio=16:9&quality=90&',N'Bánh Mì Nhân Thịt Xông Khói', 32000, 25000, 0, 20, N'Giảm giá','', 4.2 ),
    (3, 1, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
    (2, 1, 'https://cdn.tgdd.vn/Files/2021/07/02/1365019/an-bao-lau-nay-ban-co-biet-bun-dau-mam-tom-la-dac-san-o-dau-202206021309408488.jpeg',N'Bún Đậu Mắm Tôm', 39000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
	(5, 1, 'https://chacanhatrangngoctan.com/wp-content/uploads/2021/08/cach-lam-banh-mi-que-ngon.jpg',N'Bánh Mỳ Que Kẹp Xúc Xích', 25000, 22500, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 1, 'https://cdn.tgdd.vn/Files/2020/10/22/1301077/tuyet-chieu-lam-banh-mi-que-thit-cay-gion-rum-ngon-tai-te-202203142255016223.jpg',N'Bánh Mỳ Que Không Nhân', 12000, 9000, 0, 20, N'Yêu thích','', 4.9 ),

    (5, 2, 'https://cdn.tgdd.vn/Files/2022/11/17/1487645/cach-nau-xoi-xeo-ngo-thom-ngon-deo-dep-mat-cho-bua-sang-202211171330393361.jpg',N'Xôi Xéo Siêu To Khổng Lồ', 35000, 28000, 0, 20, N'Yêu thích','', 4.5 ),
    (2, 2, 'https://i.ytimg.com/vi/C1P1Cw9J1-I/maxresdefault.jpg',N'Bún Riêu Cua ', 45000, 40000, 0, 20, N'Yêu thích','', 4.8  ),
    (2, 2, 'https://vcdn-giadinh.vnecdn.net/2021/01/02/Anh-6-7105-1609558348.jpg', N'Bún Mọc', 65000, 52000, 0, 20,N'Giảm giá','', 4.9  ),
    (2, 2, 'https://cdn.tgdd.vn/Files/2020/04/03/1246339/cach-nau-bun-ca-ha-noi-thom-ngon-chuan-vi-khong-ta-13.jpg',N'Bún Cá', 48000, 42000, 0, 20, N'Yêu thích','', 4.9 ),
    (2, 2, 'https://daynauan.info.vn/wp-content/uploads/2020/04/bun-thai.jpg',N'Bún Bò Hải Sản Siêu Cay', 64000, 58000, 0, 20, N'Yêu thích','', 4.9 ),
    (4, 2, 'https://cdn.tgdd.vn/Files/2017/03/22/963765/cach-lam-ga-ran-thom-ngon-8_760x450.jpg',N'Đùi Gà Rán Cỡ Lớn', 35000, 31900, 0, 20, N'Giảm giá','', 4.9 ),
    (5, 2, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 2, 'https://statics.vinpearl.com/banh-gio-ha-noi-1_1685951846.jpg',N'Bánh Giò Nóng', 32000, 25000, 0, 20, N'Yêu thích','', 4.9 ),
	(4, 2, 'https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/8/1032286/Recipe_1314.jpg',N'Mì Xào Thịt Bò', 39000, 32000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 2, 'https://banhmiquedananglamvublog4338.edublogs.org/files/2022/09/z3744736924187_eb24b5778f10702d04836514b0f0dbba-1024x683.jpg',N'Bánh Mỳ Que Pate Chà Bông', 28000, 24000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 2, 'https://chacanhatrangngoctan.com/wp-content/uploads/2021/08/cach-lam-banh-mi-que-ngon.jpg',N'Bánh Mỳ Que Kẹp Xúc Xích', 25000, 22500, 0, 20, N'Yêu thích','', 4.9 ),
  
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
	(5, 3, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20, N'Yêu thích','', 4.3 ),
    (5, 3, 'https://images.getrecipekit.com/20230813061131-andy-20cooks-20-20roast-20pork-20banh-20mi.jpg?aspect_ratio=16:9&quality=90&',N'Bánh Mì Nhân Thịt Xông Khói', 32000, 25000, 0, 20, N'Giảm giá','', 4.2 ),
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20, N'Yêu thích','', 4.9 ),
    (3, 3, 'https://cdn.eva.vn/upload/4-2023/images/2023-11-16/c36--1--1700118255-311-width640height360.png',N'Trà Chanh', 15000, 9000, 0, 20, N'Yêu thích','', 4.9 ),
    (1, 3, 'https://danviet.mediacdn.vn/zoom/600_315/2020/8/31/com-15988108612341894071093-crop-1598810866649564149485.png',N'Cơm Rang Hải Sản', 45000, 42000, 0, 20, N'Yêu thích','', 4.9 ),
    (1, 3, 'https://cdn.tgdd.vn/2021/06/CookProduct/1200-1200x675.jpg',N'Cơm Rang Thập Cẩm', 45000, 32000, 0, 20, N'Yêu thích','', 4.9  ),
    (1, 3, 'https://afamilycdn.com/150157425591193600/2023/11/5/20171010123453-com-rang-ngon-16991701953151469268883.jpg',N'Cơm Rang Tại Gia', 45000, 32000, 0, 20, N'Yêu thích','', 4.9  ),
    (2, 3, 'https://file.hstatic.net/1000394081/article/bun-dau-mam-tom-thap-cam_2321472f6d634b1192e171c5e659e187.jpg',N'Bún Đậu Mắm Tôm', 38000, 34000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 3, 'https://banhmiquedananglamvublog4338.edublogs.org/files/2022/09/z3744736924187_eb24b5778f10702d04836514b0f0dbba-1024x683.jpg',N'Bánh Mỳ Que Pate Chà Bông', 28000, 24000, 0, 20, N'Yêu thích','', 4.9 ),
   
    (5, 4, 'https://cdn.tgdd.vn/Files/2022/11/17/1487645/cach-nau-xoi-xeo-ngo-thom-ngon-deo-dep-mat-cho-bua-sang-202211171330393361.jpg',N'Xôi Xéo Siêu To Khổng Lồ', 35000, 28000, 0, 20, N'Yêu thích','', 4.5 ),
    (2, 4, 'https://i.ytimg.com/vi/C1P1Cw9J1-I/maxresdefault.jpg',N'Bún Riêu Cua ', 45000, 40000, 0, 20, N'Yêu thích','', 4.8  ),
    (2, 4, 'https://vcdn-giadinh.vnecdn.net/2021/01/02/Anh-6-7105-1609558348.jpg', N'Bún Mọc', 65000, 52000, 0, 20, N'Giảm giá','', 4.9  ),
    (2, 4, 'https://cdn.tgdd.vn/Files/2020/04/03/1246339/cach-nau-bun-ca-ha-noi-thom-ngon-chuan-vi-khong-ta-13.jpg',N'Bún Cá', 48000, 42000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 4, 'https://chacanhatrangngoctan.com/wp-content/uploads/2021/08/cach-lam-banh-mi-que-ngon.jpg',N'Bánh Mỳ Que Kẹp Xúc Xích', 25000, 22500, 0, 20, N'Yêu thích','', 4.9 ),
    (3, 4, 'https://cdn.eva.vn/upload/4-2023/images/2023-11-16/c36--1--1700118255-311-width640height360.png',N'Trà Chanh', 15000, 9000, 0, 20, N'Yêu thích','', 4.9 ),
    (1, 4, 'https://cdn.tgdd.vn/2021/06/CookProduct/1200-1200x675.jpg',N'Cơm Rang Thập Cẩm', 45000, 32000, 0, 20, N'Yêu thích','', 4.9  ),
    (2, 4, 'https://file.hstatic.net/1000394081/article/bun-dau-mam-tom-thap-cam_2321472f6d634b1192e171c5e659e187.jpg',N'Bún Đậu Mắm Tôm', 38000, 34000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 4, 'https://cdn.tgdd.vn/Files/2020/10/22/1301077/tuyet-chieu-lam-banh-mi-que-thit-cay-gion-rum-ngon-tai-te-202203142255016223.jpg',N'Bánh Mỳ Que Không Nhân', 12000, 9000, 0, 20, N'Yêu thích','', 4.9 ),

    (3, 5, 'https://bizweb.dktcdn.net/100/421/036/files/cach-lam-tra-chanh-chuan-vi-kinh-doanh-3.jpg?v=1617175803846',N'Trà Chanh', 15000, 9000, 0, 20, N'Yêu thích','', 4.9 ),
	(4, 5, 'https://cdn.tgdd.vn/Files/2017/03/22/963765/cach-lam-ga-ran-thom-ngon-8_760x450.jpg',N'Đùi Gà Rán Cỡ Lớn', 35000, 31900, 0, 20, N'Giảm giá','', 4.9 ),
    (5, 5, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 5, 'https://cdn.tgdd.vn/Files/2020/10/22/1301077/tuyet-chieu-lam-banh-mi-que-thit-cay-gion-rum-ngon-tai-te-202203142255016223.jpg',N'Bánh Mỳ Que Không Nhân', 12000, 9000, 0, 20, N'Yêu thích','', 4.9 ),
    (3, 5, 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiLybZEi9NLNGmUNUAQnNghC2YpnZx262cPtTbBdhmO17qJAYjRKdtArVgsBeaSzMqKv4IsG0Uo8EslfS9fY3Ie2gP6KZmAXuThkxmsrIXgBAh0aZMjF-h2zgtuCUDXWHbsGuTzaRma5h8oAfZjXP-fqG-Ycvb1Gr418-zvuNrmecXZuSXh7tKycMtrXCo4/w640-h360/tra%20dao%20cam%20sa.jpg',N'Trà Thạch Đào Cam Xả', 60000, 52000, 0, 20, N'Yêu thích','', 4.9 ),
    (5, 5, 'https://banhmiquedananglamvublog4338.edublogs.org/files/2022/09/z3744736924187_eb24b5778f10702d04836514b0f0dbba-1024x683.jpg',N'Bánh Mỳ Que Pate Chà Bông', 28000, 24000, 0, 20, N'Yêu thích','', 4.9 ),
    (4, 5, 'https://cdn.tgdd.vn/Files/2021/08/10/1374266/cach-lam-mon-ga-cay-pho-mai-cay-ngon-chuan-vi-han-202201041000310531.jpg',N'Gà Phô Mai', 28000, 24000, 0, 20, N'Yêu thích','', 4.9 ),
    (2, 5, 'https://cdn.tgdd.vn/2021/04/CookProduct/1-1200x676-21.jpg',N'Bún Bò Nhiều Bò', 44000, 39500, 0, 20, N'Yêu thích','', 4.9 ),
    (2, 5, 'https://cdn.tgdd.vn/Files/2021/07/02/1365019/an-bao-lau-nay-ban-co-biet-bun-dau-mam-tom-la-dac-san-o-dau-202206021309408488.jpeg',N'Bún Đậu Mắm Tôm', 39000, 35000, 0, 20,N'Yêu thích','', 4.9 ),
	(4, 5, 'https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2022/4/8/1032286/Recipe_1314.jpg',N'Mì Xào Thịt Bò', 39000, 35000, 0, 20, N'Yêu thích','', 4.9 );
    
INSERT INTO Menu (seller_id, [status], menu_date,menu_name,is_sale)
VALUES	(1, 1, GETDATE(),'Menu1',0),
		(2, 1, GETDATE(),'Menu2',0),
		(3, 1, GETDATE(),'Menu3',0),
		(4, 1, GETDATE(),'Menu4',0),
		(5, 1, GETDATE(),'Menu5',0),
		(1, 0, GETDATE(),'Menu6',0);
		
INSERT INTO Menu_Item (menu_id, product_id)
VALUES	(1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),(1, 8),(1, 9),(1, 10),(1, 11),(1, 12),
		(2, 13),(2, 14),(2, 15),(2, 16),(2, 17),(2, 18),(2, 19),(2, 20),(2, 21),(2, 22),(2, 23),
		(3, 24),(3, 25),(3, 26),(3, 27),(3, 28),(3, 29),(3, 30),(3, 31),(3, 32),(3, 33),(3, 34),
		(4, 35),(4, 36),(4, 37),(4, 38),(4, 39),(4, 40),(4, 41),(4, 42),(4, 43),
		(5, 44),(5, 45),(5, 46),(5, 47),(5, 48),(5, 49),(5, 50),(5, 51),(5, 52),(5, 53);

INSERT INTO Menu_Item (menu_id, product_id)
VALUES	(6, 1),(6, 2),(6, 3),(6, 4),(6, 5),(6, 6),(6, 7),(6, 8),(6, 9),(6, 10),(6, 11),(6, 12);


INSERT INTO [dbo].[Report_Types] ([report_name])
VALUES (N'Dịch vụ/sản phẩm không đúng như quảng cáo'),
       (N'Sản phẩm/sản phẩm là hàng giả mạo hoặc hàng nhái'),
       (N'Sản phẩm không đáp ứng các tiêu chuẩn an toàn hoặc chất lượng'),
       (N'Lừa đảo hoặc hành vi gian lận'),
       (N'Phản đối về hành vi không đạo đức của nhà cung cấp/sản phẩm'),
       (N'Sản phẩm không phản ánh chính xác thông tin đặc tính, giá cả, hoặc nguồn gốc'),
       (N'Hành vi gây hại đến môi trường hoặc cộng đồng'),
       (N'Sản phẩm là hàng nhập lậu hoặc bất hợp pháp'),
       (N'Hành vi đe dọa hoặc quấy rối từ phía nhà cung cấp hoặc người bán'),
       (N'Sản phẩm có chứa thông tin độc hại hoặc gây nguy hiểm cho người sử dụng');

UPDATE Seller
SET number_of_foods = (
    SELECT COUNT(*) 
    FROM Product 
    WHERE seller_id = Seller.seller_id
);

UPDATE Seller
SET rating_store = (
    SELECT ROUND(AVG(CAST(Product.rating AS FLOAT)), 1)
    FROM Product
    WHERE Product.seller_id = Seller.seller_id
    GROUP BY Product.seller_id
)


/**
select * from Seller
select * from Account
select * from Notifications
select * from feedback
SELECT TOP 1 notify_id FROM Notifications WHERE acc_id = 10 ORDER BY notify_id DESC

select p.pid,  s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,
p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],
p.describe, p.rating,p.category_id as cid, c.[name] as cname
from Product p join Category c on p.category_id = c.id
join Seller s on p.seller_id = s.seller_id
where p.title like '%Hanoi%' or s.store_name like '%Hanoi%' 
UNION all
select p.pid,  s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,
p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],
p.describe, p.rating,p.category_id as cid, c.[name] as cname
from Product p join Category c on p.category_id = c.id
join Seller s on p.seller_id = s.seller_id
where p.title LIKE N'Cơm'

SELECT * FROM Product p where p.seller_id = 1
WHERE p.[status] = 'sale'

select * from [OrderDetail] 
select * from Orders  order by order_id desc

delete from OrderDetail
delete from Orders

SELECT TOP 1 order_id FROM Orders ORDER BY order_id DESC

select * from Account_Details
select * from Account_Address

INSERT INTO [Orders]
           ([acc_id] ,[nickname],[phone] ,[email],[address] ,[note] ,[order_date] 
          ,[discount] ,[total_price], is_shipped)
     VALUES
           (4, 'jisookim', '0258', '@hsjdc', 'korea', 'ok ok', GETDATE(), 1,1,1)


/*this query to view order status*/
select o.order_id, od.detail_id ,o.acc_id, od.product_id, od.product_image, od.product_title, od.product_catename, od.product_storename, od.product_oldprice, 
od.product_currentprice, od.product_status, od.quantity, od.total_price,
o.[is_accepted], o.is_delivered, od.is_feedback, o.is_purchased, o.order_date
from Orders o join OrderDetail od on o.order_id = od.order_id
join Product p on p.pid = od.product_id
join Account a on a.acc_id = o.acc_id
join Seller s on s.seller_id = p.seller_id
join Category c on c.id = p.category_id
where o.acc_id = 1

/*this query to select all order by seller id */
select DISTINCT  o.order_id, o.seller_id, a.acc_id, a.username,
 o.total_price, o.is_shipped,
o.is_accepted, o.is_delivered, o.is_feedback, o.is_purchased,
o.order_date
from OrderDetail od join orders o on od.order_id = o.order_id
join Account a on a.acc_id = o.acc_id
join Seller s on s.acc_id = a.acc_id
where o.seller_id = 1
order by o.order_id desc

select * from Feedback where acc_id  =3

update OrderDetail set is_delivered = 0  where detail_id = 64

INSERT INTO ACCOUNT (username, [password], role_id, is_block, coin, [create_time]) VALUES ('hoan', '1', 1, 0, 0,GETDATE())

select * from Account_Details where email = 'haichelseam11@gmail.com' and acc_id != 3


-- this is for view order detail for seller
select od.detail_id, o.order_id, o.seller_id, od.product_id, od.product_image, od.product_title, od.product_status, 
od.product_catename, od.product_currentprice, od.product_oldprice ,od.quantity, o.nickname, o.phone, o.email,
o.[address], o.note, o.is_shipped, o.total_price
from OrderDetail od join orders o on od.order_id = o.order_id
join Account a on a.acc_id = o.acc_id
join Seller s on s.acc_id = a.acc_id
where o.seller_id = 1 and o.order_id = 86
order by od.detail_id desc


select p.pid,  s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,
p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],
p.describe, p.rating,p.category_id as cid, c.[name] as cname
from Product p join Category c on p.category_id = c.id
join Seller s on p.seller_id = s.seller_id
	WHERE 
    c.id IN (1, 3,3) AND p.pid NOT IN (33,32)
    AND p.current_price < ( SELECT MAX(p2.current_price)
	FROM PRODUCT p2 WHERE p2.pid IN (33,32));

	select * from notifications
	delete from Notifications where notify_id = 5

	select * from FeedBack where product_id = 48

SELECT product_id, AVG(rate) AS average_rating
FROM FeedBack
WHERE product_id = 48
GROUP BY product_id

select * from Product where pid = 48

UPDATE Product SET rating = ?
Where pid = ?

Update Orders set is_delivered = 0 
where seller_id = 1 and is_accepted = 1 and is_delivered = 0

delete seller where seller_id = 10
update seller set is_active = 0 where seller_id = 6

INSERT INTO [Seller] (acc_id, phone_store, email_store, address_store, store_name, number_of_foods, store_opentime, rating_store, follower) VALUES 
(11, '0325454545', 'hoan123@gmail.com', N' Quán ăn Thứ 6, Quán Suga, Canteen số 2', N'Suga', 5, '2022-02-25', 4.8, 100);
select * from Seller 
update Seller set is_active = 0 where seller_id = 7
update account set role_id = 1 where acc_id = 12

UPDATE Seller
SET stock_product = (
    SELECT SUM(product_quantity)
    FROM Product
    WHERE seller_id = Seller.seller_id
);

*/
