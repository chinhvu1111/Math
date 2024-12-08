package E1_daily;

import java.util.Arrays;
import java.util.HashSet;

public class E178_MaximumNumberOfIntegersToChooseFromARangeI {

    public static int maxCount(int[] banned, int n, int maxSum) {
        int m=banned.length;
        int rs=0;
        HashSet<Integer> bannedSet=new HashSet<>();

        for(int e: banned){
            bannedSet.add(e);
        }
        int sum=0;

        for(int i=1;i<=n;i++){
            if(bannedSet.contains(i)){
                continue;
            }
            sum+=i;
            if(sum<=maxSum){
                rs++;
            }else{
                break;
            }
        }
        return rs;
    }

    public static int maxCountSweepLine(int[] banned, int n, int maxSum) {
        Arrays.sort(banned);

        int bannedIdx = 0, count = 0;

        // Check each number from 1 to n while sum is valid
        for (int num = 1; num <= n && maxSum >= 0; num++) {
            // Skip if current number is in banned array
            if (bannedIdx < banned.length && banned[bannedIdx] == num) {
                // Handle duplicate banned numbers
                while (bannedIdx < banned.length && banned[bannedIdx] == num) {
                    bannedIdx++;
                }
            } else {
                // Include current number if possible
                maxSum -= num;
                if (maxSum >= 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array banned) and two integers (n and maxSum).
        //- You are choosing (some number of integers) following the below rules:
        //  + The chosen (integers) have to be in the range [1, n].
        //  + (Each integer) can be chosen (at most once).
        //  + The chosen (integers) should not be in (the array banned).
        //  + The (sum of the chosen integers) should not exceed (maxSum).
        //* Return (the maximum number of integers) you can choose following (the mentioned rules).
        //  + Max (the number of chosen numbers)
        //
        // Idea
        //1.
        //1.0,
        //- Method-1: HashSet
        //+ Constraints:
        //1 <= banned.length <= 10^4
        //1 <= banned[i], n <= 10^4
        //1 <= maxSum <= 10^9
        //
        //- Brainstorm
        //- Greedy:
        //  + We will choose from small to big [1,n]
        //
        //1.1, Optimization
        //- We can use maxSum to (subtract value) (time by time)
        //
        //1.2, Complexity
        //- Space: O(m)
        //- Time: O(n+m)
        //
        //2. Sweep line
        //2.0,
        //2.1, Optimization
        //2.2, Complexity
        //- Time: O(n+m*log(m))
        //-
        //
        int[] banned =new int[]{1,6,5};
        int n=5;
        int m=6;
        System.out.println(maxCount(banned, n, m));
        System.out.println(maxCountSweepLine(banned, n, m));
    }
}
