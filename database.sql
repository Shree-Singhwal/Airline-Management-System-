-- ========================================================
-- AIRLINE MANAGEMENT SYSTEM - DATABASE SCHEMA
-- ========================================================

CREATE DATABASE IF NOT EXISTS airlinemanagementsystem;
USE airlinemanagementsystem;

-- 1. Admin/User Login Credentials
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Insert default admin credentials
INSERT INTO login (username, password) 
VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE password = VALUES(password);

-- 2. Passenger Details
CREATE TABLE IF NOT EXISTS passenger (
    aadhar VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    gender VARCHAR(10) NOT NULL
);

-- Sample Passengers
INSERT INTO passenger (aadhar, name, nationality, phone, address, gender) VALUES
('111122223333', 'Rahul Sharma', 'Indian', '9876543210', 'B-12 Connaught Place, New Delhi', 'Male'),
('444455556666', 'Priya Patel', 'Indian', '9123456780', '404 Marine Drive, Mumbai', 'Female')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 3. Flight Details
CREATE TABLE IF NOT EXISTS flight (
    flight_code VARCHAR(20) PRIMARY KEY,
    flight_name VARCHAR(100) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL
);

-- Sample Flights
INSERT INTO flight (flight_code, flight_name, source, destination) VALUES
('AI-101', 'Air India', 'Delhi', 'Mumbai'),
('AI-102', 'Air India', 'Mumbai', 'Delhi'),
('6E-205', 'IndiGo', 'Delhi', 'Bangalore'),
('6E-206', 'IndiGo', 'Bangalore', 'Delhi'),
('UK-808', 'Vistara', 'Mumbai', 'Goa'),
('UK-809', 'Vistara', 'Goa', 'Mumbai'),
('SG-401', 'SpiceJet', 'Kolkata', 'Chennai'),
('SG-402', 'SpiceJet', 'Chennai', 'Kolkata'),
('AI-500', 'Air India Express', 'Delhi', 'Dubai'),
('EK-501', 'Emirates', 'Mumbai', 'Dubai')
ON DUPLICATE KEY UPDATE flight_name = VALUES(flight_name);

-- 4. Flight Reservations / Bookings
CREATE TABLE IF NOT EXISTS reservation (
    pnr VARCHAR(20) PRIMARY KEY,
    ticket_id VARCHAR(20) NOT NULL,
    aadhar VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    nationality VARCHAR(50) NOT NULL,
    flight_name VARCHAR(100) NOT NULL,
    flight_code VARCHAR(20) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    travel_date VARCHAR(30) NOT NULL,
    FOREIGN KEY (aadhar) REFERENCES passenger(aadhar) ON DELETE CASCADE
);

-- Sample Reservation
INSERT INTO reservation (pnr, ticket_id, aadhar, name, nationality, flight_name, flight_code, source, destination, travel_date) VALUES
('PNR-100234', 'TIC-8472', '111122223333', 'Rahul Sharma', 'Indian', 'Air India', 'AI-101', 'Delhi', 'Mumbai', '2026-11-15')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 5. Ticket Cancellations
CREATE TABLE IF NOT EXISTS cancel (
    pnr VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cancel_no VARCHAR(20) NOT NULL,
    flight_code VARCHAR(20) NOT NULL,
    travel_date VARCHAR(30) NOT NULL
);
