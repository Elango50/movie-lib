DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings (
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  year INT NOT NULL,
  rating INT DEFAULT 0
);

INSERT INTO ratings VALUES(1, 'Train To Busan', '2016', 5.5);
INSERT INTO ratings VALUES(2, 'The Angry Birds Movie 2', '2019', 6);
INSERT INTO ratings VALUES(3, 'The Angry Birds Movie', '2018', 8);

select * from ratings;


