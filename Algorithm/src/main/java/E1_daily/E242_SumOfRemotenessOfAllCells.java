package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E242_SumOfRemotenessOfAllCells {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static long sumRemoteness(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        long sum=0;
        List<int[]> listNode=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=-1){
                    sum+=grid[i][j];
                    listNode.add(new int[]{i, j});
                }
            }
        }
        HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> mapParent=new HashMap<>();
        HashMap<Pair<Integer, Integer>, Long> mapSum=new HashMap<>();
        boolean[][] visited=new boolean[n][m];
        for(int[] node: listNode){
            if(visited[node[0]][node[1]]||grid[node[0]][node[1]]==-1){
                continue;
            }
            Queue<Pair<Integer, Integer>[]> nodes=new LinkedList<>();
            long curSum=0;
            Pair<Integer, Integer> curPair = new Pair<>(node[0], node[1]);
            mapParent.put(curPair, curPair);
            nodes.add(new Pair[]{curPair, curPair});

            while(!nodes.isEmpty()){
                int size = nodes.size();
                for(int i=0;i<size;i++){
                    Pair<Integer, Integer>[] curNode=nodes.poll();
                    visited[curNode[0].getKey()][curNode[0].getValue()]=true;
                    curSum+=grid[curNode[0].getKey()][curNode[0].getValue()];
//                    System.out.printf("%s %s %s\n", curNode[0].getKey(), curNode[0].getValue(), grid[curNode[0].getKey()][curNode[1].getValue()]);
//                    System.out.println(curSum);

                    for(int j=0;j<dx.length;j++){
                        int x1=curNode[0].getKey()+dx[j];
                        int y1=curNode[0].getValue()+dy[j];
                        if(x1>=0&&x1<n&&y1>=0&&y1<m&&!visited[x1][y1]&&grid[x1][y1]!=-1){
                            visited[x1][y1]=true;
                            Pair<Integer, Integer> nextNode = new Pair<>(x1, y1);
                            nodes.add(new Pair[]{nextNode, curNode[1]});
                            mapParent.put(nextNode, curNode[1]);
//                            curSum+=grid[x1][y1];
                        }
                    }
                }
            }
            mapSum.put(curPair, curSum);
        }
        long rs=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j]==-1){
                    continue;
                }
                Pair<Integer, Integer> curPair = new Pair<>(i, j);
                Pair<Integer, Integer> parent = mapParent.get(curPair);
                long curSum = !mapSum.containsKey(parent)?0: mapSum.get(parent);
                rs+=sum-curSum;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed matrix grid of order n * n.
        //- Each cell in this matrix has a value grid[i][j],
        //  + which is either a positive integer or (-1) representing (a blocked cell).
        //- You can move from (a non-blocked cell) to (any non-blocked cell) that shares an edge.
        //  + For any cell (i, j), we represent its remoteness as R[i][j] which is defined as the following:
        //  + If the cell (i, j) is a non-blocked cell, R[i][j] is the sum of the values grid[x][y]
        //  such that there is no path from the non-blocked cell (x, y) to the cell (i, j).
        //  + For blocked cells, R[i][j] == 0.
        //* Return the sum of R[i][j] over all cells.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 300
        //1 <= grid[i][j] <= 10^6 or grid[i][j] == -1
        //
        //- Brainstorm
        //
//        int[][] grid = {{-1,1,-1},{5,-1,4},{-1,3,-1}};
//        int[][] grid = {{-1,3,4},{-1,-1,-1},{3,-1,-1}};
        int[][] grid = {{5,5,1},{2,3,9},{3,6,3}};
        System.out.println(sumRemoteness(grid));
    }
}
