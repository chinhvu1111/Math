package contest;

import java.util.List;

public class E111_BlockPlacementQueries {

    public static List<Boolean> getResults(int[][] queries) {
        return null;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There exists (an infinite number line), with its origin at 0 and extending towards (the positive x-axis).
        //You are given a 2D array queries, which contains two types of queries:
        //  + For a query of type 1, queries[i] = [1, x]. Build (an obstacle at distance x) from the origin.
        //  It is guaranteed that there is (no obstacle at distance x) when the query is asked.
        //  + For a query of type 2, queries[i] = [2, x, sz].
        //  Check if it is possible to place a block of (size sz) "anywhere" in the range [0, x] on the line,
        //  such that the block (entirely) lies in the range [0, x].
        //  A block cannot be placed if it (intersects) with any obstacle,
        //  but it may touch it.
        //* Note that you do not actually place the block. Queries are separate.
        //* Return a boolean array results,
        // where results[i] is true if you can place (the block specified in the ith query of type 2), and (false otherwise).
        //
        //- Tức là query type = 1
        //  + Thì đặt được block at distance =x + (size=0)
        //- Query type = 2
        //  + Check xem đặt được không (Chứ không đặt)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= queries.length <= 15 * 10^4
        //2 <= queries[i].length <= 3
        //1 <= queries[i][0] <= 2
        //1 <= x, sz <= min(5 * 10^4, 3 * queries.length)
        //The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
        //The input is generated such that there is at least one query of type 2.
        //+ length thì không lớn
        //+ size of the block thì sẽ lớn.
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
        //Output: [false,true,true]
        //Explanation:
        //For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.
        //
        //- Bài toán trở thành:
        //  + range lớn cho đến lần queries thứ (i)
        //+ Query type = 1 sẽ cut liên tục
        //+ Query type = 2 sẽ check liên tục
        //
        //Ex:
        //0,1,5,10
        //=> Tìm range max nhất trong array được sorted
        //
        //
    }
}
