package E1_daily;

import java.util.*;

public class E100_ParallelCoursesIII {

    public static int minimumTime(int n, int[][] relations, int[] time) {
        //Space: O(n)
        int[] inDegree=new int[n+1];
        //Space: O(n)
        List<Integer>[] adjNodes=new List[n+1];
        for(int i=1;i<=n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        //Space: O(m)
        for(int[] r: relations){
            adjNodes[r[0]].add(r[1]);
            inDegree[r[1]]++;
        }
        Queue<int[]> queue=new LinkedList<>();
        int rs=0;

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                queue.add(new int[]{i, time[i-1]});
                rs=Math.max(rs, time[i-1]);
            }
        }
        //Space: O(n)
        int[] maxTimeToArrive=new int[n+1];

        //Time: O(n+m)
        while (!queue.isEmpty()){
            Queue<int[]> nextNewNodes=new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] curNode=queue.poll();
                List<Integer> adj=adjNodes[curNode[0]];
                //a -> b
                //c /
                //
                for(int nextNode: adj){
                    inDegree[nextNode]--;
                    maxTimeToArrive[nextNode]=Math.max(maxTimeToArrive[nextNode], curNode[1]+time[nextNode-1]);
                    if(inDegree[nextNode]==0){
                        nextNewNodes.add(new int[]{nextNode, maxTimeToArrive[nextNode]});
                        rs=Math.max(rs, maxTimeToArrive[nextNode]);
                    }
                }
            }
            queue=nextNewNodes;
        }
        return rs;
    }

    public static int minimumTimeRefer(int n, int[][] relations, int[] time) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int[] edge: relations) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph.get(x).add(y);
            indegree[y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] maxTime = new int[n];

        for (int node = 0; node < n; node++) {
            if (indegree[node] == 0) {
                queue.add(node);
                maxTime[node] = time[node];
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (int neighbor: graph.get(node)) {
                maxTime[neighbor] = Math.max(maxTime[neighbor], maxTime[node] + time[neighbor]);
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        int ans = 0;
        for (int node = 0; node < n; node++) {
            ans = Math.max(ans, maxTime[node]);
        }

        return ans;
    }

    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static Map<Integer, Integer> memo = new HashMap<>();

    public static int minimumTimeDFS(int n, int[][] relations, int[] time) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge: relations) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            graph.get(x).add(y);
        }

        int ans = 0;
        for (int node = 0; node < n; node++) {
            ans = Math.max(ans, dfs(node, time));
        }

        return ans;
    }

    public static int dfs(int node, int[] time) {
        if (memo.containsKey(node)) {
            return memo.get(node);
        }

        if (graph.get(node).size() == 0) {
            return time[node];
        }

        int ans = 0;
        for (int neighbor: graph.get(node)) {
            ans = Math.max(ans, dfs(neighbor, time));
        }

        memo.put(node, time[node] + ans);
        return time[node] + ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer n, which indicates that there are n courses labeled from (1 to n).
        //- You are also given a 2D integer array relations where relations[j] = [prevCourse-j, nextCourse-j] denotes
        // that (course prevCourse-j) has to be completed before (course nextCourse-j) (prerequisite relationship).
        //- Furthermore, you are given (a 0-indexed integer array time) where time[i] denotes (how many months) it takes to complete the (i+1)th course.
        //- You must find (the minimum number of months) needed to complete (all the courses) following these rules:
        //  + You may start (taking a course) at (any time) if (the prerequisites) are met.
        //  + (Any number of courses) can be taken (at the same time).
        //* Return (the minimum number of months) needed to complete (all the courses).
        //- Note:
        //  + The test cases are generated such that it is possible to complete every course
        //  (i.e., the graph is a directed acyclic graph).
        //  ==> Không có cycle.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n <= 5 * 10^4
        //0 <= relations.length <= min(n * (n - 1) / 2, 5 * 10^4)
        //relations[j].length == 2
        //1 <= prevCoursej, nextCoursej <= n
        //prevCoursej != nextCoursej
        //All the pairs [prevCoursej, nextCoursej] are unique.
        //time.length == n
        //1 <= time[i] <= 10^4
        //The given graph is a directed acyclic graph.
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
        //Output: 12
        //Explanation: The figure above represents the given graph and the time required to complete each course.
        //You can start courses 1, 2, and 3 at month 0.
        //You can complete them after 1, 2, and 3 months respectively.
        //Course 4 can be taken only after course 3 is completed, i.e., after 3 months. It is completed after 3 + 4 = 7 months.
        //Course 5 can be taken only after courses 1, 2, 3, and 4 have been completed, i.e., after max(1,2,3,7) = 7 months.
        //Thus, the minimum time needed to complete all the courses is 7 + 5 = 12 months.
        //
        //- Ở đây ta sẽ dùng (topological sort + dynamic programming)
        //- Để start to learn new course (i)
        //  + Thì phải done tất cả các course trước đó
        //      + Thời gian done các course đó là (max time) all of them.
        //
        //- Traverse bình thường theo topological sort:
        //  + Sau đó update lại (max time) cho (next course) theo các (children của nó)
        //
        //1   2  5   6
        // \ /   \  /
        //  3     4
        //    \  /
        //     2
        //- Topological sort, thường sẽ là:
        //  + Node(x) -> node(y):
        //      + inDegree[y]++ : Tăng bậc vào node(y)
        //- Thường sẽ dùng BFS để traverse.
        //- Nhưng thường sẽ scan từng level depth
        // ==> Sẽ cần nested loop
        //  + Theo reference solution thì không cần ==> Chỉ cần bfs như bình thường (CHỈ TRONG NỘI BÀI NÀY THÔI)
        //      + ĐỂ ("AN TOÀN") THÌ NESTED CŨNG ĐƯỢC.
        //- Để xác định min/max depth trong topological sort:
        //  + Cần 1 array [n+1] để lưu (max/ min) riêng
        //
        //1.1, Optimization
        //- Ngoài ra bài này có thể dùng DFS + MEMO cũng được.
        //
        //1.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n+m)
        //
        int n = 5;
        int[][] relations = {{1,5},{2,5},{3,5},{3,4},{4,5}};
        int[] time = {1,2,3,4,5};
        System.out.println(minimumTime(n, relations, time));
        System.out.println(minimumTimeRefer(n, relations, time));
        System.out.println(minimumTimeDFS(n, relations, time));
        //#Reference:
        //630. Course Schedule III
        //1882. Process Tasks Using Servers
        //2127. Maximum Employees to Be Invited to a Meeting
    }
}
