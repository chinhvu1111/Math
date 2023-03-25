-- https://stackoverflow.com/questions/11183572/whats-the-difference-between-rank-and-dense-rank-functions-in-oracle
-- rank() : sẽ đánh số 1,1 (2 số),3 (Đánh số theo số lượng)
-- dense_rank() : sẽ đánh số 1,1,2 ==> Liên tiếp nhau (Đánh số theo level)
VD:
rank()
1 : 1
1 : 1
2 : 3
2 : 3
2 : 3
4 : 6

VD:
dense_rank()
1 : 1
1 : 1
2 : 2
2 : 2
2 : 2
4 : 3

Select score,
       DENSE_RANK() OVER(ORDER BY score DESC) as `rank`
from Scores;