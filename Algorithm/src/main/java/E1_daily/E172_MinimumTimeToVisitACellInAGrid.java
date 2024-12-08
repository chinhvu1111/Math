package E1_daily;

import java.util.Comparator;
import java.util.PriorityQueue;

public class E172_MinimumTimeToVisitACellInAGrid {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static int minimumTime(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        PriorityQueue<int[]> minHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });
        minHeap.add(new int[]{0, 0, 0});
        int[][] memoCost=new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memoCost[i][j]=Integer.MAX_VALUE;
            }
        }
//        visited[0][0]=true;
        boolean isValid=false;
        for (int i = 0; i < dx.length; i++) {
            int x1=dx[i];
            int y1=dy[i];
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]<=grid[0][0]+1){
                isValid=true;
            }
        }
        if(!isValid){
            return -1;
        }

        while(!minHeap.isEmpty()){
            int[] curNode = minHeap.poll();

            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m){
                    //x -> y
                    //x + 2 + 2 +... = x + 2*n
                    //Ex:
                    //(2+1) + 2*x = 10
                    //=> x = 3
                    //(2+1) + 2*3 = 9
                    //- Next:
                    //  + (2+1) + 2*4 = 11
//                    int count = (grid[x1][y1]-(curNode[2]+1))/2;
//                    int curCost= curNode[2]+1;
//                    if(grid[x1][y1]>curNode[2]+1){
//                        curCost = (count+1)*2+curNode[2]+1;
//                    }
                    int waitTime = ((grid[x1][y1] - curNode[2]) % 2 == 0) ? 1 : 0;
                    int curCost= Math.max(grid[x1][y1]+waitTime, curNode[2]+1);
//                    System.out.println(curCost>=grid[x1][y1]);
//                    System.out.printf("curCost:%s, grid[%s][%s]:%s\n", curCost, x1,y1,grid[x1][y1]);
                    if((memoCost[x1][y1]!=Integer.MAX_VALUE&&memoCost[x1][y1]>=grid[x1][y1])||memoCost[x1][y1]<=curCost){
                        continue;
                    }
                    if(x1==n-1&&y1==m-1){
                        return curCost;
                    }
                    memoCost[x1][y1]=curCost;
                    minHeap.add(new int[]{x1, y1, curCost});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a m x n matrix grid consisting of (non-negative integers) where grid[row][col]
        // represents the (minimum time) required to be able to visit the cell (row, col),
        // which means you can visit the cell (row, col) only when (the time) you visit
        // it is (greater than or equal) to grid[row][col].
        //- You are standing in the (top-left cell of the matrix) in the 0th second,
        // and you must move to any adjacent cell in the four directions: up, down, left, and right.
        // Each move you make takes 1 second.
        //* Return (the minimum time) required in which you can visit (the bottom-right cell of the matrix).
        // If you cannot visit the bottom-right cell, then return -1.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //
        //Constraints:
        //m == grid.length
        //n == grid[i].length
        //2 <= m, n <= 1000
        //4 <= m * n <= 10^5
        //0 <= grid[i][j] <= 10^5
        //grid[0][0] == 0
        //
        //- Brainstorm
        //- This problem is same as (the find Minimum Time to Reach Last Room II)
        //  + We use dijikstra (Normal problem is harder the current problem)
        //- (0,0) -> (n-1,m-1)
        //- Min heap sort by time:
        //- Node:
        //  + (x,y, time)
        //
        //- Main point:
        //- (x,y) -> (x1,y1):
        //  + I suppost that we can not move to (x1,y1) because ((memoCost[x][y]+1) < grid[x1][y1]
        //  ==> We need to move to other cell and then backing to the (x1,y1)
        //Ex:
        //- (x,y) -> (x2,y2)
        //  => memoCost(x,y) + 2*k + 1 >= grid[x1][y1]
        //
        //- Special cases:
        //- If we stand at (0,0), we can not go to any cells:
        //  + return -1
        //
        int[][] grid = {
                {0,1,3,2},
                {5,1,2,5},
                {4,3,8,6}};
//        int[][] grid = {
//                {0,2,4},
//                {3,2,1},
//                {1,0,4}
//        };
        System.out.println(minimumTime(grid));
    }
}
