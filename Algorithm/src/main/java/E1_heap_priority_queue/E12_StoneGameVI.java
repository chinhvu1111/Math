package E1_heap_priority_queue;

import java.util.*;

public class E12_StoneGameVI {

    public static int stoneGameVIWrong(int[] aliceValues, int[] bobValues) {
        int n=aliceValues.length;
        TreeSet<int[]> sortValuesAlice=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //Chú ý:
                //- Dễ bị nonElementException
                if(Math.abs(o1[1])!=Math.abs(o2[1])){
                    return Math.abs(o1[1])-Math.abs(o2[1]);
                }
                return o1[0]-o2[0];
            }
        });
        TreeSet<int[]> sortValuesBob=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(Math.abs(o1[1])!=Math.abs(o2[1])){
                    return Math.abs(o1[1])-Math.abs(o2[1]);
                }
                return o1[0]-o2[0];
            }
        });

        for(int i=0;i<n;i++){
            sortValuesAlice.add(new int[]{i, aliceValues[i]-bobValues[i]});
            sortValuesBob.add(new int[]{i, bobValues[i]-aliceValues[i]});
        }
        boolean isAlice=true;
        int aliceNum = 0;
        int bobNum = 0;

        for(int i=0;i<n;i++){
            if(isAlice){
                int[] curMin=sortValuesAlice.last();
                sortValuesAlice.remove(curMin);
                aliceNum+=aliceValues[curMin[0]];
                sortValuesBob.remove(new int[]{curMin[0], -1*curMin[1]});
            }else{
                int[] curMax=sortValuesBob.last();
                sortValuesBob.remove(curMax);
                bobNum+=bobValues[curMax[0]];
                sortValuesAlice.remove(new int[]{curMax[0], -1*curMax[1]});
            }
            isAlice=!isAlice;
        }
//        System.out.printf("Alice: %s, bob: %s\n", aliceNum, bobNum);
        return Integer.compare(aliceNum, bobNum);
    }

    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n=aliceValues.length;
        TreeSet<int[]> sortValuesSum=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //Chú ý:
                //- Dễ bị nonElementException
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

        for(int i=0;i<n;i++){
            sortValuesSum.add(new int[]{i, aliceValues[i]+bobValues[i]});
        }
        boolean isAlice=true;
        int aliceNum = 0;
        int bobNum = 0;

        for(int i=0;i<n;i++){
            if(isAlice){
                int[] curMax=sortValuesSum.last();
                sortValuesSum.remove(curMax);
                aliceNum+=aliceValues[curMax[0]];
            }else{
                int[] curMax=sortValuesSum.last();
                sortValuesSum.remove(curMax);
                bobNum+=bobValues[curMax[0]];
            }
            isAlice=!isAlice;
        }
//        System.out.printf("Alice: %s, bob: %s\n", aliceNum, bobNum);
        return Integer.compare(aliceNum, bobNum);
    }

    public static int stoneGameVIOptimization(int[] aliceValues, int[] bobValues) {
        int n=aliceValues.length;
        PriorityQueue<int[]> sortValuesSum=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //Chú ý:
                //- Dễ bị nonElementException
                if(o1[1]!=o2[1]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });

        for(int i=0;i<n;i++){
            sortValuesSum.add(new int[]{i, aliceValues[i]+bobValues[i]});
        }
        boolean isAlice=true;
        int aliceNum = 0;
        int bobNum = 0;

        for(int i=0;i<n;i++){
            if(isAlice){
                int[] curMax=sortValuesSum.poll();
                aliceNum+=aliceValues[curMax[0]];
            }else{
                int[] curMax=sortValuesSum.poll();
                bobNum+=bobValues[curMax[0]];
            }
            isAlice=!isAlice;
        }
//        System.out.printf("Alice: %s, bob: %s\n", aliceNum, bobNum);
        return Integer.compare(aliceNum, bobNum);
    }

    public static int stoneGameVIOptimizationPreSort(int[] aliceValues, int[] bobValues) {
        int n=aliceValues.length;
        List<int[]> sortList=new ArrayList<>();

        for(int i=0;i<n;i++){
            sortList.add(new int[]{i, aliceValues[i]+bobValues[i]});
        }
        sortList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //Chú ý:
                //- Dễ bị nonElementException
                if (o1[1] != o2[1]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });
        boolean isAlice=true;
        int aliceNum = 0;
        int bobNum = 0;

        for(int i=0;i<n;i++){
            if(isAlice){
                int[] curMax=sortList.get(i);
                aliceNum+=aliceValues[curMax[0]];
            }else{
                int[] curMax=sortList.get(i);
                bobNum+=bobValues[curMax[0]];
            }
            isAlice=!isAlice;
        }
