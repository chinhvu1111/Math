package E2_intervals;

import java.util.Arrays;

public class E2_MeetingRooms {

    public static boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length==0){
            return true;
        }
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        int high=intervals[0][1];
        int n=intervals.length;

        for(int i=1;i<n;i++){
            if(high>intervals[i][0]){
                return false;
            }
            high=Math.max(high, intervals[i][1]);
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
