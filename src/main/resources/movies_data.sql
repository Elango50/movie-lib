DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  year INT NOT NULL,
  director VARCHAR(250) DEFAULT NULL
);

INSERT INTO movies VALUES(1, 'Train To Busan', '2016', 'Sang-ho Yeon');
INSERT INTO movies VALUES(2, 'The Angry Birds Movie 2', '2019', 'Thurop Van Orman');
INSERT INTO movies VALUES(3, 'The Angry Birds Movie', '2018', 'Thurop Van Orman');

select * from movies;


