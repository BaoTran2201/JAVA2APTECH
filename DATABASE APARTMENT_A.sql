-- Tạo cơ sở dữ liệu
USE master;
GO

DROP DATABASE IF EXISTS apartment;
CREATE DATABASE apartment;
GO

USE apartment;
GO


DROP table IF EXISTS members;
create table members
(
    memberID NVARCHAR(20) PRIMARY KEY,  --căn cước công dân
    memberName NVARCHAR(100) NOT NULL,
    avartar NVARCHAR(500),
    country NVARCHAR(200),
    dob datetime default GETDATE(),
    StartDate datetime default GETDATE(),
    EndDate datetime default null,
    quantity int,
    Phone NVARCHAR(15),
    Email NVARCHAR(100),
    verifyCode int, --- Nháp, test sau
    gender bit,
    apartmentID INT,
    memberStatus bit    
);


DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
    AccountID INT IDENTITY PRIMARY KEY,            
    memberName NVARCHAR(100),  
    Pass NVARCHAR(255) DEFAULT '1',
    JobRole NVARCHAR(20),       
     memberID NVARCHAR(20),  
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE SET NULL
);

DROP table IF EXISTS member_family;
create table member_family
(
    FmemberID NVARCHAR(20) PRIMARY KEY,  
    FmemberName NVARCHAR(100) NOT NULL,
     memberID NVARCHAR(20),  
    FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE SET NULL ,
    member_familyStatus bit
);


DROP table IF EXISTS services_;
create table services_
(
	servicesID INT IDENTITY PRIMARY KEY,
	serviceType Nvarchar(20),
	serviceName NVARCHAR(20),
  serviceTotal int,
	membersID NVARCHAR(20),
   member_familyStatus bit
   FOREIGN KEY (memberID) REFERENCES members(memberID) ON DELETE SET NULL ,
);


DROP table IF EXISTS bill;
create table bill
(
   billStatus bit
);


DROP table IF EXISTS contracts;
create table contracts
(
   contractsStatus bit
);


DROP table IF EXISTS floor;
create table floor
(
  floorID int IDENTITY PRIMARY KEY,
  floorNumber int not null,
  TotalApartments  int default 0,
   floorStatus INT DEFAULT 1 
  --  1: Hoạt động
  --  2: Đang bảo trì
);


DROP table IF EXISTS parking_Area;
create table parking_Area
(
   parking_AreaStatus bit
); 

DROP table IF EXISTS staff;
create table staff
(
  staff_level int,
  staffStatus bit 
); 

DROP table IF EXISTS Staff_task;
create table Staff_task
(
 Staff_taskStatus bit
); 


DROP table IF EXISTS contact;
create table contact
(
  contactStatus bit 
); 


DROP table IF EXISTS Apart_Rule;
create table Apart_Rule
(
  Apart_RuleStatus bit
); 

DROP table IF EXISTS Report;
create table Report
(
    ReportID int IDENTITY PRIMARY KEY,
    servicesID INT(20),
  ReportStatus bit

  -- servicesID
); 

DROP table IF EXISTS reject_List;
create table reject_List
(
reject_ListStatus bit
); 

DROP table IF EXISTS Apartments; --Mục đích: Quản lý thông tin về các căn hộ trong chung cư.
create table Apartments
(
 ApartmentID INT IDENTITY PRIMARY KEY,
    ApartmentName NVARCHAR(50), 
    floorID INT,
    ApartmentNumber NVARCHAR(10) NOT NULL,
    ApartmentType NVARCHAR(20),
    Area DECIMAL(10,2), -- Diện tích căn hộ
    Apartments_Status int DEFAULT '1' 
    --  Trạng thái: 1 -> Căn hộ trống ;
    --              2 -> Đã cho thuê ; 
    --              3 -> Chờ ký hợp đồng ;
    --              4 -> Đang Bảo trì ; 
    --              5 -> Chờ dọn dẹp.
    FOREIGN KEY (floorID) REFERENCES floor(floorID) ON DELETE SET NULL
); 

DROP table IF EXISTS MaintenanceRequests;
CREATE TABLE MaintenanceRequests (
    RequestID INT IDENTITY PRIMARY KEY,
    ApartmentID INT,
    IssueDescription NVARCHAR(MAX),
    RequestDate DATE, 
    MaintenanceRequests_Status NVARCHAR(20) DEFAULT 'Chưa xử lý', -- Đã xử lý/Đang xử lý/Chưa xử lý
    
);

DROP table IF EXISTS Notifications;
CREATE TABLE Notifications (
    NotificationID INT IDENTITY PRIMARY KEY,
    Title NVARCHAR(100),
    Content NVARCHAR(MAX),
    SentDate DATE
);

DROP table IF EXISTS SecurityLogs;
CREATE TABLE SecurityLogs (
    LogID INT IDENTITY PRIMARY KEY,
    LogDate DATETIME,
    Description_ NVARCHAR(MAX),
    HandledBy NVARCHAR(100) -- Nhân viên phụ trách
);