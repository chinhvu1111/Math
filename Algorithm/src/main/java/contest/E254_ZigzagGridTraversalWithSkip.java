package contest;

import java.util.ArrayList;
import java.util.List;

public class E254_ZigzagGridTraversalWithSkip {

    public static List<Integer> zigzagTraversal(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        List<Integer> rs=new ArrayList<>();
        int y=0;
        boolean[] visited=new boolean[m];
        for(int i=0;i<m;i+=2){
            rs.add(grid[0][i]);
            visited[i]=true;
        }

        for(int i=1;i<n;i++){
            boolean[] temp=new boolean[m];
            if(i%2==1){
                for(int j=m-1;j>=0;j-=1){
                    if(!visited[j]){
                        temp[j]=true;
                        rs.add(grid[i][j]);
                    }
                }
            }else{
                for(int j=0;j<m;j+=1){
                    if(!visited[j]){
                        temp[j]=true;
                        rs.add(grid[i][j]);
                    }
                }
            }
            visited=temp;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[][]  grid = {{1,2},{3,4}};
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(zigzagTraversal(grid));
    }
}
