IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'Boeking_Systeem')
BEGIN
    CREATE DATABASE Boeking_Systeem;
END
GO

USE Boeking_Systeem;
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'Boeking'))
BEGIN
    CREATE TABLE Boeking (
        boeking_id INT IDENTITY(1,1) PRIMARY KEY,
        boeking_datum DATE NOT NULL,
        interne_boeking BIT NOT NULL,
        hotel_naam NVARCHAR(255) NOT NULL, 
        begin_datum DATE NOT NULL,
        eind_datum DATE NOT NULL
    );
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'Bestemming'))
BEGIN
    CREATE TABLE Bestemming (
        bestemming_id INT IDENTITY(1,1) PRIMARY KEY,
        naam NVARCHAR(255) NOT NULL,
        beschrijving NVARCHAR(500)
    );
END
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'Overnachting'))
BEGIN
    CREATE TABLE Overnachting (
        overnachting_id INT IDENTITY(1,1) PRIMARY KEY,
        hotel_naam NVARCHAR(255) NOT NULL,
        begin_datum DATE NOT NULL,
        eind_datum DATE NOT NULL,
        prijs DECIMAL(10,2) NOT NULL,
        bestemming_id INT NOT NULL,
        FOREIGN KEY (bestemming_id) REFERENCES Bestemming(bestemming_id) ON DELETE CASCADE
    );
END
GO
