package E1_daily;

public class E196_CuttingRibbons {

    public static int getNumberSegment(int[] ribbons, int value, int n){
        int count=0;

        for(int i=0;i<n;i++){
            count+=ribbons[i]/value;
        }
        return count;
    }

    public static int maxLength(int[] ribbons, int k) {
        int n=ribbons.length;
        int max=0;

        for(int i=0;i<n;i++){
            max=Math.max(max, ribbons[i]);
        }
        int low=1,high=max;
        int rs=0;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(k<=getNumberSegment(ribbons, mid, k)){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array ribbons,
        //  + where ribbons[i] represents the (length of the ith ribbon), and (an integer k).
        //- You may cut any of the ribbons into any number of segments of (positive integer lengths),
        //  + or (perform no cuts at all).
        //
        //- For example, if you have a ribbon of length 4, you can:
        //  + Keep the ribbon of length 4,
        //  + Cut it into one ribbon of length 3 and one ribbon of length 1,
        //  + Cut it into two ribbons of length 2,
        //  + Cut it into one ribbon of length 2 and two ribbons of length 1, or
        //  + Cut it into four ribbons of length 1.
        //* Your task is to determine (the maximum length of ribbon), x,
        //  + that allows you to cut (at least k ribbons), (each of length x).
        //- You can discard any leftover ribbon from the cuts.
        //  + If it is impossible to cut k ribbons of the same length, return 0.
        //
        //Example 1:
        //
        //Input: ribbons = [9,7,5], k = 3
        //Output: 5
        //Explanation:
        //- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
        //- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
        //- Keep the third ribbon as it is.
        //Now you have 3 ribbons of length 5.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= ribbons.length <= 10^5
        //1 <= ribbons[i] <= 10^5
        //1 <= k <= 10^9
        //  + Length<=10^5 ==> Time: O(n)
        //  + k <= 10^9 ==> Time: O(log(n))
        //
        //- Brainstorm
        //- Binary search
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(log(n))
        //
        int[] ribbons = {9,7,5};
        int k = 3;
        System.out.println(maxLength(ribbons, k));
    }
}
