--
-- ** Đề bài:
-- Table: Employee
--
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | id           | int     |
-- | name         | varchar |
-- | salary       | int     |
-- | departmentId | int     |
-- +--------------+---------+
--
-- Table: Department
--
-- +-------------+---------+
-- | Column Name | Type    |
-- +-------------+---------+
-- | id          | int     |
-- | name        | varchar |
-- +-------------+---------+
-- Tìm employee có highest salary của mỗi department
--
-- Example:
-- Input:
-- Employee table:
-- +----+-------+--------+--------------+
-- | id | name  | salary | departmentId |
-- +----+-------+--------+--------------+
-- | 1  | Joe   | 70000  | 1            |
-- | 2  | Jim   | 90000  | 1            |
-- | 3  | Henry | 80000  | 2            |
-- | 4  | Sam   | 60000  | 2            |
-- | 5  | Max   | 90000  | 1            |
-- +----+-------+--------+--------------+
-- Department table:
-- +----+-------+
-- | id | name  |
-- +----+-------+
-- | 1  | IT    |
-- | 2  | Sales |
-- +----+-------+
-- Output:
-- +------------+----------+--------+
-- | Department | Employee | Salary |
-- +------------+----------+--------+
-- | IT         | Jim      | 90000  |
-- | Sales      | Henry    | 80000  |
-- | IT         | Max      | 90000  |
--
--
-- ** Bài này tư duy như sau:
-- Cách 1:
-- 1,
-- 1.1, Bài này ta chỉ cần dùng tìm max của salary và department id tương ứng
-- ==> Sau đó select để tìm danh sách employee có salary tương tự như vậy là được.
Select d.name as Department, e.name as Employee, e.salary as Salary
from Employee e join Department d on e.departmentId=d.id
where (e.salary, e.departmentId) in
      (Select max(salary), e.departmentId from Employee e join Department d on e.departmentId=d.id group by e.departmentId);
-- Cách 2:
-- 2,
-- 2.1, Ta có thể dùng rank để tìm max salary --> Sau đó tương tự
-- + Thường những dạng mà lương cao nhất của 1 phòng ban --> Thường sẽ là partition by departmentId và order by salary
-- 2.2, CTE format:
--
WITH employees_in_california AS (
    SELECT * FROM employees WHERE city = 'California'
)
SELECT emp_name, emp_age, city FROM employees_in_california
WHERE emp_age >= 32 ORDER BY emp_name;
--
-- Mysql
with w1 as (
    Select
        rank() over (partition by departmentId order by salary desc) `rank`,
            departmentId,
        name,
        salary
    from Employee
)select d.name as "Department",
        w.name as "Employee",
        w.salary as "Salary"
from w1 w join Department d
on w.departmentId=d.id and w.`rank`=1;