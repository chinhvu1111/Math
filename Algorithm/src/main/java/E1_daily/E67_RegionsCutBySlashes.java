package E1_daily;

public class E67_RegionsCutBySlashes {

    public static int[][] dxyNon={{-1,0}, {0,1}, {1,0}, {0,-1}};
    //'\'
    public static int[][] dxy1={{0,1}, {-1,0}};
    public static int[][] dxy2={{1,0}, {0,-1}};
    //'/'
    public static int[][] dxy11={{0,-1}, {-1,0}};
    public static int[][] dxy22={{0,1}, {1,0}};
    public static void dfs(int x, int y, int n, int m, String[] grid, boolean[][][] visited, int[][] dxy){
//        if(grid[x].charAt(y)=='\\'){
//            if(z==0){
//
//            }
//        }
        for(int i=0;i<dxy.length;i++){
            int x1=x+dxy[i][0]*-1;
            int y1=y+dxy[i][1]*-1;

            //'\' or '/'
            //
            //- Để đến được '\':
            //  -> \
            //     ^
            //  + Mặt dưới (0):
            //          + dx==0
            //          + dy==1
            //
            //          + dx==-1
            //          + dy==0
            //  + Mặt trên:
            //  v
            //  \ <-
            //          + dx==1
            //          + dy==0
            //
            //          + dx==0
            //          + dy==-1
            //
            //- Để đến được '/':
            //    / <-
            //    ^
            //  + Mặt dưới (0):
            //          + dx==0
            //          + dy==-1
            //
            //          + dx==-1
            //          + dy==0
            //  + Mặt trên (1):
            //    V
            //  ->/
            //
            //          + dx==0
            //          + dy==1
            //
            //          + dx==1
            //          + dy==0
            //
            //* Nếu vào:
            //  + Đi từ vị trí nào cũng vào được
            //- '\' thì ta có thể:
            //  - Mặt trên:
            //      + Đi theo y bên trên * -1:
            //          + Vì là ngược chiều nhau
            int nextX=dxy[i][0]*-1;
            int nextY=dxy[i][1]*-1;
            if(x1>=0&&y1>=0&&x1<n&&y1<m){
                if(grid[x1].charAt(y1)=='\\'
                        &&((nextX==0&&nextY==1)||(nextX==-1&&nextY==0))
                        &&!visited[x1][y1][0]){
                    visited[x1][y1][0]=true;
                    dfs(x1, y1, n, m, grid, visited, dxy1);
                }
                if(grid[x1].charAt(y1)=='\\'
                        &&((nextX==1&&nextY==0)||(nextX==0&&nextY==-1))
                        &&!visited[x1][y1][1]) {
                    visited[x1][y1][1] = true;
                    dfs(x1, y1, n, m, grid, visited, dxy2);
                }
                if(grid[x1].charAt(y1)=='/'
                        &&((nextX==0&&nextY==-1)||(nextX==-1&&nextY==0))
                        &&!visited[x1][y1][0]){
                    visited[x1][y1][0]=true;
                    dfs(x1, y1, n, m, grid, visited, dxy11);
                }
                if(grid[x1].charAt(y1)=='/'
                        &&((nextX==0&&nextY==1)||(nextX==1&&nextY==0))
                        &&!visited[x1][y1][1]) {
                    visited[x1][y1][1] = true;
                    dfs(x1, y1, n, m, grid, visited, dxy22);
                }
                if(grid[x1].charAt(y1)==' '&&!visited[x1][y1][0]){
                    visited[x1][y1][0]=true;
                    visited[x1][y1][1]=true;
                    dfs(x1, y1, n, m, grid, visited, dxyNon);
                }
            }
        }
    }