//        System.out.printf("Alice: %s, bob: %s\n", aliceNum, bobNum);
        return Integer.compare(aliceNum, bobNum);
    }

    public static void main(String[] args) {
        //**Requirement
        //- Alice and Bob take turns playing a game, with (Alice starting first).
        //- There are (n stones in a pile).
        //- On each player's turn, they can (remove a stone) from the pile and receive (points) based on (the stone's value).
        //- Alice and Bob may value (the stones differently).
        //- You are given two integer arrays of length n, (aliceValues) and (bobValues).
        //- Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value (the ith stone).
        //- The winner
        //  + is the person with (the most points) after all the stones are chosen.
        //  + If both players have the same amount of points, the game results in a draw.
        //- Both players will (play optimally).
        //- Both players know the other's values.
        //* Determine the result of the game, and:
        //  + If Alice wins, return 1.
        //  + If Bob wins, return -1.
        //  + If the game results (in a draw), return 0.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == aliceValues.length == bobValues.length
        //1 <= n <= 10^5
        //1 <= aliceValues[i], bobValues[i] <= 100
        //  + N khá lớn ==> Time: O(n)/ O(n*k)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: aliceValues = [1,3], bobValues = [2,1]
        //Output: 1
        //Explanation:
        //If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
        //Bob can only choose stone 0, and will only receive 2 points.
        //Alice wins.
        //
        //- Ở đây nó liên quan đến 2 values của Alice và Bob 1 cách riêng rẽ
        //  + Index sẽ được lưu vết chung với Alice và Bob
        //
        //- Vì 2 values depend vào nhau:
        //  => Nên ta sẽ tính hiệu 2 arrays
        //
        //- Sau đó sort chúng để chọn ưu tiên với mỗi thằng
        //Ex:
        //aliceValues = [2,4,3]
        //bobValues = [1,6,7]
        //subtraction = [1,-2,-4]
        //- Thằng alice sẽ chọn thằng có value nhỏ nhất
        //  + Alice được lợi nhất
        //- Thằng bob sẽ chọn thằng có value lớn nhất
        //  + Bob được lợi nhất
        //
        //- Special case:
        //- Có case:
        //  + (alice point > bob point) khá nhiều vs (alice point - bob point) (Âm nhưng chưa đủ)
        //==> Ta sẽ sort theo |alice point - bob point|
        //  ==> Để phân biệt ta sẽ ưu tiên ((subtraction point) >= 0)
        //Ex:
        //int[] aliceValues = {1,2};
        //int[] bobValues = {3,1};
        //
//        int[] aliceValues = {2,4,3}, bobValues = {1,6,7};
//        int[] aliceValues = {1,2};
//        int[] bobValues = {3,1};
//        int[] aliceValues = {17,34};
//        int[] bobValues = {34,17};
        int[] aliceValues = {9,8,3,8};
        int[] bobValues = {10,6,9,5};
        //- Nếu chọn |a-b| lớn nhất
        //a:3,8 = 11
        //b:5,10 = 15
        //
        //a:9,8 = 17
        //b:9,5 = 14
        //
        //a:9,8 = 17
        //b:5,3 = 8
        //
        //a:9,8 = 17
        //b:5,3 = 8
        //==> Chọn hiệu min nhất vẫn chưa tốt
        //--> Ta quên mất mối quan hệ giữa các value của cùng 1 người
        //Ex:
        //a =[1,100]
        //b =[5,101]
        //==> Nếu check theo hiệu:
        //a chọn [1]
        //b chọn [101]
        //==> Rõ ràng tệ hơn
        //- Nếu chọn theo max của từng thằng thì có đúng không?:
        //Ex:
        //a =[8,10]
        //b =[100,3]
        //+ a chọn [10]
        //+ b chọn [100]
        //==> a sẽ tệ
        //+ a chọn [8]
        //+ b chọn [3]
        //==> a sẽ tối ưu
        //--> Max của từng thằng sẽ không đúng
        //* Kinh nghiệm:
        //- Làm heap (MAX/MIN)
        //** Refer idea:
        //- Alice lấy a:
        //  + It is good for Alice
        //  ==> Cùng lúc đó nó sẽ lấy b
        //      + Cái ta cần là bad for Bob
        //- Good for Alice <=> Bad for Bob
        //  ==> Ta sẽ lấy của Bob max nhất có thể + lấy của Alice cũng phải Max
        //=> Sort by (a+b)
        //
        //1.1, Optimization
        //- Ở đây để tránh poll liên tục
        //  + Ta có thể sort
        //  + For 1 lần để chọn là xong
        //
        //* KINH NGHIỆM:
        //- Sort trước sẽ nhanh hơn là (max heap + poll)
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        //#Reference:
        //1406. Stone Game III
        //1510. Stone Game IV
        //1563. Stone Game V
        System.out.println(stoneGameVIWrong(aliceValues, bobValues));
        System.out.println(stoneGameVI(aliceValues, bobValues));
        System.out.println(stoneGameVIOptimization(aliceValues, bobValues));
        System.out.println(stoneGameVIOptimizationPreSort(aliceValues, bobValues));
    }
}
