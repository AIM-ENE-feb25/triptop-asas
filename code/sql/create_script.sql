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
                         boekingId INT IDENTITY(1,1) PRIMARY KEY,
                         boekingDatum DATE NOT NULL,
                         vertrekDatum DATE NOT NULL,
                         aankomstDatum DATE NOT NULL,
                         vertrekLocatie NVARCHAR(255) NOT NULL,
                         aankomstLocatie NVARCHAR(255) NOT NULL,
                         prijs DECIMAL(10,2) NOT NULL
);
END

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'Overnachting'))
BEGIN
CREATE TABLE Overnachting (
                              overnachtingId INT IDENTITY(1,1) PRIMARY KEY,
                              boekingId INT NOT NULL,
                              hotelNaam NVARCHAR(255) NOT NULL,
                              checkInDatum DATE NOT NULL,
                              checkOutDatum DATE NOT NULL,
                              prijs DECIMAL(10,2) NOT NULL,
                              FOREIGN KEY (boekingId) REFERENCES Boeking(boekingId)
);
END