    public static int regionsBySlashes(String[] grid) {
        int n=grid.length;
        int m=grid[0].length();
        boolean[][][] visited=new boolean[n][m][2];
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='\\'&&!visited[i][j][0]){
                    visited[i][j][0]=true;
                    rs++;
                    dfs(i, j, n, m, grid, visited, dxy1);
                }
                if(grid[i].charAt(j)=='\\'&&!visited[i][j][1]){
                    visited[i][j][1]=true;
                    rs++;
                    dfs(i, j, n, m, grid, visited, dxy2);
                }
                if(grid[i].charAt(j)=='/'
                        &&!visited[i][j][0]) {
                    visited[i][j][0] = true;
                    rs++;
                    dfs(i, j, n, m, grid, visited, dxy11);
                }
                if(grid[i].charAt(j)=='/'
                        &&!visited[i][j][1]) {
                    visited[i][j][1] = true;
                    rs++;
                    dfs(i, j, n, m, grid, visited, dxy22);
                }
                if(grid[i].charAt(j)==' '
                        &&!visited[i][j][1]) {
                    visited[i][j][0] = true;
                    visited[i][j][1] = true;
                    rs++;
                    dfs(i, j, n, m, grid, visited, dxyNon);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a:
        //  + '/'
        //  + '\'
        //  + blank space ' '.
        //- These characters divide the square into contiguous regions.
        //* Given the grid represented as a string array,
        // return the number of regions.
        //- Note that backslash characters are escaped, so a '\' is represented as '\\'.
        //- Kích thước của grid chính bằng cái input đấy
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == grid.length == grid[i].length
        //1 <= n <= 30
        //grid[i][j] is either '/', '\', or ' '.
        //+ Size cũng không quá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: grid = [" /","/ "]
        //Output: 2
        //- Tìm cách xác định số tập hợp riêng rẽ nhau:
        // |  /|
        // |/  |
        //- Ta thấy chỉ có:
        //  + 2 chéo xiên mới ghép được với nhau
        //- Ta có thể define:
        //  + / : 1
        //  + \ : 2
        //  + ' ' : 0
        //- Các 1/2 chéo nhau --> Có thể ghép thành 1 đường
        // |  1|
        // |1  |
        //+ rs=2
        // |2 1|
        // |1 2|
        //+ rs=4
        //- Nếu ta không tập trung vào '/' hay '\' thì sao:
        //  + Không phải tìm các '/' liên tiếp nhau:
        //      ==> Cái này khá khó để thực hiện
        //- Ta sẽ coi:
        //+ Các ô như các ô riêng rẽ nhau, và ta sẽ tìm tập hợp các ô có thể kết nối với nhau:
        //  + Thay vì liên quan đến các ô '/' or '\'
        //- '/':
        //  + Nếu hướng đi là từ left -> right, top -> down
        //  + Có nghĩa ô này có thể đi go (down or right)
        //- '\':
        //  + Nếu đi từ left -> right, down -> top
        //      + ô này có thể đi go (down or left)
        //  + Nếu đi từ right -> left, top -> down
        //      + ô này có thể đi go (top or right)
        //  ==> Tức là đi được 1 trong 2 hướng trùng với hướng đi trước đó.
        //===> Cứ như thế ta sẽ tìm được số tập hợp thôi.
        //- Dùng DFS nữa là xong.
        //- Đi chéo được không?
        //  + Không cần quan tâm đi được hay không ==> Nó có thể đi qua điểm trung gian khác <> nếu k có điểm trung gian thì sẽ là không đi được
        //Ex:
        //  \ /
        //  / \
        //- Mỗi '\' or '/':
        //  + Đều có mặt trên và mặt dưới
        //  + Làm sao để đánh dấu chúng đã được visited
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m+n+m)
        //- Time: O(n*m)
        //
        String[] grid = {
                " /",
                "/ "};
//        String[] grid = {"/\\","\\/"};
        System.out.println(regionsBySlashes(grid));
        //#Reference:
        //980. Unique Paths III
        //1170. Compare Strings by Frequency of the Smallest Character
        //1072. Flip Columns For Maximum Number of Equal Rows
    }
}
