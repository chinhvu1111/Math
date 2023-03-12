Select distinct CITY
from STATION
where lower(SUBSTR(CITY, length(CITY),1)) not in ('a', 'e', 'i', 'o', 'u')
or lower(SUBSTR(CITY, 1,1)) not in ('a', 'e', 'i', 'o', 'u');

Select distinct CITY
from STATION
where lower(SUBSTR(CITY, length(CITY),1)) not in ('a', 'e', 'i', 'o', 'u')
   and lower(SUBSTR(CITY, 1,1)) not in ('a', 'e', 'i', 'o', 'u');