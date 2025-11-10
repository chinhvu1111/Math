package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E377_DiagonalTraverse {

    public static int[] findDiagonalOrder(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        //n,n
        //n=3
        //0->1->2->1->0
        //Reverse + array
        List<Integer> list=new ArrayList<>();

        for (int i = n-1; i >=0; i--) {
            if(i%2==0){
                int x=0, y=i;
                while(x<n&&y>=0){
                    list.add(mat[x][y]);
                    x++;
                    y--;
                }
            }else{
                int x=i, y=0;
                while(x>=0&&y<n){
                    list.add(mat[x][y]);
                    x--;
                    y++;
                }
            }
        }
        Collections.reverse(list);
        for (int i = 1; i <n; i++) {
            if(i%2==0){
                int x=n-1, y=i;
                while(x>=0&&y<n){
                    list.add(mat[x][y]);
                    x--;
                    y++;
                }
            }else{
                int x=i, y=n-1;
                while(x<n&&y>=0){
                    list.add(mat[x][y]);
                    x++;
                    y--;
                }
            }
        }
//        System.out.println(list);
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i); // auto-unboxing
        }
        return array;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == mat.length
        //n == mat[i].length
        //1 <= m, n <= 104
        //1 <= m * n <= 104
        //-105 <= mat[i][j] <= 105
        //  + Time: O(n)
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
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        findDiagonalOrder(mat);
    }
}
