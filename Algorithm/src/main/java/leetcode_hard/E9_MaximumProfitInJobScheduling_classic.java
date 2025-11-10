package leetcode_hard;

import java.util.*;

public class E9_MaximumProfitInJobScheduling_classic {

    //[3],(10),2,[5],(8),6
    //  ==>
    public static int findNearestIndex(int[][] range, int key){
        int low=0, high=range.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(range[mid][1]<=key){
                if(range[mid+1][1]<=key){
                    low=mid+1;
                }else{
                    return mid;
                }
            }else{
                high=mid-1;
            }
        }
        return -1;
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n=startTime.length;
        int[][] range=new int[n][3];

        for (int i = 0; i < n; i++) {
            range[i][0]=startTime[i];
            range[i][1]=endTime[i];
            range[i][2]=profit[i];
        }
//        Arrays.sort(range, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
        Arrays.sort(range, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int[] dp=new int[n];
        dp[0]=range[0][2];
//        System.out.println(dp[0]);
        int rs=range[0][2];
        for(int i=1;i<n;i++){
            int curElement=range[i][2];
            int prevElement = findNearestIndex(range, range[i][0]);
            if(prevElement!=-1){
//                System.out.printf("prevElement: %s, dp[prevElement[1]]: %s\n", prevElement, dp[prevElement]);
                curElement+=dp[prevElement];
            }
            //dp[i-1]: exclude the current value
            //dp[index]+ val: include the current value
            //  + But endTime[i-1] > startTime[i]
            //  ==> Exclude the current element
            curElement=Math.max(curElement, dp[i-1]);
            dp[i]=curElement;
            rs=Math.max(rs, dp[i]);
//            System.out.printf("start: %s, end: %s, profit: %s\n", range[i][0], range[i][1], range[i][2]);
//            System.out.printf("dp[%s]: val:%s\n", i, curElement);
        }

        return rs;
    }

