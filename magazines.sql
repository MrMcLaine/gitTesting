DROP DATABASE IF EXISTS shop_of_magazines;
CREATE DATABASE shop_of_magazines CHAR SET utf8;
USE shop_of_magazines;

CREATE TABLE user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  role VARCHAR(45) NOT NULL
);  

CREATE TABLE magazine (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(255),
  price_for_mount DECIMAL(4,2) NOT NULL
);

CREATE TABLE subscription (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    magazine_id INT NOT NULL,
    status BOOLEAN DEFAULT false
); 

ALTER TABLE subscription ADD FOREIGN KEY (user_id) 
	REFERENCES user(id);
ALTER TABLE subscription ADD FOREIGN KEY (magazine_id) 
	REFERENCES magazine(id);

CREATE TABLE payment (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    magazine_id INT NOT NULL,
    date_of_payment TIMESTAMP DEFAULT now(),
    sum_payment DECIMAL(4,2) NOT NULL
);

ALTER TABLE payment ADD FOREIGN KEY (user_id) 
	REFERENCES user(id);
ALTER TABLE payment ADD FOREIGN KEY (magazine_id) 
	REFERENCES magazine(id);

