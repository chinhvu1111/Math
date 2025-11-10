package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class E279_MaximumNumberOfPointsFromGridQueries_hard {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static int findUpperBound(int[][] arr, int key){
        int low=0, high=arr.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid][0]>key){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int[] maxPoints(int[][] grid, int[] queries) {
        int n=grid.length;
        int m=grid[0].length;
        int len=queries.length;
        int[][] arr=new int[len][2];

        for(int i=0;i<len;i++){
            arr[i][0]=queries[i];
            arr[i][1]=i;
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0, 0, grid[0][0]});
        int[] prefixSum= new int[len];
//        boolean[][] visited=new boolean[n][m];
        int[][] maxVal=new int[n][m];

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                maxVal[i][j]=Integer.MAX_VALUE;
            }
        }
        maxVal[0][0]=grid[0][0];

        while(!queue.isEmpty()){
            int[] curNode = queue.poll();
//            System.out.printf("%s %s\n", curNode[0], curNode[1]);
//            visited[curNode[0]][curNode[1]]=true;
            int index=findUpperBound(arr, curNode[2]);
//            System.out.printf("Node: (%s, %s), val: %s, index: %s\n", curNode[0], curNode[1], grid[curNode[0]][curNode[1]], index);
            if(index==-1){
                continue;
            }
            prefixSum[index]++;
            for(int h=0;h<dx.length;h++) {
                int x1 = curNode[0] + dx[h];
                int y1 = curNode[1] + dy[h];
                //(x,y) ==> (x1,y1) ==> (x2,y2) ==> (x,y)
                //1 node is visited multiple times
                //  + Prefix[x]++ multiple times ==> Incorrec???
                if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < m && grid[x1][y1]<arr[len-1][0]) {
                    int nextMax = Math.max(curNode[2], grid[x1][y1]);
                    if(nextMax>=maxVal[x1][y1]||nextMax>=arr[len-1][0]){
                        continue;
                    }
                    if(maxVal[x1][y1]!=Integer.MAX_VALUE){
                        //Minus for the previous search
                        index=findUpperBound(arr, maxVal[x1][y1]);
//            System.out.printf("Node: (%s, %s), val: %s, index: %s\n", curNode[0], curNode[1], grid[curNode[0]][curNode[1]], index);
                        if(index==-1){
                            continue;
                        }
                        prefixSum[index]--;
                    }
                    maxVal[x1][y1]=nextMax;
                    queue.add(new int[]{x1, y1, nextMax});
//                    visited[x1][y1]=true;
                }
            }
        }
        int[] rs=new int[len];
        int sum=0;

        for(int i=0;i<len;i++){
            sum+=prefixSum[i];
//            System.out.printf("%s, ", prefixSum[i]);
            rs[arr[i][1]]=sum;
        }
//        System.out.println();
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n integer matrix grid and an array queries of (size k).
        //- Find (an array answer of size k) such that for each integer queries[i]
        // you start in (the top left cell of the matrix) and repeat (the following process):
        //  + If (queries[i]) is strictly ("greater than") (the value of the current cell) that you are in,
        // then you get (one point) if it is (your first time) visiting this cell,
        // and you can move to (any adjacent cell) in all 4 directions:
        //  + up, down, left, and right.
        //  + Otherwise, you (do not) get any points, and you (end) this process.
        //- After the process, answer[i] is (the maximum number of points) you can get.
        //* Note that for (each query) you are allowed to visit (the same cell) (multiple times).
        //- Return the resulting array answer.
        //
        //Example 1:
        //
        //Input: grid = [
        //  [1,2,3],
        //  [2,5,7],
        //  [3,5,1]
        //],
        //queries = [5,6,2]
        //Output: [5,8,1]
        //Explanation:
        //  + The diagrams above show which cells we visit to (get points) for (each query).
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //2 <= m, n <= 1000
        //4 <= m * n <= 10^5
        //k == queries.length
        //1 <= k <= 10^4
        //1 <= grid[i][j], queries[i] <= 10^6
        //
        //* Brainstorm:
        //- Sort queries to find from (small) to (big) element
        //- For each(i):
        //  + We need to continue to traverse
        //  ==> We have (many nodes) that make this (continuous traverse) is difficult.
        //
        //Input: grid =
        // [
        //  [1,2,3],
        //  [2,5,7],
        //  [3,5,1]
        // ],
        //Queries = [5,6,2]
        //Output:   [5,8,1]
        //Explanation: The diagrams above show which cells we visit to get points for each query.
        //Sort(queries) = [1,5,8]
        //- Prefix sum:
        //  + each node: ==> We find the index to increase(count) [Binary search]
        //- q=1: [1]
        //  + prefixSum = [1,0,0]
        //- q=5: [1,2,2,3]
        //  + prefixSum = [1,3,0]
        //- q=8: [1,2,3,2,5,3,5,1]
        //  + prefixSum = [1,3,4]
        //
        //
        //1.1, Case
        //- 1-3-(1) ==> count[1] = 1 rather than 2
        //[0,0] => [0,1] => [1,1]
        //[0,0] => [1,0] => [1,1]
        //1 3
        //1 2
        //==> [1][1] is allow to re-visit
        //  prefixSum[3] = 1
        //  ==> We can use (maxVal[x][y])
        //      + Max value for all path to [x][y]
        //      ==> This value will be (updated gradually)
        //
        //1.2, Optimization
        //
        //
//        int[][] grid = {{1,2,3},{2,5,7},{3,5,1}};
//        int[] queries = {5,6,2};
        int[][] grid = {{5,2,1},{1,1,2}};
        int[] queries = {3};
        int[] rs = maxPoints(grid, queries);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
