package contest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class E52_MaximizeHappinessOfSelectedChildren {

    public static long maximumHappinessSum(int[] happiness, int k) {
        int decre=0;
        Arrays.sort(happiness);
        int n=happiness.length;
        long rs=0;

        //n=3 ==> min= 3-2
        for(int i=n-1;i>=n-k;i--){
            if(happiness[i]-decre<0){
                break;
            }
            rs+=happiness[i]-decre;
//            System.out.println(happiness[i]-decre);
            decre++;
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an array happiness of length n, and a positive integer k.
        //- There are n children standing in a queue, where the (ith child) has happiness (value happiness[i]).
        //- You want to (select k children) from these (n children in k turns).
        //- In each turn, when you select a child, the happiness value of all the children that have not been selected till now (decreases by 1).
        //- Note that the happiness value cannot become negative and gets decremented only if it is positive.
        //* Return (the maximum sum of the happiness values of the selected children) you can achieve by selecting (k children).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == happiness.length <= 2 * 10^5
        //1 <= happiness[i] <= 10^8
        //1 <= k <= n
        //==> Time : O(n), O(n*log(n))
        //
        //- Brainstorm
        //
        //
        int[] happiness = {1,2,3};
        int k = 2;
        System.out.println(maximumHappinessSum(happiness, k));
    }
}
