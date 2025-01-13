package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E223_MinimumMovesToSpreadStonesOverGrid_number_manipulation_classic {

    public static int minimumMovesTLE(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        Queue<Pair<String, Integer>> queue=new LinkedList<>();
        StringBuilder init=new StringBuilder();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                init.append(grid[i][j]);
            }
        }
        queue.add(new Pair<>(init.toString(), 0));
        HashSet<String> visited=new HashSet<>();
        String expectionStr = "111111111";

        while(!queue.isEmpty()){
            Pair<String, Integer> curNode =queue.poll();
            String curGrid = curNode.getKey();
            int l=curGrid.length();

            for(int i=0;i<l;i++){
                if(curGrid.charAt(i)=='0'){
                    continue;
                }
                //  + 5+3 = x+n
                //  + 5-3 = x-n
                //  + 5+1 = x+1
                //  + 5-1 = x-1
                StringBuilder newNode;
                if(i+3<l){
                    newNode=new StringBuilder(curGrid);
                    newNode.setCharAt(i, (char)(curGrid.charAt(i)-1));
                    newNode.setCharAt(i+3, (char)(curGrid.charAt(i+3)+1));
                    String s = newNode.toString();
                    if(s.equals(expectionStr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(s, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i-3>=0){
                    newNode=new StringBuilder(curGrid);
                    newNode.setCharAt(i, (char)(curGrid.charAt(i)-1));
                    newNode.setCharAt(i-3, (char)(curGrid.charAt(i-3)+1));
                    String s = newNode.toString();
                    if(s.equals(expectionStr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(s, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i != 3 && i != 6 && i >= 1){
                    newNode=new StringBuilder(curGrid);
                    newNode.setCharAt(i, (char)(curGrid.charAt(i)-1));
                    newNode.setCharAt(i-1, (char)(curGrid.charAt(i-1)+1));
                    String s = newNode.toString();
                    if(s.equals(expectionStr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(s, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i!=2&&i!=5&&i!=8&&i<l-1){
                    newNode=new StringBuilder(curGrid);
                    newNode.setCharAt(i, (char)(curGrid.charAt(i)-1));
                    newNode.setCharAt(i+1, (char)(curGrid.charAt(i+1)+1));
                    String s = newNode.toString();
                    if(s.equals(expectionStr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(s, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
            }
        }
        return -1;
    }

    public static int minimumMovesTLE1(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        Queue<Pair<char[], Integer>> queue=new LinkedList<>();
        char[] init=new char[9];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                init[i*n+j]=(char)(grid[i][j]+'0');
            }
        }
        queue.add(new Pair<>(init, 0));
        HashSet<String> visited=new HashSet<>();
        String expectionArr = "111111111";

        while(!queue.isEmpty()){
            Pair<char[], Integer> curNode =queue.poll();
            char[] curGrid = curNode.getKey();
            int l=curGrid.length;

            for(int i=0;i<l;i++){
                if(curGrid[i]=='0'){
                    continue;
                }
                //  + 5+3 = x+n
                //  + 5-3 = x-n
                //  + 5+1 = x+1
                //  + 5-1 = x-1
                char[] newNode;
                if(i+3<l){
                    newNode= Arrays.copyOf(curGrid, 9);
                    newNode[i]=(char)(curGrid[i]-1);
                    newNode[i+3]=(char)(curGrid[i+3]+1);
                    String s=String.valueOf(newNode);

                    if(s.equals(expectionArr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i-3>=0){
                    newNode= Arrays.copyOf(curGrid, 9);
                    newNode[i]=(char)(curGrid[i]-1);
                    newNode[i-3]=(char)(curGrid[i-3]+1);
                    String s=String.valueOf(newNode);

                    if(s.equals(expectionArr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i != 3 && i != 6 && i >= 1){
                    newNode= Arrays.copyOf(curGrid, 9);
                    newNode[i]=(char)(curGrid[i]-1);
                    newNode[i-1]=(char)(curGrid[i-1]+1);
                    String s=String.valueOf(newNode);

                    if(s.equals(expectionArr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
                if(i!=2&&i!=5&&i!=8&&i<l-1){
                    newNode= Arrays.copyOf(curGrid, 9);
                    newNode[i]=(char)(curGrid[i]-1);
                    newNode[i+1]=(char)(curGrid[i+1]+1);
                    String s=String.valueOf(newNode);

                    if(s.equals(expectionArr)){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(s)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(s);
                    }
                }
            }
        }
        return -1;
    }

    public static int minimumMoves(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;

        Queue<Pair<Integer, Integer>> queue=new LinkedList<>();
        StringBuilder init=new StringBuilder();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                init.append(grid[i][j]);
            }
        }
        queue.add(new Pair<>(Integer.parseInt(init.toString()), 0));
        HashSet<Integer> visited=new HashSet<>();
        int expectionInt = 111111111;
        Integer[] num10= {0,1,10,100,1_000,1_000_0, 1_000_00, 1_000_000, 1_000_000_0, 1_000_000_00, 1_000_000_000};
        List<Integer> num10List = Arrays.asList(num10);
//        Collections.reverse(num10List);
        //index=i
        //110101101

        while(!queue.isEmpty()){
            Pair<Integer, Integer> curNode =queue.poll();
            int curGrid = curNode.getKey();
//            System.out.println(curGrid);
            int l=9;
            //010|101|011
            int digitNum=1;

            //[1]01
            //101/100 = 1
            //001[9]000
            //001[9]000/1000
            //=19/%10
            for(int i=l-1;i>=0;i--){
                int curDigit=(curGrid/digitNum)%10;
                digitNum=digitNum*10;
                if(curDigit==0){
                    continue;
                }
                //  + 5+3 = x+n
                //  + 5-3 = x-n
                //  + 5+1 = x+1
                //  + 5-1 = x-1
                int newNode;
                if(i+3<l){
                    newNode=curGrid;
                    newNode-=num10List.get(9-i);
                    newNode+=num10List.get(9-(i+3));
                    if(newNode==expectionInt){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(newNode)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(newNode);
                    }
                }
                if(i-3>=0){
                    newNode=curGrid;
                    newNode-=num10List.get(9-i);
                    newNode+=num10List.get(9-(i-3));
                    if(newNode==expectionInt){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(newNode)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(newNode);
                    }
                }
                if(i != 3 && i != 6 && i >= 1){
                    newNode=curGrid;
                    newNode-=num10List.get(9-i);
                    newNode+=num10List.get(9-(i-1));
                    if(newNode==expectionInt){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(newNode)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(newNode);
                    }
                }
                if(i!=2&&i!=5&&i!=8&&i<l-1){
                    newNode=curGrid;
                    newNode-=num10List.get(9-i);
                    newNode+=num10List.get(9-(i+1));
                    if(newNode==expectionInt){
                        return curNode.getValue()+1;
                    }
                    if(!visited.contains(newNode)){
                        queue.add(new Pair<>(newNode, curNode.getValue()+1));
                        visited.add(newNode);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing (the number of stones) in (each cell).
        //- The grid contains (exactly 9 stones), and there can be (multiple stones) in (a single cell).
        //- In (one move), you can move (a single stone) from (its current cell) to (any other cell) if the (two cells) share (a side).
        //* Return (the minimum number of moves) required to place (one stone) in (each cell).
        //  + (Min the number of movement) to move (1 stone/ 1 cell)
        //
        //Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
        //Output: 3
        //Explanation: One possible sequence of moves to place one stone in each cell is:
        //1- Move one stone from cell (2,1) to cell (2,2).
        //2- Move one stone from cell (2,2) to cell (1,2).
        //3- Move one stone from cell (1,2) to cell (0,2).
        //In total, it takes 3 moves to place one stone in each cell of the grid.
        //It can be shown that 3 is the minimum number of moves required to place one stone in each cell.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //grid.length == grid[i].length == 3
        //0 <= grid[i][j] <= 9
        //Sum of grid is equal to 9.
        //
        //- Brainstorm
        //Ex:
        //Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
        //Output: 4
        //Explanation: One possible sequence of moves to place one stone in each cell is:
        //1- Move one stone from cell (0,1) to cell (0,2).
        //2- Move one stone from cell (0,1) to cell (1,1).
        //3- Move one stone from cell (2,2) to cell (1,2).
        //4- Move one stone from cell (2,2) to cell (2,1).
        //In total, it takes 4 moves to place one stone in each cell of the grid.
        //It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
        //
        //- We can use BFS to solve this problem
        //grid = [[1,1,0],[1,1,1],[1,2,1]]
        //expection = [[1,1,1],[1,1,1],[1,1,1]]
        //- We can flat the grid ==> array with 1 dimension
        //- How to move stone[i][j] to:
        //  + [i+1][j]
        //  + [i][j+1]
        //  + [i-1][j]
        //  + [i][j-1]
        //1,2,3
        //4,5,6
        //7,8,9
        //
        //[1,1] = 5 = (1*n+(1+1))
        //  + 2,4,6,8
        //
        //- Formula:
        //  + 5+3 = x+n
        //  + 5-3 = x-n
        //  + 5+1 = x+1
        //  + 5-1 = x-1 ==> Careful to the stone at (4) cannot move (3)
        //
        //- Visited ==> hashSet<String>
        //- Expect:
        //  + s = "111111111"
        //
//        int[][] grid = {{1,1,0},{1,1,1},{1,2,1}};
        int[][] grid = {{0,0,0},{0,0,9},{0,0,0}};
//        System.out.println(minimumMovesTLE(grid));
//        System.out.println(minimumMovesTLE1(grid));
        System.out.println(minimumMoves(grid));
    }
}
