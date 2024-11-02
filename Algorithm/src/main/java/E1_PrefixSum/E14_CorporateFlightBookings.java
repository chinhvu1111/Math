package E1_PrefixSum;

import java.util.Arrays;
import java.util.Comparator;

public class E14_CorporateFlightBookings {

    public static int[] corpFlightBookings(int[][] bookings, int n) {
//        Arrays.sort(bookings, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
        int[] prefixSum=new int[n];

        for(int[] b: bookings){
            //Tại sao lại +:
            //  + Cộng value cho thằng nằm trong range
            prefixSum[b[0]-1]+=b[2];
            //Tại sao lại -:
            //  + Sau khi out khoảng thì - đi giá trị cũ (Đã add trong khoảng)
            //      + 1 khoảng chỉ được add 1 lần
            if(b[1]<n){
                prefixSum[b[1]]-=b[2];
            }
        }
        for(int i=1;i<n;i++){
            prefixSum[i]=prefixSum[i-1]+prefixSum[i];
        }
        return prefixSum;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n flights) that are labeled from (1 to n).
        //- You are given an array of flight bookings,
        //  where bookings[i] = [firsti, lasti, seatsi]
        // represents a booking for flights (first-i) through (last-i) (inclusive) with (seats-i) seats reserved for (each flight) in the range.
        //* Return (an array answer of length n), where answer[i] is the total number of seats reserved for (flight i).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= n <= 2 * 10^4
        //1 <= bookings.length <= 2 * 10^4
        //bookings[i].length == 3
        //1 <= firsti <= lasti <= n
        //1 <= seatsi <= 10^4
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
        //Output: [10,55,45,25,25]
        //Explanation:
        //Flight labels:        1   2   3   4   5
        //Booking 1 reserved:  10  10
        //Booking 2 reserved:      20  20
        //Booking 3 reserved:      25  25  25  25
        //Total seats:         10  55  45  25  25
        //Hence, answer = [10,55,45,25,25]
        //
        //Input: bookings = [[1,7,10],[2,5,20],[3,4,25]], n = 7
        //1,2,3,4,5,6,7
        //[1,7,10], s[1] += 10, s[7+1] -= 10
        //[5,8,20], s[5] += 20, s[8+1] -= 20
        //
        //s = [10, 0, 0, 0,20, 0, 0,-10,-20]
        //- CT:
        //s[i] = s[i-1] + s[i]
        //s = [10,10,10,10,30,30,30, 20]
        //
        //Tại sao lại +:
        //  + Cộng value cho thằng nằm trong range
        //==============
        //prefixSum[b[0]-1]+=b[2];
        //==============
        //Tại sao lại -:
        //  + Sau khi out khoảng thì - đi giá trị cũ (Đã add trong khoảng)
        //      + 1 khoảng chỉ được add 1 lần
        //==============
        //if(b[1]<n){
        //    prefixSum[b[1]]-=b[2];
        //}
        //==============
        //
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        int[] rs = corpFlightBookings(bookings, n);
        for (int i = 0; i < n; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //3134. Find the Median of the Uniqueness Array
        //1646. Get Maximum in Generated Array
        //929. Unique Email Addresses
    }
}
