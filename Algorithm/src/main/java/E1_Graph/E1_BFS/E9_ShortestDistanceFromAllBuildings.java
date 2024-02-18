package E1_Graph.E1_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class E9_ShortestDistanceFromAllBuildings {
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static void solution(int[][] grid, int[][] dpMin, int x, int y, int n, int m, int[][] connectCount){
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{x, y});
        boolean[][] visited=new boolean[n][m];
        int[][] temp=new int[n][m];

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            visited[curNode[0]][curNode[1]]=true;
            int val=0;

            if(curNode[0]!=x||curNode[1]!=y){
                val=temp[curNode[0]][curNode[1]];
            }

            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]==0){
                    temp[x1][y1]+=val+1;
                    dpMin[x1][y1]+=temp[x1][y1];
                    queue.add(new int[]{x1,y1});
                    visited[x1][y1]=true;
                    connectCount[x1][y1]++;
                }
            }
        }
    }

    public static int shortestDistance(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] minDp=new int[n][m];
        int[][] connectCount=new int[n][m];
        int numHouse=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    solution(grid, minDp, i, j, n, m, connectCount);
                    numHouse++;
//                    println(minDp);
//                    System.out.println();
                }
            }
        }
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0&&connectCount[i][j]==numHouse){
//                    System.out.printf("%s %s, value: %s\n", i, j, minDp[i][j]);
                    rs=Math.min(minDp[i][j], rs);
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int[] solutionOptimization(int[][] grid, int[][] dpMin, int x, int y, int n, int m, int emptyVal){
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{x, y});
        int depth=0;
        int minVal=Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            int size=queue.size();

            for(int j=0;j<size;j++){
                int[] curNode=queue.poll();

                for(int i=0;i<dx.length;i++){
                    int x1=curNode[0]+dx[i];
                    int y1=curNode[1]+dy[i];

                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==emptyVal){
                        grid[x1][y1]--;
                        dpMin[x1][y1]+=depth+1;
                        queue.add(new int[]{x1,y1});
                        minVal=Math.min(minVal, dpMin[x1][y1]);
                    }
                }
            }
            depth++;
        }
        emptyVal--;
        println(grid);
//        System.out.printf("Min val: %s, empty Val: %s\n", minVal, emptyVal);
        return new int[]{minVal, emptyVal};
    }

    public static int shortestDistanceOptimization(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dpMin=new int[n][m];
        int emptyVal=0;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    int[] curRs=solutionOptimization(grid, dpMin, i, j, n, m, emptyVal);

                    rs= curRs[0];
                    emptyVal=curRs[1];
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void println(int[][] minDp){
        int n=minDp.length;
        int m=minDp[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s,", minDp[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Đề bài:
        //- You are given an m x n grid of values 0, 1, or 2, where:
        //+ each 0 marks an empty land that you can pass by freely,
        //+ each 1 marks a building that you cannot pass through, and
        //+ each 2 marks an obstacle that you cannot pass through.
        //You want to build a house on an empty land that (reaches all buildings) in (the shortest total travel distance).
        // You can only move up, down, left, and right.
        //* Return the shortest travel distance for such a house.
        // If it is not possible to build such a house according to the above rules, return -1.
        //- Tìm điểm có thể traverse đến các house mất ít time nhất
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 50
        //grid[i][j] is either 0, 1, or 2.
        //There will be at least one building in the grid.
        //==> Size của array không quá lớn.
        //
        //- Brainstorm
        //Ex:
        //Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
        //Output: 7
        //Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
        //The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
        //So return 7.
        //- Tức là ta cần tìm point sao cho sum min
        //==> Return lại sum
        //
        //- Ở đây ta dùng BFS ở mỗi điểm để biết điểm nào xa nhất/ gần nhất nó.
        //- Cách đơn giản nhất là:
        //  + Xét all nodes + bfs cho chúng để tìm khoảng cách nhỏ nhất giữa mỗi node <-> các node khác.
        //
        //- Có case liên quan đến việc không traverse được:
        //  + Thì ta cần lưu 1 array để check số lượng house mỗi node connect được:
        //  --> Nếu == total of houses : Thì ta mới có thể xét được.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time : O(N*N*M*M)
        //- Space : O(N*M)
        //
        //2. Có 2 ideas:
        //+ BFS từ empty space --> Houses
        //+ BFS từ house --> all empty space
        //- Ta thấy rằng:
        //+ Không phải tất cả các empty node đều có thể connect đến all house.
        //- Ta sẽ xét lần lượt từng house
        //  + Step 1: Xét all empty node mà house-1 có thể connect đến: mark 0 --> -1
        //  + Step 2 : Xét house-2 chỉ scan trên the empty được mark==-1 ==> Tức là nó sẽ giảm đi số lượng chỗ này.
        //- Làm như trên thì ta sẽ vừa cover được case các node không đến được house + vừa reduce được time
        //
        //** KINH NGHIỆM:
        //- BFS liên quan đến depth ==> Level traversal
        //  + pop theo size + depth tăng lên là được: Không cần lưu depth array.
        //  + Thậm chí không cần visited luôn.
        //
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
//        int[][] grid = {{1,2,0}};
//        System.out.println(shortestDistance(grid));
        System.out.println(shortestDistanceOptimization(grid));
        //
        //#Reference:
        //296. Best Meeting Point
        //1162. As Far from Land as Possible
        //Walls and Gates
        //Best Meeting Point
        //As Far from Land as Possible
    }
}
