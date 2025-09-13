CREATE DATABASE IJSE;

USE IJSE;

SHOW TABLES;

CREATE TABLE student(
    reg_number INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_details VARCHAR(25) NOT NULL
);

CREATE TABLE lecture(
    lecture_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    contact_details VARCHAR(25) NOT NULL
);

