package E1_leetcode_medium_dynamic;

public class E108_UncrossedLines {

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int[][] dp=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static int maxUncrossedLinesOptimization(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int[]dp=new int[m+1];
        int[] prevDp=new int[m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[j]=prevDp[j-1]+1;
                }else{
                    dp[j]=Math.max(dp[j-1], prevDp[j]);
                }
            }
            for(int j=1;j<=m;j++){
                prevDp[j]=dp[j];
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given nums1, nums2
        //- Ta có thể vẽ line giữa 2 số nếu nums1[i]==nums2[j]
        //* Return số lượng lines nhiều nhất mà ta có thể vẽ
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= nums1.length, nums2.length <= 500
        //1 <= nums1[i], nums2[j] <= 2000
        //
        //- Brainstorm
        //- Nums1 và nums2 có thể khác length nhau
        //
        //Ex:
        //Input: nums1 = [1,4,2], nums2 = [1,2,4]
        //Output: 2
        //Explanation: We can draw 2 uncrossed lines as in the diagram.
        //We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
        // 1    4   2
        // |      \
        // 1    2   4
        //- dp[i][j] : chứa số lines của lớn nhất trong khi xét đến (index=i với nums1 và index=j với nums2)
        //- Nếu nums1[i]==nums2[j] thì
        //+ dp[i][j]= dp[i-1][j-1] + 1
        //+ dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        //
        //1.2, Optimization
        //- Ta có thể optimize thêm space:
        //Ex:
        // 1    4   2 ==> Nums1 --> Ta sẽ xét dần dần bằng cách loop (each element in nums1) ==> map sang ==> nums2
        // |      \
        // 1    2   4 ==> Ta chỉ cần lưu 1 array dp cho 1 nums2
        //Ex:
        //0   (1)   2   3
        //
        //0   1   2   (3) => cur[]
        //prev(index=0) = 0,1,2,3
        //
        //* Ta thấy rằng:
        //- Ta đang xét đến index=i
        //- Ta sẽ chỉ dùng lại array của index=(i-1)
        //- Formula:
        //+ [i]==[j] : cur[i]=prev[i-1]+1
        //+ [i]!=[j] : cur[i]=max(cur[i-1], prev[i])
        //
        //
        //1.3, Complexity
        //- Space :
        //  + Pre-optimization : O(n*m)
        //  + Optimization : O(min(n,m))
        //- Time : O(n*m)
        //#Reference:
        //1418. Display Table of Food Orders in a Restaurant
        //1031. Maximum Sum of Two Non-Overlapping Subarrays
        //1220. Count Vowels Permutation
        int[] nums1 = {1,4,2}, nums2 = {1,2,4};
        System.out.println(maxUncrossedLines(nums1, nums2));
        System.out.println(maxUncrossedLinesOptimization(nums1, nums2));
    }
}
