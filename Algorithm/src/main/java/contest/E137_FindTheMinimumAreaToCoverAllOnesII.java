package contest;

public class E137_FindTheMinimumAreaToCoverAllOnesII {

    public static int minimumAreaSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + grid[i-1][j-1];
            }
        }
        int xMin=Integer.MAX_VALUE, xMax=0, yMin=Integer.MAX_VALUE, yMax=0;
        int countOne=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    xMin=Math.min(xMin, i);
                    xMax=Math.max(xMax, i);
                    yMin=Math.min(yMin, j);
                    yMax=Math.max(yMax, j);
                    countOne++;
                }
            }
        }
        //Ex
        //[1,0,1,0],
        //[0,1,0,1]
        System.out.println(getArea(prefix, 1,1,2,2));
        int rs=Integer.MAX_VALUE;

        for (int x1 = 1; x1 < m; x1++) {
            for (int y1 = 1; y1 < n; y1++) {
                for (int x2 = x1 + 1; x2 <= m; x2++) {
                    for (int y2 = y1 + 1; y2 <= n; y2++) {
                        int area1 = getArea(prefix, 1, 1, x1, y1);
                        int area2 = getArea(prefix, x1 + 1, y1 + 1, x2, y2);
                        int area3 = getArea(prefix, x2 + 1, y2 + 1, m, n);
                        int sumArea = area1 + area2 + area3;
                        if(sumArea==countOne){
                            rs=Math.min(rs, x1*y1+(x2-x1)*(y2-y1)+ (m-x2)*(n-y2));
                        }
                    }
                }
            }
        }

        return rs;
    }

    private static int getArea(int[][] prefix, int x1, int y1, int x2, int y2) {
        return prefix[x2][y2] - prefix[x1-1][y2] - prefix[x2][y1-1] + prefix[x1-1][y1-1];
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D binary array grid. You need to find 3 (non-overlapping) rectangles having (non-zero) areas
        // with horizontal and vertical sides such that (all the 1's in grid) lie inside (these rectangles).
        //* Return the (minimum possible) sum of the area of these rectangles.
        //- Note that the rectangles are allowed to touch.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= grid.length, grid[i].length <= 30
        //grid[i][j] is either 0 or 1.
        //The input is generated such that there are at least three 1's in grid.
        //+ Length không quá lớn --> recursion được.
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: grid = [[1,0,1,0],[0,1,0,1]]
        //Output: 5
        //[1,0,1,0],
        //[0,1,0,1]
        //- Bài toán thành cho 1 hình chữ nhật (rectangle)
        //  + Tìm cách chia nó thành 3 sub rectangle sao cho:
        //      + Sum min + cover all 1
        //  + Hướng đến việc cover ít số 0 nhất có thể
        //
        //
        int[][] grid = {
                {1,0,1,0},
                {0,1,0,1}};
        System.out.println(minimumAreaSum(grid));
    }
}
