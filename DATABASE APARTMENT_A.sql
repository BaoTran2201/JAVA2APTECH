USE master;
GO
DROP DATABASE IF EXISTS apartment;
CREATE DATABASE apartment;
GO


USE apartment;
GO
--bảng login
DROP TABLE IF EXISTS Login;
CREATE TABLE Login (
    memberID INT IDENTITY PRIMARY KEY, 
    username NVARCHAR(50), 
    pass NVARCHAR(255), 
	email NVARCHAR(50),
	jobRole NVARCHAR(50) NOT NULL DEFAULT 'user',
    loginStatus BIT DEFAULT 0
);

GO
--  Bảng tòa nhà
DROP TABLE IF EXISTS Building;
CREATE TABLE Building (
    BuildingID INT IDENTITY PRIMARY KEY,
    BuildingName NVARCHAR(50) NOT NULL UNIQUE,
    TotalFloors INT DEFAULT 1,
    BuildingStatus NVARCHAR(20) DEFAULT 'Hoạt động'
);

--  Bảng tầng
DROP TABLE IF EXISTS floor;
CREATE TABLE floor (
    FloorID INT IDENTITY PRIMARY KEY,
    BuildingID INT NULL,  -- Cho phép NULL để khi Building bị xóa, giá trị sẽ NULL
    FloorNumber INT NOT NULL,
    FloorName NVARCHAR(50) NOT NULL,
    TotalApartments INT DEFAULT 0, 
    FloorStatus NVARCHAR(20) DEFAULT 'Hoạt động',
    FOREIGN KEY (BuildingID) REFERENCES Building(BuildingID) ON DELETE SET NULL
);

--  Bảng căn hộ
DROP TABLE IF EXISTS Apartments;
CREATE TABLE Apartments (
    ApartmentID INT IDENTITY PRIMARY KEY,
    BuildingID INT NULL,  -- Cho phép NULL để khi Building bị xóa, giá trị sẽ NULL
    FloorID INT NULL,     -- Cho phép NULL để khi Floor bị xóa, giá trị sẽ NULL
    ApartmentNumber NVARCHAR(10) NOT NULL,
    ApartmentType NVARCHAR(20),
    Area FLOAT, 
    Apartments_Status INT DEFAULT 1, 
    --FOREIGN KEY (BuildingID) REFERENCES Building(BuildingID) ON DELETE SET NULL,
    FOREIGN KEY (FloorID) REFERENCES floor(FloorID) ON DELETE SET NULL
);

