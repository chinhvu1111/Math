package E1_heap_priority_queue;

import javafx.util.Pair;

import java.util.*;

public class E6_CampusBikes {

    public static class Tuple{
        int workerId;
        int bikeId;
        int dist;
        public Tuple(int workerId, int bikeId, int dist){
            this.workerId=workerId;
            this.bikeId=bikeId;
            this.dist=dist;
        }

        @Override
        public String toString() {
            return workerId+", "+bikeId+", "+dist+"\n";
        }
    }

    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int n=workers.length;
        int m=bikes.length;
        HashMap<Integer, List<Tuple>> workerToBike=new HashMap<>();
        HashMap<Integer, List<Tuple>> bikeToWorker=new HashMap<>();
        TreeSet<Tuple> assigment=new TreeSet<>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                if(o1.dist!=o2.dist){
                    return o1.dist-o2.dist;
                }
                if(o1.workerId!=o2.workerId){
                    return o1.workerId-o2.workerId;
                }
                return o1.bikeId-o2.bikeId;
            }
        });
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Tuple curTuple=new Tuple(i, j, manhattanDistance(workers[i][0], workers[i][1], bikes[j][0], bikes[j][1]));
//                System.out.println(curTuple);
                List<Tuple> oldWtoBTuples = workerToBike.computeIfAbsent(i, k -> new ArrayList<>());
                oldWtoBTuples.add(curTuple);
                List<Tuple> oldBtoWTuples = bikeToWorker.computeIfAbsent(j, k -> new ArrayList<>());
                oldBtoWTuples.add(curTuple);
                assigment.add(curTuple);
            }
        }
        int[] rs=new int[m];
//        System.out.println(assigment);

        while(!assigment.isEmpty()){
            Tuple curAssignment=assigment.pollFirst();
            rs[curAssignment.workerId]=curAssignment.bikeId;
            System.out.printf("Assign: (worker-id) %s to (bike-id) %s\n", curAssignment.workerId, curAssignment.bikeId);
            List<Tuple> relatedAssignment=workerToBike.get(curAssignment.workerId);
            for(Tuple t: relatedAssignment){
                assigment.remove(t);
            }
            List<Tuple> relatedAssignment1=bikeToWorker.get(curAssignment.bikeId);
            for(Tuple t: relatedAssignment1){
                assigment.remove(t);
            }
        }
        return rs;
    }

    public static int[] assignBikesOptimization(int[][] workers, int[][] bikes) {
        int n=workers.length;
        int m=bikes.length;
        HashSet<Integer> workerVisited=new HashSet<>();
        HashSet<Integer>  bikeVisited=new HashSet<>();
        TreeSet<Tuple> assigment=new TreeSet<>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                if(o1.dist!=o2.dist){
                    return o1.dist-o2.dist;
                }
                if(o1.workerId!=o2.workerId){
                    return o1.workerId-o2.workerId;
                }
                return o1.bikeId-o2.bikeId;
            }
        });
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Tuple curTuple=new Tuple(i, j, manhattanDistance(workers[i][0], workers[i][1], bikes[j][0], bikes[j][1]));
//                System.out.println(curTuple);
                assigment.add(curTuple);
            }
        }
        int[] rs=new int[n];
        int count=0;
//        System.out.println(assigment);

        while(!assigment.isEmpty()&&count!=n){
            Tuple curAssignment=assigment.pollFirst();
            if(workerVisited.contains(curAssignment.workerId)||bikeVisited.contains(curAssignment.bikeId)){
                continue;
            }
            count++;
            workerVisited.add(curAssignment.workerId);
            bikeVisited.add(curAssignment.bikeId);
            rs[curAssignment.workerId]=curAssignment.bikeId;
            System.out.printf("Assign: (worker-id) %s to (bike-id) %s\n", curAssignment.workerId, curAssignment.bikeId);
        }
        return rs;
    }

    public static int manhattanDistance(int x, int y, int x1, int y1){
        return Math.abs(x-x1)+ Math.abs(y-y1);
    }

    public static void main(String[] args) {
        // Requirement
        //- Có 1 campus XY và N workers,m bikes
        //- Given an array workers of length n where workers[i] = [xi, yi] is the position of the ith worker.
        //- You are also given an array bikes of length m where bikes[j] = [xj, yj] is the position of the jth bike.
        //- All the given positions are unique.
        //- Assign a bike to each worker.
        // + Choose the (worker-i, bike-j) pair with the (shortest) Manhattan distance between each other and assign (the bike) to that (worker).
        // + If there are multiple (worker-i, bike-j) pairs with the same shortest Manhattan distance,
        // we choose the pair with (the smallest worker index). If there are multiple ways to do that, we choose the pair with (the smallest bike index).
        //+ Repeat this process until there are (no available workers).
        //- (The Manhattan distance) between two points (p1 and p2) is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
        //* Return an array answer of length n, where answer[i] is the index (0-indexed) of the bike that the (ith worker) is assigned to.
        //- Ta sẽ assign từng pair một ==> Đi từ những pair có Manhattan distance từ MIN --> MAX.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == workers.length
        //m == bikes.length
        //1 <= n <= m <= 1000
        //workers[i].length == bikes[j].length == 2
        //0 <= xi, yi < 1000
        //0 <= xj, yj < 1000
        //All worker and bike locations are unique.
        //
        //- Brainstorm
        //Ex:
        //workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
        //Output: [0,2,1]
        //Explanation: (Worker 0) grabs (Bike 0) at first.
        // (Worker 1) and (Worker 2) share the same distance to (Bike 2),
        // thus (Worker 1) is assigned to (Bike 2), and (Worker 2) will take (Bike 1).
        // So the output is [0,2,1].
        //
        //(bike-index, worker-index, distance)
        //- Ta sẽ tính all distances đôi một ==> add vào priorityQueue
        //  + Sort theo : distance, work_index, bike_index
        //- Khi lấy 1 element ==> cần remove các distance mà có sự xuất hiện của worker đó
        //- Ở đây các pair là unique ==> TreeSet
        //  + Ta có thể remove dựa trên TreeSet được.
        //
        //1.1, Optimization
        //- Xoá đoạn remove + thêm đoạn visited để trace
        //- Count để đếm số người đã (assign==n) ==> break
        //
        //1.2, Complexity
        //- N is the number of workers
        //- M is the number of bikes
        //- Space : O(N*M)
        //- Time : O(N*M*Log(N*M))
        //
        //#Reference:
        //1066. Campus Bikes II
        int[][] workers = {{0,0},{1,1},{2,0}}, bikes = {{1,0},{2,2},{2,1}};
//        int[] rs=assignBikes(workers, bikes);
        int[] rs=assignBikesOptimization(workers, bikes);
        for(int i=0;i<rs.length;i++){
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
