package E1_daily;

import java.util.*;

public class E209_FindBuildingWhereAliceAndBobCanMeet_classic {


    public static int[] leftmostBuildingQueriesUndone(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        //Time: O(m)
        int[][] sortQueries = new int[m][3];

        //Time: O(m)
        for (int i = 0; i < m; i++) {
            sortQueries[i][0] = Math.max(queries[i][0], queries[i][1]);
            sortQueries[i][1] = i;
        }
        //Time: O(n*log(n))
        Arrays.sort(sortQueries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int minIndex = Integer.MAX_VALUE;
        //Time: O(m)
        for (int i = m - 1; i >= 0; i--) {
            minIndex = Math.min(minIndex, sortQueries[i][1]);
            sortQueries[i][2] = minIndex;
        }
        for (int i = 0; i < n; i++) {

        }
        return null;
    }

    public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        List<int[]> monoStack=new ArrayList<>();
        int n=heights.length;
        int m=queries.length;
        //Time: O(m)
        //Space: O(m)
        int[] rs=new int[m];
        Arrays.fill(rs, -1);

        List<List<int[]>> newQueries=new ArrayList<>();

        //Time: O(n)
        //Space: O(n)
        for(int i=0;i<heights.length;i++){
            newQueries.add(new ArrayList<>());
        }
        //Time: O(m)
        for(int i=0;i<m;i++){
            int a=queries[i][0];
            int b=queries[i][1];

            if(a>b){
                int temp=a;
                a=b;
                b=temp;
            }
            if(heights[b]>heights[a]||a==b){
                rs[i]=b;
            }else{
                newQueries.get(b).add(new int[]{heights[a], i});
            }
        }

        //Time: O(n)
        for(int i=n-1;i>=0;i--){
//            int monoSize=monoStack.size();
            for(int[] q: newQueries.get(i)){
                //Time: O(log(n))
                int pos=search(monoStack, q[0]);
                if(pos>=0){
                    rs[q[1]]=pos;
                }
            }
            while (!monoStack.isEmpty()&&monoStack.get(monoStack.size()-1)[0]<=heights[i]){
                monoStack.remove(monoStack.size()-1);
            }
            monoStack.add(new int[]{heights[i], i});
        }
        return rs;
    }

    public static int search(List<int[]> monoStack, int curHeight){
        int n=monoStack.size();
        int low=0, high=n-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(monoStack.get(mid)[0]>curHeight){
                rs=monoStack.get(mid)[1];
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed array heights) of (positive integers),
        //  where heights[i] represents (the height of the ith building).
        //- If a person is in building i,
        //  + they can move to any other building j if and only if:
        //      + i < j and heights[i] < heights[j].
        //- You are also (given another array queries) where queries[i] = [ai, bi].
        //- On (the ith query),
        //  + Alice is in (building ai) while Bob is in (building bi).
        //* Return (an array ans) where ans[i] is (the index of the "leftmost" building) where Alice and Bob can meet on the ith query.
        //- If Alice and Bob cannot move to (a common building on query i), set ans[i] to -1.
        //
        //Example 1:
        //
        //Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
        //Output: [2,5,-1,5,2]
        //Explanation:
        //In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
        //In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
        //In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
        //In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
        //In the fifth query, Alice and Bob are already in the same building.
        //For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
        //For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= heights.length <= 5 * 10^4
        //1 <= heights[i] <= 10^9
        //1 <= queries.length <= 5 * 10^4
        //queries[i] = [ai, bi]
        //0 <= ai, bi <= heights.length - 1
        //  + length<=10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //  + they can move to any other building j if and only if:
        //      + i < j and heights[i] < heights[j].
        //Ex:
        //heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
        //Output: [2,5,-1,5,2]
        //- index1, index2 ==> Find (the leftmost) common index
        //
        //* Idea:
        //- Brute force
        //- Recursion
        //
        //- Brute force:
        //  + We find max of (heights[i1], height[i2])
        //  Start at (index=i2+1)
        //
        //- Search by value that is related to index of the queries[i][1]:
        //  + Idea is that we will sort the queries by queries[1] desc
        //- Sort (values) that is not related to (index of them)
        //  + We need to find the (left most element)
        //Ex:
        //heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
        //Output: [2,5,-1,5,2]
        //- index1, index2 ==> Find (the leftmost) common index
        //[0,1] <=> max(6,4) = 6
        //  + index=2
        //[0,3] <=> max(6,5) = 6
        //  + index=5
        //
        //queries =             [[6,2],[6,4],[8,5],[5,5],[8,3]]
        //sort(queries_index) = [[5,5,0],[6,2,1],[6,4,2],[8,3,3],[8,5,4]]
        //- Check x:
        //  + x>arr[0]
        //  + index(x)>arr[1]
        //- Sort query by value:
        //  + value by value from small to big
        //- For bigger value, they don't care about the previous value that:
        //  + They are (less than) the current value
        //Ex:
        //- bigger value with:
        //  + Smaller index
        //      + It is ok ==> Append new element with bigger index
        //      (current_index...peek_index, new index) : valid
        //  + Bigger index
        //      (peek_index, new index... current_index)
        //      Ex: h=1,2,8,10,13,14,[21] queries=[[6,5], [7,1]
        //      rs=[6(21),2(8)]
        //      ==> DIFFICULT
        //- Sort height + suffix min index of height
        //  +
        //  q=[6,5],
        //  heights=      [7,1,10,14,20,31]
        //  sort_heights= [1,(7),10,14,20,31]
        //  7 is not valid although we find 7
        //      + Valid index>5 could be laid at (first indices or last indices)
        //
        //* Solution:
        //- Next Greater Element II
        //  + return the (next greater number) for every element in nums.
        //Input: nums = [1,2,1]
        //Output: [2,-1,2]
        //=======CODE
        //  + from right to left:
        //      + stack.peek()< current:
        //          + stack.pop()
        //      + stack.push(current)
        //=======CODE
        //
        //- newQueries[Greater_indexOfHeight] = [Height[a],indexOfQueries]
        //- monoStack:
        //  + Pair<Height[i], indexOfHeight)
        //  ==> (Max height) with (smaller index)
        //      + (Smaller index) because we go from (right -> left)
        //* KINH NGHIỆM:
        //- Khi dùng stack bằng array:
        //- Tốt nhất là theo chiều:
        //  + [a,b,c][top]: Nếu dùng stack thường
        //  + [a,b,c][size-1]: Để dùng như array ==> ta sẽ để cùng 1 chiều ntn cho đồng nhất.
        //
        //      Ex: monoStack (top)[10,12,15,17]
        //          + Index incremental
        //          + Value decremental
        //      + If we use array list:
        //      Ex: monoStack [17,12,15,10(index==size-1 : heightIndex_Decremental)]
        //          + Index decremental
        //          + Value incremental
        //
        //- For monoStack [10,12,15,17]:
        //  + We can find the bigger value with (min index)
        //- Binary search on monotonic
        //
        //* KINH NGHIỆM:
        //- Việc sort value + search từ (small -> big)
        //
//        int[] heights = {6, 4, 8, 5, 2, 7};
//        int[][] queries = {{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2}};
        int[] heights = {5,3,8,2,6,1,4,6};
        int[][] queries = {{0,7},{3,5},{5,2},{3,0},{1,6}};
        int[] rs = leftmostBuildingQueries(heights, queries);
        int n = rs.length;
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n+m+n*log(n))

        for (int i = 0; i < n; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
    }
}
