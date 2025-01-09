-- Tạo cơ sở dữ liệu
USE master;
GO

DROP DATABASE IF EXISTS scoremanagement;
CREATE DATABASE scoremanagement;
GO

USE scoremanagement;
GO

-- Bảng Khoa (Faculty)
DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
    IDFac VARCHAR(5) PRIMARY KEY,       
    NameFac NVARCHAR(50) NOT NULL      
);
GO

-- Bảng Lớp (Class)
DROP TABLE IF EXISTS class;
CREATE TABLE class (
    IDClass VARCHAR(10) PRIMARY KEY,   
    NameClass NVARCHAR(50) NOT NULL,  
    IDFac VARCHAR(5),                 
    FOREIGN KEY (IDFac) REFERENCES faculty(IDFac)

);
GO

-- Bảng Sinh Viên (Student)
DROP TABLE IF EXISTS student;
CREATE TABLE student (
    IDStu VARCHAR(10) PRIMARY KEY,    
    NameStu NVARCHAR(50) NOT NULL,   
    Gender BIT NOT NULL,             -- Giới tính (1 = Nam, 0 = Nữ)
    Birth DATE NOT NULL,             
    Home_Address NVARCHAR(100),      
    IDClass VARCHAR(10),              
    FOREIGN KEY (IDClass) REFERENCES class(IDClass)
);
GO

-- Bảng Môn Học (Subject)
DROP TABLE IF EXISTS subject;
CREATE TABLE subject (
    IDSub VARCHAR(10) PRIMARY KEY,   
    NameSub NVARCHAR(100) NOT NULL, 
         
);
GO

-- Bảng Điểm Thi (Score)
DROP TABLE IF EXISTS score;
CREATE TABLE score (
    IDStu VARCHAR(10),               
    IDSub VARCHAR(10),               
    Sc1 DECIMAL(4, 2),
    Sc2 DECIMAL(4, 2),
    Sc3 DECIMAL(4, 2),  
    Sctotal DECIMAL(4, 2),       
    Rate NVARCHAR(10),           
    PRIMARY KEY (IDStu, IDSub),    
    FOREIGN KEY (IDStu) REFERENCES student(IDStu)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (IDSub) REFERENCES subject(IDSub)
        ON DELETE CASCADE ON UPDATE CASCADE
);
GO

-- trigger 
DROP TRIGGER IF EXISTS trg_UpdateScore;
GO
CREATE TRIGGER trg_UpdateScore
ON score
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE score
    SET 
        Sctotal = Sc1 * 0.3 + Sc2 * 0.3 + Sc3 * 0.4,
        Rate = CASE 
            WHEN (Sc1 * 0.3 + Sc2 * 0.3 + Sc3 * 0.4) >= 8 THEN N'A'
            WHEN (Sc1 * 0.3 + Sc2 * 0.3 + Sc3 * 0.4) >= 6.5 THEN N'B'
            WHEN (Sc1 * 0.3 + Sc2 * 0.3 + Sc3 * 0.4) >= 5 THEN N'C'
            ELSE N'D'
        END
    FROM score
    WHERE score.IDStu IN (SELECT IDStu FROM inserted) AND score.IDSub IN (SELECT IDSub FROM inserted);
END;
GO


-- Bảng Tài Khoản (Login)
DROP TABLE IF EXISTS login;
CREATE TABLE login (
    ID INT PRIMARY KEY IDENTITY,    
    Username NVARCHAR(50) NOT NULL, 
    Pass NVARCHAR(255) NOT NULL,  
    JobRole NVARCHAR(20) NOT NULL  
);
GO

-- Thêm dữ liệu mẫu vào bảng Faculty
INSERT INTO faculty (IDFac, NameFac)
VALUES
('F01', N'Công Nghệ Thông Tin'),
('F02', N'Kinh Tế'),
('F03', N'Kỹ Thuật');

-- Thêm dữ liệu mẫu vào bảng Class
INSERT INTO class (IDClass, NameClass, IDFac)
VALUES
('C01', N'CNTT 01', 'F01'),
('C02', N'Kinh Tế 01', 'F02'),
('C03', N'Kỹ Thuật 01', 'F03');

