package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E362_MaximumNumberOfEventsThatCanBeAttended {

    public static int maxEvents(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        int n=events.length;
        int maxDay=0;
        int rs=0;

        for (int i = 0; i < n; i++) {
            maxDay=Math.max(maxDay, events[i][1]);
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        int j=0;
        for(int i=1;i<=maxDay;i++){
            while(j<n&&events[j][0]<=i){
                queue.add(events[j][1]);
                j++;
            }
            //Get event[1]>=i ==> Get min of them
            while(!queue.isEmpty()&&queue.peek()<i){
                queue.poll();
            }
            if(!queue.isEmpty()){
                rs++;
                queue.poll();
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of events where events[i] = [startDayi, endDayi].
        //- Every (event i) starts at startDay-i and ends at endDay-i.
        //- You can attend (an event i) at any day d where startTimei <= d <= endTimei.
        //- You can only attend one event at any time d.
        //
        //* Return the maximum number of events you can attend.
        //
        //Ex:
        //Input: events = [[1,2],[2,3],[3,4]]
        //Output: 3
        //Explanation: You can attend all the three events.
        //One way to attend them all is as shown.
        //Attend the first event on day 1.
        //Attend the second event on day 2.
        //Attend the third event on day 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= events.length <= 10^5
        //events[i].length == 2
        //1 <= startDayi <= endDayi <= 10^5
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //- For all of elements such that:
        //  + Event[i][0]<=i ==> get that
        //      + For to get valid element with (min event[i][1]) such that:
        //          + event[i][1]>=i
        //- j++ ==> To find the next one.
        //
        //1.1, Case
        //
        int[][] events= {{1,4},{4,4},{2,2},{3,4},{1,1}};
        //{1,1},{1,4},{2,2},{3,4},{4,4}
        //- We should choose {2,2} rather than using {1,4}
        //  + How to choose???
        //{1,1},{1,4},{2,2}
        //  => rs=3
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //# Reference:
        //2008. Maximum Earnings From Taxi
        //2402. Meeting Rooms III
        //
//        int[][] events= {{1,2},{2,3},{3,4},{1,2}};
        System.out.println(maxEvents(events));
    }
}
