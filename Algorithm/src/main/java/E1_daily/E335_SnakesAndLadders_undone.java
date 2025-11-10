package E1_daily;

import java.util.LinkedList;
import java.util.Queue;

public class E335_SnakesAndLadders_undone {

    public static int[] getCoordination(int pos, int n, int m){
        //15/6 = 2
        int mul=pos/m;
        //6,5,4
        //1,2,3
        //(even + pos%m!=0)
        int row = mul + (pos%m==0?1:0);
        int x;
        if(pos%m==0){
            x=n-mul;
        }else{
            x=n-mul-1;
        }
        if(row%2==0){
            if(pos%m!=0){
                return new int[]{x, pos-mul*m-1};
            }else{
                return new int[]{x, m-1};
            }
        }else{
            if(pos%m!=0){
                return new int[]{x, m-pos%m};
            }else{
                return new int[]{x, 0};
            }
        }
    }

    public static int snakesAndLadders(int[][] board) {
        int n=board.length;
        int m=board[0].length;
        Queue<int[]> queue=new LinkedList<>();
        boolean[] visited=new boolean[n*m+1];
        queue.add(new int[]{1, 0, 0});
        visited[1]=true;

        while(!queue.isEmpty()){
            int[] node=queue.poll();
            visited[node[0]]=true;
//            System.out.printf("Before Node: %s, dist: %s\n", node[0], node[1]);
//            int[] curCoor = getCoordination(node[0], n, m);
//            if(board[curCoor[0]][curCoor[1]]==n*m){
//                return node[1]+1;
//            }
//            if(board[curCoor[0]][curCoor[1]]!=-1&&!visited[board[curCoor[0]][curCoor[1]]]){
//                queue.add(new int[]{board[curCoor[0]][curCoor[1]], node[1]+1});
//                visited[board[curCoor[0]][curCoor[1]]]=true;
//            }
            if(node[0]==n*m){
                return node[1];
//                System.out.println(node[1]);
            }
            int[] ladderNode = getCoordination(node[0], n, m);
            if(node[2]==0&&board[ladderNode[0]][ladderNode[1]]==n*m){
                return node[1]+1;
            }
            for(int i=1;i<=6;i++){
                int nextNode = node[0]+i;
                if(nextNode>n*m){
                    continue;
                }
                int[] curNode=getCoordination(nextNode, n, m);
//                System.out.printf("Node: %s, %s %s, dist: %s\n", nextNode, curNode[0], curNode[1], node[1]+1);
                //8 ==> [5,6]
                //board[5,6] ==> Move
                int nextNextNode = board[curNode[0]][curNode[1]];
//                System.out.println(nextNextNode);
//                int[] nextNextNodeCoordination=getCoordination(nextNextNode, n, m);
                if(nextNextNode!=-1&&node[2]==0
//                        &&(board[nextNextNodeCoordination[0]][nextNextNodeCoordination[1]]==-1||!visited[board[nextNextNodeCoordination[0]][nextNextNodeCoordination[1]]]))
                        &&(!visited[nextNextNode]))
                {
                    queue.add(new int[]{nextNextNode, node[1]+1, 1});
//                    if(board[nextNextNodeCoordination[0]][nextNextNodeCoordination[1]]!=-1){
//                        visited[board[nextNextNodeCoordination[0]][nextNextNodeCoordination[1]]]=true;
//                    }
                    visited[nextNextNode]=true;
                }else{
                    if(board[curNode[0]][curNode[1]]==-1&&!visited[nextNode]){
                        queue.add(new int[]{nextNode, node[1]+1, 0});
                        visited[nextNode]=true;
                    }else if(board[curNode[0]][curNode[1]]!=-1&&!visited[board[curNode[0]][curNode[1]]]){
                        queue.add(new int[]{board[curNode[0]][curNode[1]], node[1]+1, 1});
                        visited[board[curNode[0]][curNode[1]]]=true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an n x n integer matrix board where the cells are labeled from 1 to (n^2)
        // in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0])
        // and alternating direction each row.
        //
        //- You start on (square 1) of the board.
        //- In each move, starting from square curr, do the following:
        //  + Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
        //      + This choice simulates the result of a standard 6-sided die roll: i.e.,
        //      there are always at most 6 destinations, regardless of the size of the board.
        //  + If next has a snake or ladder, you (must) move to the destination of that snake or ladder.
        //  Otherwise, you move to next.
        //  + The game ends when you reach the square n2.
        //- A board square on row r and column c has a snake or ladder if board[r][c] != -1.
        // The destination of that snake or ladder is board[r][c].
        //- Squares 1 and n^2 are not the starting points of any snake or ladder.
        //
        //* Note that you only take a snake or ladder at most once per dice roll.
        // If the destination to a snake or ladder is the start of another snake or ladder,
        // you do not follow the subsequent snake or ladder.
        //
        //- For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2.
        // You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
        //- Return the least (number of dice rolls) required to reach the square n^2.
        // If it is not possible to reach the square, return -1.
        //
        //Ex:
        //[
        //  [-1,4],
        //  [-1,3]
        //]
        //- Same (ladder/ Snake)
        //- Choose next step???
        //  + following 1-> n^2
        //  + move to (x+1,...,x+6)
        //
        //Example 1:
        //
        //Input: board =
        // [[-1,-1,-1,-1,-1,-1],
        // [-1,-1,-1,-1,-1,-1],
        // [-1,-1,-1,-1,-1,-1],
        // [-1,35,-1,-1,13,-1],
        // [-1,-1,-1,-1,-1,-1],
        // [-1,15,-1,-1,-1,-1]]
        //Output: 4
        //Explanation:
        //In the beginning, you start at square 1 (at row 5, column 0).
        //You decide to move to square 2 and must take the ladder to square 15.
        //You then decide to move to square 17 and must take the snake to square 13.
        //You then decide to move to square 14 and must take the ladder to square 35.
        //You then decide to move to square 36, ending the game.
        //This is the lowest possible number of moves to reach the last square, so return 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == board.length == board[i].length
        //2 <= n <= 20
        //board[i][j] is either -1 or in the range [1, n^2].
        //The squares labeled 1 and n2 are not the starting points of any snake or ladder.
        //
        //* Brainstorm:
        //- How to calculate the coordication from (1-> n^2)
        //[7,8,9]
        //[6,5,4]
        //[1,2,3]
        //- 2 <=> (2,1)
        //2/3 = i/m + (i-1)
        //- Odd: left->right
        //- Even: right->left
        //- If (i/m)%2==1:
        //  + i+1
        //- If (i/m)%2==0:
        //  + m-1-i
        //
        //1.1, Case
        //  + If next has a snake or ladder, you (must) move to the destination of that snake or ladder.
        //  ==> Đến ladder thì phải dùng
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n+m)
        //- Space: O(n+m)
//        int[][] board =
//                {
//                        {-1,-1,-1,-1,-1,-1},
//                        {-1,-1,-1,-1,-1,-1},
//                        {-1,-1,-1,-1,-1,-1},
//                        {-1,35,-1,-1,13,-1},
//                        {-1,-1,-1,-1,-1,-1},
//                        {-1,15,-1,-1,-1,-1}
//                };
//        int[] coordination = getCoordination(3, 2, 2);
//        int[] coordination = getCoordination(15, 6, 6);
//        int[] coordination = getCoordination(7, 6, 6);
//        int[] coordination = getCoordination(12, 6, 6);
//        int[] coordination = getCoordination(2, 6, 6);
//        int[][] board=
//                {
//                        {-1,-1,-1,46,47,-1,-1,-1},
//                        {51,-1,-1,63,-1,31,21,-1},
//                        {-1,-1,26,-1,-1,38,-1,-1},
//                        {-1,-1,11,-1,14,23,56,57},
//                        {11,-1,-1,-1,49,36,-1,48},
//                        {-1,-1,-1,33,56,-1,57,21},
//                        {-1,-1,-1,-1,-1,-1,2,-1},
//                        {-1,-1,-1,8,3,-1,6,56}
//                };
//        int[][] board=
//        {
//                {-1,-1,27,13,-1,25,-1},
//                {-1,-1,-1,-1,-1,-1,-1},
//                {44,-1,8,-1,-1,2,-1},
//                {-1,30,-1,-1,-1,-1,-1},
//                {3,-1,20,-1,46,6,-1},
//                {-1,-1,-1,-1,-1,-1,29},
//                {-1,29,21,33,-1,-1,-1}
//        };
//        int[][] board = {
//                {1,1,-1},
//                {1,1,1},
//                {-1,1,1}
//        };
        int[][] board =
                {
                        {-1,42,12,-1,1,-1,-1},
                        {-1,-1,5,-1,-1,46,44},
                        {18,22,6,39,-1,-1,-1},
                        {-1,-1,40,-1,-1,-1,37},
                        {49,38,24,-1,14,29,-1},
                        {-1,-1,6,-1,-1,-1,20},
                        {-1,-1,12,10,-1,5,26}
                };
        //4,3
        //1,2
        //=====
        //(0,1)
//        System.out.printf("%s %s\n", coordination[0], coordination[1]);
        //(4,2)
        System.out.println(snakesAndLadders(board));
    }
}
