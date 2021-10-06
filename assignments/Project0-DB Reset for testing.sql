 DROP TABLE transactions;
 DROP TABLE accounts;
 DROP TABLE users;
 
 CREATE TABLE users(
 	user_id int auto_increment,
 	username VARCHAR(200),
 	password VARCHAR(200),
 	email VARCHAR(200),
 	first_name VARCHAR(200),
 	last_name VARCHAR(200),
 	CONSTRAINT PRIMARY KEY (user_id)
 );
 
 CREATE TABLE accounts(
 	account_id int auto_increment,
 	account_type VARCHAR(200),
 	balance decimal(10,2),
 	user_id int,
 	CONSTRAINT PRIMARY KEY (account_id),
 	CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (user_id)
 );
 
 CREATE TABLE transactions(
 	transaction_id int auto_increment,
 	deposit bool,
 	withdrawal bool,
 	transfer bool,
 	transfer_to_account_id int,
 	transaction_amount decimal(10,2),
 	account_id int,
 	CONSTRAINT PRIMARY KEY (transaction_id),
 	CONSTRAINT FOREIGN KEY (account_id) REFERENCES accounts (account_id)
 );

INSERT INTO users (username, password, email, first_name, last_name)
VALUES('apetersen','badpassword','andrew.petersen@revature.net','Andrew','Petersen');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES('kplummer1','P4ssw0rd!','kyle.plummer@revature.org','Kyle','Plummer');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES('kirkStinks','theBestCap\'n','jlp@starfleet.sft','Jean-Luc','Picard');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES('lfcjr.coach','upTheReds','aay@l.epl','Ali','al-Yassiri Jr.');

INSERT INTO users (username, password, email, first_name, last_name)
VALUES('bobbyt','dropthetables','bobby@droptable.xkc','Robert\'); DROP TABLE users','XKCD');

INSERT INTO accounts (account_type, balance, user_id)
VALUES('Checking',42.00,1);

INSERT INTO accounts (account_type, balance, user_id)
VALUES('Business',0.01,2);

SELECT * FROM users u;
SELECT * FROM accounts a;
SELECT * FROM transactions;