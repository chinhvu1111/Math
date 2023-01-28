## Method 1
Select p.SecondHighestSalary
from (
         (Select salary as SecondHighestSalary
          from (Select salary, row_number() over(order by salary desc) as rowNum
                from (Select distinct salary from Employee) e1) e
          where e.rowNum = 2
          union all
          Select null as SecondHighestSalary)) p
order by p.SecondHighestSalary desc limit 1;

# Method 2
#- Tính max thứ 2 bằng cách tính so sánh với max nhất
#- Select max(salary) from Employee : Để có thể trả lại null
Select max(salary) as SecondHighestSalary
from Employee where salary < (Select max(salary) from Employee);

#Method 3
-- SELECT
--     select_list
-- FROM
--     table_name
-- LIMIT [offset,] row_count;
-- offset : được tính từ 0
-- row_count : số row cần lấy.
-- ifnull( empty, null) : Nếu không thấy thì sẽ để null.
-- UNION expression:
-- #####
-- SELECT column_name(s) FROM table1
-- UNION ALL
-- SELECT column_name(s) FROM table2;
SELECT IFNULL(
               (SELECT DISTINCT Salary
                FROM Employee
                ORDER BY Salary DESC
                LIMIT 1,1), NULL) AS SecondHighestSalary;
