package interviews;

public class E129_LongestNiceSubarray {

//    public static int longestNiceSubarray(int[] nums) {
//        int start=-1;
//        int n=nums.length;
//        int rs=0;
//        int i;
//
//        for(i=1;i<n;i++){
//            if((nums[i]&nums[i-1])==1){
//                rs=Math.max(rs, i-start-1);
//                start=i-1;
//                System.out.printf("%s,", i);
//            }
//        }
//        rs=Math.max(rs, i-start-1);
//        System.out.println();
//
//        return rs;
//    }

    public static int longestNiceSubarray(int[] nums) {
        int n=nums.length;
        int curVal=0;
        int start=0;
        int maxLen=0;

        for(int i=0;i<n;i++){
            while ((curVal&nums[i])!=0){
                curVal=curVal^nums[start];
                start++;
            }
            if((curVal&nums[i])==0){
                maxLen=Math.max(maxLen, i-start+1);
            }
            curVal=curVal | nums[i];
        }
        return maxLen;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array nums consisting of positive integers).
        //We call (a subarray of nums nice) if (the bitwise AND of every pair of elements)
        // that are in different positions in the subarray (is equal to 0).
        //* Return (the length of the longest nice subarray).
        //- A subarray is a contiguous part of an array.
        //- Note that (subarrays of length 1) are always considered nice.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //==> Chỉ có thể xử lý O(n)
        //
        //- Brainstorm
        //a=11000
        //b=00110
        //c=10001
        //+ a&b=0
        //+ b&c=0
        //+ a&c!=0
        //==> not nice subarray
        //- Ta thấy
        //a&b==0
        //a&c==0
        //==> bit trong a không thuộc (b và c)
        //==> a&(b or c) == 0
        //
        //- Bỏ đi 1 số bằng cách
        //  + Bỏ đi bit của a liên quan
        //Ex:
        //10011 ==> bỏ đi 11
        //  + Bỏ đi 3
        //  10011 ^ 11 = 10000
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
//        int nums[]=new int[]{1,3,8,48,10};
//        int nums[]=new int[]{3,1,5,11,13};
        int nums[]=new int[]{1,3,8,48,10};
//        System.out.println(1&3);
        System.out.println(longestNiceSubarray(nums));
        //
        //#Reference:
        //904. Fruit Into Baskets
        //1839. Longest Substring Of All Vowels in Order
    }
}
