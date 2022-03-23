-- update trigger
DELIMITER //

CREATE TRIGGER my_update_trigger
    BEFORE UPDATE ON `artists`
    FOR EACH ROW
BEGIN
    SET NEW.updatedAt = NOW();
END //

DELIMITER ;

-- use procedure with cursor
SET @emailList = '';
CALL createEmailList(@emailList);
SELECT @emailList;

-- createEmailList(@INOUT variable)
DELIMITER //

CREATE PROCEDURE createEmailList(INOUT emailList varchar(4000))
BEGIN
	DECLARE finished INTEGER DEFAULT 0;
	DECLARE emailAddress varchar(100) DEFAULT "";

	DEClARE curEmail CURSOR FOR SELECT email FROM artists;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

	OPEN curEmail;

	getEmail: LOOP
		FETCH curEmail INTO emailAddress;
		IF finished = 1 THEN 
			LEAVE getEmail;
		END IF;
		-- build email list
		SET emailList = CONCAT(emailAddress,";",emailList);
	END LOOP getEmail;
	
    CLOSE curEmail;
END //

DELIMITER ;

-- getAllAlbumsOfArtist(artistName)
DELIMITER //

CREATE PROCEDURE GetAllAlbumsOfArtist(IN artistName VARCHAR(50))
BEGIN
	SELECT
	artists.stage_name,
	bands.band_name,
	albums.title,
	albums.genre,
	albums.release_date
	FROM artists_bands
	INNER JOIN artists ON artists.id = artists_bands.artist_id
	INNER JOIN bands ON bands.id = artists_bands.band_id
	INNER JOIN albums ON albums.band_id = bands.id
    WHERE artists.stage_name = artistName;
END //

DELIMITER ;

-- getAllAlbumsOfBand(bandName)
DELIMITER //

CREATE  PROCEDURE GetAllAlbumsOfBand(IN bandName VARCHAR(50))
BEGIN
SELECT
	bands.band_name,
	albums.title,
    albums.genre,
    albums.release_date
	FROM albums
	INNER JOIN bands on bands.id = albums.band_id
    WHERE bands.band_name = bandName;
END

DELIMITER ;

-- getTotalAlbums()
DELIMITER //

CREATE PROCEDURE GetTotalAlbums()
BEGIN
	DECLARE totalAlbums INT DEFAULT 0;
    
    SELECT COUNT(*) 
    INTO totalAlbums
    FROM albums;
    
    SELECT totalAlbums;
END //

DELIMITER ;

-- getAverageAlbumsPerBand()
DELIMITER //

CREATE FUNCTION GetAverageAlbumsPerBand(bands INT, albums INT) 
	RETURNS decimal(20,2)
    DETERMINISTIC
BEGIN
	DECLARE result DECIMAL(20,2) DEFAULT 0;
	SET result = albums / bands;
	RETURN result;
END

DELIMITER ;