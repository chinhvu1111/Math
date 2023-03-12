-- ** Đề bài :
- Group các task trong khoảng liền kề vào với nhau:
+ start-date, end-date
<=> end-date=start1-date (Cùng 1 group task)

-- Bài này tư duy như sau:
1.
1.1, Ta tìm các khoảng liên tiếp nhau --> Cần biến đổi tư duy thành tìm 2 biên của các khoảng.
- Ta thấy biên
+ start-date thường sẽ không thuộc vào 1 end-date nào của thẳng đằng trước.
+ end-date thường sẽ không thuộc vào 1 start-date nào của thẳng đằng sau.
1.2,
- Ngoài ra khi tìm được rồi --> ta cần phải nối 2 biên cặp lại với nhau dạng (start_date, end_date)
+ Chỉ nối các cặp có cùng level.
==> Ta cần đánh dấu level ==> Có thể order by + id row để đánh dấu.

Method 1: Join
Select start_date, end_date
from
(Select start_date, row_number() over(order by start_date) as rn
from Projects where start_date not in (Select end_date from Projects)) s
inner join
(Select end_date, row_number() over(order by end_date) as rn
from Projects where end_date not in (Select start_date from Projects)) s1
on s.rn=s1.rn order by end_date-start_date;

Method 2:
SELECT MIN(START_DATE) AS PROJECT_START_DATE, MAX(END_DATE) AS PROJECT_END_DATE
FROM
-- TABLE T2 WITH PROJECT_ID COLUMN
(SELECT T1.*,
        -- CUMULATIVE SUM OF UNIQUE PROJECTS ORDERED BY START_DATE
        SUM(CASE WHEN T1.START_DATE IN (SELECT END_DATE FROM PROJECTS) THEN 0 ELSE 1 END)
            OVER (ORDER BY START_DATE) AS PROJECT_ID
 FROM PROJECTS T1) T2
-- GROUP BY PROJECT_ID
GROUP BY PROJECT_ID
-- ORDER BY TOTAL TASK_ID AND START_DATE
ORDER BY COUNT(TASK_ID), MIN(START_DATE);