-- Thêm dữ liệu mẫu vào bảng Student
INSERT INTO student (IDStu, NameStu, Gender, Birth, Home_Address, IDClass)
VALUES
('SV001', N'Nguyễn Văn A', 1, '2000-01-15', N'Hà Nội', 'C01'),
('SV002', N'Trần Thị B', 0, '2001-05-20', N'Hải Phòng', 'C01'),
('SV003', N'Phạm Văn C', 1, '1999-09-25', N'Nam Định', 'C02');

-- Thêm dữ liệu mẫu vào bảng Subject
INSERT INTO subject (IDSub, NameSub)
VALUES
('MH001', N'Lập Trình Java'),
('MH002', N'Cơ Sở Dữ Liệu'),
('MH003', N'Mạng Máy Tính');

-- Thêm dữ liệu mẫu vào bảng Score
INSERT INTO score (IDStu, IDSub, Sc1, Sc2, Sc3)
VALUES
('SV001', 'MH001', 8.0, 7.5, 9.0),
('SV001', 'MH002', 6.5, 7.0, 8.0),
('SV002', 'MH001', 9.0, 8.5, 9.5),
('SV003', 'MH003', 5.0, 5.5, 6.0);

-- Thêm dữ liệu mẫu vào bảng Login
INSERT INTO login (Username, Pass, JobRole)
VALUES
('admin', 'admin123', 'Admin'),
('user1', 'user123', 'User');
GO
INSERT INTO student (IDStu, NameStu, Gender, Birth, Home_Address, IDClass)
VALUES
('SV005', N'Nguyễn A', 1, '2001-02-25', N'Hà Nội', 'C02'),
('SV006', N'Phan B', 0, '2001-03-10', N'Hồ Chí Minh', 'C01'),
('SV007', N'Vũ C', 1, '2000-12-05', N'Đà Nẵng', 'C02'),
('SV008', N'Hoàng D', 1, '2000-11-22', N'Hải Phòng', 'C03'),
('SV009', N'Lê E', 0, '2002-01-19', N'Thái Nguyên', 'C02'),
('SV010', N'Nguyễn F', 1, '2001-07-04', N'Bình Dương', 'C01'),
('SV011', N'Phạm G', 0, '2000-04-28', N'Vinh', 'C03'),
('SV012', N'Trần H', 1, '2001-08-13', N'Cần Thơ', 'C01'),
('SV013', N'Võ I', 1, '2001-09-17', N'Bắc Giang', 'C02'),
('SV014', N'Ngô J', 0, '2002-03-05', N'Hà Nội', 'C03'),
('SV015', N'Bùi K', 1, '2000-10-12', N'Quảng Ninh', 'C02'),
('SV016', N'Hồ L', 0, '2001-11-23', N'Trà Vinh', 'C01'),
('SV017', N'Đoàn M', 1, '2001-06-07', N'Bình Thuận', 'C03'),
('SV018', N'Nguyễn N', 0, '2002-02-14', N'Tuyên Quang', 'C02'),
('SV019', N'Phan O', 1, '2000-05-11', N'Lâm Đồng', 'C01'),
('SV020', N'Vũ P', 0, '2001-10-25', N'Sóc Trăng', 'C03'),
('SV021', N'Trần Q', 1, '2000-08-18', N'Bắc Kạn', 'C02'),
('SV022', N'Hoàng R', 0, '2002-01-08', N'Vĩnh Long', 'C01'),
('SV023', N'Lê S', 1, '2000-12-01', N'Tiền Giang', 'C03'),
('SV024', N'Nguyễn T', 0, '2001-07-22', N'Ninh Bình', 'C02'),
('SV025', N'Phạm U', 1, '2001-05-06', N'Phú Thọ', 'C03'),
('SV026', N'Trần V', 0, '2000-09-29', N'Quảng Nam', 'C02'),
('SV027', N'Võ W', 1, '2001-01-17', N'Gia Lai', 'C01'),
('SV028', N'Ngô X', 0, '2002-04-12', N'Hải Dương', 'C03'),
('SV029', N'Bùi Y', 1, '2000-06-30', N'Lào Cai', 'C02'),
('SV030', N'Hồ Z', 0, '2001-02-05', N'Khánh Hòa', 'C01'),
('SV031', N'Đoàn AA', 1, '2002-08-23', N'Thái Bình', 'C02'),
('SV032', N'Nguyễn AB', 0, '2001-10-19', N'Thừa Thiên Huế', 'C03'),
('SV033', N'Phan AC', 1, '2000-05-25', N'Long An', 'C01'),
('SV034', N'Vũ AD', 0, '2002-12-12', N'Yên Bái', 'C02'),
('SV035', N'Trần AE', 1, '2000-11-03', N'Hà Giang', 'C03');
go
CREATE OR ALTER PROCEDURE InsertStudentAndScore
    @IDStu VARCHAR(10),
    @NameStu NVARCHAR(50),
    @Gender BIT,
    @Birth DATE,
    @Home_Address NVARCHAR(100),
    @IDClass VARCHAR(10),
    @IDSub VARCHAR(10),
    @Sc1 DECIMAL(4, 2),
    @Sc2 DECIMAL(4, 2),
    @Sc3 DECIMAL(4, 2)

