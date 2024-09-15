package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E92_CountPairsOfPointsWithDistancek_classic {

    public static int countPairsWrong(List<List<Integer>> coordinates, int k) {
        HashMap<Integer, Integer> xCount=new HashMap<>();
        HashMap<Integer, Integer> yCount=new HashMap<>();
        int n=coordinates.size();

        for(List<Integer> e: coordinates) {
            xCount.put(e.get(0), xCount.getOrDefault(e.get(0), 0) + 1);
            yCount.put(e.get(1), yCount.getOrDefault(e.get(1), 0) + 1);
        }
//        System.out.println(xCount);
//        System.out.println(yCount);
        int[] countKX=new int[k+1];
        int[] countKY=new int[k+1];

        for(int i=0;i<=k;i++){
            //0 -> 1 -> 2 -> ... -> 10
            //5 = 2+3 or 3+2
            //
            //
            int curCount=0;
            for(Map.Entry<Integer, Integer> e: xCount.entrySet()){
                if(i==0){
                    //1^1 = 0
                    //2^2 = 0
                    curCount+=e.getValue()*(e.getValue()-1)/2;
                }else if(i>=e.getKey()&&xCount.containsKey(i^e.getKey())&&(i^e.getKey())>e.getKey()){
                    curCount+=e.getValue()*xCount.get(i^e.getKey());
                }
            }
            countKX[i]=curCount;
            //
            curCount=0;
            for(Map.Entry<Integer, Integer> e: yCount.entrySet()){
                if(i==0){
                    //1^1 = 0
                    //2^2 = 0
                    curCount+=e.getValue()*(e.getValue()-1)/2;
                }else if(i>=e.getKey()&&yCount.containsKey(i^e.getKey())&&(i^e.getKey())>e.getKey()){
                    curCount+=e.getValue()*yCount.get(i^e.getKey());
                }
            }
            if(i%2==0&&yCount.containsKey(i/2)){
                //k=10
                //vals = [5,5,5,5]
                //==> 3+2+1 = n*(n-1)/2
                int countVal = yCount.get(i/2);
                curCount+=countVal*(countVal-1)/2;
            }
            countKY[i]=curCount;
        }
        for (int i = 0; i <= k; i++) {
            System.out.printf("CountX: %s, X=%s, CountY: %s, Y=%s\n", countKX[i], i, countKY[i], i);
        }
        System.out.println();
        int rs=0;
        for(int i=0;i<=k;i++){
            rs+=countKX[i]*countKY[k-i];
            System.out.printf("CountX: %s, X=%s, CountY: %s, Y=%s\n", countKX[i], i, countKY[k-i], k-i);
        }
        return rs;
    }

//    public static class Pair<K,V>{
//        K key;
//        V value;
//        public Pair(K x, V y){
//            this.key=x;
//            this.value=y;
//        }
//
//        public K getKey() {
//            return key;
//        }
//
//        public void setKey(K key) {
//            this.key = key;
//        }
//
//        public V getValue() {
//            return value;
//        }
//
//        public void setValue(V value) {
//            this.value = value;
//        }
//
//        @Override
//        public int hashCode() {
//            return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
//        }
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o instanceof Pair) {
//                Pair pair = (Pair) o;
//                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
//                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
//                return true;
//            }
//            return false;
//        }
//    }

    public static int countPairs(List<List<Integer>> coordinates, int k) {
        HashMap<Pair<Integer, Integer>, Integer> hashPairCount=new HashMap<>();

        for(List<Integer> e: coordinates) {
            Pair<Integer, Integer> curPair=new Pair<>(e.get(0), e.get(1));
            hashPairCount.put(curPair, hashPairCount.getOrDefault(curPair,0)+1);
        }
//        System.out.println(coordinates.size());
//        System.out.println(hashPairCount.size());
        int rs=0;
        HashSet<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> visited= new HashSet<>();
        for(Pair<Integer, Integer> key: hashPairCount.keySet()){
            //x
            for(int i=0;i<=k;i++){
                //1^5+3^2 != 1^5+2^3
                int expectedX = key.getKey()^i;
//                int expectedY = (k-i)^key.getValue();
                int expectedY = key.getValue()^(k-i);
                Pair<Integer, Integer> p=new Pair<>(expectedX, expectedY);
                if(visited.contains(new Pair<>(key, p))||visited.contains(new Pair<>(p, key))){
                    continue;
                }
                if(expectedX==key.getKey()&&expectedY==key.getValue()){
                    int curCount = hashPairCount.get(p);
//                    rs+=curCount*(curCount-1)/2;
                    //Overflow
                    long curVal = (long)curCount*(curCount-1)/2;
                    rs+=curVal;
                    visited.add(new Pair<>(key, p));
                    visited.add(new Pair<>(p, key));
//                    visited.add(key);
                }else if(hashPairCount.containsKey(p)){
//                    System.out.printf("%s %s\n", key, p);
                    rs+=hashPairCount.get(key)*hashPairCount.get(p);
                    visited.add(new Pair<>(key, p));
                    visited.add(new Pair<>(p, key));
//                    visited.add(key);
                }
            }
        }
//        System.out.println(visited.size());
        return rs;
    }

    public static int countPairsOptimization(List<List<Integer>> coordinates, int k) {
        HashMap<Integer, Map<Integer, Integer>> map=new HashMap<>();
        int rs=0;

        for (List<Integer> c: coordinates){
            int x1=c.get(0);
            int y1=c.get(1);

            for(int j=0;j<=k;j++){
                int x2=x1^j;
                int y2=y1^(k-j);
                if(map.containsKey(x2)&&map.get(x2).containsKey(y2)){
                    rs+=map.get(x2).get(y2);
                }
            }
            map.computeIfAbsent(x1, a -> new HashMap<>()).put(y1, map.get(x1).getOrDefault(y1,0 )+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array coordinates and (an integer k),
        // where coordinates[i] = [xi, yi] are the coordinates of (the ith point) in a 2D plane.
        //- We define (the distance) between
        //  + two points (x1, y1) and (x2, y2) = (x1 XOR x2) + (y1 XOR y2) where XOR is the bitwise XOR operation.
        //* Return the number of pairs (i, j) such that i < j and the distance between (points i and j is equal to k).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= coordinates.length <= 50000
        //0 <= xi, yi <= 10^6
        //0 <= k <= 100
        //  + length khá lớn <=50000:
        //      + Time: O(n*k)
        //  + K không quá lớn
        //      + Ta có thể dùng k để tính
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
        //Output: 2
        //Explanation: We can choose the following pairs:
        //- (0,1): Because we have (1 XOR 4) + (2 XOR 2) = 5.
        //- (2,3): Because we have (1 XOR 5) + (3 XOR 2) = 5.
        //
        //- Formula:
        //  + a xor b xor b xor c = a xor c
        //  = a xor 0 xor c = a xor c
        //
        //- Nếu tính đôi (i,j) một
        //  ==> Kiểu gì nó cũng thành O(n^2)
        //
        //- (i,j) = sum(coordinates[i][0] xor coordinates[j][0]) + sum(coordinates[i][1] xor coordinates[j][1])
        //  + Ta có thể phân theo (x,y) riêng rẽ dc k:
        //      + Tính count số lượng cặp (coordinates[i][0] xor coordinates[j][0]) = x
        //      ==> Đoạn này dùng hashmap để count được
        //  ==> Phân riêng rẽ:
        //      + Không đi theo cặp (x,y) và (x1, y1) được
        //+ a xor b = x
        //  + b = a xor x
        ///
//        System.out.println(3^5^5^8);//11
//        System.out.println(3^8);//11
//        System.out.println(12^0);/12
//        int[][] coordinates = {{1,2},{4,2},{1,3},{5,2}};
//        int k = 5;
//        int[][] coordinates = {{10,57},{12,62},{92,44},{7,60},{8,55},{13,50},{5,55},{71,82},{64,26},{68,43},
//                {61,88},{9,44},{95,16},{17,16},{12,53},{9,59},{81,44},{3,56},{70,94},{0,58},{84,29},{13,63},
//                {79,87},{19,39},{74,35},{92,7},{31,6},{2,50}};
//        int k = 13;
        int[][] coordinates = {{94,23},{86,58},{126,55},{107,23},{121,60},{89,28},{123,15},{127,3},{100,49},{5,3},{81,49},{93,0},{95,37},{92,25}};
        int k = 53;
        List<List<Integer>> coordinatesList =new ArrayList<>();
        for(int[] e: coordinates){
            coordinatesList.add(Arrays.asList(e[0], e[1]));
        }
//        System.out.println(countPairsWrong(coordinatesList, k));
        System.out.println(countPairs(coordinatesList, k));
        System.out.println(countPairsOptimization(coordinatesList, k));
//        System.out.println(1^5+3^2);
//        System.out.println((1^5)+(3^2));
//        System.out.println(3^2);
//        System.out.println(2^3);
        //** Kinh nghiệm:
        //- Tức là khi nhân 2 số int ==> Có thể overflow nếu số int quá lớn
        System.out.println(50000/2*(50000-1));
        //n*(n-1)/2 = n^2-n
        //
        //- Issue:
        //- TLE:
        //(x,y),(x1,y1)
        //+ Với (sumX + (x,y)):
        //  + Ta sẽ cần lưu visited
        //  + Khi xét đến (x,y)
        //      + Ta đã xét full sum của nó rồi ==> Trong 1 hash sẽ không có chuyện cần loop lại
        //  + Nếu xét (x1,y1) trong loop:
        //      + Thì sau đó --> Vẫn có thể xét tiếp được (Không thể skip)
        //
        //* Solution:
        //** Kinh nghiệm:
        //- Đếm số cặp với nhau
        //  + Ta có thể cộng lần lượt được.
        //Ex:
        //1,2,1,1,2
        //- Giả sử đếm cặp (1,2)
        //  + rs = 3*2=6
        //- Đi lần lượt từ index (0,1,2,3,4)
        //  + index=0: rs=0
        //  + index=1: rs=1
        //  + index=2: rs=2
        //  + index=3: rs=3
        //      + 1 kết hợp với 1 số 2
        //  + index=4: rs+=3
        //      + 1 số 2 kết hợp với (3 số 1)
        //- Ta chỉ cần scan count của thằng còn lại:
        //  + Check the remainging item exists
        //  + rs+=count(remaining_item)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*k)
    }
}
