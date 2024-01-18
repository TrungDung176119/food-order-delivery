
create database food_test5

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
    id INT PRIMARY KEY,
    [name] NVARCHAR(255)
);

CREATE TABLE Seller (
	seller_id INT PRIMARY KEY IDENTITY(1,1),
	acc_id INT,
	phone_store INT,
	email_store NVARCHAR(120),
	[address_store] NVARCHAR(MAX),
	store_name NVARCHAR(40),
	number_of_foods INT,    -- number of product that seller have
	store_opentime DATE,
	rating_store float,             -- rate = average of all rating product
	follower INT,			      -- Số lượng người theo dõi cửa hàng 
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
	seller_id INT,              -- đơn hàng của người nào bán
	acc_id INT,
    nickname NVARCHAR(32),      -- Tên khách hàng
	phone INT,
	email INT,
	[address] NVARCHAR(MAX),
	note NVARCHAR(MAX),
    order_date DATE,    -- Ngày đặt hàng
    is_delivered INT,   -- Trạng thái giao hàng (ví dụ:  0 chưa giao, 1 đang giao, 2 đã giao)
	is_accepted INT,    -- Trạng thái chấp nhận đơn đặt hàng (ví dụ: -2 đã hủy by seller, -1 đã hủy by user, 0 chưa chấp nhận, 1 đã chấp nhận)
    is_feedback INT,    -- Đánh giá đơn đặt hàng (ví dụ:  0 chưa đánh giá , 1 đã đánh giá)
	discount INT,       -- Giảm giá (nếu có)
	total_price int,

    -- Tạo khóa ngoại để liên kết Orders với Account
    CONSTRAINT FK_Orders_Account FOREIGN KEY (acc_id)
    REFERENCES Account (acc_id),
	-- Tạo khóa ngoại để liên kết Orders với OrderDetail
    CONSTRAINT FK_Orders_Seller FOREIGN KEY (seller_id)
    REFERENCES Seller (seller_id)
);

