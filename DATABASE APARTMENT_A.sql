USE master;
GO

DROP DATABASE IF EXISTS apartment;
CREATE DATABASE apartment;
GO

USE apartment;
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
    memberID INT IDENTITY PRIMARY KEY, 
    memberName NVARCHAR(100) NOT NULL,
    avatar NVARCHAR(500),
    country NVARCHAR(200),
    dob DATE NULL,
    StartDate DATE NULL,
    EndDate DATE NULL,
    quantity INT,
    Phone NVARCHAR(15),
    Email NVARCHAR(100),
    verifyCode INT,  
    gender BIT, 
    apartmentID INT NULL,
    memberStatus BIT, 
    FOREIGN KEY (apartmentID) REFERENCES Apartments(ApartmentID) ON DELETE SET NULL
);
ALTER TABLE members ADD identityImage NVARCHAR(500);

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