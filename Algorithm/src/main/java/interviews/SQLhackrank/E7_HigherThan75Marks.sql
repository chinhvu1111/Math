SELECT NAME FROM STUDENTS WHERE MARKS>75 ORDER BY SUBSTRING(NAME,-3), ID;

Select a.name from (
Select name, id from STUDENTS
where marks>75 order by substr(name, length(name)-2, 3), ID asc)
a;