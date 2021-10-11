DROP DATABASE IF EXISTS Airport_DB;
CREATE DATABASE Airport_DB;

USE Airport_DB;

drop table if exists customers;
drop table if exists cities;
drop table if exists flights;
drop table if exists tickets;
drop table if exists customers_flights;
drop table if exists manifest_table;


create table customers_flights
(
	junction_id int auto_increment,
	customer_id int not null,
	flight_num int not null,
	index (customer_id),
	index (flight_num),
	constraint primary key  (junction_id)
);

create table customers
(
	customer_id int auto_increment,
	first_name varchar(200),
	last_name varchar(200),
	index(customer_id),
	constraint primary key (customer_id),
	constraint foreign key (customer_id) references customers_flights (customer_id)
);

create table cities
(
	city_id int auto_increment,
	city varchar(200) not null,
	state char(2) not null,
	index(city_id),
	constraint primary key (city_id)
);

create table flights
(
	flight_num int not null,
	flight_date date not null,
	seat_num int not null,
	departure_city varchar(200) not null,
	arrival_city varchar(200) not null,
	departure_time time(4) not null,
	arrival_time time(4) not null,
	index(flight_num),
	constraint primary key (flight_num),
	constraint foreign key (flight_num) references customers_flights (flight_num)
);

create table tickets
(
	ticket_id varchar(200) auto_increment,
	customer_id int,
	flight_num int not null,
	seat_num int not null,
	price int not null,
	index (ticket_id),
	constraint primary key (ticket_id),
	constraint foreign key (customer_id) references customers (customer_id),
	constraint foreign key (flight_num) references flights (flight_num)
);

create table admin
(
	admin_id int auto_increment,
	username varchar(200) not null,
	password varchar(200) not null,
	first_name varchar(200) not null,
	last_name varchar(200) not null,
	index(admin_id),
	constraint(username),
	constraint primary key (admin_id)
);

####################################################################
#################### POPULATE THE DATABASE #########################
####################################################################

-- insert into customers_flights (customer_id) values (1);
-- insert into customers_flights (customer_id) values (2);
-- insert into customers_flights (customer_id) values (3);
-- insert into customers_flights (customer_id) values (4);
-- insert into customers_flights (customer_id) values (5);
-- 
-- insert into cities (city, state) values ("New York", "NY");
-- insert into cities (city, state) values ("Los Angeles", "CA");
-- insert into cities (city, state) values ("Boston", "MA");
-- insert into cities (city, state) values ("Philadelphia", "PA");
-- insert into cities (city, state) values ("Dallas", "TX");
-- 
-- insert into flights (flight_num, flight_date, seat_num, deparature_city, arrival_city, departure_time, arrival_time) values (1,date: "2021-12-21", 1, "New York", "Los Angeles", "12:23", "3:38");
-- insert into flights (flight_num, flight_date, seat_num, deparature_city, arrival_city, departure_time, arrival_time) values (1, date: "2021-12-21", 2, "New York", "Los Angeles", "12:23", "3:38");
-- insert into flights (flight_num, flight_date, seat_num, deparature_city, arrival_city, departure_time, arrival_time) values (1, date: "2021-12-21", 3, "New York", "Los Angeles", "12:23", "3:38");
-- insert into flights (flight_num, flight_date, seat_num, deparature_city, arrival_city, departure_time, arrival_time) values (1, date: "2021-12-21", 4, "New York", "Los Angeles", "12:23", "3:38");
-- insert into flights (flight_num, flight_date, seat_num, deparature_city, arrival_city, departure_time, arrival_time) values (1, date: "2021-12-21", 5, "New York", "Los Angeles", "12:23", "3:38");
-- 
-- insert into tickets (customer_id, flight_num, seat_num, price) values