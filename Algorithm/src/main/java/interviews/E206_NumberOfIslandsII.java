package interviews;

import java.util.ArrayList;
import java.util.List;

public class E206_NumberOfIslandsII {
    public static int[][] offset={{0,1},{0,-1},{1,0},{-1,0}};

    public static List<Integer> numIslands2(int n, int m, int[][] positions) {
        // write your code here
        int arr[][]=new int[m][n];
        int[] parent=new int[m*n];

        List<Integer> result=new ArrayList<>();
        int count=0;

        for(int[] pos: positions){
            int i=pos[0];
            int j=pos[1];
            arr[i][j]=1;


        }
        return null;
    }

    public static void main(String[] args) {

    }
}
