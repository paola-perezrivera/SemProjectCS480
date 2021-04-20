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