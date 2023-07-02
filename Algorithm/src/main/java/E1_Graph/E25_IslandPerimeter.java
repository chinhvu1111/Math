package E1_Graph;

public class E25_IslandPerimeter {

    public static int islandPerimeter(int[][] grid) {
        return 1;
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
        //
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}};

    }
}
