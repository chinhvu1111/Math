package E1_leetcode_medium_dynamic;

public class E152_MinimumTimeToMakeRopeColorful {

    public static int minCost(String colors, int[] neededTime) {
        int n= neededTime.length;
        int index=0;
        char prevChar='-';
        int rs=0;

        while(index<n){
            int maxNeed=neededTime[index];
            int sum=neededTime[index];
            prevChar=colors.charAt(index);

            while(index+1<n&&prevChar==colors.charAt(index+1)){
                maxNeed=Math.max(maxNeed, neededTime[index+1]);
                sum+=neededTime[index+1];
                index++;
            }
            if(sum!=maxNeed){
                rs+=sum-maxNeed;
            }
            index++;
        }
        return rs;
    }

    public static int minCostOptimization(String colors, int[] neededTime) {
        int n= neededTime.length;
        int time=0;

        for(int i=1;i<n;i++){
            if(colors.charAt(i)==colors.charAt(i-1)){
                time+= Math.min(neededTime[i], neededTime[i-1]);
                neededTime[i]=Math.max(neededTime[i], neededTime[i-1]);
            }
        }
        return time;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Alice has n balloons arranged on a rope.
        // You are given a 0-indexed string colors where colors[i] is the color of the (ith balloon).
        //- Alice wants (the rope to be colorful).
        // She does not want (two consecutive balloons) to be of (the same color), so she asks Bob for help.
        // Bob can (remove some balloons) from the rope to make it colorful.
        // You are given a 0-indexed integer array neededTime where (neededTime[i]) is the time (in seconds)
        // that Bob needs to remove the (ith balloon) from the rope.
        //* Return (the minimum time) Bob needs to make (the rope colorful).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == colors.length == neededTime.length
        //1 <= n <= 10^5
        //1 <= neededTime[i] <= 10^4
        //colors contains only lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //Input: colors = "abaac", neededTime = [1,2,3,4,5]
        //Output: 3
        //Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
        //Bob can remove the blue balloon at index 2. This takes 3 seconds.
        //There are no longer two consecutive balloons of the same color. Total time = 3.
        //- Cách 1 dùng for khá dễ dàng
        //- Nếu dùng dynamic programming thì sao?
        //==> Chỉ khác chỗ gán neededtime[i]= max(neededtime[i-1 --> same] đằng trước.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
//        String colors = "aabaa";
        String colors = "abaac";
//        int[] neededTime = {1,2,3,4,1};
        int[] neededTime = {1,2,3,4,5};
        System.out.println(minCost(colors, neededTime));
        System.out.println(minCostOptimization(colors, neededTime));
        //#Reference:
        //2571. Minimum Operations to Reduce an Integer to 0
        //1792. Maximum Average Pass Ratio
        //2870. Minimum Number of Operations to Make Array Empty
    }
}
