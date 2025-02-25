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
    loginStatus BIT DEFAULT 1
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
    FOREIGN KEY (BuildingID) REFERENCES Building(BuildingID) ON DELETE SET NULL,
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
DROP TABLE IF EXISTS services;
CREATE TABLE services (
    ServiceID INT IDENTITY PRIMARY KEY,
    ServiceName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Price FLOAT,
    ServiceStatus BIT DEFAULT 1 
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

--  Bảng nhân viên
DROP TABLE IF EXISTS staff;
CREATE TABLE staff (
    StaffID INT IDENTITY PRIMARY KEY,
    StaffName NVARCHAR(100) NOT NULL,
    StaffRole NVARCHAR(50), 
    Phone NVARCHAR(15),
    staffStatus BIT DEFAULT 1 
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
    SentDate DATE
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

-- Chèn dữ liệu mẫu
INSERT INTO Building (BuildingName, TotalFloors, BuildingStatus) 
VALUES ('Tòa A', 5, 'Hoạt động');

INSERT INTO floor (BuildingID, FloorNumber, FloorName) 
VALUES (1, 1, 'Tầng 1'),
       (1, 2, 'Tầng 2'),
       (1, 3, 'Tầng 3');
go
INSERT INTO Apartments (BuildingID, FloorID, ApartmentNumber, ApartmentType, Area, Apartments_Status)
VALUES (1, 1, '101', '2PN', 50, 1),
       (1, 2, '201', '3PN', 75, 1),
       (1, 3, '301', '1PN', 40, 2);

       SELECT 
    a.ApartmentID, 
    b.BuildingName, 
    f.FloorNumber, 
    a.ApartmentNumber, 
    a.ApartmentType, 
    a.Area, 
    CASE 
        WHEN a.Apartments_Status = 1 THEN N'Đang ở' 
        WHEN a.Apartments_Status = 2 THEN N'Trống'
        WHEN a.Apartments_Status = 3 THEN N'Đang sửa chữa'
        ELSE N'Không xác định' 
    END AS ApartmentStatus
FROM Apartments a
LEFT JOIN floor f ON a.FloorID = f.FloorID
LEFT JOIN Building b ON a.BuildingID = b.BuildingID;
go
--trigger
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
INSERT INTO Login (username, pass, jobRole, loginStatus)
VALUES 
    ('admin', '1', 'admin', 1)
go

INSERT INTO Login (username, pass, email, jobRole, loginStatus)
VALUES 
    ('u2', '1', 'lebao545@gmail.com', 'user', 1),
    ('u1', '1', 'lebao545@gmail.com', 'user', 0);
go
SELECT * FROM Login WHERE username = 'baobao123' AND email = 'leoba545@gmail.com'
go
INSERT INTO members (memberID, memberName, avatar, country, dob, StartDate, EndDate, quantity, Phone, cccd, verifyCode, gender, apartmentID, memberStatus)
VALUES 
(2, 'Nguyen Van A', 'resources\image\avt.jpg', 'Vietnam', '1990-05-12', '2024-01-01', '2025-01-01', 3, '0987654321', '123456789', 9876, 1, 1, 1),
(3, 'Tran Thi B', 'avatar2.jpg', 'Vietnam', '1995-09-23', '2024-02-15', '2025-02-15', 2, '0976543210', '987654321', 5432, 0, 2, 1);
SELECT * FROM members WHERE memberID = 2
select *from Login
SELECT*FROM members
SELECT *FROM Login l
JOIN members m ON l.memberID = m.memberID;
go
CREATE TRIGGER trg_AfterInsertLogin
ON Login
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Chỉ chèn memberID và memberName, các cột khác sẽ nhận giá trị NULL mặc định
    INSERT INTO members (memberID, memberName)
    SELECT memberID, 'New Member' FROM inserted;
END;
GO