CREATE TABLE OrderDetail (
	detail_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT, -- ID đơn đặt hàng
    product_id INT, -- ID sản phẩm
    quantity INT, -- Số lượng sản phẩm đặt hàng
	price INT,
    total_price INT, -- Tổng tiền
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
	seller_id INT,
	acc_id INT,
	feed_date DATE,  -- ngày đánh giá đơn
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

CREATE TABLE Menu (
    menu_id INT PRIMARY KEY IDENTITY(1,1),
    seller_id INT,
    menu_date DATE,

    FOREIGN KEY (seller_id) REFERENCES Seller(seller_id)
);

CREATE TABLE Menu_Item (
	menu_item_id INT PRIMARY KEY IDENTITY(1,1),
    menu_id INT,
    product_id INT,
 
	FOREIGN KEY (product_id) REFERENCES Product(pid),
    FOREIGN KEY (menu_id) REFERENCES Menu(menu_id),
);


--

INSERT INTO [Role] VALUES ('user'), ('seller'),('admin')

INSERT INTO [Account] ([username], [password], [role_id], [is_block], [create_time], coin) VALUES 
('admin', '1', 3, 0, GETDATE(), 200),   -- acc_id = 1
('jisoo', '1', 1, 0, GETDATE(), 200),   --        = 2
('seller', '1', 2, 0, GETDATE(), 200),	--		  = 3
('jennie', '1', 2, 0, GETDATE(), 200),
('lisa', '1', 2, 0, GETDATE(), 200),	
('seller2', '1', 2, 0, GETDATE(), 200),
('rose', '1', 2, 0, GETDATE(), 200);	


select * from [Seller] 

INSERT INTO [Category] (id, [name]) VALUES
(1, N'Cơm Rang / Thường'),
(2, N'Bún / Phở'),
(3, N'Đồ Uống'),
(4, N'Đồ Ăn Nhanh'),
(5, N'Bánh Bao / Bánh Mỳ / Xôi')


INSERT INTO [Seller] (acc_id, phone_store, email_store, address_store, store_name, number_of_foods, store_opentime, rating_store, follower) VALUES 
(3, '0326644123', 'hoan@123', N'Quán ăn Thứ 1, Hà Nội Phố, Canteen số 1', N'HanoiPho', 5, GETDATE(), 4.9, 120),
(4, '0326415882', 'hoan@123', N'Quán ăn Thứ 2, Quán Simdo, Canteen số 1', N'Simdo', 5, GETDATE(), 4.7, 120),
(5, '0325545454', 'hoan@123', N' Quán ăn Thứ 3, Quán Quang Anh Canteen, Canteen số 1', N'Quang Anh Canteen', 5, GETDATE(), 4.6, 120),
(6, '0325454545', 'hoan@123', N' Quán ăn Thứ 4, Quán Daily Kebal Haus, Canteen số 1', N'Daily Kebal Haus', 5, GETDATE(), 4.4, 120);




INSERT INTO [dbo].[Product] ([category_id] ,[seller_id],[image], [title],[old_price] ,[current_price] ,[amount_of_sold] ,[number_in_stock] ,[status] ,[describe] ,[rating]) VALUES
    (1, 1, 'https://cdn.tgdd.vn/2021/06/CookProduct/1200-1200x675.jpg',N'Cơm Rang Thập Cẩm', 45000, 35000, 0, 20,'Yêu thích','', 4.9  ),
    (1, 1, 'https://static.vinwonders.com/production/com-rang-dua-bo-ha-noi-1.jpg',N'Cơm Rang Dưa Bò', 45000, 40000, 0, 20,'Yêu thích+','', 4.9  ),
    (2, 1, 'https://cdn.tgdd.vn/Files/2018/04/01/1078873/nau-bun-bo-hue-cuc-de-tai-nha-tu-vien-gia-vi-co-san-202109161718049940.jpg',N'Bún Bò Huế', 65000, 52000, 0, 20,'Sale','', 4.9  ),
    (2, 1, 'https://cdn.tgdd.vn/2021/04/CookProduct/1-1200x676-21.jpg',N'Bún Bò Cay Nhẹ', 48000, 42000, 0, 20,'Yêu thích','', 4.8 ),
    (2, 1, 'https://daynauan.info.vn/wp-content/uploads/2020/04/bun-thai.jpg',N'Bún Bò Hải Sản Siêu Cay', 64000, 58000, 0, 20,'Yêu thích+','', 4.4 ),
    (2, 1, 'https://cdn.tgdd.vn/Files/2022/01/25/1412805/cach-nau-pho-bo-nam-dinh-chuan-vi-thom-ngon-nhu-hang-quan-202201250230038502.jpg',N'Phở Bò Nam Định', 42000, 35000, 0, 20,'Yêu thích','', 4.9 ),
    (5, 1, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20,'Yêu thích','', 4.3 ),
    (5, 1, 'https://images.getrecipekit.com/20230813061131-andy-20cooks-20-20roast-20pork-20banh-20mi.jpg?aspect_ratio=16:9&quality=90&',N'Bánh Mì Nhân Thịt Xông Khói', 32000, 25000, 0, 20,'Sale','', 4.2 ),
    (3, 1, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20,'Yêu thích+','', 4.9 ),

    (5, 2, 'https://cdn.tgdd.vn/Files/2022/11/17/1487645/cach-nau-xoi-xeo-ngo-thom-ngon-deo-dep-mat-cho-bua-sang-202211171330393361.jpg',N'Xôi Xéo Siêu To Khổng Lồ', 35000, 28000, 0, 20,'Yêu thích','', 4.5 ),
    (2, 2, 'https://i.ytimg.com/vi/C1P1Cw9J1-I/maxresdefault.jpg',N'Bún Riêu Cua ', 45000, 40000, 0, 20,'Yêu thích','', 4.8  ),
    (2, 2, 'https://vcdn-giadinh.vnecdn.net/2021/01/02/Anh-6-7105-1609558348.jpg', N'Bún Mọc', 65000, 52000, 0, 20,'Sale','', 4.9  ),
    (2, 2, 'https://cdn.tgdd.vn/Files/2020/04/03/1246339/cach-nau-bun-ca-ha-noi-thom-ngon-chuan-vi-khong-ta-13.jpg',N'Bún Cá', 48000, 42000, 0, 20,'Yêu thích','', 4.9 ),
    (2, 2, 'https://daynauan.info.vn/wp-content/uploads/2020/04/bun-thai.jpg',N'Bún Bò Hải Sản Siêu Cay', 64000, 58000, 0, 20,'Yêu thích+','', 4.9 ),
    (4, 2, 'https://cdn.tgdd.vn/Files/2017/03/22/963765/cach-lam-ga-ran-thom-ngon-8_760x450.jpg',N'Đùi Gà Rán Cỡ Lớn', 35000, 31900, 0, 20,'Sale','', 4.9 ),
    (5, 2, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20,'Yêu thích','', 4.9 ),
    (5, 2, 'https://statics.vinpearl.com/banh-gio-ha-noi-1_1685951846.jpg',N'Bánh Giò Nóng', 32000, 25000, 0, 20,'Yêu thích+','', 4.9 ),
  
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20,'Yêu thích+','', 4.9 ),
	(5, 3, 'https://thophat.com/wp-content/uploads/2022/03/BB-Chay-DB-640g-1.jpg', N'Bánh Bao Nóng', 22000, 14000, 0, 20,'Yêu thích','', 4.3 ),
    (5, 3, 'https://images.getrecipekit.com/20230813061131-andy-20cooks-20-20roast-20pork-20banh-20mi.jpg?aspect_ratio=16:9&quality=90&',N'Bánh Mì Nhân Thịt Xông Khói', 32000, 25000, 0, 20,'Sale','', 4.2 ),
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20,'Yêu thích+','', 4.9 ),
    (3, 3, 'https://cdn.tgdd.vn/Files/2021/08/10/1374160/hoc-cach-pha-tra-sua-o-long-dai-loan-thom-ngon-chuan-vi-ai-cung-me-202108100039248020.jpg',N'Trà Sữa Truyền Thống', 42000, 35000, 0, 20,'Yêu thích+','', 4.9 );


	update product set title = N'Bún Bò Hải Sản Siêu Cay'
	where pid = 7


