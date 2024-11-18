package E1_binary_search_topic;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class E27_MaximumNumberOfRobotsWithinBudget {

    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n=chargeTimes.length;
//        int sum=0;
//        int[][] prefixSum=new int[n][2];
//
//        for(int i=0;i<n;i++){
//            sum+=runningCosts[i];
//            prefixSum[i][0]=sum;
//            prefixSum[i][1]=i;
//        }
        TreeSet<long[]> set=new TreeSet<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0]!=o2[0]){
                    return (int) (o2[0] - o1[0]);
                }
                return (int) (o1[1] - o2[1]);
            }
        });
        long curSum=0;
        int size=0;
        int maxSize=0;

        for(int i=0;i<n;i++){
            set.add(new long[]{chargeTimes[i], i});
            curSum+=runningCosts[i];
            size++;
            while (!set.isEmpty()&&curSum*size+set.first()[0]>budget){
                set.remove(new long[]{chargeTimes[i-size+1], i-size+1});
                curSum-=runningCosts[i-size+1];
                size--;
            }
            maxSize=Math.max(maxSize, size);
        }
        return maxSize;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You have n robots.
        //- You are given two (0-indexed integer arrays), (chargeTimes and runningCosts), both of length n.
        //- The (ith robot) costs (chargeTimes[i] units) to charge and costs (runningCosts[i] units) to run.
        //- You are also given (an integer budget).
        //- The (total cost) of running (k chosen robots) is equal to
        //  + max(chargeTimes) + k * sum(runningCosts),
        //  where max(chargeTimes) is (the largest charge cost) among the (k robots) and sum(runningCosts) is (the sum of running costs) among the (k robots).
        //* Return (the maximum number of ("consecutive") robots) you can run such that (the total cost) does (not exceed budget).
        //
        //Example 1:
        //
        //Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
        //Output: 3
        //Explanation:
        //It is possible to run all individual and consecutive pairs of robots within budget.
        //To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
        //It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
        //=> Max consecutive robots can be run
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //chargeTimes.length == runningCosts.length == n
        //1 <= n <= 5 * 10^4
        //1 <= chargeTimes[i], runningCosts[i] <= 10^5
        //1 <= budget <= 10^15
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
        //- Consecutive:
        //  + Prefix sum
        //  + Slice window
        //  + queue
        //chargeTimes = [3,6,1,3,4]
        //runningCosts = [2,1,3,4,5]
        //- Slice window:
        //  total cost = max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24
        //  + Add new element(3):
        //  total cost = max(3,6,1,3) + 4 * sum(2,1,3,4) = 6 + 3 * 10 = 36
        //
        //- Add new element:
        //  + Increase cost
        //- We just remove the index<=i-k
        //- Max heap:
        //  + We remove the element[i] from the queue
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        int[] chargeTimes = {3,6,1,3,4}, runningCosts = {2,1,3,4,5};
        int budget = 25;
//        int[] chargeTimes = {11,12,19}, runningCosts = {10,8,7};
//        int budget = 19;
        System.out.println(maximumRobots(chargeTimes, runningCosts, budget));
        //#Reference:
        //2040. Kth Smallest Product of Two Sorted Arrays
        //2071. Maximum Number of Tasks You Can Assign
    }
}
