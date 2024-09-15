package E1_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E42_MinimumFuelCostToReportToTheCapital {

    public static long[] solution(HashMap<Integer, HashSet<Integer>> tree, int prevNode, int curNode, int seats){
        HashSet<Integer> adj=tree.get(curNode);
        if(adj.size()==1&&adj.contains(prevNode)){
            return new long[]{1, 1, 1};
        }
        //representatives = x+1
        //cars = x ==> tệ thì mới x+1
        //==> Để cái này init == all of 0 cho dễ
        long[] curInfo=new long[]{0, 0, 0};

        for(int nextCity: adj){
            if(nextCity==prevNode){
                continue;
            }
            long[] nextInfoOfCity = solution(tree, curNode, nextCity, seats);
            //number of the representatives
            curInfo[0]+=nextInfoOfCity[0];
            //number of the cars
            curInfo[1]+=nextInfoOfCity[1];
            //Cost
            curInfo[2]+=nextInfoOfCity[2];
        }
        //Seats = 2
        //Cars = 3
        //number of representatives = 4
        //- Phân để số lượng cars nhỏ nhất:
        //  + số lượng người <= car * seats
        //      + Cars không đổi
        long[] curRs;
        long nextCost;
        long minCars;
//        while (minCars*seats<curInfo[0]+1){
//            minCars++;
//        }
        minCars = (curInfo[0]+1)/seats;
        if(minCars*seats<curInfo[0]+1){
            minCars++;
        }
//        if(curInfo[0]+1<=curInfo[1]*seats){
//            if(curNode!=0){
//                nextCost=curInfo[2]+curInfo[1];
//            }else{
//                nextCost=curInfo[2];
//            }
//            curRs=new long[]{curInfo[0]+1, curInfo[1], nextCost};
//        }else{
//            if(curNode!=0){
//                nextCost=curInfo[2]+curInfo[1]+1;
//            }else{
//                nextCost=curInfo[2];
//            }
//            curRs = new long[]{curInfo[0]+1, curInfo[1]+1, nextCost};
//        }
        if(curNode!=0){
            nextCost=curInfo[2]+minCars;
        }else{
            nextCost=curInfo[2];
        }
        curRs = new long[]{curInfo[0]+1, curInfo[1]+1, nextCost};
        //n cars --(cost=n)--> x(n+1) cars --(cost=n+n+1)--> y
        //n cars --(cost=n)--> x n cars --(cost=n+n)--> y
//        System.out.printf("Cur node: %s, Number of people: %s, number of cars: %s, cost: %s\n", curNode, curRs[0], curRs[1], curRs[2]);
        return curRs;
    }

    public static long minimumFuelCost(int[][] roads, int seats) {
        if(roads.length==0){
            return 0;
        }
        HashMap<Integer, HashSet<Integer>> tree=new HashMap<>();

        for(int[] r: roads){
            HashSet<Integer> adj=tree.getOrDefault(r[0], new HashSet<>());
            adj.add(r[1]);
            tree.put(r[0], adj);
            HashSet<Integer> adj1=tree.getOrDefault(r[1], new HashSet<>());
            adj1.add(r[0]);
            tree.put(r[1], adj1);
        }
        long[] rs = solution(tree, -1, 0, seats);
        return rs[2];
    }
    
    public static void main(String[] args) {
        //** Requirement
        //- There is a tree (i.e., a connected, (undirected graph) with no cycles) structure country network consisting of n cities numbered
        // from (0 to n - 1) and (exactly n - 1 roads).
        //- The capital city is (city 0).
        //- You are given a 2D integer array roads where roads[i] = [ai, bi] denotes
        // that there exists (a bidirectional road) connecting cities (ai) and (bi).
        //- There is (a meeting) for (the representatives of each city).
        //  + The meeting is in (the capital city).
        //- There is (a car) in (each city).
        //  + Chỉ có (1 car/1 city) thôi.
        //  + You are given (an integer seats) that indicates (the number of seats) in (each car).
        //- (A representative) can use the car in their city to travel or change the car and ride with (another representative).
        //- (The cost of traveling between two cities) is (one liter of fuel).
        //* Return (the minimum number of liters of fuel) to reach (the capital city).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= n <= 105
        //roads.length == n - 1
        //roads[i].length == 2
        //0 <= ai, bi < n
        //ai != bi
        //roads represents a valid tree.
        //1 <= seats <= 10^5
        //
        //- Brainstorm
        //
        //- Seats = 2
        // E  \
        // A --- B --- D
        // C  /
        //+ Ở B chỉ có thể chở (2 representatives):
        //  + E, A có thể dồn cùng 1 car, cost = 1 + 1 = 2
        //  + C sẽ đến B bằng 1 car của C và tiếp tục đến D bằng car đó, cost = 1 + 1 = 2
        //
        //- Bản chất mỗi representatives đều có 1 car riêng nên nếu đến 1 city khác thì có thể chọn:
        //  + Ngồi cùng xe 1 người khác cho save cost
        //  + Vẫn ngồi xe đó đi tiếp
        //
        //- Đi chung khi nào thì sẽ save cost hơn:
        //Ex:
        // A -> B -> C -> D
        //- Nếu A đến C mới chung với B:
        //  + Cost = (A->B) + (B->C)*2 + (C->D) = 1 + 1*2 + 1 = 4
        //- Nếu A đến B chung với B luôn:
        //  + Cost = (A->B) + (B-> C) + (C->D) = 1 + 1 + 1 = 3
        //==> Chung càng sớm thì càng save cost
        //
        //- Nếu số seat nhỏ thì việc phân bố đi chung ntn thì save cost:
        //Ex:
        //- seats = 2
        // A -> B -> C -> D -> E -> F
        //- (A,B) đi chung, (D,E) đi chung
        //==> Min
        //
        //- Nếu nhiều thành phố trỏ vào cùng 1 thành phố + seats giới hạn:
        //Ex:
        //+ seats = 2
        //D -- \
        //A -->  B
        //C /  /
        //E --/
        //+ Khi sang 1 city khác ta sẽ có thêm 1 car
        //  + Khi đến cùng 1 city ==> Vai trò các (representatives) là như nhau
        //- Nếu tính số (representatives) rồi qua mỗi thành phố ta chia lại thì sao
        //  ==> Chia như thế sẽ tối ưu hơn
        //Ex:
        //- seats = 2
        //D(1) -- \
        //A(1) -->  B (5) ==> 3 cars
        //C(1) /  /
        //E(1) --/
        //+ Ta cần keep (số lượng representations) và (số lượng cars)
        //  + Đôi khi (gộp rồi thì car == 1)
        //
        //Ex:
        //          3
        //        /   \
        //      1      2
        //        \
        //         0
        //       /   \
        //     4      5
        //    /
        //   6
        //Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
        //Output: 7
        //Explanation:
        //- Representative2 goes directly to city 3 with 1 liter of fuel.
        //- Representative2 and representative3 go together to city 1 with 1 liter of fuel.
        //- Representative2 and representative3 go together to the capital with 1 liter of fuel.
        //- Representative1 goes directly to the capital with 1 liter of fuel.
        //- Representative5 goes directly to the capital with 1 liter of fuel.
        //- Representative6 goes directly to city 4 with 1 liter of fuel.
        //- Representative4 and representative6 go together to the capital with 1 liter of fuel.
        //It costs 7 liters of fuel at minimum.
        //It can be proven that 7 is the minimum number of liters of fuel needed.
        //
        //- Cost sẽ phụ thuộc vào số lượng cars
        //- Mỗi node sẽ cần lưu thông tin về số lượng cars mà nó sẽ đi tiếp đến capital city
        //Ex:
        //          3 (1)
        //        /   \
        //      1 (2)   2 (1)
        //        \
        //         0
        //       /   \
        //     4 (1)  5 (1)
        //    /
        //   6 (1)
        //- Leaf node:
        //  + return 1 (Số cars, số representatives)
        //- current node:
        //  + Tính các giá trị vào += solution()
        //
//        int[][] roads = {{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}};
//        int seats = 2;
//        int[][] roads = {};
//        int seats = 1;
        int[][] roads = {{0,1},{0,2},{1,3},{1,4}};
        //       0
        //     /  \
        //    1    2
        //  /  \
        //4     3
        //(4->1) + (1->3) + (1->0)
        //  + (1->0) gộp hết vào thành 1 car được.
        //= 1 + 1 + 1 = 3
        //- Bug cuối liên quan đến time limit:
        //  + Tìm min cars để cover được all of representatives
        //===== CODE
        //long minCars;
//        while (minCars*seats<curInfo[0]+1){
//            minCars++;
//        }
        //minCars = (curInfo[0]+1)/seats;
        //if(minCars*seats<curInfo[0]+1){
        //    minCars++;
        //}
        //===== CODE
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(H)
        //- Time : O(V+E)
        //
        int seats = 5;
        System.out.println(minimumFuelCost(roads, seats));
    }
}
