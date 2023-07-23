package E1_Graph.E1_BFS;

import java.util.Arrays;

public class E1_WallsAndGates {
    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    //         (x-1,y)
    //  (x,y-1) (x,y)  (x,y+1)
    //         (x+1,y)
    public static int traverseWrong(int x, int y, int[][] minWeightPath, int[][] rooms, boolean[][] visited){
        if(rooms[x][y]==-1){
            return (minWeightPath[x][y]=Integer.MAX_VALUE);
        }
        if(rooms[x][y]==0){
            return 0;
        }
        if(minWeightPath[x][y]!=0&&minWeightPath[x][y]!=Integer.MAX_VALUE){
            return minWeightPath[x][y];
        }
        visited[x][y]=true;
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];
            if(x1>=0&&x1<rooms.length&&y1>=0&&y1<rooms[0].length){
                if(visited[x1][y1]){
                    continue;
                }
                int currentValue=traverseWrong(x1, y1, minWeightPath, rooms, visited);
//                System.out.printf("%s %s, %s\n", x1, y1, currentValue);
                if(currentValue==Integer.MAX_VALUE){
                    continue;
                }
                rs=Math.min(rs, traverseWrong(x1, y1, minWeightPath, rooms, visited));
            }
        }
        visited[x][y]=false;
        if(rs==Integer.MAX_VALUE){
            return (minWeightPath[x][y]=Integer.MAX_VALUE);
        }
        return (minWeightPath[x][y]=(rs+1));
    }

    public static void traverse(int x, int y, int[][] minWeightPath, int[][] rooms, boolean[][] visited, int value){
        if(rooms[x][y]==-1){
            return;
        }
        if(minWeightPath[x][y]>value){
            minWeightPath[x][y]=value;
        }else{
            return;
        }
        visited[x][y]=true;

        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];
            if(x1>=0&&x1<rooms.length&&y1>=0&&y1<rooms[0].length){
                if(visited[x1][y1]||rooms[x1][y1]==0){
                    continue;
                }
                traverse(x1, y1, minWeightPath, rooms, visited, value+1);

            }
        }
        visited[x][y]=false;
    }

    public static void wallsAndGates(int[][] rooms) {
        int n=rooms.length;
        int m=rooms[0].length;
        int[][] minWeightPath=new int[n][m];
        boolean[][] visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(minWeightPath[i], Integer.MAX_VALUE);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(rooms[i][j]==0){
                    traverse(i, j, minWeightPath, rooms, visited, 0);
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(rooms[i][j]==Integer.MAX_VALUE){
                    rooms[i][j]=minWeightPath[i][j];
                }
                System.out.printf("%s,", rooms[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //-1 A wall or an obstacle.
        //0 A gate.
        //INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
        //
        //- Điền mỗi empty room khoảng cách từ nó đến nearest gate.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //m == rooms.length
        //n == rooms[i].length
        //1 <= m, n <= 250
        //rooms[i][j] is -1, 0, or 2^31 - 1.
        //
        //- Brainstorm
        //- Nếu làm bình thường thì sẽ bị cases:
        //+ (x, y) --> (x-1, y) (Không phải đường thoã mãn do Weight=5) (Weight[x-1][y]=4)
        //+ (x,y) --> (x+1,y) (Weight=1)
        // và (x-1, y) --> tính theo (x,y) (Weight=2) ==> Chứ không phải =4 ==> SAI.
        //[[2,-1,0,2],
        // [2,2,2,-1],
        // [2,-1,2,-1],
        // [0,-1,2,2]]
        //Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
        //
        //- Cách đơn giản hơn là từ gate --> Đi ra + ghi đè vào all.
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //+ n, m is the size of the array
        //- Time complexity : O(n*m)
        //- Space complexity : O(n*m)
        //
        //1.3, Lỗi sai:
        //- Như đã bị test case bên trên --> Lỗi khi đi từng node để tìm cổng
        //** Với dạng bài như thế này (TÌM ĐƯỜNG ĐI NGẮN NHẤT ĐẾN)
        // ==> Ta nên đi từ target để update all nodes kề
        //
        //#Reference:
        //130. Surrounded Regions
        //317. Shortest Distance from All Buildings
        //489. Robot Room Cleaner
        int[][] rooms={
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}};
//        int[][] rooms={{-1}};
//        int[][] rooms={{}};
//        int[][] rooms={{Integer.MAX_VALUE}};
        wallsAndGates(rooms);
    }
}
