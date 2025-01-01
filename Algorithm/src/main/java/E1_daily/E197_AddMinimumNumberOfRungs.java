package E1_daily;

public class E197_AddMinimumNumberOfRungs {

    public static int addRungs(int[] rungs, int dist) {
        int n=rungs.length;
        int rs=0;
        int prevHeight=0;

        for(int i=0;i<n;i++){
//            rs+=Math.ceil((float)(rungs[i]-prevHeight)/dist)-1;
            rs+=(rungs[i]-prevHeight-1)/dist;
            //precision number after main digits
            prevHeight=rungs[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a strictly increasing integer array) rungs that represents (the height of rungs) on a ladder.
        //- You are currently on the floor (at height 0), and you want to reach (the last rung).
        //- You are also (given an integer dist).
        //- You can only climb to (the next highest rung) if the distance between
        // where you are currently at (the floor or on a rung) and (the next rung) is (at most dist).
        //- You are able to insert rungs (at any positive integer height) if (a rung is not already there).
        //* Return (the minimum number of rungs) that must be added to the ladder in order for you to climb to (the last rung).
        //
        //- rungs (n) bậc thang
        //
        //Example 1:
        //
        //Input: rungs = [1,3,5,10], dist = 2
        //Output: 2
        //Explanation:
        //You currently cannot reach the last rung.
        //Add rungs at heights 7 and 8 to climb this ladder.
        //The ladder will now have rungs at [1,3,5,7,8,10].
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= rungs.length <= 10^5
        //1 <= rungs[i] <= 10^9
        //1 <= dist <= 10^9
        //rungs is strictly increasing.
        //  + length <= 10^5 ==> Time: O(n)
        /// + dist <= 10^9 ==> Time: O(log(n))
        //
        //- Brainstorm
        //Math.ceil((float)(rungs[i]-prevHeight)/dist)-1
        //= (rungs[i]-prevHeight-1)/dist
        //
//        int[] rungs = {3,6,8,10};
//        int dist = 3;
        int[] rungs = {1,3,5,10};
        int dist = 2;
        //
        System.out.println(addRungs(rungs, dist));
    }
}