    static class The_Comparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            return list1.get(0) - list2.get(0);
        }
    }

    private static int findMaxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        // min heap having {endTime, profit}
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(new The_Comparator());

        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0), end = jobs.get(i).get(1), profit = jobs.get(i).get(2);

            // keep popping while the heap is not empty and
            // jobs are not conflicting
            // update the value of maxProfit
            while (pq.isEmpty() == false && start >= pq.peek().get(0)) {
                maxProfit = Math.max(maxProfit, pq.peek().get(1));
                pq.remove();
            }

            ArrayList<Integer> combinedJob = new ArrayList<>();
            combinedJob.add(end);
            combinedJob.add(profit + maxProfit);

            // push the job with combined profit
            // if no non-conflicting job is present maxProfit will be 0
            pq.add(combinedJob);
        }

        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while (pq.isEmpty() == false) {
            maxProfit = Math.max(maxProfit, pq.peek().get(1));
            pq.remove();
        }

        return maxProfit;
    }

    public static int jobSchedulingReferSlow(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        // storing job's details into one list
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);

            jobs.add(currJob);
        }

        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        return findMaxProfit(jobs);
    }

    public static void main(String[] args) {
        //** Requirement
        //- We have n jobs, where (every job) is scheduled to be done from (startTime[i] to endTime[i]), obtaining (a profit of profit[i]).
        //- You're given the (startTime, endTime) and (profit arrays),
        //* return (the maximum profit) you can take such that there are (no two jobs) in the subset with (overlapping time range).
        //- If you choose (a job) that (ends) at (time X) you will be able to start (another job) that (starts) at (time X).
        //          40  40 40
        //     10   10  10
        //  50 50 50|70 70 70 70
        //- 1  2    3   4  5  6
        //
        //Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
        //Output: 120
        //Explanation: The subset chosen is the first and fourth job.
        //Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
        //1 <= startTime[i] < endTime[i] <= 10^9
        //1 <= profit[i] <= 10^4
        //  + Profit > 0 ==> Take as much as possible
        //  + range_length <= 5*10^4
        //      ==> Time: O(n*k)
        //  + startTime[i]/ endTime[i] <= 10^9
        //      ==> Long
        //
        //* Brainstorm:
        //- The sum is not related to (length of time) (endTime-startTime)
        //- Overlap ==> Sort by (startTime)
        //
        //Ex:
        //          40  40 40
        //     10   10  10
        //  50 50 50|70 70 70 70
        //  1  2    3   4  5  6
        //
        //Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
        //Output: 120
        //- [1-3],[2-4],[3-5],[3-6]
        //
        //- Can we use dynamic programming?
        //- Dp[i] is (the max profit) we can get if we use range[i] as (the last one)
        //  + The last one?
        //
        //- Each range we can:
        //  + Take
        //  + Not take ==> We get back to (the previous index)???
        //      + dp[i-1] or dp[i-k]
        //
        //- At index=i:
        //  + We find (the nearest range) with (endTime[j] >= startTime[i])
        //  + How about if we don't use the range[j]?
        //  Ex:
        //      30
        //  1 ----- 3
        //          10
        //     2 ---------5
        //                    10
        //                5 ----- 8
        //  ==> (2,5) is near (5,8) but we should use (1,3) [(1,3) is overlap with (2,5)]
        //  ==> We need to check both of sites:
        //      + (Include) the current element
        //      + (Exclude) the current element
        //* Experience:
        //- Dp[i] covers both of (include(i) and exclude(i)) sites
        //- Dp[i] = Max(dp[i-1](Exclude), dp[index]+profix[i])
        //  + dp[index] also cover (exclude) and (include) sites
        //
        //[2,8]: max=9
        //[4,5]: max=9
        //[4,9]: max=9
        //[8,10] ==> Get [4,5]
        //  + Max index=2
        //8,5,(9),[10]
        //==> sort (5,8,9)
        //
        //* Main point:
        //- Bài toán không phải là tìm element with (max index) mà:
        //  + nums[index] <= value
        //  ==> ntn thì sẽ take O(n^2)
        //* 2 bài toán phải "RÕ RÀNG KHÁC NHAU" Mà là:
        //  + Ta lấy current element ==> Phải bỏ đi toàn bộ những elements mà overlap với (current elements):
        //      + ==> Sort
        //      + Search thằng <= value
        //          + Nhưng mà lấy max index ==> Giống kiểu tìm được index ==> index++ đến khi có new elements[index] > value
        //          ==> Return
        //          ==> Vì tất cả những elements[index] > value ==> Bỏ đi không cho vào
        //
        //* Không phải lúc nào cũng sort theo startTime
        //==> Ở đây ta sẽ sort theo endTime để check overlap
        //
        //- Nếu sort theo endTime rồi thì:
        //==> Chắc chắn rằng nếu endTime[i] <=key ==> all index(0 -> i-1) sẽ valid
        //  + Include:
        //      + Chọn element gần nhất là valid
        //  + Exclude:
        //      + dp[i] = dp[i-1]
        //      ==> Tức là dp[i-1] sẽ đại diện cho dp[i] trong lần tính tiếp theo
        //      ==> Tức là nếu dp[j] không overlap với dp[i]
        //          ** Main point:
        //              + Cần phải chắc chắn thoả mãn không overlap với dp[i-1]
        //              ==> Luôn đúng vì endTime tăng dần.
        //
        //1.1, Case
        //
        //
//        int[] startTime = {1,2,3,4,6},
//                endTime = {3,5,10,6,9},
//                profit = {20,20,100,70,60};
//        int[] startTime = {1,1,1},
//                endTime = {2,3,4},
//                profit = {5,6,4};
        int[] startTime = {4,2,4,8,2},
                endTime = {5,5,5,10,8},
                profit =  {1,2,8,10,4};
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        System.out.println(jobScheduling(startTime, endTime, profit));
        System.out.println(jobSchedulingReferSlow(startTime, endTime, profit));
    }
}
