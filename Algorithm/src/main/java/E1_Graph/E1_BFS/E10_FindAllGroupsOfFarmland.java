package E1_Graph.E1_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E10_FindAllGroupsOfFarmland {

    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static int[] getGroup(int[][] land, boolean[][] visited, int x, int y, int n, int m){
        //Time : O(n*m)
        //Space: O(n*m)
        Queue<int[]> nodes=new LinkedList<>();
        nodes.add(new int[]{x, y});
        visited[x][y]=true;
        int[] leftTop =new int[]{x, y};
        int[] rightDown =new int[]{x, y};

        while(!nodes.isEmpty()){
            int[] curNode=nodes.poll();

            for(int i=0;i<dx.length;i++){
                int x1=curNode[0]+dx[i];
                int y1=curNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&land[x1][y1]==1){
                    visited[x1][y1]=true;
                    nodes.add(new int[]{x1, y1});
                    leftTop[0]=Math.min(leftTop[0], x1);
                    leftTop[1]=Math.min(leftTop[1], y1);
                    rightDown[0]=Math.max(rightDown[0], x1);
                    rightDown[1]=Math.max(rightDown[1], y1);
                }
            }
        }
        return new int[]{leftTop[0], leftTop[1], rightDown[0], rightDown[1]};
    }

    public static int[][] findFarmland(int[][] land) {
        int n=land.length;
        int m=land[0].length;
        List<int[]> rsList=new ArrayList<>();
        //Space : O(n*m)
        boolean[][] visited=new boolean[n][m];

        //Time : O(n)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]&&land[i][j]==1){
                    int[] curGroup = getGroup(land, visited, i, j, n, m);
                    rsList.add(curGroup);
                }
            }
        }
        int[][] rs=new int[rsList.size()][4];

        for(int i=0;i<rsList.size();i++){
            rs[i][0]=rsList.get(i)[0];
            rs[i][1]=rsList.get(i)[1];
            rs[i][2]=rsList.get(i)[2];
            rs[i][3]=rsList.get(i)[3];
            System.out.printf("%s %s %s %s\n", rs[i][0], rs[i][1], rs[i][2], rs[i][3]);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- 0: forested land
        //- 1: farm land
        //- Có các "group":
        //  + Là nhiều farm land kết hợp tạo thành rectangle
        //  + Không có 2 groups nào là kề nhau 4 directions.
        //Find the coordinates of the top left and bottom right corner of each group of farmland.
        // A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
        //* Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
        // If there are (no groups) of farmland, return (an empty array).
        //- You may return the answer in any order.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //m == land.length
        //n == land[i].length
        //1 <= m, n <= 300
        //land consists of only 0's and 1's.
        //Groups of farmland are rectangular in shape.
        //
        //- Brainstorm
        //Ex:
        //Input: land = [[1,0,0],[0,1,1],[0,1,1]]
        //Output: [[0,0,0,0],[1,1,2,2]]
        //
        //Ex:
        //Input: land =
        // [[1,0,0],
        // [0,1,1],
        // [1,1,1]]
        //==> Invalid testcase
        //Ex:
        //Input: land =
        // [[1,0,1],
        // [0,1,1],
        // [0,1,1]]
        //==> Invalid testcase
        //
        //- Không có 2 groups cạnh nhau --> Tức là toàn bộ các group của testcases must be a (rectangle).
        //
        //- Bài này có thể dùng bfs được.
        //
        //1.1, Optimization
        //- Đoạn này để tránh dùng visited[][] ==> Thay đổi value input array là được.
        //  + Change from 1 to 0.
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //#Reference:
        //1905. Count Sub Islands
        int[][] land={{1,0,0},{0,1,1},{0,1,1}};
        findFarmland(land);
    }
}
