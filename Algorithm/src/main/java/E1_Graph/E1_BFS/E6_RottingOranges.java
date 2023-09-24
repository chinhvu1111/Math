package E1_Graph.E1_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E6_RottingOranges {

    //           (-1,0)
    //  (0,-1)   (0, 0), (0, 1)
    //           (1, 0)
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};

    public static int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        int numRottenOrange=0;
        Queue<int[]> nodes=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    nodes.add(new int[]{i, j});
                }else if(grid[i][j]==1){
                    numRottenOrange++;
                }
            }
        }
        if(numRottenOrange==0){
            return 0;
        }else if(nodes.isEmpty()){
            return -1;
        }

        int time=0;

        while(!nodes.isEmpty()){
            int size=nodes.size();
            time++;
//            List<int[]> list = new ArrayList<>(nodes);
//            for(int j=0;j<size;j++){
//                System.out.printf("%s %s, ", list.get(j)[0], list.get(j)[1]);
//            }
//            System.out.printf("Size: %s \n", size);

            for(int j=0;j<size;j++){
                int[] currentNode=nodes.poll();
                int x=currentNode[0];
                int y=currentNode[1];
                for(int i=0;i<dx.length;i++){
                    int x1=dx[i]+x;
                    int y1=dy[i]+y;

                    if(x1>=0&&x1<n&&y1>=0&&y1<m){
                        if(grid[x1][y1]==1){
                            grid[x1][y1]=2;
                            nodes.add(new int[]{x1, y1});
//                            System.out.printf("Add: %s %s\n", x1, y1);
                            numRottenOrange--;
                            if(numRottenOrange==0){
                                return time;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //+ 0 representing an empty cell,
        //+ 1 representing a fresh orange, or
        //+ 2 representing a rotten orange.
        //- Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
        //* Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
        //+ Số lượng minimum time to all of fresh orange becoming the rotten orange
        //
        //** Idea
        //1.
        //- Constraint
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 10
        //grid[i][j] is 0, 1, or 2.
        //
        //- Brainstorm
        //- Ta sẽ tính số lượng count các organ chưa bị rotten
        //- Ta sẽ add all rotten orange vào queue
        //+ Sau đó sẽ dùng bfs để duyệt các orange bên 4 direction
        //+ Mỗi lần duyệt trúng 1 orange ==> change grid[i][j]=2 (Mark as rotten)
        //- Áp dụng cách duyệt queue theo layer dạng poll() số lần = size (queue)
        //
        //1.1, Optimization
        //* Kinh nghiệm code:
        //- Luôn tư duy là làm thế nào code có thể chạy hết các branch mình viết ra
        //Ex:
        //- Đặt câu hỏi là có trường hợp nào code đi ra khỏi loop không?
        //- Đặt câu hỏi là có trường hợp nào code bị stuck at loop không?
        //- Đặt câu hỏi là có trường hợp nào code bị index out of bound không?
        //- Có trường hợp nào cut time không?
        //
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(n)
        //
//        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid = {{1,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
        //#Reference:
        //2101. Detonate the Maximum Bombs
        //2258. Escape the Spreading Fire
    }
}
