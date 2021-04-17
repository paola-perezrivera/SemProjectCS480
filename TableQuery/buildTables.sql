-- this is where we build the tables
-- update the code here when we need to build a particular table based on csv files so far
use Filing;

DROP TABLE IF EXISTS candidateRaceGender;
DROP TABLE IF EXISTS candidateInfos;
DROP TABLE IF EXISTS candidateNames;
CREATE TABLE candidateNames (
	C_ID int NOT NULL AUTO_INCREMENT,
	FirstName varchar(45),
    LastName varchar(45),
    primary key(C_ID));

INSERT INTO candidateNames(FirstName, LastName)
	SELECT FirstName, LastName FROM peoples GROUP BY FirstName, LastName;

CREATE TABLE candidateRaceGender (
	C_ID int NOT NULL,
    Pronouns varchar(45),
    Race varchar(45),
	Hispanic varchar(3),
	primary key (C_ID),
    foreign key (C_ID) references candidateNames(C_ID));

INSERT INTO candidateRaceGender
	Select C_ID, GenderPronoun, Race, Hispanic from peoples
    join candidateNames using (FirstName, LastName);
    
CREATE TABLE candidateInfos (
	C_ID int NOT NULL,
    ElecDate varchar(45),
    ElectionType varchar(45),
	PartyName varchar(45),
    OfficeName varchar(45),
-- not sure if PK is needed here, but if so this is how the PKs should be separated
-- since there was a candidate who was in 2 different offices for the same ElecDate and Election Type
--    primary key (C_ID, OfficeName),
    foreign key (C_ID) references candidateNames(C_ID));

INSERT INTO candidateInfos
	Select C_ID, ElecDate, ElectionType, PartyName, OfficeName from peoplesWithDuplicates
    join candidateNames using (FirstName, LastName);