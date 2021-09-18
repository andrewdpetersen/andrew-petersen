USE todolist;

CREATE TABLE todoitems(
	item_index int AUTO_INCREMENT,
	complete boolean,
	item_text varchar(200),
	CONSTRAINT PRIMARY KEY (item_index)
);

INSERT INTO todoitems (complete, item_text) VALUES (FALSE,'Create to do list');
INSERT INTO todoitems (complete, item_text) VALUES (FALSE,'Debug to do list');
INSERT INTO todoitems (complete, item_text) VALUES (FALSE,'Enjoy to do list');
