-- Hàm substring trong mysql (i,j)
-- i :pos (từ 1)
-- j : length string
Select distinct CITY from STATION where SUBSTR(CITY, length(CITY)) in ('a', 'e', 'i', 'o', 'u')