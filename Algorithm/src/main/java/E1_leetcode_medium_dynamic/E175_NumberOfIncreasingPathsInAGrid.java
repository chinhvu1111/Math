package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E175_NumberOfIncreasingPathsInAGrid {

    public static long[][] memo;
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};
    public static int mod= 1_000_000_007;

    public static long solution(int x, int y, int[][] grid, int n, int m){
        if(memo[x][y]!=-1){
            return memo[x][y];
        }
        long curRs=0;
        for (int i = 0; i < dx.length; i++) {
            int x1=x+dx[i];
            int y1=y+dy[i];
            if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]>grid[x][y]){
                curRs=(curRs+solution(x1, y1, grid, n, m))%mod;
            }
        }
        return memo[x][y] = (curRs+1)%mod;
    }

    public static int countPaths(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        memo=new long[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], -1);
        }
        long rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(memo[i][j]!=-1){
                    continue;
                }
                solution(i, j, grid, n, m);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(memo[i][j]==-1){
                    continue;
                }
                rs=(rs+memo[i][j])%mod;
            }
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an m x n integer matrix grid), where you can move from (a cell) to any adjacent cell in (all 4 directions).
        //* Return (the number of strictly increasing paths) in the grid such that you can start from (any cell) and (end at any cell).
        //- Since the answer may be very large, return it modulo 10^9 + 7.
        //- Two paths are considered different if they (do not have exactly) the (same sequence) of visited cells.
        //  + Value giống nhau những khác cell thì vẫn khác nhau.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 1000
        //1 <= m * n <= 10^5
        //1 <= grid[i][j] <= 10^5
        //  + n*m khá to ==> Time: O(n*m)
        //
        //- Brainstorm
        //- Ta phải tính theo length (2 ==> n)
        //- Bài này dùng memo được:
        //  + Memo[i][j] là số lượng path tăng dần đi từ memo[i][j] ==> Hết
        //      + Có thể lưu ntn không?
        //      ==> Nên lưu kiểu khác
        //  + Sẽ không thể có cycle vì:
        //      + strictly increasing ==> Luôn tăng dần (Nếu back về old element ==> Giảm dần rồi)
        //
        //- Ta thấy rằng:
        //  + Memo[i][j]: Nên lưu độ dài lớn nhất dãy tăng dần có tốt hơn không?
        //      + CHƯA CHỔT
        //Ex:
        //
        //  + Nếu tính các chuỗi con:
        //  - Paths with length 1: [1], [1], [3], [4].
        //  - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
        //  - Paths with length 3: [1 -> 3 -> 4].
        //The total number of paths is 4 + 3 + 1 = 8.
        //Xét 1 path:
        //path = [1 -> 3 -> 4]
        //count=count[1] + count[3] + count[4]
        //- Nếu chọn điểm đến là 1 -> Đến (any cell)
        //  + Việc còn lại là chọn any cell
        //count = (1, 1->3,1->3->4) + (3, 3->4) + (4)
        //count = 7
        //  + 3 + 2 + 1 ==> Không nối all được.
        //  ==> Đây chính là rs
        //- 1 cell[i][j]:
        //  + Có thể trả về nhiều length ==> Do có nhiều path thì sẽ lưu ntn?
        //** Main point:
        //- Bản chất 1 node + nối sang 1 node khác
        //  + Node khác đó chứa x path
        //==> rs = 1+x + x
        //  + 1 là thêm 1 case có duy nhất 1 node
        //      + (x)
        //  + x là điểm hiện tại sẽ nối ==> Với mọi case từ node kia (CHỈ NỐT KỀ CỦA x).
        //      + x -> [(x1 ->...), (x1 -> ...]
        //** CT:
        //- Số path bắt đầu từ (i,j) = số path bắt đầu từ (i1,j1) + 1
        //  + Cộng cell(i,j) đứng 1 mình
        //  + x = x1+1
        //- Lưu ý:
        //  + Không phải x = x1*2+1:
        //      + Cộng dồn vào sẽ sai vì (i,j) không thể nối với những điểm không phải adj của nó
        //      sum(i1,j1)*2+1 ==> Là nối cell(x,y) vào all cell(x1,y1) là sai
        //** Memo vẫn sẽ lưu số path mà ("phải") bắt đầu từ cell[i][j] ==> any
        //- Vì traverse từ any cell:
        //  + loop O(n*m) time ==> Để tìm ra số case tại mỗi cell[i][j]
        //      + Nếu memo[i][j]!=-1:
        //          + Đi rồi ==> Skip
        //- Sau khi có memo rồi:
        //  + Sum all các count path tại mỗi cell[i][j] vào ==> rs
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        int[][] grid = {{1,1},{3,4}};
        System.out.println(countPaths(grid));
    }
}
