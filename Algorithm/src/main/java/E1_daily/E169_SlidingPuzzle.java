package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E169_SlidingPuzzle {
    static int[] dir={-1,1,3,-3};

    public static int slidingPuzzle(int[][] board) {
        int n=board.length;
        int m=board[0].length;
        Queue<Pair<String, int[]>> nodes=new LinkedList<>();
        StringBuilder initStr=new StringBuilder();
        int zeroIndex=0;
        String target= "123450";

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                if(board[i][j]==0){
                    zeroIndex=i*2+j+1;
                }
                initStr.append(board[i][j]);
            }
        }
        String s= initStr.toString();

        if(s.equals(target)){
            return 0;
        }
        nodes.add(new Pair<>(s, new int[]{zeroIndex, 0}));
        HashSet<String> visited=new HashSet<>();
        visited.add(s);

        while(!nodes.isEmpty()){
            Pair<String, int[]> curNode = nodes.poll();
            int curZeroIndex=-1;
            String curStr = curNode.getKey();

            for(int i=0;i<curStr.length();i++){
                if(curStr.charAt(i)=='0'){
                    curZeroIndex=i;
                    break;
                }
            }
            for(int j=0;j<dir.length;j++){
                if((curZeroIndex==3&&j==0)||(curZeroIndex==2&&j==1)){
                    continue;
                }
                int nextIndex=curZeroIndex+dir[j];
                if(nextIndex<0||nextIndex>=curStr.length()){
                    continue;
                }
                StringBuilder nextStr=new StringBuilder();

                for(int h=0;h<curStr.length();h++){
                    if(h==nextIndex){
                        nextStr.append(curStr.charAt(curZeroIndex));
                    }else if(h==curZeroIndex){
                        nextStr.append(curStr.charAt(nextIndex));
                    }else{
                        nextStr.append(curStr.charAt(h));
                    }
                }
                String nextS=nextStr.toString();
                if(visited.contains(nextS)){
                    continue;
                }
                if(nextS.equals(target)){
                    return curNode.getValue()[1]+1;
                }
                visited.add(nextS);
                nodes.add(new Pair<>(nextS, new int[]{nextIndex, curNode.getValue()[1]+1}));
            }
        }
        return -1;
    }

    public static int slidingPuzzleReference(int[][] board) {
        // Direction map for zero's possible moves in a 1D representation of the 2x3 board
        int[][] directions = new int[][] {
                { 1, 3 },
                { 0, 2, 4 },
                { 1, 5 },
                { 0, 4 },
                { 1, 3, 5 },
                { 2, 4 },
        };

        String target = "123450";
        StringBuilder startState = new StringBuilder();

        // Convert the 2D board into a string representation
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                startState.append(board[i][j]);
            }
        }

        Set<String> visited = new HashSet<>(); // To store visited states
        Queue<String> queue = new LinkedList<>();
        queue.add(startState.toString());
        visited.add(startState.toString());

        int moves = 0;

        // BFS to find the minimum number of moves
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String currentState = queue.poll();

                // Check if we reached the target solved state
                if (currentState.equals(target)) {
                    return moves;
                }

                int zeroPos = currentState.indexOf('0');
                for (int newPos : directions[zeroPos]) {
                    String nextState = swap(currentState, zeroPos, newPos);

                    // Skip if this state has been visited
                    if (visited.contains(nextState)) continue;

                    // Mark the new state as visited and add it to the queue
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
            moves++;
        }
        return -1;
    }

    // Helper method to swap characters at indices i and j in the string
    private static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- On an 2 x 3 board, there are five tiles labeled from (1 to 5), and (an empty square) represented by 0.
        //- A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
        //- The state of the board is solved if and only if the board is
        //  [[1,2,3],[4,5,0]].
        //- Given the puzzle board,
        //* return (the least number of moves) required so that (the state of the board) is solved.
        //- If it is impossible for the state of the board to be solved, return -1.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //board.length == 2
        //board[i].length == 3
        //0 <= board[i][j] <= 5
        //Each value board[i][j] is unique.
        //
        //- Brainstorm
        //- BFS solution:
        //- Move
        //[[1,2,3],
        // [4,5,0]].
        //=>
        //[[1,2,3],
        // [4,0,5]].
        //+ dp[i][j]=0:
        //- We can move following this rule:
        //  + dp[i][j] <=> dp[i-1][j]
        //  + dp[i][j] <=> dp[i][j-1]
        //  + dp[i][j] <=> dp[i][j+1]
        //  + dp[i][j] <=> dp[i+1][j]
        //- Hash function:
        //[[1,2,3],
        // [4,5,0]].
        //  + s = 123450
        //[[1,2,3],
        // [4,0,5]].
        //  + s = 123405
        //[[1,0,3],
        // [4,2,5]].
        //  + s = 103425
        //
        //- Rule:
        //  + s[i]==0:
        //      + replace with s[i+1]
        //      + replace with s[i-1]
        //      + replace with s[i+3] if exists
        //      + replace with s[i-3] if exists
        //
        //- How to swap the digit?
        //  + Node:
        //      + Integer, index with zero value
        //- s = 103425
        //  + index = 1
        //  + s[index] <=> s[index+1]
        //  => s = 130425
        //  +
        //
        char[] s={'a','b'};
        char[] s1={'a','b'};
        System.out.println(Arrays.toString(s).equals(Arrays.toString(s1)));
//        System.out.println();
//        int[][] board = {{1,2,3},{4,0,5}};
        int[][] board = {{3,2,4},{1,5,0}};
        //{{3,2,4},
        // {1,5,0}}
        //
        //- Special cases:
        // {{3,2,4},
        // {0,5,1}}
        //- 324051
        //  ==> (0) can not swap to (4)
        //
        //{{3,2,0},
        // {4,5,1}}
        //  ==> (0) can not swap to (4)
        //
        //1.1, Optimization
        //- We can calculate result without using (more space of the node)
        //- We will pop (all of the nodes) for each (layer):
        //  + We increase moves to (1 unit)
        //
        //* Small array:
        //  + We can list all of (the replacement positions) for each (index of the string).
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(slidingPuzzle(board));
        System.out.println(slidingPuzzleReference(board));
        //#Reference:
        //3277. Maximum XOR Score Subarray Queries
        //2079. Watering Plants
        //948. Bag of Tokens
    }
}
