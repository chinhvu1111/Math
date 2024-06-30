package contest;

public class E135_FindTheMinimumAreaToCoverAllOnesI {

    public static int minimumArea(int[][] grid) {
        int n=grid.length;
        int m = grid[0].length;
        int xMin=Integer.MAX_VALUE, xMax=0, yMin=Integer.MAX_VALUE, yMax=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    xMin=Math.min(xMin, i);
                    xMax=Math.max(xMax, i);
                    yMin=Math.min(yMin, j);
                    yMax=Math.max(yMax, j);
                }
            }
        }
        if(xMin==Integer.MAX_VALUE||yMin==Integer.MAX_VALUE){
            return 0;
        }
        return (xMax-xMin+1)*(yMax-yMin+1);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D binary array grid.
        // Find a rectangle with horizontal and vertical sides with the smallest area,
        // such that all the 1's in grid lie inside this rectangle.
        //* Return the minimum possible area of the rectangle.
        //- Tìm rectangle có diện tích smallest --> Cover hết các value = 1
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= grid.length, grid[i].length <= 1000
        //grid[i][j] is either 0 or 1.
        //The input is generated such that there is at least one 1 in grid.
        //
        //- Brainstorm
        //Ex:
        //grid=
        //0,1,0
        //1,0,1
        //- Tìm ymin, ymax, x min, x max là được
        //
        int[][] grid = {{0,1,0},{1,0,1}};
        System.out.println(minimumArea(grid));
    }
}
