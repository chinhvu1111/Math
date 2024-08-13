package E1_daily;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class E70_LongestWellPerformingInterval {

    public static int longestWPI(int[] hours) {
        int n=hours.length;
        int[] prefixSumTiringDay=new int[n+1];

        for(int i=1;i<=n;i++){
            if(hours[i-1]>8){
                prefixSumTiringDay[i]=prefixSumTiringDay[i-1]+1;
            }else{
                prefixSumTiringDay[i]=prefixSumTiringDay[i-1]-1;
            }
//            System.out.printf("%s,", prefixSumTiringDay[i]);
        }
        //1,2,5,6,10
        TreeSet<int[]> sortVal=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                return o2[0]-o1[0];
                return o1[0]-o2[0];
            }
        });

        //- Max length ==> cần ưu tiên cái có length min
        //==> sort theo length + lấy cả những prefix sum <0/>=0 --> Vì ta có thể - đi là được
        //- Nếu <=x ==> max length là bao nhiêu?
        //  + Sort theo value:
        //      + Add từng phần tử vào tree:
        //          + Nếu current val --> max ==> Nó sẽ (so sánh với length) của thằng < current val ngay trước nó
        //              + cur length = Math.min(cur length, prev length) ==> đoạn này cũng chả cần xét vì length tằng dần khi xét
        //
        int rs=0;
        //0 = {int[2]@617} [0, 2]
        //1 = {int[2]@628} [-1, 1]
        //2 = {int[2]@629} [-1, 3]
        //==> search (0,-1)
        //- Giảm dần: 0 -> -1
        //  +
        for(int i=1;i<=n;i++){
            int[] e = sortVal.floor(new int[]{prefixSumTiringDay[i]-1, 1});
//            if(e!=null&&e[0]==prefixSumTiringDay[i]){
//                e = sortVal.floor(new int[]{prefixSumTiringDay[i], e[1]});
//            }
//            int[] e = sortVal.floor(new int[]{prefixSumTiringDay[i], -1});
            if(prefixSumTiringDay[i]>0){
                rs=i;
            }else if(i==1){
                if(prefixSumTiringDay[i]>0){
                    rs=Math.max(rs, i);
                }
            }else if(e!=null){
                rs=Math.max(rs, i-e[1]);
            }
            sortVal.add(new int[]{prefixSumTiringDay[i], i});
        }
        return rs;
    }

    public static int longestWPIRefactor(int[] hours) {
        int n=hours.length;
        int[] prefixSumTiringDay=new int[n+1];

        for(int i=1;i<=n;i++){
            if(hours[i-1]>8){
                prefixSumTiringDay[i]=prefixSumTiringDay[i-1]+1;
            }else{
                prefixSumTiringDay[i]=prefixSumTiringDay[i-1]-1;
            }
//            System.out.printf("%s,", prefixSumTiringDay[i]);
        }
        TreeSet<int[]> sortVal=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int rs=0;

        for(int i=1;i<=n;i++){
            int[] e = sortVal.floor(new int[]{prefixSumTiringDay[i]-1, 1});
            if(prefixSumTiringDay[i]>0){
                rs=i;
            }else if(e!=null){
                rs=Math.max(rs, i-e[1]);
            }
            sortVal.add(new int[]{prefixSumTiringDay[i], i});
        }
        return rs;
    }

    public static int longestWPIOptimization(int[] hours) {
        int n=hours.length;
        Map<Integer, Integer> seen=new HashMap<>();
        int score=0;
        int rs=0;

        for(int i=0;i<n;i++){
            score+=hours[i]>8?1:-1;
            if(score>0){
                rs=i+1;
            }else{
                if(!seen.containsKey(score)){
                    seen.put(score, i);
                }
//                seen.putIfAbsent(score, i);
                if(seen.containsKey(score-1)){
                    rs=Math.max(rs, i-seen.get(score-1));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- We are (given hours), (a list of the number of hours worked) per day for (a given employee).
        //- A day is considered to be (a tiring day)
        //  + if and only if (the number of hours worked) is (strictly) greater than 8.
        //- (A well-performing interval) is an interval of days
        // for which (the number of tiring days) is strictly larger than (the number of non-tiring days).
        //* Return (the length of the longest well-performing interval).
        //- Longest interval mà (khoảng thời gian mệt mỏi > thời gian không mệt mỏi)
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= hours.length <= 10^4
        //0 <= hours[i] <= 16
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: hours = [9,9,6,0,6,6,9]
        //Output: 3
        //Explanation: The longest well-performing interval is [9,9,6].
        //- Dùng prefix sum cũng được:
        //  + max nhất trừ đi min đằng trước là xong.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //2.
        //2.0,
        //** NOTE:
        //- Sum có thể +1 ==> có thể -1
        //  + Score-1 có thể tồn tại ==> Lấy earilest (index) của (score -1) là được nếu có.
        //      + Vì có thể luôn tăng dần (increase) ==> Vẫn cần check
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //  ==> (4),3,2,1,2,3,4,(5)
//        int[] hours = {9,9,6,0,6,6,9};
//        int[] hours = {6,6,8};
        int[] hours = {8,10,6,16,5};
        System.out.println(longestWPI(hours));
        System.out.println(longestWPIRefactor(hours));
        System.out.println(longestWPIOptimization(hours));
        //#Reference:
        //2272. Substring With Largest Variance
        //953. Verifying an Alien Dictionary
        //1521. Find a Value of a Mysterious Function Closest to Target
    }
}
