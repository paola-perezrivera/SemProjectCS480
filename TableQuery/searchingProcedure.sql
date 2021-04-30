drop procedure if exists searchingoverall;
set delimiter ;;
-- this is for the searching wireframe where the user can search
-- with up to 8 attributes based on the diagram that Paola drew for this
-- the defaults are varchars, but modify the types to be based on what we're actually searching for
-- or modify the number of parameters if we want to search for less
-- also modify attribute1, attribute2, ... to what we actually want to search for
-- the bad news is there's no way to make these parameters entirely optional without the if statements
create procedure searchingoverall(parameter1 varchar(45), parameter2 varchar(45), parameter3 varchar(45), parameter4 varchar(45),
	parameter5 varchar(45), parameter6 varchar(45))
begin
	select FirstName, LastName from candidateInfos 
    natural join candidateRaceGender natural join candidateNames
    -- if(statement, true expression, false expression)
    where if(parameter1 is not NULL, Race = parameter1, 0 = 0)
    and if(parameter2 is not NULL, Pronouns = parameter2, 0 = 0)
    and if(parameter3 is not NULL, OfficeName = parameter3, 0 = 0)
    and if(parameter4 is not NULL, Hispanic = parameter4, 0 = 0)
    and if(parameter5 is not NULL, PartyName = parameter5, 0 = 0)
    and if(parameter6 is not NULL, date_format(str_to_date( ElecDate, "%m/%d/%Y"), "%Y") = parameter6, 0 = 0);
end ;;
set delimiter ;