--  Bảng quản lý thành viên
DROP TABLE IF EXISTS members;
CREATE TABLE members (
    memberID INT PRIMARY KEY, 
    memberName NVARCHAR(100) NOT NULL,
    avatar NVARCHAR(500) NULL,
    country NVARCHAR(200) NULL,
    dob DATE NULL,  
    StartDate DATE NULL,  
    EndDate DATE NULL,  
    quantity INT NULL,
    Phone NVARCHAR(15) NULL,
    cccd NVARCHAR(100) NULL,
    verifyCode INT NULL,  
    gender BIT NULL, 
    apartmentID INT NULL,
    memberStatus BIT NULL, 
    identityImage NVARCHAR(500) NULL,
    FOREIGN KEY (memberID) REFERENCES Login (memberID),
    FOREIGN KEY (apartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

--  Bảng dịch vụ chung cư
DROP TABLE IF EXISTS Services;
CREATE TABLE Services (
    ServiceID INT IDENTITY PRIMARY KEY,
    ServiceName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Price FLOAT,
    DurationDays INT NOT NULL,  -- Thêm số ngày của gói dịch vụ (bắt buộc nhập)
    ServiceStatus BIT DEFAULT 1
);
--Lưu đăng kí dịch vụ
DROP TABLE IF EXISTS UserServices;
CREATE TABLE UserServices (
    UserServiceID INT IDENTITY PRIMARY KEY,
    memberID INT NOT NULL,
    ServiceID INT NOT NULL,
    Daystart DATE NULL,  -- Ngày đăng ký mặc định là ngày hiện tại
    Dayend DATE NULL,  -- Sẽ tự động cập nhật dựa trên DurationDays
    StatusSer BIT DEFAULT 0,  
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE CASCADE,
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID) ON DELETE CASCADE
);
--  Bảng nhân viên
DROP TABLE IF EXISTS staff;
CREATE TABLE staff (
    StaffID INT IDENTITY PRIMARY KEY,	
    StaffName NVARCHAR(100) NOT NULL, 
    Phone NVARCHAR(15) , 
    staffStatus BIT DEFAULT 1  
);
DROP TABLE IF EXISTS staff_login;
CREATE TABLE staff_login (
    staffID INT PRIMARY KEY,  
    username NVARCHAR(50) NOT NULL UNIQUE, 
    password NVARCHAR(255) NOT NULL DEFAULT '1', 
    loginStatus BIT DEFAULT 1,		
    FOREIGN KEY (staffID) REFERENCES staff(StaffID) 
);
DROP TABLE IF EXISTS StaffServices;
CREATE TABLE StaffServices (
    StaffServiceID INT IDENTITY PRIMARY KEY,
    StaffID INT NOT NULL,
    UserServiceID INT NOT NULL,
    AssignmentDate DATE DEFAULT GETDATE(), 
    StatusDone BIT DEFAULT 1,  -- 1 = Đang làm, 0 = Hoàn thành
    FOREIGN KEY (StaffID) REFERENCES staff(StaffID) ON DELETE CASCADE,
    FOREIGN KEY (UserServiceID) REFERENCES UserServices(UserServiceID) ON DELETE CASCADE
);
DROP TABLE IF EXISTS Invoices;
CREATE TABLE Invoices (
    InvoiceID INT IDENTITY PRIMARY KEY,
    memberID INT NOT NULL,
    InvoiceDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(18,2) DEFAULT 0,  -- Tổng tiền hóa đơn, cập nhật sau khi thêm chi tiết
    PaymentStatus BIT DEFAULT 0,  -- 0: Chưa thanh toán, 1: Đã thanh toán
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE CASCADE
);

-- Bảng chi tiết hóa đơn
DROP TABLE IF EXISTS InvoiceDetails;
CREATE TABLE InvoiceDetails (
    InvoiceDetailID INT IDENTITY PRIMARY KEY,
    InvoiceID INT NOT NULL,
    ServiceID INT NOT NULL,
    ServiceName NVARCHAR(100) NOT NULL,  -- Lưu tên dịch vụ tại thời điểm tạo hóa đơn
    Price DECIMAL(18,2) NOT NULL,
    Quantity INT NOT NULL DEFAULT 1,  -- Nếu trùng ServiceID thì tăng số lượng
    SubTotal AS (Price * Quantity) PERSISTED,  -- Tổng tiền tự động tính
    FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID) ON DELETE CASCADE,
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID) ON DELETE CASCADE
);

