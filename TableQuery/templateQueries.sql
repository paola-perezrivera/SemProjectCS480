use Filing;

drop procedure if exists searchingparty;
set delimiter ;;
create procedure searchingparty(in targetparty varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateInfos natural join candidateNames where partyName = targetparty;
end ;;
set delimiter ;;

drop procedure if exists searchinggender;
set delimiter ;;
create procedure searchinggender(in targetpronoun varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    where Pronouns = targetpronoun;
end ;;
set delimiter ;;

drop procedure if exists searchinghispanic;
set delimiter ;;
create procedure searchinghispanic(in targethispanic varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    where Hispanic = targethispanic;
end ;;
set delimiter ;;

drop procedure if exists searchingrace;
set delimiter ;;
create procedure searchingrace(in targetrace varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateRaceGender
    natural join candidateNames
    where Race = targetrace;
end ;;
set delimiter ;;

drop procedure if exists searchingoffice;
set delimiter ;;
create procedure searchingoffice(in targetoffice varchar(45))
begin
	-- I guess this is one way of storing the result - putting it in a procedure
    select FirstName, LastName from candidateInfos
    natural join candidateNames
    where OfficeName = targetoffice;
end ;;
set delimiter ;;