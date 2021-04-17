-- this is for joining the 2 tables candidates and RaceGender into one
-- where if the name shows up in both tables, we have the full info for that candidate
use Filing;
DROP VIEW IF EXISTS peoples;
CREATE VIEW peoples AS (select * from candidates
join raceGenderInfo using (FirstName, LastName)
group by FirstName, LastName);

-- this is for the Infos table where we can have duplicate candidate IDs
-- this is used for the infos table for example where we see the same candidate run for different terms
DROP VIEW IF EXISTS peoplesWithDuplicates;
CREATE VIEW peoplesWithDuplicates AS (select * from candidates
join raceGenderInfo using (FirstName, LastName));