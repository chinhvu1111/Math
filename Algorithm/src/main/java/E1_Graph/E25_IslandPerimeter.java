package E1_Graph;

public class E25_IslandPerimeter {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int islandPerimeter(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==0){
                    continue;
                }
                int currentRs=0;

                for(int h=0;h<dx.length;h++){
                    int x1=i+dx[h];
                    int y1=j+dy[h];
                    if(x1>=0&&x1<n&&y1>=0&&y1<m&&grid[x1][y1]==1){
                        continue;
                    }
                    currentRs++;
                }
//                System.out.printf("%s %s %s\n", i, j, currentRs);
                rs+=currentRs;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //- perimeter : Chu vi
        //
        //** Requirement
        //+ Có duy nhất 1 hòn đảo
        //+ Hòn đảo có thể chứa sông hồ
        //+ Sông hồ không kết nối với nước ngoài
        //+ Land=1
        //+ Water=0
        //* Tính chu vi của hòn đảo, mỗi cạnh của square dài =1
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //+ row == grid.length
        //+ col == grid[i].length
        //+ 1 <= row, col <= 100
        //+ grid[i][j] is 0 or 1.
        //
        //- Brainstorm:
        //+ Chu vi của 1 hình bất kỳ là :
        //+ perimeter = all square - the number of edges that they used to connect to other edges
        //--> Tức là chu vi all - số cạnh dùng để kết nối với các squares khác.
        //Ex:
        //0 0 0 0 0 0
        //0 1 1 1 1 0
        //0 1 1 0 1 0
        //0 1 1 1 1 0
        //0 0 0 0 0 0
        //perimeter = 2 + 1 + 1 + 2 + 1 + 2 + 1 + 1 + 2 + 1
        //+ Bị dính cases có water ở giữa
        //
        //- Cần fill hết water inside to calculate the perimeter.
        //+ --> Không cần phải fill vì bài này không có cases này xảy ra.
        //
        //1.1, Optimization
        //- Nếu xét bình thường 4 chiều thì sẽ có lúc trùng nhau --> Ta có thể giảm số chiều đi.
        //
        //- VD:
        //0 0 0 0
        //0 1 1 0
        //0 0 1 0
        //0 0 0 0
        //+ Result = 4 + 2 + 2 = 8
        //- Vì 2 ô vuông kết hợp với nhau thì nếu xét từng ô ta chỉ cần xét
        //==> 1 cạnh giao nhau thôi ==> Đến từ 2 square trừ đi 2 (rs-=2)
        //      (top)
        //(left) (i,j) mà thôi
        //+ current_rs=4
        //+ left==1 : current_rs-=2
        //+ right==1 : current_rs-=2
        //
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}};
        System.out.println(islandPerimeter(grid));
        //#Reference:
        //1. Two Sum
        //733. Flood Fill
        //1034. Coloring A Border

    }
}
