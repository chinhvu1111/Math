package E1_Graph.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E4_SwimInRisingWater {

    //          (-1,0)
    //   (0,-1),(0,0), (0,1)
    //          (1,0)
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int swimInWater(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        int[][] minTime=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(minTime[i], Integer.MAX_VALUE);
        }
        minTime[0][0]=grid[0][0];
        //Space: O(n*m)
        PriorityQueue<int[]> queue=new PriorityQueue<>((a,b) -> a[2]-b[2]);
        queue.add(new int[]{0,0, minTime[0][0]});

        //Time: O(n*m)
        while(!queue.isEmpty()){
            //Time: O(log(n*m))
            int[] curNode=queue.poll();
            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m){
                    int nextDist=Math.max(curNode[2], grid[x1][y1]);
                    if(nextDist<minTime[x1][y1]){
                        minTime[x1][y1]=nextDist;
                        queue.add(new int[]{x1, y1, nextDist});
                    }
                }
            }
        }
        return minTime[n-1][m-1];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho grid[][]
        //- At t time, the depth water everywhere is t
        //- We can swim from a square to another 4-directionally adjacent square if and only if the elevation of both square are at most (t)
        //- We can swim infinite at zero time.
        //* Return (min time) to reach from (n-1,n-1) to (0,0)
        //Ex:
        //Input: grid =
        // [
        // [0,1,2,3,4],
        // [24,23,22,21,5],
        // [12,13,14,15,16],
        // [11,17,18,19,20],
        // [10,9,8,7,6]]
        //Output: 16
        //Explanation: The final route is shown.
        //We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == grid.length
        //n == grid[i].length
        //1 <= n <= 50
        //0 <= grid[i][j] < n^2
        //Each value grid[i][j] is unique.
        //
        //- Brainstorm
        //- Ở mỗi node[i,j] ta thấy rằng ta sẽ tìm (đường đi ngắn nhất/ minimum time) từ start[0,0] --> node[i,j]
        //
        //- Idea
        //- Bài này idea là tìm min value trong all path từ (0,0) --> (n-1, n-1)
        //- Vì yếu tố swim được khi ở thời gian t ==> value mỗi case sẽ là max all giá trị các node trên path đó
        //- Với bài này nếu node -> node1 ==> Sau đó back xét (node1 -> node)
        //+ Case này sẽ bỏ qua vì ta add vào queue khi giá trị min mới của node thay đổi
        // <=> (nextMin < minDist[node1]) queue.add(node) : Vì là max lớn dần <=> positive number (dijkstra)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(V*E*Log(V))
        //- Space : O(V+E)
        //
        //#Reference:
        //1631. Path With Minimum Effort
        //
    }
}
