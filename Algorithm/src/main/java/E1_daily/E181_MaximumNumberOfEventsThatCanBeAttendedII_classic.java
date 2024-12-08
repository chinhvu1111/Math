package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class E181_MaximumNumberOfEventsThatCanBeAttendedII_classic {

    public static int maxValueWrong(int[][] events, int k) {
//        int n=events.length;
        List<int[]> times=new ArrayList<>();

        for(int[] e: events){
            times.add(new int[]{e[0], 1, e[2]});
            times.add(new int[]{e[1]+1, 0, e[2]});
        }
        times.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        int[] maxValue = new int[k+1];
        int[] temp = new int[k+1];
        int[] prevMaxValue = new int[k+1];

        //max_value[k] = max(max_value[k], max_value[k-1] + timeVal[2])
        //- mark == 1:
        //  + new event
        //- mark == 0:
        //  + end this event
        //  ==> We can use this event (to choose)
        //      + But we don't sure whether this event will be overlap with the previous events or not
        //Ex:
        //start=1, end=3, start=4, end=5, (start=6)
        //  + (start=6):
        //      + We calculate the result for (start==6) using:
        //          + end=3 or end=5
        //          ==> Because there indices are not overlap with (the current index)
        //- This way is not workable
        for(int[] timeVal:times){
            if(timeVal[1]==1){
//                for(int j=1;j<=k;j++){
//                    maxValue[j]=Math.max(prevMaxValue[j], prevMaxValue[j-1]+timeVal[2]);
//                }
            }else{
                for(int j=1;j<=k;j++){
                    maxValue[j]=Math.max(prevMaxValue[j], prevMaxValue[j-1]+timeVal[2]);
                }
                for(int j=1;j<=k;j++){
                    prevMaxValue[j]=maxValue[j];
                }
            }
        }
        int rs=Integer.MIN_VALUE;
        for(int i=1;i<=k;i++){
            rs=Math.max(rs, maxValue[i]);
        }
        return rs;
    }

    public static int solution(int[][] events, int[][] dp, int index, int count, int n, int k){
        if(index>=n||count==k){
            return 0;
        }
        if(dp[index][count]!=-1){
            return dp[index][count];
        }
        int low=index;
        int high=n-1;
        int rs=-1;
        int maxVal=events[index][1]+1;

        while (low<=high){
            int mid=low+(high-low)/2;
            if(events[mid][0]>=maxVal){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
//        System.out.printf("%s %s\n", index, rs);
        int include=events[index][2];
        if(rs!=-1){
            include+=solution(events, dp, rs, count+1, n, k);
        }
        int exclude= solution(events, dp, index+1, count, n, k);
        return dp[index][count]=Math.max(include, exclude);
    }

    public static int maxValue(int[][] events, int k) {
        int n=events.length;
        //We use top down approach
        int[][]dp=new int[n][k];
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }

        return solution(events, dp, 0, 0, n, k);
    }

    public static int bisectRight(int[][] events, int target, int n){
        int low=0,high=n-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(events[mid][0]>=target){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int maxValueBottomUp(int[][] events, int k) {
        int n=events.length;
        //We use top down approach
        int[][]dp=new int[n+1][k+1];
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        //We have the same idea but index will decrease from (n-1 to 0)
        //For each index:
        //  + We calculate using the right elements that are not overload with the current element.
        for(int i=n-1;i>=0;i--){
            int leftIndex= bisectRight(events, events[i][1]+1, n);

            for(int count=1;count<=k;count++){
                int include = events[i][2];
                if(leftIndex!=-1){
                    include+=dp[leftIndex][count-1];
                }
                dp[i][count]=Math.max(dp[i+1][count], include);
            }
        }
        return dp[0][k];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of events where events[i] = [startDay-i, endDay-i, value-i].
        //- The ith event starts at startDay-i and ends at endDay-i, and if you attend this event, you will receive a value of value-i.
        //- You are also given (an integer k) which represents (the maximum number of events) you can attend.
        //- You can only attend one event at a time.
        //- If you choose to attend an event, you must attend the entire event.
        //- Note that the end day is inclusive:
        //  + that is, you cannot attend two events where one of them starts and the other ends on the same day.
        //* Return the maximum sum of values that you can receive by attending events.
        //
        //- It is expansion of the previous problem
        //  + = prev + (k events)
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= events.length
        //1 <= k * events.length <= 10^6
        //1 <= startDay-i <= endDay-i <= 10^9
        //1 <= value-i <= 10^6
        //  + k* events.length <= 10^6 (1s)
        //  => Time: O(k*length)
        //
        //- Brainstorm
        //- Choose k elements:
        //  + Using dynamic programming
        //- For each index=i:
        //  + We mark as from 0 to kth chosen events
        //- If we add both of (start, end) to times, we calculate the max value from 0 -> k for (each index)
        //
        //- Note:
        //  + Include : init = event[index][2]
        //      + If we can go right ==> include+= solution()
        //  + 10^6:
        //      + time<1s ==> It is still workable
        //
//        int[][] events = {{1,2,4},{3,4,3},{2,3,1}};
//        int k = 2;
        int[][] events = {{1,2,4},{3,4,3},{2,3,10}};
        //sort = {{1,2,4},{2,3,10},{3,4,3}}
        int k = 2;
        //
//        System.out.println(maxValueWrong(events, k));
        System.out.println(maxValue(events, k));
        System.out.println(maxValueBottomUp(events, k));
        //
        //1.1, Optimization
        //- Top down method:
        //- We have the same idea but index will decrease from (n-1 to 0)
        //- For each index:
        //  + We calculate using the right elements that are not overload with the current element.
        //
        //1.2, Complexity
        //- Space: O(n*k)
        //- Time: O(n*log(n)+n*k*log(n))
        //
        //#Reference:
        //1353. Maximum Number of Events That Can Be Attended
        //2008. Maximum Earnings From Taxi
        //2402. Meeting Rooms III
    }
}
