-- create tables
CREATE TABLE artists(
	id INT AUTO_INCREMENT,
	stage_name VARCHAR(50),
	first_name VARCHAR(50),
    last_name VARCHAR(50),
    birthday DATE,
	PRIMARY KEY(id)
);

CREATE TABLE bands(
	id INT AUTO_INCREMENT,
	band_name VARCHAR(50),
	formation_place VARCHAR(50),
    formation_date DATE,
	PRIMARY KEY(id)
);

CREATE TABLE artists_bands(
	id INT AUTO_INCREMENT,
	artist_id INT,
	band_id INT,
	PRIMARY KEY(id),
    FOREIGN KEY (artist_id) REFERENCES artists(id),
    FOREIGN KEY (band_id) REFERENCES bands(id)
);

CREATE TABLE albums(
	id INT AUTO_INCREMENT,
	band_id INT,
	title VARCHAR(50),
    genre VARCHAR(20),
    release_date DATE,
	PRIMARY KEY(id),
    FOREIGN KEY (band_id) REFERENCES bands(id)
);