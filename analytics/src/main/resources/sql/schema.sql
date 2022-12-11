create database if not exists analytics;
use analytics;
create table if not exists user (
    id int,
    name text
);
create table if not exists event (
    eventDate text,
    eventId int,
    eventType text,
    metadata text,
    userId int
);
