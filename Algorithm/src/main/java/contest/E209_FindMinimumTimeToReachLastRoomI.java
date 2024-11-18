package contest;

import java.util.Arrays;

public class E209_FindMinimumTimeToReachLastRoomI {

    public static int[][] memo;
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static void solution(int x, int y, int n, int m, int time, int[][] moveTime){
        memo[x][y]=time;
        if(x==n-1&&y==m-1){
            return;
        }
        for(int i=0;i<dx.length;i++){
            int x1= x+dx[i];
            int y1= y+dy[i];
            if(x1>=0&&y1>=0&&x1<n&&y1<m){
                int curTime = Math.max(time+1, moveTime[x1][y1]+1);
                if(memo[x1][y1]>curTime){
                    solution(x1, y1, n, m, curTime, moveTime);
                }
            }
        }
    }

    public static int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        memo=new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        solution(0,0, n, m, 0, moveTime);
        return memo[n-1][m-1];
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is (a dungeon with n x m rooms) arranged as a grid.
        //- You are given a 2D array moveTime of size n x m,
        //  where moveTime[i][j] represents (the minimum time) in seconds
        // when you can start moving to that room.
        //- You start from the room (0, 0) at time (t = 0) and can move to an adjacent room.
        //- Moving between adjacent rooms (takes exactly one second).
        //* Return (the minimum time) to reach the room (n - 1, m - 1).
        //- Two rooms are adjacent if they share (a common wall), either (horizontally or vertically).
        //
        //Example 1:
        //Input: moveTime = [[0,4],[4,4]]
        //Output: 6
        //Explanation:
        //The minimum time required is 6 seconds.
        //At time t == 4, move from room (0, 0) to room (1, 0) in one second.
        //At time t == 5, move from room (1, 0) to room (1, 1) in one second.
        //- Each room has the open time:
        //  + moveTime[i][j] represents (the minimum time) in seconds when we can start moveing to that room
        //
        //(0,0) -> (1,0)
        //  + t=4: take 1 second
        //(1,0) -> (1,1)
        //  + t=5: take 1 second
        //
        //Ex:
        //A -> B (4)
        //  + A phải đi 4 second rồi
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n == moveTime.length <= 50
        //2 <= m == moveTime[i].length <= 50
        //0 <= moveTime[i][j] <= 10^9
        //  + n,m <= 50: Time = O(n^3)
        //
        //** Brainstorm
        //- (0,0) -> (n-1,m-1)
        //  + It takes min = (n+m) seconds.
        //- We can solve this using BFS?
        //  - If we meet the cell[i][j] = 5 but we just traversed 3 seconds:
        //      + We can add the cell[i][j] with 5 seconds
        //          + If we add this cell with 5 value included in the node:
        //              + It is difficult to maintain (the visited state)
        //      + If we don't add new node
        //          + Visited[i][j] is still false
        //      + For the BFS algorithm:
        //          + We need to (find the path) to go the destination so if we (skip the node)
        //          ==> We (can not reach the destination)
        //Ex:
        //(0,0) -> (1,5):10000 -> (2,3) : 10001
        //(0,0) -> (1,0) -> (2,3) : 3
        //  + We need to recalculate (2,3)
        //- Cache the depth for the node (i,j)
        //
        //
        int[][] moveTime = {{0,4},{4,4}};
        System.out.println(minTimeToReach(moveTime));
    }
}
