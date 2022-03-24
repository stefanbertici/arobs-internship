-- dirty read test transaction
-- first connection

START TRANSACTION;

UPDATE artists
SET stage_name = 'EMIN3M'
WHERE id = 1;

DO sleep(10);

ROLLBACK;

-- second connection
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

SELECT stage_name
FROM artists
WHERE id = 1;

DO SLEEP(10);

SELECT stage_name
FROM artists
WHERE id = 1;