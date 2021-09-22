#Puts us in our database
USE banking_db;


###### If we need to do a hard reset: run the commented out sections
######
######
-- DROP TABLE user_accounts;
-- DROP TABLE accounts;
-- DROP TABLE users;

-- Creates a linkage table
-- CREATE TABLE user_accounts(
-- 	junction_id INT AUTO_INCREMENT,
-- 	user_id INT NOT NULL,
-- 	account_id INT NOT NULL,
-- 	index(user_id),
-- 	index(account_id),
-- 	CONSTRAINT PRIMARY KEY (junction_id)
-- );

-- Creates a table for banking info
-- CREATE TABLE accounts(
-- 	account_id INT NOT NULL,
-- 	user_id INT NOT NULL,
-- 	account_type varchar(200),
-- 	acct_balance decimal(10,2),
-- 	CONSTRAINT PRIMARY KEY (account_id),
-- 	CONSTRAINT FOREIGN KEY (account_id) REFERENCES user_accounts (account_id)
-- );

-- Creates a table for users
-- CREATE TABLE users(
-- 	user_id INT NOT NULL,
-- 	username VARCHAR(200) NOT NULL,
-- 	password VARCHAR(200) NOT NULL,
-- 	CONSTRAINT PRIMARY KEY (user_id),
-- 	CONSTRAINT FOREIGN KEY (user_id) REFERENCES user_accounts (user_id)
-- );
#####
#####
##### END HARD RESET
