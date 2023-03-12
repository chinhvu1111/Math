Select distinct CITY
from STATION
where lower(SUBSTR(CITY, length(CITY),1)) not in ('a', 'e', 'i', 'o', 'u');