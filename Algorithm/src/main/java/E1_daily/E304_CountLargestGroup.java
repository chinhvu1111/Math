package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E304_CountLargestGroup {

    public static int countLargestGroup(int n) {
        HashMap<Integer, Integer> mapCount =new HashMap<>();
        int maxSize=0;

        for (int i = 1; i <= n; i++) {
            int sumDigit=0;
            int curNumber=i;

            while (curNumber!=0){
                sumDigit+=curNumber%10;
                curNumber/=10;
            }
            int curCount = mapCount.getOrDefault(sumDigit, 0);
            mapCount.put(sumDigit, curCount+1);
            maxSize=Math.max(maxSize, curCount+1);
        }
        int rs=0;

        for (Map.Entry<Integer, Integer> e: mapCount.entrySet()) {
            if(e.getValue()==maxSize){
                rs++;
            }
        }
//        System.out.println(mapCount);
        return rs;
    }

    public static void main(String[] args) {
        //- You are given an integer n.
        //- Each number from (1 to n) is grouped according to (the sum of its digits).
        //* Return (the number of groups) that have (the largest size).
        //
        //Example 1:
        //
        //Input: n = 13
        //Output: 4
        //Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
        //[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
        //There are 4 groups with largest size.
        //
        int n = 13;
        System.out.println(countLargestGroup(n));
        //
        //#Reference:
        //400. Nth Digit
        //1515. Best Position for a Service Centre - HARD
        //1828. Queries on Number of Points Inside a Circle
        //2048. Next Greater Numerically Balanced Number
        //1822. Sign of the Product of an Array
        //858. Mirror Reflection
    }
}
