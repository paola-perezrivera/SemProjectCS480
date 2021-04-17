-- same as candidates.sql, but this is its separate file
-- because if I put this in the candidate.sql, I'll have to do the 
-- import data wizard thing all over again for the candidates
use Filing;

DROP TABLE IF EXISTS raceGenderInfo;
CREATE TABLE raceGenderInfo (
	LastName varchar(45),
    FirstName varchar(45),
    GenderPronoun varchar(45),
    Race varchar(45),
    Hispanic varchar(3)
);