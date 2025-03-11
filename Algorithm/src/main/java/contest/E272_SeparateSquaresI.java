package contest;

import java.util.Arrays;
import java.util.Comparator;

public class E272_SeparateSquaresI {

    public static double separateSquaresWrong(int[][] squares) {
        int n= squares.length;
        double maxSum=0;
        Arrays.sort(squares, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        double maxY=0;

        for(int i=0;i<n;i++){
            maxSum+=squares[i][2]*squares[i][2];
            maxY=Math.max(maxY, squares[i][1]+squares[i][2]);
        }
        double low=0, high=maxY;
        double diff=Math.pow(10, -5);
        double rs=-1;
        while(Math.abs(high-low)>=diff){
            double mid=low+(high-low)/2;
            double curArea=0;
            for(int i=0;i<n;i++){
//                if(Math.abs(squares[i][1]-mid)>diff){
//                    break;
//                }
                if(squares[i][1]-mid>diff){
                    break;
                }
                curArea+=(Math.min(mid-squares[i][1], squares[i][2]))*squares[i][2];
            }
            System.out.printf("Outside: low: %s, high: %s, Mid:%s, area: %s, diff: %s\n", low, high, mid, curArea, maxSum-2*curArea);
            if(maxSum-curArea<=curArea){
//                if(maxSum-2*curArea>=0&&Math.abs(maxSum-2*curArea)<=diff){
//                    rs=mid;
//                }
                System.out.printf("low: %s, high: %s, Mid:%s, area: %s, diff: %s\n", low, high, mid, curArea, maxSum-2*curArea);
                System.out.println(Math.abs(maxSum-2*curArea)-diff);
//                System.out.println(mid);
                high=mid-diff;
            }else{
                low=mid+diff;
            }
//            System.out.println(Math.abs(maxSum-2*curArea)-diff);
            if(Math.abs(maxSum-2*curArea)<=diff){
                rs=mid;
            }
            System.out.printf("Diff(High-low): %s\n", high-low);
        }
        return Math.ceil(rs*100000)/100000;
    }

    public static double diffArea(double line, int[][] squares){
        int n=squares.length;
        double aboveArea=0d, belowArea=0d;
        for (int i = 0; i < n; i++) {
//            int x = squares[i][0];
            int y = squares[i][1];
            int l = squares[i][2];
            double curArea=(double)l*l;
            if(line<=y){
                aboveArea+=curArea;
            }else if(line>=y+l){
                belowArea+=curArea;
            }else{
                double aboveHeight = (y+l)-line;
                double belowHeight = line-y;
                aboveArea+=l*aboveHeight;
                belowArea+=l*belowHeight;
            }
        }
        return aboveArea-belowArea;
    }

    public static double separateSquares(int[][] squares) {
        double low=0, high=2*1e9;
        for(int i=0;i<60;i++) {
            double mid = low + (high - low) / 2;
            double diff = diffArea(mid, squares);
            if (diff > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array squares.
        //- Each squares[i] = [xi, yi, li] represents the coordinates of the bottom-left point
        // and the side length of a square parallel to the x-axis.
        //- Find the minimum y-coordinate value of a horizontal line such that the total area of the squares above
        // the line equals the total area of the squares below the line.
        //* Answers within 10-5 of the actual answer will be accepted.
        //* Note: Squares may overlap. Overlapping areas should be counted multiple times.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= squares.length <= 5 * 10^4
        //squares[i] = [xi, yi, li]
        //squares[i].length == 3
        //0 <= xi, yi <= 10^9
        //1 <= li <= 10^9
        //The total area of all the squares will not exceed 10^12.
        //  + Time: O(n*log(n))
        //  + val <= 10^9 ==> Time: O(log(N))
        //
        //- Brainstorm
        //- Binary search
        //- For y, we need to find the total area of the squares that it account for
        //
        //Ex:
        //- y = 3
        //- Sweep line
        //- For each time we check the current total of areas:
        //Ex:
        //Y=4
        //- Check for each square:
        //  + Calculate for the squares with min(y) is greater than Y
        //  + sum+= (Y-y)*size
        //- Overlaping:
        //  ==> Việc tăng 0.00005 cho (low, high) vẫn đúng vì đơn giản tăng nhiều or ít hơn thôi
        //
        //- This solution above is (invalid) because, We can not capture the result at all
        //
        //- The tolerance means (Sai số):
        //Let's start with learning what does it mean bytolerance, i.e., Answers within 10⁻⁵ of the actual answer will be accepted.
        //
        //- Loose Tolerance (10): Estimating the height of a building.
        //  + Scenario: If the actual height is 100 meters, any answer between 90 and 110 meters is acceptable.
        //- Moderate Tolerance (0.1): Measuring the temperature of a room.
        //  + Scenario: If the true temperature is 20.0°C, an answer between 19.9°C and 20.1°C is acceptable.
        //- Ultra-Precise Tolerance (0.00001): Measuring the thickness of a human hair.
        //  + Scenario: If the actual thickness is 0.10000 mm, your answer must be between 0.09999 mm and 0.10001 mm.
        //* Hence, The phrase "Answers within 10⁻⁵ of the actual answer will be accepted" means your answer must be
        // within ±0.00001 of (the true answer).
        //So, if the problem specifies a tolerance of 10⁻⁵, your answer is considered correct if:abs(Your Answer − Actual Answer) ≤ 0.00001
        //
        //* Binary search on the real number:
        //- Nếu làm cách bình thường thì không được vì với real number:
        //  + Không thể (+/-)1 được ==> Giữa 2 số [0,1] có an infinite set of numbers
        //
        //* Instead, we update:
        //- If the condition suggests the target is higher, set
        //  + lo = mid.
        //- If lower, set
        //  + hi = mid.
        //
        //* How can we stop the loop?
        //- Method1:
        //  + high-low>x
        //      + x=10^-5
        //      * mid sẽ sai số là (10^-5)/2 = (5*10^-6)
        //- Method2:
        // + Instead of checking the interval width directly, you can perform (a fixed number of iterations) say 60.
        // Each iteration halves (the search interval), guaranteeing that after (a sufficient number of iterations),
        // the interval is extremely narrow.
        //
        //- Assume an initial interval of widthW.
        //- After each iteration, the width is halved. After 60 iterations, the width becomes:W/2^60
        //
        //Example:
        //If W=1 intreval [0, 1] then 1/2^60
        // is somewhere around 10⁻²⁰ which is much smaller than 10⁻⁵.
        //So, even if W is large 2×10^9 in our case, then (2×10^9)/(2^60)
        // is still around 10⁻¹⁰ which is much smaller than the given tolerance.
        //- Thus, after 60 iterations, the midpoint(lo+hi)/2 is extremely precise,
        // and the error is guaranteed to be within (the required tolerance).
        //- Use logarithms to find out (the exact number of iterations) required,
        // it will come out to be around 50 in this problem.
        // This step is not required tho, an estimate works just fine.
        //
        //* Explaination:
        //for (int i = 0; i < 60; i++) {
        //  + Với 60 times ==> Chia low gần high nhất có thể đến (10^-10) < 10^-5
        //  ==> Ok r
        //
        //#Code sample:
        //============================
        //- Template:
        //double lo = initial_lo; // Set initial lower bound
        //double hi = initial_hi; // Set initial upper bound
        //
        //for (int i = 0; i < 60; i++) {
        //    double mid = (lo + hi) / 2.0;
        //    if (condition(mid)) {
        //        lo = mid; // or adjust hi based on your condition
        //    } else {
        //        hi = mid;
        //    }
        //}
        //double answer = (lo + hi) / 2.0;
        //============================
        //- double curArea=(double)l*l;
        //  + ==> Exceed range of the doubel number
        //
        //* Question 2:In my code, I just returnedhiat the end of the loop. Is that acceptable?
        //- Answer:Yes, it is acceptable to returnhibecause by the time the binary search loop terminates,
        // the interval[lo,hi] is extremely narrow — within the specified tolerance.
        //- At this point,lo,hi, and even the mid point are nearly identical.
        // Since the difference between them is negligible compared to the required precision,
        // return ing hi guarantees that the result is within the allowed error margin.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(60*log(10^9))
        //
//        int[][] squares = {{0,0,2},{1,1,1}};
        int[][] squares = {{2,5,3},{8,12,4}};
        //Output: 10.78572
        //Expected: 12.87500
        System.out.println(separateSquares(squares));
//        System.out.println(separateSquaresWrong(squares));
    }
}
