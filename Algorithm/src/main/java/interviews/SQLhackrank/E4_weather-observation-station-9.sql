-- add dấu ; sau
-- Không điền blank line sau
-- lower in mysql
Select distinct CITY
from STATION
where lower(SUBSTR(CITY, 1,1)) not in ('a', 'e', 'i', 'o', 'u');