--  Bảng hóa đơn
DROP TABLE IF EXISTS bill;
CREATE TABLE bill (
    BillID INT IDENTITY PRIMARY KEY,
    ApartmentID INT NULL,
    Amount FLOAT NOT NULL,
    DueDate DATE NOT NULL,
    PaymentStatus BIT DEFAULT 0, 
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

-- Bảng hợp đồng thuê/mua căn hộ
DROP TABLE IF EXISTS contracts;
CREATE TABLE contracts (
    ContractID INT IDENTITY PRIMARY KEY,
    memberID INT NULL,
    ApartmentID INT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE,
    ContractType NVARCHAR(20), 
    ContractStatus NVARCHAR(20) DEFAULT 'Đang hiệu lực',
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE SET NULL,
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

--  Bảng khu vực gửi xe
DROP TABLE IF EXISTS parking_Area;
CREATE TABLE parking_Area (
    ParkingID INT IDENTITY PRIMARY KEY,
    ApartmentID INT NULL,
    VehicleType NVARCHAR(50),
    SlotNumber NVARCHAR(20),
    Parking_AreaStatus BIT DEFAULT 1, 
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

--  Bảng yêu cầu bảo trì căn hộ
DROP TABLE IF EXISTS MaintenanceRequests;
CREATE TABLE MaintenanceRequests (
    RequestID INT IDENTITY PRIMARY KEY,
    ApartmentID INT NULL,
    IssueDescription NVARCHAR(MAX),
    RequestDate DATE, 
    MaintenanceRequests_Status NVARCHAR(20) DEFAULT 'Chưa xử lý',
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

--  Bảng thông báo gửi đến cư dân
DROP TABLE IF EXISTS Notifications;
CREATE TABLE Notifications (
    NotificationID INT IDENTITY PRIMARY KEY,
    Title NVARCHAR(100),
    Content NVARCHAR(MAX),
    SentDate DATE DEFAULT GETDATE()  -- Mặc định là ngày hiện tại
);

--  Bảng báo cáo sự cố
DROP TABLE IF EXISTS Report;
CREATE TABLE Report (
    ReportID INT IDENTITY PRIMARY KEY,
    ApartmentID INT NULL,
    ReportDescription NVARCHAR(MAX),
    ReportStatus NVARCHAR(20) DEFAULT 'Chưa xử lý',
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);

--  Bảng nội quy chung cư
DROP TABLE IF EXISTS Apart_Rule;
CREATE TABLE Apart_Rule (
    RuleID INT IDENTITY PRIMARY KEY,
    RuleDescription NVARCHAR(MAX),
    RuleStatus BIT DEFAULT 1 
);

--  Bảng nhật ký an ninh
DROP TABLE IF EXISTS SecurityLogs;
CREATE TABLE SecurityLogs (
    LogID INT IDENTITY PRIMARY KEY,
    LogDate DATETIME,
    Description_ NVARCHAR(MAX),
    HandledBy NVARCHAR(100)
);
go
DROP TABLE IF EXISTS Feedback;
CREATE TABLE Feedback (
    FeedbackID INT IDENTITY PRIMARY KEY,
    memberID INT NULL,
    namefb NVARCHAR(100) NULL,
    feedbackTittle NVARCHAR(50)  NULL,
    note NVARCHAR(1000) NULL,
    FeedbackDate DATETIME DEFAULT GETDATE(),
    Statusfb bit DEFAULT 0,
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE SET NULL
);
GO
DROP TABLE IF EXISTS FeedbackImages;
CREATE TABLE FeedbackImages (
    ImageID INT IDENTITY PRIMARY KEY,
    FeedbackID INT NOT NULL,
    ImagePath NVARCHAR(255) NOT NULL,
    FOREIGN KEY (FeedbackID) REFERENCES Feedback(FeedbackID) ON DELETE CASCADE
);
Go
INSERT INTO Building (BuildingName, TotalFloors, BuildingStatus) 
VALUES ('A', 5, 'Hoạt động');
select *from Building
SELECT BuildingID FROM Building WHERE BuildingName = 'Tòa A';

INSERT INTO floor (BuildingID, FloorNumber, FloorName) 
VALUES (1, 1, 'Tầng 1'),
       (1, 2, 'Tầng 2'),
       (1, 3, 'Tầng 3');
go
INSERT INTO Login (username, pass, jobRole, loginStatus)
VALUES 
    ('admin', '1', 'admin', 1)
go
go
CREATE TRIGGER trg_AfterInsertLogin
ON Login
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    INSERT INTO members (memberID, memberName, avatar)
    SELECT memberID, 'New Member', 'images/user.png' FROM inserted;
END;
GO
CREATE TRIGGER trg_DefaultDate ON members
AFTER INSERT
AS
BEGIN
   UPDATE members
   SET dob = COALESCE(dob, GETDATE()),
	 StartDate = COALESCE(StartDate, GETDATE()),
     EndDate = COALESCE(EndDate, GETDATE())
    WHERE memberID IN (SELECT memberID FROM inserted);
END;
go
INSERT INTO Login (username, pass, email, jobRole, loginStatus)
VALUES 
    ('u2', '1234', 'lebao545@gmail.com', 'user', 1),
    ('u1', '1234', 'lebao545@gmail.com', 'user', 0);
go

INSERT INTO Services (ServiceName, Description, Price, DurationDays, ServiceStatus) 
VALUES 
('House Cleaning', 'General house cleaning service.', 150000, 1, 1),
('Plumbing Repair', 'Fixing leaks and plumbing issues.', 200000, 1, 1),
('Electrical Maintenance', 'Fixing electrical issues.', 250000, 1, 1),
('Gym Training', 'Access to gym facilities with trainer support.', 300000, 30, 1),
('Swimming Pool Access', 'Unlimited access to the swimming pool.', 400000, 30, 1),
('Laundry Service', 'Washing and ironing clothes.', 120000, 1, 1),
('Parcel Delivery', 'Delivering parcels within the residence.', 50000, 1, 1);
go

---------------------------------------trigger
CREATE TRIGGER trg_SetStartAndEndDate
ON UserServices
AFTER INSERT, UPDATE
AS
BEGIN
    -- Chỉ cập nhật Daystart và Dayend khi StatusSer = 1
    UPDATE us
    SET 
        us.Daystart = CASE 
                         WHEN i.StatusSer = 1 THEN GETDATE()  -- Ngày hiện tại khi kích hoạt
                         ELSE us.Daystart  -- Giữ nguyên nếu chưa kích hoạt
                     END,
        us.Dayend = CASE 
                        WHEN i.StatusSer = 1 THEN DATEADD(DAY, s.DurationDays, GETDATE())  
                        ELSE NULL  -- Nếu chưa kích hoạt thì không có ngày kết thúc
                    END
    FROM UserServices us
    INNER JOIN inserted i ON us.UserServiceID = i.UserServiceID
    INNER JOIN Services s ON i.ServiceID = s.ServiceID
    WHERE i.StatusSer = 1;  -- Chỉ áp dụng khi StatusSer = 1
END;
go
CREATE TRIGGER trg_UpdateInvoicePayment
ON UserServices
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    -- Cập nhật PaymentStatus chỉ khi tất cả dịch vụ của memberID đều có StatusSer = 1
    UPDATE i
    SET i.PaymentStatus = 1, 
        i.InvoiceDate = GETDATE()  -- Lưu thời gian thanh toán
    FROM Invoices i
    WHERE NOT EXISTS (
        -- Kiểm tra xem có dịch vụ nào của memberID chưa được thanh toán không
        SELECT 1 FROM UserServices us
        WHERE us.memberID = i.memberID
        AND us.StatusSer = 0  -- Nếu còn dịch vụ chưa thanh toán thì không update
    );
END;
DROP TRIGGER IF EXISTS trg_AutoCreateStaffLogin;
GO
CREATE TRIGGER trg_AutoCreateStaffLogin
ON staff
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO staff_login (staffID, username, password, loginStatus)
    SELECT 
        i.StaffID, 
        CONCAT('staff', i.StaffID), 
        '1',  
        1     
    FROM inserted i;
END;
GO
CREATE TRIGGER tr_UpdateApartmentStatus
ON members
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật phòng cũ về trạng thái trống (Status = 0)
    UPDATE Apartments
    SET Apartments_Status = 1
    WHERE ApartmentID IN (
        SELECT deleted.apartmentID 
        FROM deleted
        WHERE deleted.apartmentID IS NOT NULL
    );

    -- Cập nhật phòng mới thành đã có người ở (Status = 1)
    UPDATE Apartments
    SET Apartments_Status = 2
    WHERE ApartmentID IN (
        SELECT inserted.apartmentID 
        FROM inserted
        WHERE inserted.apartmentID IS NOT NULL
    );
END;

------------------------------------
GO
INSERT INTO staff (StaffName, Phone) 
VALUES 
    ('Nguyễn Văn C', '0901234567'), 
    ('Lê Thị D', '0912345678'),
    ('Phạm Văn E', '0923456789');
go


INSERT INTO Feedback (memberID, namefb, feedbackTittle, note)
VALUES 
    (2, 'Nguyen Van A', 'System Error', 'I encountered an error while logging into the system.'),
    (3, 'Tran Thi B', 'Poor Service', 'The support staff responds very slowly.')
GO
INSERT INTO FeedbackImages (FeedbackID, ImagePath)
VALUES 
    (1, 'images/user.png'),
    (1, 'images/user.png'),
    (2, 'images/user.png')
go
INSERT INTO Apartments (BuildingID, FloorID, ApartmentNumber, ApartmentType, Area, Apartments_Status)
VALUES 
    (1, 1, '101', '2PN', 50, 1),
    (1, 1, '102', '2PN', 52, 1),
    (1, 1, '103', '2PN', 53, 1),
    (1, 1, '104', '2PN', 51, 1),
    (1, 1, '105', '2PN', 54, 1),

    
    (1, 2, '201', '3PN', 75, 1),
    (1, 2, '202', '3PN', 78, 1),
    (1, 2, '203', '3PN', 76, 1),
    (1, 2, '204', '3PN', 77, 1),
    (1, 2, '205', '3PN', 74, 1),

    (1, 3, '301', '1PN', 40, 1),
    (1, 3, '302', '1PN', 42, 1),
    (1, 3, '303', '1PN', 41, 1),
    (1, 3, '304', '1PN', 43, 1),
    (1, 3, '305', '1PN', 39, 1)
Go


select *from Login
select*from members
select * from Apartments
SELECT StatusDone FROM StaffServices WHERE staffID=2
select *from Login