AS
BEGIN
    -- Kiểm tra xem IDClass có tồn tại trong bảng class không
    IF NOT EXISTS (SELECT 1 FROM class WHERE IDClass = @IDClass)
    BEGIN
        PRINT 'IDClass không tồn tại trong bảng class';
        RETURN;
    END

    -- Kiểm tra xem sinh viên đã tồn tại chưa
    IF EXISTS (SELECT 1 FROM student WHERE IDStu = @IDStu)
    BEGIN
        PRINT 'IDStu đã tồn tại trong bảng student';
    END
    ELSE
    BEGIN
        -- Thêm dữ liệu vào bảng student
        INSERT INTO student (IDStu, NameStu, Gender, Birth, Home_Address, IDClass)
        VALUES (@IDStu, @NameStu, @Gender, @Birth, @Home_Address, @IDClass);
    END

    -- Kiểm tra xem điểm của sinh viên đã tồn tại chưa
    IF EXISTS (SELECT 1 FROM score WHERE IDStu = @IDStu AND IDSub = @IDSub)
    BEGIN
        PRINT 'Điểm của sinh viên với môn học này đã tồn tại trong bảng score';
    END
    ELSE
    BEGIN
        -- Thêm dữ liệu vào bảng score
        INSERT INTO score (IDStu, IDSub, Sc1, Sc2, Sc3)
        VALUES (@IDStu, @IDSub, @Sc1, @Sc2, @Sc3);
    END
END;
GO


CREATE OR ALTER PROCEDURE DeleteStudent
    @IDStu VARCHAR(10)
AS
BEGIN
    -- Kiểm tra xem học sinh có tồn tại không
    IF NOT EXISTS (SELECT 1 FROM student WHERE IDStu = @IDStu)
    BEGIN
        PRINT 'Học sinh không tồn tại';
        RETURN;
    END

    -- Xóa dữ liệu trong bảng student
    DELETE FROM student
    WHERE IDStu = @IDStu;

    PRINT 'Học sinh đã được xóa thành công';
END;
GO
EXEC insertStudentAndScore
    @IDStu = '2000123',
    @NameStu = 'BBBBB',
    @Gender = 1,  
    @Birth = '2000-01-01',
    @Home_Address = 'Hà Nội',
    @IDClass = 'C01',
    @IDSub = 'MH001',
    @Sc1 = 7.5,
    @Sc2 = 8.0,
    @Sc3 = 9.0

go
CREATE OR ALTER PROCEDURE SelectStudent
AS
BEGIN
    SELECT *
FROM 
    student s
END;
GO
exec SelectStudent
go
CREATE or ALTER PROCEDURE insertStu
    @IDStu VARCHAR(10),
    @NameStu NVARCHAR(50),
	@Home_Address NVARCHAR(100),
	@Birth DATE,
    @Gender BIT,
    @IDClass VARCHAR(10)
