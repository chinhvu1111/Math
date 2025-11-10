package E1_daily;

public class E374_FindTheMinimumAreaToCoverAllOnesI {

    public static int minimumArea(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[] topLeft=new int[2];
        topLeft[0]=Integer.MAX_VALUE;
        topLeft[1]=Integer.MAX_VALUE;
        int[] bottomRight=new int[2];
        bottomRight[0]=Integer.MIN_VALUE;
        bottomRight[1]=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                if(grid[i][j]==1){
                    topLeft[0]=Math.min(topLeft[0], i);
                    topLeft[1]=Math.min(topLeft[1], j);
                    bottomRight[0]=Math.max(bottomRight[0], i);
                    bottomRight[1]=Math.max(bottomRight[1], j);
                }
            }
        }
//        System.out.printf("%s %s\n", topLeft[0],topLeft[1]);
//        System.out.printf("%s %s\n", bottomRight[0],bottomRight[1]);
        return (bottomRight[0]-topLeft[0]+1)*(bottomRight[1]-topLeft[1]+1);
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
        int[][] grid = {{0,1,0},{1,0,1}};
        System.out.println(minimumArea(grid));
    }
}
