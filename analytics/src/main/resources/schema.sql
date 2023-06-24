CREATE DATABASE IF NOT EXISTS analytics;

USE analytics;

CREATE TABLE IF NOT EXISTS User (
    address TEXT,
    birthday TIMESTAMP NOT NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    email VARCHAR(255) NOT NULL UNIQUE,
    firstName TEXT NOT NULL,
    gender TINYTEXT,
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    lastName TEXT NOT NULL,
    middleName TEXT,
    password TEXT NOT NULL,
    phone VARCHAR(50),
    role TEXT
);

CREATE TABLE IF NOT EXISTS Visitor (
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id VARCHAR(36) NOT NULL UNIQUE,
    userId VARCHAR(36),
    FOREIGN KEY (userId)
        REFERENCES User(id)
);

CREATE TABLE IF NOT EXISTS Event (
    browserName VARCHAR(255),
    canonicalUrl TEXT NOT NULL,
    createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deviceType TEXT,
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    metadata TEXT NOT NULL,
    pageTitle TEXT,
    properties TEXT,
    referrer TEXT,
    sessionId TEXT NOT NULL,
    timezoneOffset TEXT NOT NULL,
    type TEXT NOT NULL,
    visitorId VARCHAR(36) NOT NULL,
    FOREIGN KEY (visitorId)
        REFERENCES Visitor(id)
);
