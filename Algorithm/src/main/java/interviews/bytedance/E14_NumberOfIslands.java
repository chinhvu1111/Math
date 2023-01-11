package interviews.bytedance;

public class E14_NumberOfIslands {

    public static int dx[]=new int[]{-1,0,1,0};
    public static int dy[]=new int[]{0,1,0,-1};

    public static void dfs(char[][] grid, int x, int y, int n, int m){
        for(int i=0;i<dx.length;i++){
            int x1=x+dx[i];
            int y1=y+dy[i];

            if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]=='1'){
                visited[x1][y1]=true;
                dfs(grid, x1, y1, n, m);
            }
        }
    }
    public static boolean[][] visited;
    public static int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int n=grid.length;
        int m=grid[0].length;
        visited=new boolean[n][m];
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j]&&grid[i][j]=='1'){
                    dfs(grid, i, j, n, m);
                    rs++;
                }
            }
        }

        return rs;
    }

    static char[][] g;
    public static int numIslandsRefactor(char[][] grid) {
        int islands = 0;
        g = grid;
        for (int i=0; i<g.length; i++)
            for (int j=0; j<g[i].length; j++)
                islands += sink(i, j);
        return islands;
    }
    static int sink(int i, int j) {
        if (i < 0 || i == g.length || j < 0 || j == g[i].length || g[i][j] == '0')
            return 0;
        g[i][j] = '0';
        sink(i+1, j); sink(i-1, j); sink(i, j+1); sink(i, j-1);
        return 1;
    }

    public static void main(String[] args) {
//        char[][] grid = new char[][]{
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}
//        };
        char[][] grid = new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}};
        System.out.println(numIslands(grid));
        System.out.println(numIslandsRefactor(grid));
        //
        //** Đề bài:
        //- Tìm số lượng island riêng rẽ (Connected với nhau)
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1, Bài này chỉ dùng dfs
        //1.2, Tối ưu :
        //- Ta sẽ không viết for mà viết riêng rẽ conditions + return 0 khi hết case
        //- return khi chạy qua được for.
        //- result+= dfs()
        //
        //#Reference:
        //201. Bitwise AND of Numbers Range
        //130. Surrounded Regions
        //286. Walls and Gates
        //305. Number of Islands II
        //
    }
}
