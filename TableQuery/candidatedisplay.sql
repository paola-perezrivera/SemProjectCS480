-- this is for showing the table with the attributes we are looking for
-- there probably could've been a better option of just dropping columns
SELECT LastName, FirstName, ElecDate, ElectionType, PartyName, OfficeName FROM candidates ORDER BY LastName, FirstName;
