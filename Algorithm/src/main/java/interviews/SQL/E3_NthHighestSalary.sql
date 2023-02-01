CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
declare M int;
set M=N-1;
RETURN (
        # Write your MySQL query statement below.
      Select distinct salary from Employee order by salary desc limit M,1
    );
END