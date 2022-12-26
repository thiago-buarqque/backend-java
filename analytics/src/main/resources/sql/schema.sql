create database if not exists analytics;
use analytics;
create table if not exists User (
    id int not null auto_increment primary key,
    name text,
    phoneNumber text
);
create table if not exists Event (
    eventDate text,
    eventId int not null auto_increment primary key,
    eventType text,
    metadata text,
    userId int
);
