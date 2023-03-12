Select *
from (Select Concat(Name, '(', SUBSTR(occupation, 1, 1), ')') as rs from OCCUPATIONS) s1
UNION ALL
Select a.rs
from (Select concat('There are a total of ', count(1), ' ', lower(occupation), 's.') as rs,
             count(1)                                                                   num,
             occupation
      from OCCUPATIONS
      group by occupation) a
order by rs;