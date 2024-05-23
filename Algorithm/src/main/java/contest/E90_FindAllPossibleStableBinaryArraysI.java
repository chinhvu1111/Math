package contest;

public class E90_FindAllPossibleStableBinaryArraysI {

    public static int numberOfStableArrays(int zero, int one, int limit) {
        int[][] dp=new int[one+1][one+zero+1];
        int maxLen=one+zero;
        System.out.println(maxLen);
        dp[1][1]=1;
        dp[0][1]=1;

        for(int i=1;i<=maxLen;i++){
            //1111110
            //
//            int numCase=0;
            int maxOne=Math.min(i-1, one);
            for(int j=maxOne;j>=1;j--){
                int curNumZero=i-j;

                //> limit ==> Phải có 0 và 1
                // trong interval len = limit+1 có thể có:
                //  + 1 -> limit chữ số 1
                for(int l=1;l<=limit;l++){
                    if(j>=l){
                        dp[j][i]+=dp[j-l][i-l];
                    }
                    if(curNumZero>=l){
                        dp[j][i]+=dp[l][i-l];
                    }
                }
            }
        }
        for(int i=1;i<=maxLen;i++){
            for(int j=0;j<=Math.min(one, i);j++){
                System.out.printf("one=%s, len=%s, %s\n", j, i, dp[j][i]);
            }
        }
        return dp[one][one+zero];
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given 3 positive integers (zero), (one), and (limit).
        //- A binary array arr is called stable if:
        //  + The number of occurrences of 0 in arr is exactly zero.
        //  + The number of occurrences of 1 in arr is exactly one.
        //  + Each subarray of arr with a size greater than limit must contain both 0 and 1.
        //* Return the total number of stable binary arrays.
        //  - Đếm số lượng stable binary arrays:
        //   + subarray với size>limit thoả mãn đk trên.
        //- Since the answer may be very large, return it modulo 10^9 + 7.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= zero, one, limit <= 200
        //==> Không quá lớn
        //
        //- Brainstorm
        //- Với dạng bài count số lượng --> Dynamio programming
        //Ex
        //Input: zero = 2, one = 1, limit = 2
        //Output: 2
        //Explanation:
        //The two possible stable binary arrays are [1,0] and [0,1], as both arrays have a single 0 and a single 1, and no subarray has a length greater than 2.
        // [1,0,1]
        //- Each subarray of arr with a size greater than limit must contain both 0 and 1.
        //==> Tức là cứ (limit+1) length thì sẽ xuất hiện (0,1)
        //  + Sum của subarry với length(limit+1) = (1 -> limit)
        //   + Loại bỏ 2 cases:
        //      + Sum = 0 : Toàn 0
        //      + Sum = limit + 1 : Toàn 1
        //- Do exact zero, one
        //==> max length = zero + one
        //
        //- Count/ Find số lượng binary array với:
        //  + count(0) = zero
        //  + count(1) = one
        //  ==> ntn?
        //  ==> Trong lúc đó lấy prefix sum luôn được không
        //
        //- Giả sử không tính đến việc numZero và numOne
        //  + Ta chỉ xét việc limit sum ==> Xét đến cuối mới count 2 số trên
        //- dp[i][j][pos]:
        //- Length cũng thể hiện suy được numOne -> numZero
        //  + Số case mà có thể đạt được [i][j]
        //- So sánh giữa length=x và length = x-limit ntn?
        //  + Prefix sum
        //  + [x][y] ==> Trừ đi limit chính là số lượng số 1 và số lượng số 0 tương ứng
        //  + dp[x][y]+=dp[x-i][y-limit+i]
        //
        //- Init
        //- dp[0][0]=0
        //- dp[1][0]=1
        //- dp[0][1]=1
        //- dp[1][1]=1
        //
        //Ex:
        //zero = 3, one = 3, limit = 2
        // [0,0,1,0,1,1],
        // [0,0,1,1,0,1],
        // [0,1,0,0,1,1],
        // [0,1,0,1,0,1],
        // [0,1,0,1,1,0],
        // [0,1,1,0,0,1],
        // [0,1,1,0,1,0],
        // [1,0,0,1,0,1],
        // [1,0,0,1,1,0],
        // [1,0,1,0,0,1],
        // [1,0,1,0,1,0],
        // [1,0,1,1,0,0],
        // [1,1,0,0,1,0]
        // [1,1,0,1,0,0]
        int zero = 3, one = 3, limit = 2;
        System.out.println(numberOfStableArrays(zero, one, limit));
    }
}
