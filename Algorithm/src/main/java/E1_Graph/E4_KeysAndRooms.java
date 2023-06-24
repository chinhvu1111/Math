package E1_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E4_KeysAndRooms {

    public static void solutionDFS(boolean[] visited, List<List<Integer>> rooms, Integer currentNode){
        if(visited[currentNode]){
            return;
        }
        visited[currentNode]=true;
        List<Integer> nextNodes=rooms.get(currentNode);

        for(Integer node: nextNodes){
            solutionBFS(visited, rooms, node);
        }
    }

    public static void solutionBFS(boolean[] visited, List<List<Integer>> rooms, Integer curNode){
        Queue<Integer> roomsQueue=new LinkedList<>();
        roomsQueue.add(curNode);

        while (!roomsQueue.isEmpty()){
            Integer currentNode=roomsQueue.poll();
            visited[currentNode]=true;
            List<Integer> nextNodes=rooms.get(currentNode);

            for(Integer node:nextNodes){
                if(!visited[node]){
                    visited[node]=true;
                    roomsQueue.add(node);
                }
            }
        }
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n=rooms.size();
        boolean[] visited=new boolean[n];
        solutionDFS(visited, rooms, 0);

        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Mỗi phòng có danh sách keys tương ứng --> Lấy keys trong đó để đi được các phỏng khác.
        //
        //** Idea
        //1.
        //1.0,
        //- BFS
        //- Bài này đơn giản là scan all bằng bfs nếu điểm nào đến rồi thì không xét nữa
        //+ Chú ý duy nhất là cần đặt visit ở mọi điểm traverse đến.
        //1.1, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //2.0,
        //- DFS
        //1.1, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
    }
}
