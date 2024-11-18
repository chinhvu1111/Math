package contest;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E210_FindMinimumTimeToReachLastRoomII {

    public static int[][][] memo;
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};

    public static void solution(int x, int y, int n, int m, int time, int[][] moveTime, int turn){
        if(x==n-1&&y==m-1){
            return;
        }
        for(int i=0;i<dx.length;i++){
            int x1= x+dx[i];
            int y1= y+dy[i];
            if(x1>=0&&y1>=0&&x1<n&&y1<m){
                int cost = turn==0?1:2;
                int curTime = Math.max(time+cost, moveTime[x1][y1]+cost);
                if(memo[x1][y1][turn]>curTime){
                    memo[x1][y1][turn]=curTime;
                    solution(x1, y1, n, m, curTime, moveTime, 1-turn);
                }
            }
        }
    }

    public static int minTimeToReachTLE(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        memo=new int[n][m][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(memo[i][j], Integer.MAX_VALUE);
            }
        }
        solution(0,0, n, m, 0, moveTime, 0);
        return Math.min(memo[n-1][m-1][0], memo[n-1][m-1][1]);
    }

    public static int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[2]-b[2]);
        memo=new int[n][m][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(memo[i][j], Integer.MAX_VALUE);
            }
        }
        queue.add(new int[]{0, 0, 0, 0});
        while (!queue.isEmpty()){
            int[] curNode = queue.poll();
            boolean isDone= false;
            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m){
                    int cost = curNode[3]==0?1:2;
                    int curTime = Math.max(curNode[2]+cost, moveTime[x1][y1]+cost);
                    if(memo[x1][y1][curNode[3]]>curTime){
                        memo[x1][y1][curNode[3]]=curTime;
                        if(x1==n-1&&y1==m-1){
                            isDone=true;
                            break;
                        }
                        queue.add(new int[]{x1, y1, curTime, 1-curNode[3]});
                    }
                }
            }
            if(isDone){
                break;
            }
        }
        return Math.min(memo[n-1][m-1][0], memo[n-1][m-1][1]);
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is a dungeon with n x m rooms arranged as a grid.
        //- You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents (the minimum time in seconds)
        // when you can start moving to that room.
        //- You start from the room (0, 0) at time t = 0 and can move to (an adjacent room).
        //- Moving between adjacent rooms takes (one second) for (one move) and two seconds (for the next), alternating between (the two).
        //* Return (the minimum time) to reach the room (n - 1, m - 1).
        //- Two rooms are adjacent if they share (a common wall), either (horizontally or vertically).
        //
        //Example 1:
        //Input: moveTime = [[0,4],[4,4]]
        //Output: 7
        //Explanation:
        //The minimum time required is 7 seconds.
        //At time t == 4, move from room (0, 0) to room (1, 0) in (one second).
        //At time t == 5, move from room (1, 0) to room (1, 1) in (two seconds).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n == moveTime.length <= 750
        //2 <= m == moveTime[i].length <= 750
        //0 <= moveTime[i][j] <= 10^9
        //  + n and m is bigger
        //  + move is bigger
        //
        //** Brainstorm
        //
        //Ex:
        //A(0) -(1)-> B(1) -(2)> C
        //  + We need to store the memo[i][j][turn]
        //- If we have the [i][j][1] = 10
        //  + It means (x,y) -2-> (i,j)
        //      + If we get [i][j][0] = 10 => Ignore
        //Ex:
        //A -> B -> C -> D
        //  + 1 + 2 + 1
        //  + 2 + 1 + 2
        //- How to cut branch
        //- Could we do the dijikstra for this problem?
        //  - If we go to the [n-1][m-1] (for the first time)
        //  => It means we will get (the minimum cost) to get there
        //
//        int[][] moveTime = {{0,4},{4,4}};
        //
        //** EXP:
        //- Những bài dạng cost để đến (i,j) hoặc là min cost để đến (i,j)
        //  + Cost có thể là distance (Biến đổi thành dist theo turn)
        //  ==> Có thể làm dijikstra
        //
        int[][] moveTime = {{0,58},{27,69}};
        //Rs: 70
        //Expected: 71
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m*log(n*m))
        //
        System.out.println(minTimeToReachTLE(moveTime));
        System.out.println(minTimeToReach(moveTime));
    }
}