AS
BEGIN
    -- Kiểm tra xem ID lớp có tồn tại trong bảng class hay không
    IF EXISTS (SELECT 1 FROM class WHERE IDClass = @IDClass)
    BEGIN
        -- Thực hiện chèn dữ liệu vào bảng student
        INSERT INTO student (IDStu, NameStu, Home_Address, Birth, Gender, IDClass)
        VALUES (@IDStu, @NameStu, @Home_Address, @Birth, @Gender, @IDClass);
    END
END;
GO

EXEC insertStu '111', 'Nguyen Van A', 'HCM', '2000-01-01', 1, 'C01';
go

create or alter procedure deleteStu
@id VARCHAR(10)
as 
begin 
	delete from student where IDStu=@id
end 
go

CREATE OR ALTER PROCEDURE insertFaculty
    @IDFac VARCHAR(5),
    @NameFac NVARCHAR(50)
AS
BEGIN

    INSERT INTO faculty (IDFac, NameFac)
    VALUES (@IDFac, @NameFac);
END;
GO
create or alter procedure updateStu
    @NameStu NVARCHAR(50),
	@Home_Address NVARCHAR(100),
	@Birth DATE,
    @Gender BIT,
    @IDClass VARCHAR(10),
	@IDStu VARCHAR(10)
as 
begin 
	Update student
	set NameStu=@NameStu,
	Home_Address=@Home_Address,
	Birth=@Birth,
	Gender=@Gender,
	IDClass=@IDClass
	where IDStu=@IDStu
end 
go
EXEC updateStu 
    @NameStu = N'Nguyen Van C',
    @Home_Address = N'789 GHI Street',
    @Birth = ' 2000-01-01',
    @Gender = 0,
    @IDClass = 'c01',
    @IDStu = '2000123';

exec deleteStu 1
select nameFac from faculty
select NameSub from subject
select *from score
select * from student
select IDSub from subject
SELECT * FROM student WHERE IDStu LIKE 'sv%';

SELECT s.IDStu, s.NameStu , s.Gender, s.Birth, s.Home_Address, c.IDClass, c.NameClass
FROM student s, class c, faculty f 
WHERE s.IDClass = c.IDClass AND c.IDFac = f.IDFac;

SELECT 
    s.IDStu, 
    s.NameStu, 
    c.NameClass, 
    f.NameFac,
    sc.IDSub,
    sub.NameSub,
    sc.Sc1,
    sc.Sc2,
    sc.Sc3,
    sc.Sctotal,
    sc.Rate
FROM 
    student s
JOIN 
    class c ON s.IDClass = c.IDClass
JOIN 
    faculty f ON c.IDFac = f.IDFac
JOIN 
    score sc ON s.IDStu = sc.IDStu
JOIN 
    subject sub ON sc.IDSub = sub.IDSub;
	SELECT * FROM score

select *from class
go
CREATE or alter  PROCEDURE sp_InsertScoreByStudent
    @IDStu VARCHAR(10),
    @IDSub VARCHAR(10),
    @Sc1 DECIMAL(4, 2),
    @Sc2 DECIMAL(4, 2),
    @Sc3 DECIMAL(4, 2)
AS
BEGIN
    BEGIN
        INSERT INTO score (IDStu, IDSub, Sc1, Sc2, Sc3)
        VALUES (@IDStu, @IDSub, @Sc1, @Sc2, @Sc3);
    END
END;
go
go
CREATE OR ALTER PROCEDURE insertFaculty
    @IDFac VARCHAR(5),
    @NameFac NVARCHAR(50)
AS
BEGIN

    INSERT INTO faculty (IDFac, NameFac)
    VALUES (@IDFac, @NameFac);
END;
GO
CREATE PROCEDURE deleteFaculty
    @id NVARCHAR(10) 
AS
BEGIN
    DELETE FROM Faculty WHERE IDFac = @id;
END;
go
CREATE OR ALTER PROCEDURE UpdateFaculty
    @IDFac VARCHAR(5),       
    @NameFac NVARCHAR(50)    
AS
BEGIN
    -- Kiểm tra xem ID khoa có tồn tại không
    IF NOT EXISTS (SELECT 1 FROM faculty WHERE IDFac = @IDFac)
    BEGIN
        PRINT 'Faculty ID does not exist.';
        RETURN;
    END

    -- Cập nhật thông tin khoa
    UPDATE faculty
    SET NameFac = @NameFac
    WHERE IDFac = @IDFac;

    PRINT 'Faculty updated successfully.';
