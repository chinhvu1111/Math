
Select * from Logs;
-- ** Đề bài:
--
-- Tìm num mà xuất hiện 3 lần liên tiếp trong bảng logs
-- Ta có id là primary key.
-- ==> Data đã được sắp xếp từ trước
-- ** Bài này tư duy như sau:
-- Cách 1:
-- 1. Select nest 3 lần là sẽ ra kết quả
-- id+1 = id1
-- id1+1=id2
-- 1.1, Câu hỏi:
-- + Nếu ta muốn tìm n lần liên tiếp thì sao?
-- 1.2, Đúc kết:
-- + (a,b) in (select a,b from t) : Ta có thể dùng nhiều variables trong in
-- + Với nhưng số hữu hạn ta có thể viết SQL nested hữu hạn được.
--
Select distinct num as ConsecutiveNums from Logs where (num, id+1) in (Select num, id from Logs where (num, id+1) in (Select num, id from Logs));
-- 1.3, Refactor:
-- - Thay vì viết nest nhiều lần ta có thể sử dụng table Logs nhiều lần
-- VD:
--
Select distinct l1.num as ConsecutiveNums from Logs l1, Logs l2, Logs l3
where l1.id+1=l2.id and l2.id+1=l3.id and l1.num=l2.num and l2.num=l3.num;
-- - 2 cách trên time run là như nhau --> O(n^3)
-- Cách 2:
--
-- LEAD and LAG function:
-- the LEAD and LAG functions were first introduced in SQL Server 2012. They are window functions.
-- The LEAD function is used to access data from SUBSEQUENT rows along with data from the current row.
-- The LAG function is used to access data from PREVIOUS rows along with data from the current row.
-- + An ORDER BY clause is required when working with LEAD and LAG functions, but a PARTITION BY clause is optional.
-- LEAD: Xem row đằng trước row hiện tại
-- LAG : Xem row đằng sau row hiện tại
-- Luôn có phương thức order by (theo id là tốt nhất) và partition by là optional.

SELECT DISTINCT num as ConsecutiveNums
FROM
    (
        SELECT num, LEAD(num, 1) OVER(ORDER BY id) AS ld, LAG(num, 1) OVER (ORDER BY id) AS lg
        FROM logs
    )t
WHERE num=ld and num=lg;