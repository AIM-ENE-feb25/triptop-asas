CREATE DATABASE payment_prototype;
GO

USE payment_prototype;
GO

CREATE TABLE payments (
    payment_id VARCHAR(50) PRIMARY KEY,
    is_payd BIT NOT NULL DEFAULT 0,
    currency_code VARCHAR(3) NOT NULL,
    amount DECIMAL(10,2) NOT NULL
);