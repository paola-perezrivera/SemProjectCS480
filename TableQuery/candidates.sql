-- this is for pupulating the candidate table in the filing db
-- after running this query, right click "Tables" in the schema on the left under Filing
-- click "Table Data Import Wizard" and load the csvs in the github into the candidates table
create database if not exists Filing;
use Filing;

DROP TABLE IF EXISTS candidates;
CREATE TABLE candidates (
	CandidateID int,
    BracketID int,
    SlateID int,
    LastName varchar(45),
    FirstName varchar(45),
    AffiliateCommittee varchar(100),
    HeadOfSlate varchar(45),
    Address1 varchar(90),
    Address2 varchar(90),
    City varchar(45),
    State varchar(2),
    Zip int, ## There are import failures by zip in wizard
             ## ERRORS: 
             ## - Row import failed with error: ("Incorrect integer value: '' for column 'Zip' at row 1", 1366)
			 ## - Row import failed with error: ("Data truncated for column 'Zip' at row 1", 1265)
    FileDate varchar(45),
    Sequence int,
    CandidateStatus varchar(45),
    StatusDate varchar(45),
    WebsiteAddress varchar(200),
    ElecDate varchar(45),
    ElectionType varchar(10),
    PartyName varchar(45),
    PartySequence int,
    OfficeName varchar(100),
    BallotGroup varchar(45),
    OfficeSequence int,
    FormerName varchar(45),
    EmailAddress varchar(100)
    -- , primary key (LastName, FirstName)
);

-- populates raceGenderInfo table by through "import wizard"
-- directions for import wizard above. File to import: "./csv_tables/race_gender.csv"
DROP TABLE IF EXISTS raceGenderInfo;
CREATE TABLE raceGenderInfo (
	LastName varchar(45),
    FirstName varchar(45),
    GenderPronoun varchar(45),
    Race varchar(45),
    Hispanic varchar(3),
    uid int primary key auto_increment
    ## When L&F used as fk canidates can't be duplicated in candidates table
    -- , foreign key(LastName, FirstName) references candidates(LastName, FirstName)
);