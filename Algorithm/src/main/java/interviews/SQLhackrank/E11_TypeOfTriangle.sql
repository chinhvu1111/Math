-- Kinh nghiệm liên quan đến debug sql:
1.
1.1,
-- - Để debug thì ta print hết các columns ra để check kết quả có đúng hay không.
-- - if(condition, 'a','b')
--
1.2, Test cases
20 20 40 Not A Triangle
20 20 40 Isosceles
- Case bên trong cover cả vào cases bên ngoài, kinh nghiệm sắp xếp điều kiến như sau:
+ Bên ngoài sẽ cover bên trong if1( if2) : if1 sẽ tổng quan hơn if2
VD:
Điều kiện không phải tam giác sẽ cần check đầu tiên khi check (a=b and b=c)

Select if(a+b<=c or b+c<=a or c+a<=b, 'Not A Triangle',
          if(a=b and b=c and c=a, 'Equilateral',
             if(a=c or b=a or c=b, 'Isosceles', 'Scalene'))) as rs from TRIANGLES;