select p.pid,  s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,
p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],
p.describe, p.rating,p.category_id as cid, c.[name] as cname
from Product p join Category c on p.category_id = c.id
join Seller s on p.seller_id = s.seller_id
where p.title like '%Cơm%'

SELECT * FROM Product p
WHERE p.[status] = 'sale'


-- Drop the foreign key constraint on seller_id
ALTER TABLE Orders
DROP CONSTRAINT FK_Orders_Seller;

-- Drop the column seller_id
ALTER TABLE Orders
DROP COLUMN seller_id;


-- Add the seller_id column to OrderDetail
ALTER TABLE OrderDetail
ADD seller_id INT;

-- Add the foreign key constraint
ALTER TABLE OrderDetail
ADD CONSTRAINT FK_OrderDetail_Seller FOREIGN KEY (seller_id)
REFERENCES Seller (seller_id);
------ * thêm 4 dòng này vào database. seller_id phải ở trong bảng OrderDetail
alter table orders 
add is_purchased int   -- Trạng thái đã thanh toán đơn hàng hay chưa ( có thể dùng cho thanh toán = ví )

INSERT INTO Account_Details (acc_id, nickname, image, email, phone, gender, dateOfBirth)
VALUES
(1, 'Admin', 'admin_image.jpg', 'admin@example.com', '123456789', 1, '1990-01-01'),
(2, 'Jisoo', 'jisoo_image.jpg', 'jisoo@example.com', '987654321', 0, '1995-03-30'),
(3, 'Seller1', 'seller1_image.jpg', 'seller1@example.com', '456789123', 1, '1985-12-15'),
(4, 'Jennie', 'jennie_image.jpg', 'jennie@example.com', '789123456', 0, '1992-07-20'),
(5, 'Lisa', 'lisa_image.jpg', 'lisa@example.com', '654321987', 0, '1996-11-02'),
(6, 'Seller2', 'seller2_image.jpg', 'seller2@example.com', '321987654', 1, '1988-09-08'),
(7, 'Rose', 'rose_image.jpg', 'rose@example.com', '147258369', 0, '1994-05-10');