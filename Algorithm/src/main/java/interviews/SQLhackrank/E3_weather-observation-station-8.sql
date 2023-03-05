
Select distinct CITY
from STATION
where SUBSTR(CITY, length(CITY)) in ('a', 'e', 'i', 'o', 'u')
and SUBSTR(CITY, 1,1) in ('a', 'e', 'i', 'o', 'u')