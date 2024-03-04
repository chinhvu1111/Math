package contest;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class E49_MinimumOperationsToWriteTheLetterYOnAGrid {

    public static int minimumOperationsToWriteY(int[][] grid) {
        int n=grid.length;
        HashSet<Pair<Integer, Integer>> listYNodes=new HashSet<>();
        HashSet<Pair<Integer, Integer>> listNonYNodes=new HashSet<>();
        int center=(n-1)/2;
        listYNodes.add(new Pair<>(center, center));
        int x=0, y=0;
        while (x<center){
            listYNodes.add(new Pair<>(x, y));
            x++;
            y++;
        }
        x=0;
        y=n-1;
        while (y>center){
            listYNodes.add(new Pair<>(x, y));
            x++;
            y--;
        }
        x=center;
        y=center;
        while (x<=n-1){
            listYNodes.add(new Pair<>(x, y));
            x++;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Pair<Integer, Integer> curPair=new Pair<>(i, j);
                if(!listYNodes.contains(curPair)){
                    listNonYNodes.add(curPair);
                }
            }
        }
//        System.out.println(listYNodes);
//        System.out.println(listNonYNodes);
        int rs=Integer.MAX_VALUE;

//        int count=0;
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                Pair<Integer, Integer> curPair=new Pair<>(i, j);
//                if(listNonYNodes.contains(curPair)){
//                    if(grid[i][j]!=2){
//                        count++;
//                    }
//                    System.out.printf("%s, ", grid[i][j]);
//                }else{
//                    System.out.printf("%s, ", "x");
//                }
//            }
//            System.out.println();
//        }
//        for(Pair<Integer, Integer> nodeNotY: listNonYNodes){
//            if(grid[nodeNotY.getValue()][nodeNotY.getValue()]!=2){
//                count++;
//            }
//        }
//        System.out.println(count);
        for(int i=0;i<=2;i++){
            //Change to 0
            int curRs=0;
            for(Pair<Integer, Integer> node: listYNodes){
                if(grid[node.getKey()][node.getValue()]!=i){
                    curRs++;
                }
//                int[] choice={0,1,2};
            }
            int minRs=Integer.MAX_VALUE;

            for(int j=0;j<=2;j++){
                if(j==i){
                    continue;
                }
                int notYrs=0;

                for(Pair<Integer, Integer> nodeNotY: listNonYNodes){
                    if(grid[nodeNotY.getKey()][nodeNotY.getValue()]!=j){
                        notYrs++;
                    }
                }
                minRs=Math.min(minRs, notYrs);
            }
            rs=Math.min(rs, curRs+minRs);
        }
        //0,0,0, 0,0
        //0,0,0,(0),0 -> (1,3)
        //0,0,0, 0,0  ->
        //0,0,0, 0,0
        //0,0,0, 0,0
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Biến đổi sao cho thành chữ Y trong matrix thoả mãn:
        //We say that a cell belongs to the Letter Y if it belongs to one of the following:
        //
        //- The diagonal starting at the top-left cell and ending at the center cell of the grid.
        //- The diagonal starting at the top-right cell and ending at the center cell of the grid.
        //- The vertical line starting at the center cell and ending at the bottom border of the grid.
        //- The Letter Y is written on the grid if and only if:
        //
        //- All values at cells belonging to the Y are equal.
        //- All values at cells not belonging to the Y are equal.
        //- The values at cells belonging to the Y are different from the values at cells not belonging to the Y.
        //* Return the minimum number of operations needed to write the letter Y on the grid given
        // that in one operation you can change the value at any cell to 0, 1, or 2.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= n <= 49
        //n == grid.length == grid[i].length
        //0 <= grid[i][j] <= 2
        //n is odd.
        //==> O(n^2)/ recursively dc
        //
        //- Brainstorm
        //- Brute force
        //- Y nếu change thì mất bao nhiêu
        //Cases:
        //Case 1:
        //- Y change to 1
        //- Other change to (0,2)
        //Case 2:
        //- Y change to 2
        //- Other change to (0,1)
        //Case 2:
        //- Y change to 3
        //- Other change to (0,2)
        //
//        int[][] grid = {{1,2,2},{1,1,0},{0,1,0}};
        int[][] grid = {{0,1,0,1,0},{2,1,0,1,2},{2,2,2,0,1},{2,2,2,2,2},{2,1,2,2,2}};
        System.out.println(minimumOperationsToWriteY(grid));
    }
}
