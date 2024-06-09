package contest;

import java.util.Arrays;
import java.util.Comparator;

public class E117_CountDaysWithoutMeetings {

    public static int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int n=meetings.length;

        //1----------6
        //     2---------7
        //        3--6
        int maxX=0;
        int rs=0;

        for(int i=0;i<n;i++){
            int[] curMeeting = meetings[i];
            if(maxX<curMeeting[0]){
                rs+=curMeeting[0]-maxX-1;
            }
            maxX=Math.max(maxX, curMeeting[1]);
        }
        if(maxX<days){
            rs+=days-maxX;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a positive integer days representing (the total number of days) an employee is "available" for work (starting from day 1).
        // You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i]
        // represents the (starting) and (ending) days of (meeting i) (inclusive).
        //* Return (the count of days) when the employee is available for work but (no meetings) are scheduled.
        //- Note: The meetings may overlap.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= days <= 10^9
        //1 <= meetings.length <= 10^5
        //meetings[i].length == 2
        //1 <= meetings[i][0] <= meetings[i][1] <= days
        //
        //- Brainstorm
        //- Tìm ngày không có meeting
        //
//        int[][] arr = {{5,7},{1,3},{9,10}};
        //{1,3},{5,7},{9,10}
//        int[][] arr = {{2,4},{1,3}};
        //{1,3},{2,4}
        int[][] arr = {{3,4},{4,8},{2,5},{3,8}};
        //{2,5},{3,4},{3,8},{4,8},
//        System.out.println(countDays(10, arr));
//        System.out.println(countDays(5, arr));
        System.out.println(countDays(8, arr));
        //
    }
}
