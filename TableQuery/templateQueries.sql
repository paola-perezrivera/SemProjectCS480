use Filing;

drop procedure if exists searchingparty;
set delimiter ;;
-- how many <targetparty> party members ran in general?
create procedure searchingparty(in targetparty varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateInfos
    natural join candidateNames
    where PartyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchinggender;
set delimiter ;;
-- how many <targetparty> party members ran who use the <targetpronoun> pronouns?
create procedure searchinggender(in targetparty varchar(45), in targetpronoun varchar(45))
begin
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    natural join candidateInfos
    where Pronouns = targetpronoun
    and PartyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchinghispanic;
set delimiter ;;
-- how many <targetparty> party members ran who are <targetethnicity> ethnicity?
create procedure searchinghispanic(in targetparty varchar(45), in targetethnicity varchar(45))
begin
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    natural join candidateInfos
    where Hispanic = targetethnicity
    and PartyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchingrace;
set delimiter ;;
-- how many <targetparty> party members ran who are <targetrace> race?
create procedure searchingrace(in targetparty varchar(45), in targetrace varchar(45))
begin
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    natural join candidateInfos
    where Race = targetrace
    and PartyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchingoffice;
set delimiter ;;
-- how many <targetparty> party members ran for <targetoffice> office?
create procedure searchingoffice(in targetparty varchar(45), in targetoffice varchar(45))
begin
    select FirstName, LastName from candidateInfos
    natural join candidateNames
    where OfficeName = targetoffice
    and PartyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchingoverall;
set delimiter ;;
-- this is for the searching wireframe where the user can search
-- with up to 8 attributes based on the diagram that Paola drew for this
-- the defaults are varchars, but modify the types to be based on what we're actually searching for
-- or modify the number of parameters if we want to search for less
-- also modify attribute1, attribute2, ... to what we actually want to search for
-- the bad news is there's no way to make these parameters entirely optional without the if statements
create procedure searchingoverall(parameter1 varchar(45), parameter2 varchar(45), parameter3 varchar(45), parameter4 varchar(45),
	parameter5 varchar(45), parameter6 varchar(45), parameter7 varchar(45), parameter8 varchar(45))
begin
	select * from candidateInfos
    -- if(statement, true expression, false expression)
    where if(parameter1 is not NULL, attribute1 = parameter1, 0 = 0)
    and if(parameter2 is not NULL, attribute2 = parameter2, 0 = 0)
    and if(parameter3 is not NULL, attribute3 = parameter3, 0 = 0)
    and if(parameter4 is not NULL, attribute4 = parameter4, 0 = 0)
    and if(parameter5 is not NULL, attribute5 = parameter5, 0 = 0)
    and if(parameter6 is not NULL, attribute6 = parameter6, 0 = 0)
    and if(parameter7 is not NULL, attribute7 = parameter7, 0 = 0)
    and if(parameter8 is not NULL, attribute8 = parameter8, 0 = 0);
end ;;
set delimiter ;

drop procedure if exists demographics;
set delimiter ;;
-- this is the demographics function that will return the table of people who we want info on
-- this is an example of getting the number of people who represent certain pronouns
create procedure demographics()
begin
	select PartyName, count(*) as numCount, overallcount from candidateInfos
    join (select count(*) as overallcount from candidateNames)
    group by PartyName;
end ;;
set delimiter ;