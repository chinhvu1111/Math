-- ** Đề bài:
-- - Xác định kind of node của mỗi nodes bên trong table
-- Ex:
-- + leaf
-- + root
-- + inner
-- ** Bài này tư duy như sau:
-- 1.
-- 1.1,
-- - 1 node là root khi nó không có parent / (Vừa không có child vừa không có parent)
-- - 1 node là leaf khi nó không có child
-- - Ở đây ta chỉ cần join Tree với Tree trên đk
-- t.id=t1.p_id là được.
-- + Chú ý điều kiện node là leaf khi:
-- t1.id is null and t.p_id is not null (Tức là không có child và phải có parent)
Select distinct t.id, IF(t1.id is null and t.p_id is not null, 'Leaf',IF(t.p_id is null, 'Root','Inner')) as `type`
from Tree t left join Tree t1
on t.id=t1.p_id;