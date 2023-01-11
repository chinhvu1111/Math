package interviews.bytedance;

public class E15_SpiralMatrixII {
    public static int[] dx=new int[]{-1,0,1,0};
    public static int[] dy=new int[]{0,1,0,-1};

    public static void solution(int direction, int[][] grid, int num, int x, int y){
        //Mấy bài dạng này có thể debug dựa trên (x, y) 1 cách dễ dàng
        if(num==grid.length*grid.length+1){
            return;
        }
        int newNum=num;
        if(x<0||x>=grid.length||y<0||y>=grid[0].length||grid[x][y]!=0){
            x=x-dx[direction];
            y=y-dy[direction];
            for(int i=1;i<=3;i++){
                int dir=(direction+i)%4;
                int x1=x+dx[dir];
                int y1=y+dy[dir];

                if(x1>=0&&x1<grid.length&&y1>=0&&y1<grid.length&&grid[x1][y1]==0){
                    x=x1;
                    y=y1;
                    direction=dir;
                    break;
                }
            }
        }else{
            grid[x][y]=num;
            x=x+dx[direction];
            y=y+dy[direction];
            newNum++;
        }
        solution(direction, grid, newNum, x, y);
    }

    public static int[][] generateMatrix(int n) {
        int[][] grid=new int[n][n];
        solution(1, grid, 1, 0, 0);
        return grid;
    }

    public static int[][] generateMatrixFaster(int n) {
        int[][] m = new int[n][n];
        dfs(m, 0, 0, false, 1);
        return m;
    }
    private static void dfs(int[][] m, int row, int col, boolean goup,int count) {
        if (row < 0 || col < 0 || col >= m[0].length || row >= m.length || m[row][col] != 0) return;
        m[row][col] = count;
        count++;
        if (goup) dfs(m, row-1, col, true, count);
        dfs(m, row, col+1, false, count);
        dfs(m, row + 1, col, false, count);
        dfs(m, row, col-1, false, count);
        dfs(m, row-1, col, true, count);
    }

    public static void main(String[] args) {
//        generateMatrix(3);
//        generateMatrix(2);
        generateMatrix(1);
        generateMatrixFaster(1);
        //
        //** Đề bài
        //- Bài này là fill số vào hình vuông sao cho nó chạy theo hình xoắn ốc + tăng dần
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Bài này có thể làm đệ quy bằng cách:
        //+ Đếm số tăng dần == n*n+1 --> return không làm gì nữa
        //+ if{grid[x][y])!=0 --> lùi lại 1 bước + không tăng num nữa
        //+ Với trường hợp đi quá --> Cần tìm direction mới phù hợp
        //==> Tránh trường hợp chạy đến vô cực.
        //
        //1.2, Tối ưu bằng cách sắp xếp việc chạy cho đúng thứ tự để:
        //- Việc chạy sau sẽ không back lại việc chạy trước --> Tránh loop
        //VD: (up --> bottom) ==> không recursively ra (left --> right)
        //==> Tránh việc check lại
        //- Chỉ 1 case duy nhất là (bottom --> up) --> trước khi chạy đến direction khác ==> Cần chạy hết direction cũ đã.
        //
    }
}
