package contest;

public class E184_MaximumMultiplicationScore {

    public static long maxScore(int[] a, int[] b) {
        int n=b.length;
        long[] maxA=new long[n];
        long[] maxB=new long[n];
        long[] maxC=new long[n];
//        long[] maxD=new long[n];
        long max=Long.MIN_VALUE;

        //i: 0 -> n-4
        //Ex:
        //n=7
        //i: 0 -> 3
        //a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]
        //
        for(int i=0;i<n-3;i++){
            max=Math.max((long) a[0] *(long)b[i], max);
            maxA[i]=max;
//            System.out.printf("A: i:%s, val:%s\n", i, max);
        }
        max=Long.MIN_VALUE;
        for(int i=1;i<n-2;i++){
            max=Math.max((long) a[1]*(long)b[i]+maxA[i-1], max);
            maxB[i]=max;
//            System.out.printf("B: i:%s, val:%s\n", i, max);
        }
        max=Long.MIN_VALUE;
        for(int i=2;i<n-1;i++){
            max=Math.max((long) a[2] *(long)b[i]+maxB[i-1], max);
            maxC[i]=max;
//            System.out.printf("C: i:%s, val:%s\n", i, max);
        }
        max=Long.MIN_VALUE;
        long rs=Long.MIN_VALUE;
        for(int i=3;i<n;i++){
            max=Math.max((long) a[3] *(long)b[i]+maxC[i-1], max);
//            maxD[i]=max;
//            System.out.printf("D: i:%s, val:%s\n", i, max);
            rs=Math.max(rs, max);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (an integer array a of size 4) and another (integer array b of size) (at least 4).
        //- You need to choose 4 indices i0, i1, i2, and i3 from the array b such that i0 < i1 < i2 < i3.
        //- Your score will be equal to the value a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3].
        //* Return (the maximum score) you can achieve.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //a.length == 4
        //4 <= b.length <= 10^5
        //-10^5 <= a[i], b[i] <= 10^5
        //  + Length = 10^5 khá lớn ==> Time: O(n)/ O(n*k)
        //
        //** Brainstorm
        //Example 1:
        //Input: a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]
        //Output: 26
        //Explanation:
        //We can choose the indices 0, 1, 2, and 5. The score will be 3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26.
        //
        //- 4 index phụ thuộc nhau:
        //  + i0 < i1 <i2 <i3 (3 -> n-1)
        //  => 0 < 1 < 2 < 3
        //- Bài này làm greedy được không?
        //
        //- Brute force được không
        //- dp[0][0-> n-4]:
        //  + dp[0][j]: max score nếu xét index=i0 trong khoảng từ (0 -> j)
        //- dp[1][1-> n-3]
        //- dp[2][2-> n-2]
        //- dp[3][3-> n-1]
        //
        //- CT:
        int[] a = {3,2,5,6}, b = {2,-6,4,-5,-3,2,-7};
        System.out.println(maxScore(a, b));
    }
}
