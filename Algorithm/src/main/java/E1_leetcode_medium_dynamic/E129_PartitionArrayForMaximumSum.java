package E1_leetcode_medium_dynamic;

public class E129_PartitionArrayForMaximumSum {

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n=arr.length;

        int[][] maxSub=new int[n][n];
        for(int i=0;i<n;i++){
            maxSub[i][i]=arr[i];
        }
        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int l=i+j-1;
                maxSub[j][l] = Math.max(maxSub[j+1][l-1], Math.max(arr[j], arr[l]));
//                System.out.printf("%s %s, val: %s\n", j, l, maxSub[j][l]);
            }
        }
        int[] dp=new int[n+1];
        for(int i=0;i<n;i++){
            int curPos=i;
            int curRs=0;
            //i => dp[i+1]
            //0,1 ==>
            while(curPos>=0&&i-curPos<=k-1){
                curRs=Math.max(curRs, dp[curPos]+maxSub[curPos][i]*(i-curPos+1));
//                System.out.printf("dp[curPos]: %s, curPos: %s, i: %s, maxSub[curPos][i]: %s\n", dp[curPos], curPos, i, maxSub[curPos][i]);
                curPos--;
            }
//            System.out.println(curRs);
            dp[i+1]=curRs;
        }
//        for(int i=1;i<=n;i++){
//            System.out.printf("%s, ", dp[i]);
//        }
        return dp[n];
    }

    public static int maxSumAfterPartitioningOptimization(int[] arr, int k) {
        int n=arr.length;

        int[] dp=new int[n+1];
        for(int i=0;i<n;i++){
            int curPos=i;
            int curRs=0;
            //i => dp[i+1]
            //0,1 ==>
            int curMax=arr[i];
            while(curPos>=0&&i-curPos<=k-1){
                curMax=Math.max(curMax, arr[curPos]);
                curRs=Math.max(curRs, dp[curPos]+curMax*(i-curPos+1));
//                System.out.printf("dp[curPos]: %s, curPos: %s, i: %s, maxSub[curPos][i]: %s\n", dp[curPos], curPos, i, maxSub[curPos][i]);
                curPos--;
            }
//            System.out.println(curRs);
            dp[i+1]=curRs;
        }
//        for(int i=1;i<=n;i++){
//            System.out.printf("%s, ", dp[i]);
//        }
        return dp[n];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given an integer array arr, partition the array into (contiguous) subarrays of (length at most k).
        // After partitioning, each subarray has their values changed to become the maximum value of that subarray.
        //  - Chia 1 array thành nhiều phần với (max length = k)
        //  - Mỗi phần sau khi chia ==> change thành all max value của từng array
        //* Return the largest sum of the given array after partitioning.
        // Test cases are generated so that the answer fits in a 32-bit integer.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= arr.length <= 500
        //0 <= arr[i] <= 10^9
        //1 <= k <= arr.length
        //--> Số không quá lớn ==> O(n^2) được
        //
        //- Brainstorm
        //- Chia thành các subarray có chiều dài không vượt quá k ntn?
        //- Khi nào thì chia / không chia?
        //
        //Ex:
        //Input: arr = [1,15,7,9,2,5,10], k = 3
        //Output: 84
        //Explanation: arr becomes [15,15,15,9,10,10,10]
        //[1,15,7,9,2,5,10]
        //- Ta sẽ tìm cái sum mà sum >> old sum nhiều nhất
        //- Ta sẽ cần tìm max của từng subarray một:
        //  - maxSub[i][j] : value max của elements của subarry(i,j)
        //- Việc phân chia (k) có thể tính dựa trên việc phân chia (k-1) được không
        //  + Nếu k là chiều dài của subarray ==> Không
        //- Giả sử dp[i] : Là max sum của all khi xét đến index=i
        //  - i là vị trí ta đang xét tới
        //[1,15,7,9,2,5,10], k=3
        //+ i=0 : dp[0] = 1
        //+ i=1 :
        //  + Có thể ghép k-1 các element phía trước.
        //  dp[1] = max(dp[0]+15, 15+15) = 30
        //+ i=2 :
        //  + Có thể ghép k-1 các element phía trước.
        //  dp[2] = 15*3 = 45
        //
        //1.1, Optimization
        //- Bỏ cái đoạn xét max của subarray đi vì ta chỉ cần loop k phần tử trước đó + tiện xét max luôn
        //
        //1.2, Complexity
        //- Before optimization:
        //  - Time : O(n^2)
        //  - Space : O(n^2)
        //- After optimization:
        //  - Time : O(n*k)
        //  - Space : O(n)
        //
//        int[] arr= {1,15,7,9,2,5,10};
//        int k=3;
        int[] arr= {1,4,1,5,7,3,6,1,9,9,3};
        int k=4;
        System.out.println(maxSumAfterPartitioning(arr, k));
        System.out.println(maxSumAfterPartitioningOptimization(arr, k));
        //#Reference:
        //2098. Subsequence of Size K With the Largest Even Sum
        //2767. Partition String Into Minimum Beautiful Substrings
    }
}
