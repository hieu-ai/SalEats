DROP DATABASE IF EXISTS SalEats;
CREATE DATABASE SalEats;

USE SalEats;
CREATE TABLE UserInfo (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    email VARCHAR(50) NOT NULL,
    pw VARCHAR(50) NOT NULL,
    gglogin VARCHAR(50)
);

INSERT INTO UserInfo ( username, email, pw, gglogin)
	VALUES	( 'abc', 'abc@gmail.com', 'abcs','');
    
CREATE TABLE Favorites (
	username VARCHAR(50),
	foreign key (username) references UserInfo(username),
    restaurant VARCHAR(50) NOT NULL,
    imglink VARCHAR(150),
    address VARCHAR(150) ,
    yelplink VARCHAR(150),
    phone VARCHAR(50) ,
    cuisine VARCHAR(50) ,
    price VARCHAR(50) ,
    rating VARCHAR(50) 
);

SELECT * FROM UserInfo ui , Favorites f Where ui.username = f.username;