END;
GO
CREATE PROCEDURE insertClass (
    @IDclass VARCHAR(50),
    @NameClass NVARCHAR(100),
    @IDFac VARCHAR(50)
)
AS
BEGIN
    INSERT INTO class (IDclass, NameClass, IDFac)
    VALUES (@IDclass, @NameClass, @IDFac);
END;
GO
CREATE OR ALTER PROCEDURE UpdateClass
    @IDClass VARCHAR(10),          
    @NameClass NVARCHAR(100),      
    @IDFac VARCHAR(10)             
AS
BEGIN
   
    IF NOT EXISTS (SELECT 1 FROM class WHERE IDClass = @IDClass)
    BEGIN
        PRINT 'Class ID does not exist.';
        RETURN;
    END

    UPDATE class
    SET NameClass = @NameClass, IDFac = @IDFac
    WHERE IDClass = @IDClass;

    PRINT 'Class updated successfully.';
END;
GO
CREATE OR ALTER PROCEDURE deleteClass
    @IDClass VARCHAR(10)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM class WHERE IDClass = @IDClass)
    BEGIN
        PRINT 'Class ID does not exist.';
        RETURN;
    END

    -- Xóa lớp học
    DELETE FROM class
    WHERE IDClass = @IDClass;

    PRINT 'Class deleted successfully.';
END;
go
CREATE OR ALTER PROCEDURE AllScores
AS
BEGIN
Select * from score
END;
go
CREATE OR ALTER PROCEDURE UpdateScore
    @Sc1 DECIMAL(10, 2),
    @Sc2 DECIMAL(10, 2),
    @Sc3 DECIMAL(10, 2),
    @IDStu NVARCHAR(50),
    @IDSub NVARCHAR(50)
AS
BEGIN
    UPDATE score
    SET Sc1 = @Sc1,
        Sc2 = @Sc2,
        Sc3 = @Sc3
    WHERE IDStu = @IDStu AND IDSub = @IDSub;
END
GO
CREATE OR ALTER PROCEDURE InsertScore
    @IDStu VARCHAR(10),
    @IDSub VARCHAR(10),
    @Sc1 DECIMAL(10, 2),
    @Sc2 DECIMAL(10, 2),
    @Sc3 DECIMAL(10, 2)
AS
BEGIN
    -- Kiểm tra xem IDStu và IDSub đã có điểm hay chưa
    IF EXISTS (SELECT 1 FROM score WHERE IDStu = @IDStu AND IDSub = @IDSub AND (Sc1 IS NOT NULL OR Sc2 IS NOT NULL OR Sc3 IS NOT NULL))
    BEGIN
        PRINT 'Điểm theo IDStu và IDSub đã tồn tại!';
    END
    ELSE
    BEGIN
        INSERT INTO score (IDStu, IDSub, Sc1, Sc2, Sc3)
        VALUES (@IDStu, @IDSub, @Sc1, @Sc2, @Sc3);
    END;
END;
GO

EXEC InsertScore @IDStu = 'SV034', @IDSub = 'MH002', @Sc1 = 8.50, @Sc2 = 7.75, @Sc3 = 9.00;
go
CREATE PROCEDURE DeleteScore
    @IDStu VARCHAR(10),
    @IDSub VARCHAR(10)
AS
BEGIN
    DELETE FROM score
    WHERE IDStu = @IDStu AND IDSub = @IDSub;
END;
GO



select *from subject
EXEC sp_InsertScoreByStudent 'SV005', 'MH001', 8.50, 7.75, 9.00;
SELECT s.IDStu, stu.NameStu, s.IDSub, sub.NameSub , s.Sc1, s.Sc2, s.Sc3, s.Sctotal, s.Rate
FROM score s
JOIN subject sub ON s.IDSub = sub.IDSub
JOIN student stu ON s.IDStu = stu.IDStu;
GO
EXEC InsertScore
    @Sc1 = 5, 
    @Sc2 = 1, 
    @Sc3 = 1,  
    @IDStu = 'SV005', 
    @IDSub = 'MH001';


