package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class E147_FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] adjNodes=new ArrayList[n];

        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        for(int[] e: edges){
            adjNodes[e[0]].add(new int[]{e[1], e[2]});
            adjNodes[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] numReachableCity=new int[n];
        int minReachableCity=Integer.MAX_VALUE;
        int maxIndex=-1;

        for(int i=0;i<n;i++){
            PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[1]-b[1]);
            boolean[] visited=new boolean[n];
//            if(numReachableCity[i]>minReachableCity){
//                continue;
//            }
            numReachableCity[i]=-1;
            queue.add(new int[]{i, 0});
            int count=-1;
            while (!queue.isEmpty()){
                int[] curNode=queue.poll();
                List<int[]> adj=adjNodes[curNode[0]];
//                numReachableCity[curNode[0]]++;
                if(!visited[curNode[0]]){
                    count++;
                }else {
                    continue;
                }
                visited[curNode[0]]=true;

                for(int[] e: adj){
                    if(!visited[e[0]]){
                        if(curNode[1]+e[1]<=distanceThreshold){
                            queue.add(new int[]{e[0], curNode[1]+e[1]});
                        }
                    }
                }
            }
            if(count!=-1&&minReachableCity>=count){
                maxIndex=Math.max(maxIndex, i);
                minReachableCity=count;
            }
        }
//        System.out.printf("Index: %s, val: %s\n", maxIndex, minReachableCity);
//        for(int i=0;i<n;i++){
//            System.out.printf("%s %s\n", i, numReachableCity[i]);
//        }
        return maxIndex;
    }

    public static int findTheCityOptimization(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] adjNodes=new ArrayList[n];

        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        for(int[] e: edges){
            adjNodes[e[0]].add(new int[]{e[1], e[2]});
            adjNodes[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] numReachableCity=new int[n];
        int minReachableCity=Integer.MAX_VALUE;
        int maxIndex=-1;

        for(int i=0;i<n;i++){
            PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[1]-b[1]);
            boolean[] visited=new boolean[n];
            if(numReachableCity[i]>minReachableCity){
                continue;
            }
            numReachableCity[i]=-1;
            queue.add(new int[]{i, 0});
            int count=-1;
            while (!queue.isEmpty()){
                int[] curNode=queue.poll();
                List<int[]> adj=adjNodes[curNode[0]];
                if(!visited[curNode[0]]){
                    numReachableCity[curNode[0]]++;
                    count++;
                }else {
                    continue;
                }
                visited[curNode[0]]=true;

                for(int[] e: adj){
                    if(!visited[e[0]]){
                        if(curNode[1]+e[1]<=distanceThreshold){
                            queue.add(new int[]{e[0], curNode[1]+e[1]});
                        }
                    }
                }
            }
            if(count!=-1&&minReachableCity>=count){
                maxIndex=Math.max(maxIndex, i);
                minReachableCity=count;
            }
        }
//        System.out.printf("Index: %s, val: %s\n", maxIndex, minReachableCity);
//        for(int i=0;i<n;i++){
//            System.out.printf("%s %s\n", i, numReachableCity[i]);
//        }
        return maxIndex;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n cities numbered from (0 to n-1).
        // Given the array edges where edges[i] = [fromi, toi, weighti]
        // represents a bidirectional and weighted edge between cities (fromi and toi), and given the integer (distanceThreshold).
        //* Return the city with the (smallest number of cities) that are reachable through some path
        // and whose distance is at (most distanceThreshold), If there are multiple such cities,
        // => return the city with the (greatest number).
        //Notice that the distance of a path connecting (cities i and j) is equal to (the sum of the edges' weights) along that path.
        //- Với distanceThreshold = x
        //  + return lại city số lượng cities mà nó có thể reach là MIN + (với max number city nếu chung số lượng min cities).
        //- Danh sách citites này có thể có cycle.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n <= 100
        //1 <= edges.length <= n * (n - 1) / 2
        //edges[i].length == 3
        //0 <= fromi < toi < n
        //1 <= weighti, distanceThreshold <= 10^4
        //All pairs (fromi, toi) are distinct.
        //==> Số node cũng không lớn lắm
        //
        //- Brainstorm
        //Example 1:
        //Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
        //Output: 3
        //Explanation: The figure above describes the graph.
        //The neighboring cities at a distanceThreshold = 4 for each city are:
        //City 0 -> [City 1, City 2]
        //City 1 -> [City 0, City 2, City 3]
        //City 2 -> [City 0, City 1, City 3]
        //City 3 -> [City 1, City 2]
        //Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
        //
        //- Mỗi city chỉ có thể đến được trong distanceTheshold
        //  ==> Dijikstra trên mỗi node được k?
        //- Có thể cut nhánh được không?
        //  + Ta có thể để min các cities có thể đến là x
        //  => Mỗi lần xét ta sẽ lấy gán min nếu lấy được new min.
        //  + Và khi 1 node --> các node khác : Ta cần update cái hệ số các nodes mà reachable +=1
        //  => Nếu có node vượt quá min => Ignore không cần xét.
        //- Mỗi node có nên lưu lại số lượng reachable tương ứng với threshold không?
        //  + Phần này khá khó ==> Thử viết dạng traditional trước nếu không được thì sẽ tối ưu thêm.
        //Ex:
        // 0 --(3)-- 1
        //        /  |
        //      /   (1)
        //   /       |
        // 3 --(1)-- 2
        //Example 1:
        //Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
        //Output: 3
        //Explanation: The figure above describes the graph.
        //The neighboring cities at a distanceThreshold = 4 for each city are:
        //City 0 -> [City 1, City 2]
        //City 1 -> [City 0, City 2, City 3]
        //City 2 -> [City 0, City 1, City 3]
        //City 3 -> [City 1, City 2]
        //
        //
        int n = 4, distanceThreshold = 4;
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(findTheCity(n, edges, distanceThreshold));
        //#Reference:
        //3003. Maximize the Number of Partitions After Operations
        //2876. Count Visited Nodes in a Directed Graph
        //2310. Sum of Numbers With Units Digit K
    }
}
