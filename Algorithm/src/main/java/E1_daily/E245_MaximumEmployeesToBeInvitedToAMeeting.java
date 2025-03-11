package E1_daily;

import java.util.*;

public class E245_MaximumEmployeesToBeInvitedToAMeeting {

    public static int bfs(int node, HashMap<Integer, List<Integer>> graph, int[] maxDistances, HashSet<Integer> visitedNodes, int n){
        if(maxDistances[node]!=-1){
            return maxDistances[node];
        }
//        boolean[] visited=new boolean[n];
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{node, 0});
        int maxDist=0;

        while(!queue.isEmpty()){
            int[] curNode=queue.poll();
            List<Integer> adj=graph.get(curNode[0]);
            for(int nextNode: adj){
                if(visitedNodes.contains(nextNode)){
                    continue;
                }
//                visited[nextNode]=true;
                visitedNodes.add(nextNode);
                queue.add(new int[]{nextNode, curNode[1]+1});
                maxDist=Math.max(curNode[1]+1, maxDist);
            }
        }
        return maxDistances[node]=maxDist;
    }

    public static int maximumInvitations(int[] favorite) {
        int n=favorite.length;
        HashMap<Integer, List<Integer>> reversedGraph= new HashMap<>();
        int[] maxDistances=new int[n];
        Arrays.fill(maxDistances, -1);

        for(int i=0;i<n;i++){
            reversedGraph.put(i, new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            List<Integer> adj=reversedGraph.get(favorite[i]);
            adj.add(i);
            reversedGraph.put(favorite[i], adj);
        }
        boolean[] visited=new boolean[n];
        int longestCycleLength=0;
        int twoCycleInvitations=0;

        for(int i=0;i<n;i++){
            if(visited[i]){
                continue;
            }
//            boolean[] curVisited=new boolean[n];
            //Use array that allocate more spece
            //==> Timeout
            HashMap<Integer, Integer> distMap=new HashMap<>();
//            int[] dist=new int[n];
//            Arrays.fill(dist, -1);
            int curPerson = i;
            int distance=0;
            while(true){
                if(visited[curPerson]){
                    break;
                }
                visited[curPerson]=true;
                distMap.put(curPerson, distance++);
                if(distMap.containsKey(favorite[curPerson])){
                    int curCycleLength=distance-distMap.get(favorite[curPerson]);
                    longestCycleLength=Math.max(longestCycleLength, curCycleLength);
                    if(curCycleLength==2){
//                        boolean[] visitedNodes=new boolean[n];
                        HashSet<Integer> visitedNodes=new HashSet<>();
                        visitedNodes.add(curPerson);
                        visitedNodes.add(favorite[curPerson]);
//                        twoCycleInvitations=Math.max(twoCycleInvitations,
//                                2+bfs(curPerson, reversedGraph, maxDistances, visitedNodes, n)
//                                        +bfs(favorite[curPerson], reversedGraph, maxDistances, visitedNodes, n));
                        twoCycleInvitations+=
                                2+bfs(curPerson, reversedGraph, maxDistances, visitedNodes, n)
                                +bfs(favorite[curPerson], reversedGraph, maxDistances, visitedNodes, n);
                    }
                    break;
                }
                curPerson=favorite[curPerson];
            }
        }
        return Math.max(twoCycleInvitations, longestCycleLength);
    }
    public static void main(String[] args) {
        //** Requirement
        //- A company is organizing (a meeting) and has (a list of n employees), waiting to be invited.
        //- They have arranged for (a large circular table), (capable of seating any number of employees).
        //- The employees are numbered from (0 to n - 1).
        //- Each employee has (a favorite person) and they will attend the meeting only if they can sit next to
        // (their favorite person) at the table.
        //- (The favorite person) of an employee is (not themself).
        //- Given a 0-indexed integer array favorite,
        // where favorite[i] denotes the favorite person of the ith employee,
        //* return (the maximum number of employees) that can be invited to the meeting.
        //
        //Example 1:
        //
        //Input: favorite = [2,2,1,2]
        //Output: 3
        //Explanation:
        //The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
        //All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
        //Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
        //The maximum number of employees that can be invited to the meeting is 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == favorite.length
        //2 <= n <= 10^5
        //0 <= favorite[i] <= n - 1
        //favorite[i] != i
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: favorite = [2,2,1,2]
        //
        //- If we arrange the ith employee as the first node:
        //  + This employee can go to (i employee)
        //  ==> BFS
        //- We can start at any employee:
        //- A -> B
        //  + B is favourite of A
        //  + If A is favourite of B:
        //      + B can sit beside any employee
        //  + If A is not favourite of B:
        //      + B can sit only beside the favourite employees
        //
        //- Maximum number of employees that can be invited to the meeting:
        //  + Max depth we can go using BFS
        //- Do we have any case:
        // A -> B -> C (B is parent of C)
        //  + C can go to (A,B...)
        //  ==> If we go back to (A or B) is duplicated
        //
        //- We should not go back to (A or B):
        //  + Our intention is that we need to put the employee around (the circular table)
        //
        //Ex:
        //0 -> 1 -> 2 -> []
        //- At node=2, we cannot anywhere:
        //  + Because the all of nodes have been visited
        //      + 1 is not parent of 2
        //      + 0 is not parent of 2
        //          ==> This path is not valid
        //
        //      + 1 is not parent of 2
        //      + 0 is parent of 2
        //          ==> This path is valid
        //
        //      + 1 is parent of 2
        //      + 0 is not parent of 2
        //          ==> This path is valid
        //- We have two type of way:
        //  + A one-way connected cycle: This is where employees form a directed chain,
        //      like a -> b -> c -> d -> a.
        //  + A mutual two-way cycle: In this case, two people like each other mutually,
        //      meaning a <-> b.
        //      + This connection is used to connect (the two connections)
        //Ex:
        //Extended Path for 2-Cycles:
        //For instance, for the 2-cycle (12, 15) (blue rectangle),
        // we need to find the longest paths from each of these employees.
        // Starting from 12, we can trace a path: [18, 4, 22, 7, 11, 12], and
        // from 15, we trace a path: [17, 21, 3, 8, 10, 1, 14, 5, 15].
        //Ex:
        // 9
        //  \
        //   V
        //    8
        //      \
        //       V
        //        7  <----> 2
        //                   ^
        //                    \
        //                     4
        //                      ^
        //                       \
        //                        5
        // max length = 3 + 3 + 2 = 7
        //
        //- So our core solution consists of three parts:
        //  + Cycle Detection
        //      + Find the longest cycle
        //      ==> Because we only have (1 node to 1 node)
        //      ==> Just loop to find the cycle
        //  + Finding the Longest Path
        //      A -> B -> C ->  D
        //            ^        /
        //             \      V
        //                E
        //      0 -> 1 -> 2 -> 3
        //             \      /
        //                 4
        //      => Lenth of cycle = 4 - 1 = 3
        //      + 2 <- 3 <- 4
        //      => length[2]=2
        //      ==> This is the reversed graph
        //          + To find the longest path from any node.
        //
//        int[] favorite = {2,2,1,2};
        // 0 -> 2 <- 3
        //     ^
        //   /
        //  V
        // 1
        //
//        int[] favorite = {3,0,1,4,1};
        //favorite = [3,0,1,4,1]
        // 0 -> 3 -> 4
        // ^      /
        // |  V
        // 1 <-- 2
        //
        int[] favorite = {1,0,3,2,5,6,7,4,9,8,11,10,11,12,10};
        System.out.println(maximumInvitations(favorite));
        //
    }
}
