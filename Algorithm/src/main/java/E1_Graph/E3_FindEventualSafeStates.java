package E1_Graph;

import java.util.List;

public class E3_FindEventualSafeStates {

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        return null;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Count the number of safe nodes in graph
        //- Terminal node:
        //+ A node is a terminal node if there are no outgoing edges ==> Not loop path.
        //
        //- Safe node:
        //+ A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Using union find --> detect.
    }
}
