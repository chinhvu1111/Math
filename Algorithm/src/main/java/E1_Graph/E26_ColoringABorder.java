package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E26_ColoringABorder {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int oldColor=grid[row][col];
        int n=grid.length;
        int m=grid[0].length;
        Queue<int[]> nodes=new LinkedList<>();

        nodes.add(new int[]{row, col});
        boolean[][] visited=new boolean[n][m];

        while (!nodes.isEmpty()){
            int[] currentNode=nodes.poll();
            int x=currentNode[0];
            int y=currentNode[1];
            if(x==0||y==0||x==n-1||y==m-1){
                grid[x][y]=color;
            }
            visited[x][y]=true;
            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]){
                    if(grid[x1][y1]==oldColor){
                        nodes.add(new int[]{x1, y1});
                        visited[x1][y1]=true;
                    }else{
                        grid[x][y]=color;
//                        visited[x1][y1]=true;
                    }
                }
            }
        }
        return grid;
    }

    public static void println(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.printf("%s,", grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Tô màu đường viền bao gồm các square sao cho nó chứa square(row, col)
        //- Tô màu đường viền cùng màu điểm đã cho.
        //- 2 square kề nhau --> 4 directions
        //- 2 square thuộc cùng 1 connnected components nếu:
        //+ They are adjacent
        //+ They have the same color
        //- Đường viền là đường chứa các squares thuộc (connected component) tiếp xúc với ít nhất 1 square (không thuộc connected components) hoặc trên biên của grid
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ m == grid.length
        //+ n == grid[i].length
        //+ 1 <= m, n <= 50
        //+ 1 <= grid[i][j], color <= 1000
        //
        //- Clear requirement:
        //VD:
        //grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
        //[[(1), 1],
        // [ 1,  2]]
        //-->
        //[[3,3],
        // [3,2]]
        //
        //VD-1:
        //grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
        //[[1,(2),2],
        // [2,3,2]]
        //
        //grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
        //1 2 2 2 2 0
        //1 1  1  3 4
        //1 2 (1),1 3
        //1 1, 1, 1 3
        //0 0 0 0 0 0
        //
        //- Brainstorm
        //- Cần xác định thế nào là không tiếp xúc điểm không thuộc (connected component)
        //+ Tức là điểm ta traverse qua hết all connected to current node ==> this node is not in (the collection)
        //+ Tức là chỉ cần có kết nối với 1 điểm khác màu là được.
        //+ Nếu điểm khác màu đó bị bao quanh bởi các điểm giống nhau thì sao???
        //==> Theo đề bài thì vẫn bị coi là border ==> Vẫn bị đổi màu.
        //+ Điểm hiện tại cũng thế --> Vẫn có thể sát điểm != color ==> Vẫn bị đổi màu.
        //VD:
        //0 1 1  1  3 4
        //1 1 2 (1),1 3
        //0 1 1, 1, 1 3
        //0 0 0 0 0 0 0
        //
        //[[5,1,1,1,3,4],
        // [1,1,2,1,1,3],
        // [5,1,1,1,1,3],
        // [5,5,5,5,5,5]]
        //
        //Expected result:
        //[[5,7,7, 7, 3,4],
        // [7,7,2,(7),7,3],
        // [5,7,7, 7, 7,3],
        // [5,5,5, 5, 5,5]]
        //
        //- Special test case:
        //** Dùng bfs + thay đổi gía trị grid ==> chú ý 1 điều rằng nếu thay đổi --> Cần đánh visited nếu không thì về sau khi duyệt lại
        //==> Value thay đổi mà vẫn quét như trong bài này là kết quả của (current node) tính theo kết quả các nodes xung quanh --> có thể sẽ bị sai.
        //
        //VD:
        //[[2,1,3,2,1,1,2],
        //[1,2,3,1,2,1,2],
        //[1,2,1,2,2,2,2],
        //[2,1,2,2,2,2,2],
        //[2,3,3,3,(2),1,2]]
        //
        //4, 4, 3
        //
        //
        //[[2,1,3,2,1,1,3],
        //[1,2,3,1,3,1,3],
        //[1,2,1,3,2,3,3],
        //[2,1,3,3,[2],3,3], --> ở đây nếu 2 quét xuống dưới --> thấy 3 !=2 ==> 2 hợp lệ (WRONG) / ==> Đáng nhẽ phải quét giá trị cũ là 2 (Invalid) / --> Cần đánh visited
        //[2,3,3,3,3,1,3]]
        //- Bài này dùng BFS traverse những điểm cùng màu:
        //+ Nếu điểm hiện tại connect với (1 điểm khác màu/ Ở biên) --> Đổi màu nó
        //+ Dùng local var để nhớ màu node hiện tại + traverse đến hết.
        //#Reference:
        //1. Two Sum
        //2596. Check Knight Tour Configuration
        //2197. Replace Non-Coprime Numbers in Array
        //1561. Maximum Number of Coins You Can Get
        //164. Maximum Gap
        //817. Linked List Components
        //714. Best Time to Buy and Sell Stock with Transaction Fee
        int[][] grid={
                {5,1,1,1,3,4},
                {1,1,2,1,1,3},
                {5,1,1,1,1,3},
                {5,5,5,5,5,5}};
        colorBorder(grid, 1, 3, 7);
        println(grid);
    }
}
