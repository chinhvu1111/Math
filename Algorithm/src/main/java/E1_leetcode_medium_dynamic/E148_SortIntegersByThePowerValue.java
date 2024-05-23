package E1_leetcode_medium_dynamic;

import java.util.*;

public class E148_SortIntegersByThePowerValue {

    public static int solution(int curVal, HashMap<Integer, Integer> dp){
        if(curVal==1){
            return 0;
        }
        if(dp.containsKey(curVal)){
            return dp.get(curVal);
        }
        int curRs;
        if(curVal%2==1){
            curRs=solution(curVal*3+1, dp)+1;
        }else {
            curRs=solution(curVal/2, dp)+1;
        }
        dp.put(curVal, curRs);
        return curRs;
    }

    public static int getKth(int lo, int hi, int k) {
        HashMap<Integer, Integer> dp=new HashMap<>();
        List<int[]> list=new ArrayList<>();
        for(int i=lo;i<=hi;i++){
            list.add(new int[]{i, solution(i, dp)});
        }
//        System.out.println(list);
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        return list.get(k-1)[0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
        //  + if x is even then x = x / 2
        //  + if x is odd then x = 3 * x + 1
        //For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
        //Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order,
        // if two or more integers have the same power value sort them by ascending order.
        //* Return the kth integer in the range [lo, hi] (sorted by) the (power value).
        //Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps
        // and that the power of x is will fit in a 32-bit signed integer.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= lo <= hi <= 1000
        //1 <= k <= hi - lo + 1
        //
        //- Brainstorm
        //
        //
        int lo = 12, hi = 15, k = 2;
        System.out.println(getKth(lo, hi, k));
    }
}
