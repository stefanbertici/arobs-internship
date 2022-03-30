CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(45),
	last_name VARCHAR(45),
	email VARCHAR(45),
	encrypted_password VARCHAR(45),
	country_of_origin VARCHAR(45),
	role VARCHAR(45),
	status VARCHAR(45),
	PRIMARY KEY (id)
  );
  
  CREATE TABLE playlists (
	id INT NOT NULL AUTO_INCREMENT,
	owner_user_id INT,
	type VARCHAR(45),
	created_date DATE,
	updated_date DATE,
	PRIMARY KEY (id),
    FOREIGN KEY (owner_user_id)
		REFERENCES users(id)
  );
  
  CREATE TABLE songs (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(45),
	duration TIME,
	created_date DATE,
	PRIMARY KEY (id)
  );
  
  CREATE TABLE albums (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(45),
	description VARCHAR(45),
    genre VARCHAR(45),
	release_date DATE,
    label VARCHAR(45),
	PRIMARY KEY (id)
  );
  
  CREATE TABLE artists (
	id INT NOT NULL AUTO_INCREMENT,
	activity_start_date DATE,
	activity_end_date DATE,
	type VARCHAR(45),
	PRIMARY KEY (id)
  );