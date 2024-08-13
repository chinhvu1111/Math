package contest;

import java.util.List;

public class E163_SnakeInMatrix {

    //up, right, down, left
    public static int[] dx={-1, 0, 1, 0};
    public static int[] dy={0, 1, 0, -1};
    public static int finalPositionOfSnake(int n, List<String> commands) {
        int x=0, y=0;
        for(String c: commands){
            if(c.equals("UP")){
                x=x+dx[0];
                y=y+dy[0];
            }else if(c.equals("RIGHT")){
                x=x+dx[1];
                y=y+dy[1];
            }else if(c.equals("DOWN")){
                x=x+dx[2];
                y=y+dy[2];
            }else if(c.equals("LEFT")){
                x=x+dx[3];
                y=y+dy[3];
            }
        }
        return x*n+y;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is an undirected tree with n nodes labeled from (0 to n - 1), and rooted at node 0.
        // You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        //- A node is good if all the subtrees rooted at its children have the same size.
        //* Return the number of good nodes in the given tree.
        //- A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //
        //
    }
}
