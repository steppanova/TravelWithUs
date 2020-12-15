DROP DATABASE IF EXISTS tourdb;
CREATE DATABASE tourdb;
use tourdb;


CREATE TABLE account (
  id    INTEGER PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(60)  NOT NULL ,
  email         VARCHAR(100)  NOT NULL
);

CREATE TABLE country (
  id   INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  url VARCHAR(50) NOT NULL,
  tour_count INTEGER  NOT NULL
);

INSERT INTO country VALUES (DEFAULT, 'Egypt','/egypt',3);
INSERT INTO country VALUES (DEFAULT, 'Turkey','/turkey',2);
INSERT INTO country VALUES (DEFAULT, 'Maldives Republic','/maldives',1);
INSERT INTO country VALUES (DEFAULT, 'Morocco','/morocco',1);
INSERT INTO country VALUES (DEFAULT, 'Thailand','/thailand',2);
INSERT INTO country VALUES (DEFAULT, 'Italy','/italy',3);
INSERT INTO country VALUES (DEFAULT, 'France','/france',1);
INSERT INTO country VALUES (DEFAULT, 'Israel','/israel',1);
INSERT INTO country VALUES (DEFAULT, 'England','/england',1);



CREATE TABLE city (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_country INTEGER  NOT NULL,
   FOREIGN KEY (id_country) REFERENCES country (id)
   ON DELETE CASCADE
    ON UPDATE CASCADE,
  name       VARCHAR(50) NOT NULL,
  tour_count INTEGER 
);

INSERT INTO city VALUES (DEFAULT, 1, 'Hurghada',1);
INSERT INTO city VALUES (DEFAULT, 1, 'Sharm El Sheikh',1);
INSERT INTO city VALUES (DEFAULT, 2, 'Antalya',1);
INSERT INTO city VALUES (DEFAULT, 2, 'Bodrum',1);
INSERT INTO city VALUES (DEFAULT, 3, 'Male',1);
INSERT INTO city VALUES (DEFAULT, 4, 'Casablanca',1);
INSERT INTO city VALUES (DEFAULT, 5, 'Bangkok',1);
INSERT INTO city VALUES (DEFAULT, 6, 'Milan',1);
INSERT INTO city VALUES (DEFAULT, 6, 'Florence',1);
INSERT INTO city VALUES (DEFAULT, 1, 'Cairo',1);
INSERT INTO city VALUES (DEFAULT, 7, 'Paris',0);
INSERT INTO city VALUES (DEFAULT, 9, 'London',1);
INSERT INTO city VALUES (DEFAULT, 8, 'Tel Aviv',0);
INSERT INTO city VALUES (DEFAULT, 8, 'Jerusalem',1);
INSERT INTO city VALUES (DEFAULT, 6, 'Rome',1);
INSERT INTO city VALUES (DEFAULT, 7, 'Nice',1);
INSERT INTO city VALUES (DEFAULT, 5, 'Pattaya',1);


CREATE TABLE tour (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(40) NOT NULL,
  hotel_type  VARCHAR(50) NOT NULL,
  description  VARCHAR(50) NOT NULL,
  duration  INTEGER,
  price      NUMERIC(8,2),
  person    INTEGER,
  hot_tour  INTEGER,
  image_link VARCHAR(40) NOT NULL,
  id_country INTEGER REFERENCES country (id),
  id_city INTEGER REFERENCES city (id)
);


INSERT INTO tour VALUES (DEFAULT, 'Rest Hurghada','*****', 'Mild climate/Fascinating views/Incredible fauna', 6, 800, 2, 0, 'hurghada.jpg', 1, 1);
INSERT INTO tour VALUES (DEFAULT, 'Rest Sharm el Sheih','****', 'Mild climate/Fascinating views/Incredible fauna', 7, 950, 2, 1, 'RestSharmelSheih.jpg', 1, 2);
INSERT INTO tour VALUES (DEFAULT, 'Rest Antalya','****', 'Mild climate/Fascinating views/Incredible fauna', 4, 450, 1, 0, 'antalya.jpg', 2, 3);
INSERT INTO tour VALUES (DEFAULT, 'Rest Bodrum','*****', 'Beautiful beaches/The most delicious cuisine', 5, 950, 2, 1, 'bodrum.jpg', 2, 4);
INSERT INTO tour VALUES (DEFAULT, 'Rest Male', '****', 'Mild climate/Fascinating views/Incredible fauna', 6, 650, 1, 0, 'maldives.jpg', 3, 5);
INSERT INTO tour VALUES (DEFAULT, 'Rest Casablanca','****', 'Incredible views/Mirror water pools', 5, 860, 1, 1, 'casablanca.jpg', 4, 6);
INSERT INTO tour VALUES (DEFAULT, 'Excursion Bangkok','***', 'Mild climate/Fascinating views/Incredible fauna', 5, 800, 1, 0, 'bangkok.jpg', 5, 7);
INSERT INTO tour VALUES (DEFAULT, 'Shopping Milan', '****', 'Mild climate/Fascinating views/Incredible fauna', 7, 900, 2, 1, 'milan.jpg', 6, 8);
INSERT INTO tour VALUES (DEFAULT, 'Shopping London', '****', 'Incredible views/The most delicious cuisine', 5, 950, 2, 1, 'london.jpg', 9, 12);
INSERT INTO tour VALUES (DEFAULT, 'Rest Cairo', '****', 'Beautiful beaches/The most delicious cuisine', 7, 950, 2, 1 , 'cairo.jpg', 1, 10);
INSERT INTO tour VALUES (DEFAULT, 'Excursion Florence', '***', 'Mirror water pools/Beautiful beaches', 5, 500, 1, 0, 'florence.jpg', 6, 9);
INSERT INTO tour VALUES (DEFAULT, 'Excursion Jerusalem','***', 'Mirror water pools/Beautiful beaches', 5, 600, 1, 0, 'jerusalem.jpg', 8, 14);
INSERT INTO tour VALUES (DEFAULT, 'Excursion Rome','***', 'Incredible views/Beautiful beaches', 5, 920, 2, 0, 'rome.jpg', 6, 15);
INSERT INTO tour VALUES (DEFAULT, 'Rest Nice', '****', 'Mirror water pools/The most delicious cuisine', 6, 1150, 2, 0, 'nice.jpg', 7, 16);
INSERT INTO tour VALUES (DEFAULT, 'Rest Pattaya', '***', 'Beautiful beaches/The most delicious cuisine', 5, 2150, 2, 0,'pattaya.jpg', 5, 17);


CREATE TABLE orders (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_account   INTEGER NOT NULL ,
    FOREIGN KEY (id_account) REFERENCES account(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  created DATETIME      NOT NULL
);


CREATE TABLE orders_item (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_orders   INTEGER NOT NULL ,
  id_tour      INTEGER NOT NULL,
   FOREIGN KEY (id_tour) REFERENCES tour(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
	FOREIGN KEY (id_orders) REFERENCES orders(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  count integer NOT NULL
);

SELECT * FROM account;
SELECT * FROM country;
SELECT * FROM city;
SELECT * FROM tour;
SELECT * FROM orders;
SELECT * FROM orders_item;


