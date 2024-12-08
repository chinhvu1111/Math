package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class E180_TwoBestNonOverlappingEvents {

    public static int getIndexOfEventWithMaxVal(int currentStart, int[][] events, int n){
        int low=0;
        int high=n-1;
        int rs=-1;

        //Find min index
        while(low<=high){
            int mid=low+(high-low)/2;
            if(events[mid][0]>=currentStart){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int maxTwoEvents(int[][] events) {
        int n= events.length;
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int rs=0;
        int[] suffixMax=new int[n];
        int maxVal=Integer.MIN_VALUE;

        for(int i=n-1;i>=0;i--){
            maxVal=Math.max(maxVal, events[i][2]);
            suffixMax[i]=maxVal;
        }
        for (int i = 0; i <n; i++) {
            int index=getIndexOfEventWithMaxVal(events[i][1]+1, events, n);
            rs=Math.max(rs, events[i][2]);
//            System.out.printf("%s %s\n", i, index);
            if(index!=-1){
                rs=Math.max(rs, events[i][2]+suffixMax[index]);
            }
        }
        return rs;
    }

    public static int findEvents(int[][] events, int index, int count, int[][] dp){
        if(count==2 || index>= events.length){
            return 0;
        }
        if(dp[index][count]!=-1){
            //Time: O(n)
            return dp[index][count];
        }
        //Max of including and excluding the current event
        int end=events[index][1];
        int low=index+1, high=events.length-1;

        //Time: O(log(n))
        while(low<high){
            int mid = low+ (high-low)>>1;
            if(events[mid][0]>end){
                high=mid;
            }else{
                low=mid+1;
            }
        }
        //If we include the current event, we will only search the events that are not overlap with the current event
        int include = events[index][2] + (low<events.length && events[low][0]> end?findEvents(events, low, count+1, dp):0);
        int exclude = findEvents(events, index+1, count, dp);
        dp[index][count]=Math.max(include, exclude);
        return dp[index][count];
    }

    public static int maxTwoEventsTopDownDynamicProgramming(int[][] events) {
        int n = events.length;
        //
        //Space: O(n)
        int[][] dp=new int[n][3];

        //Time: O(n)
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }
        //
        return findEvents(events, 0, 0, dp);
    }

    public static int maxTwoEventsGreedy(int[][] events) {
        List<int[]> times=new ArrayList<>();

        for(int[] e: events){
            times.add(new int[]{ e[0], 1, e[2]});
            times.add(new int[]{ e[1]+1, 0, e[2]});
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
        int rs=0, maxValue=0;

        for(int[] timeVal: times){
            if(timeVal[1]==1){
                rs = Math.max(rs, timeVal[2]+maxValue);
            }else{
                maxValue=Math.max(maxValue, timeVal[2]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed 2D integer array of events where events[i] = [startTime-i, endTime-i, value-i].
        //- The (ith event) starts at startTime-i and ends at endTime-i, and if you attend this event, you will receive a value of value-i.
        //- You can choose (at most) (two non-overlapping events) to attend such that (the sum of their values) is maximized.
        //* Return (this maximum sum).
        //- Note that the (start time and end time) is inclusive:
        //  + that is, you cannot attend two events where one of them starts and the other ends at the same time.
        //  + More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
        //- Choose <=2 events that are not overlap
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= events.length <= 10^5
        //events[i].length == 3
        //1 <= startTime-i <= endTime-i <= 10^9
        //1 <= value-i <= 10^6
        //  + length <= 10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //Input: events = [[1,3,2],[4,5,2],[2,4,3]]
        //Output: 4
        //Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
        //
        //- event element could be (duplicated) with (different or same) value
        //- Binary search:
        //  + Sort by (start)
        //- For first event:
        //  + We can find the second event with (start-2>end-1)
        //  + Max val of all of events
        //
        //- Suffix max
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        //2. Top down dynamic programming
        //2.0,
        //- For each event at index idx, the function computes two possible outcomes:
        //  + (including) the current event in the selection
        //  + or (excluding) it.
        //- If the current event is included, a binary search is performed to find the next event that starts after the current event's end time.
        // The result of including the event is the sum of the event's value and the recursive result of selecting the next event,
        // incrementing the count of selected events.
        //- Otherwise, we exclude the current event and call the recursive function on the next index.
        //- The (recurrence) chooses the maximum value between (including or excluding) the current event,
        // which is then stored in the dp table to avoid (redundant calculations).
        //- The result for a given state (idx, cnt) is thus the maximum of either (selecting or skipping) the current event,
        // ensuring optimal (selection of up to) (two non-overlapping events).
        //
        //- If we include (the current event), we will only search the events that are (not overlap) with the current event
        //  + Searching from (low+1, n-1)
        //- If we exclude (the current event), we will only search from (index+1 to n-1)
        //  + int include = events[index][2] + (low<events.length && events[low][0]> end?findEvents(events, low, count+1, dp):0);
        //  + int exclude = findEvents(events, index+1, count, dp);
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //3. Greedy
        //3.0,
        //- Because we only need to choose two elements:
        //  + For index=i:
        //      + Including the current event:
        //          + Get max value of the previous events having the end < (current start time)
        //* Main point:
        //==> To get max of the previous events we need to add "both":
        //  + Start + value
        //  + End + value
        //  * Consider the (start, end) are the event.
        //
        //      + Excluding the current event:
        //          + Ignore
        //
        //#Reference:
        //1235. Maximum Profit in Job Scheduling
        //1751. Maximum Number of Events That Can Be Attended II
        //2555. Maximize Win From Two Segments
        //
        //- This problem is based on line sweep algorithm:
        //Maximum Population Year -> 1854
        //Points That Intersect With Cars -> 2848
        //Car Pooling -> 1094
        //My Calendar II -> 731
        //Shifting Letters II -> 2381
        //Perfect Rectangle -> 391
        //Rectangle Area II -> 850
        //Number of Flowers in Full Bloom -> 2251
        int[][] events = {{1,3,2},{4,5,2},{2,4,3}};
        System.out.println(maxTwoEvents(events));
        System.out.println(maxTwoEventsTopDownDynamicProgramming(events));
        System.out.println(maxTwoEventsGreedy(events));
    }
}
