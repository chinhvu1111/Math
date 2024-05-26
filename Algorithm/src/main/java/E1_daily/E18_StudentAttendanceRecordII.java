package E1_daily;

import java.util.Arrays;

public class E18_StudentAttendanceRecordII {

    public static int checkRecordBottomUpWrongIdea(int n) {
        int[][] dpLate =new int[n][3];
        int[][] dpAbsent =new int[n][2];
        int[] dpPresent =new int[n];
        //L
        //Late 0 lần liên tiếp
        //  + A/P
        dpLate[0][0]=2;
        //Late 1 lần liên tiếp
        dpLate[0][1]=1;
        //absent 0 lần
        //  + L/P
        dpAbsent[0][0]=1;
        //A
        //absent 1 lần
        dpAbsent[0][0]=1;
        //P
        dpPresent[0]=1;

        for(int i=1;i<n;i++){
            //L
            dpLate[i][1] = dpLate[i-1][0];
            dpLate[i][2] = dpLate[i-1][1];
            //A
            dpAbsent[i][1] = dpAbsent[i-1][0];
            //P
            dpPresent[i] = dpPresent[i-1] + dpLate[i-1][2] + dpLate[i-1][1] +  dpAbsent[i-1][1] + dpAbsent[i-1][0];
        }
        return dpPresent[n-1] + dpLate[n-1][2] + dpLate[n-1][1] + dpAbsent[n-1][1] + dpAbsent[n-1][0];
    }

    public static int[][][] memo;
    public static int MOD=1_000_000_007;
    public static int solution(int totalAbsent, int consecutiveLate, int index, int n){
        int curCase=0;

        if(totalAbsent==2||consecutiveLate==3){
            return 0;
        }
        if(index==n){
            return 1;
        }
        if(memo[index][totalAbsent][consecutiveLate]!=-1){
            return memo[index][totalAbsent][consecutiveLate];
        }
        //Fill A
        curCase+=solution(totalAbsent+1, 0, index+1, n)%MOD;
        //Fill L
        curCase=(curCase+solution(totalAbsent, consecutiveLate+1, index+1, n))%MOD;
        //Fill P
        curCase=(curCase+solution(totalAbsent, 0, index+1, n))%MOD;
        return memo[index][totalAbsent][consecutiveLate]=curCase;
    }

    public static int checkRecordTopDown(int n) {
        memo=new int[n][2][3];
        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        return solution(0, 0, 0, n);
    }

    public static int checkRecordBottomUp(int n) {
        int[][][] dp=new int[n][2][3];

//        for(int i=0;i<n;i++){
//            for(int j=0;j<2;j++){
//                Arrays.fill(dp[i][j], -1);
//            }
//        }
        //dp[i][j][k]
        //  + Là số lượng cases khi absent (j) và late liên tiếp (k) lần
        //- Fill A:
        //- Fill L:
        //- Fill P:
        //* CT:
        //
        //Fill P
        dp[0][0][0] = 1;
        //Fill A
//        dp[0][1][0] += 1;
        dp[0][1][0] += 1;
        //Fill L
        dp[0][0][1] += 1;
        for(int i=1;i<n;i++){
            for(int totalAbsent=0;totalAbsent<2;totalAbsent++){
                for(int consecutiveLate=0;consecutiveLate<3;consecutiveLate++){
                    //Fill A
                    //- Điền A thì đằng trước có thể:
                    //  + total absent = cur absent - 1
                    //  + late thì tuỳ ý
                    if(totalAbsent>=1){
                        dp[i][totalAbsent][0]=(dp[i][totalAbsent][0] + dp[i-1][totalAbsent-1][consecutiveLate])%MOD;
                    }
                    //Fill L
                    //- Điền L thì đằng trước có thể:
                    //  + prev late = cur late - 1
                    //  + Keep absent
                    if(consecutiveLate>=1){
                        dp[i][totalAbsent][consecutiveLate]=(dp[i][totalAbsent][consecutiveLate] + dp[i-1][totalAbsent][consecutiveLate-1])%MOD;
                    }
                    //Fill P
                    //- Điền P thì đằng trước có thể:
                    //  + Sao cũng được.
                    dp[i][totalAbsent][0] = (dp[i][totalAbsent][0] + dp[i - 1][totalAbsent][consecutiveLate])%MOD;
                }
            }
        }
        int rs=0;

        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                rs=(rs+dp[n-1][i][j])%MOD;
            }
        }
        return rs;
    }

    public static int checkRecordBottomUpOptimizization(int n) {
        int[][] dp=new int[2][3];
        int[][] prevDp=new int[2][3];

//        for(int i=0;i<n;i++){
//            for(int j=0;j<2;j++){
//                Arrays.fill(dp[i][j], -1);
//            }
//        }
        //dp[i][j][k]
        //  + Là số lượng cases khi absent (j) và late liên tiếp (k) lần
        //- Fill A:
        //- Fill L:
        //- Fill P:
        //* CT:
        //
        //Fill P
        prevDp[0][0] = 1;
        //Fill A
//        dp[0][1][0] += 1;
        prevDp[1][0] += 1;
        //Fill L
        prevDp[0][1] += 1;
        if(n==1){
            dp[0][0] = 1;
            //Fill A
//        dp[0][1][0] += 1;
            dp[1][0] += 1;
            //Fill L
            dp[0][1] += 1;
        }
        for(int i=1;i<n;i++){
            for(int totalAbsent=0;totalAbsent<2;totalAbsent++){
                for(int consecutiveLate=0;consecutiveLate<3;consecutiveLate++){
                    //Fill A
                    //- Điền A thì đằng trước có thể:
                    //  + total absent = cur absent - 1
                    //  + late thì tuỳ ý
                    if(totalAbsent>=1){
                        dp[totalAbsent][0]=(dp[totalAbsent][0] + prevDp[totalAbsent-1][consecutiveLate])%MOD;
                    }
                    //Fill L
                    //- Điền L thì đằng trước có thể:
                    //  + prev late = cur late - 1
                    //  + Keep absent
                    if(consecutiveLate>=1){
                        dp[totalAbsent][consecutiveLate]=(dp[totalAbsent][consecutiveLate] + prevDp[totalAbsent][consecutiveLate-1])%MOD;
                    }
                    //Fill P
                    //- Điền P thì đằng trước có thể:
                    //  + Sao cũng được.
                    dp[totalAbsent][0] = (dp[totalAbsent][0] + prevDp[totalAbsent][consecutiveLate])%MOD;
                }
            }
            for(int totalAbsent=0;totalAbsent<2;totalAbsent++){
                for(int consecutiveLate=0;consecutiveLate<3;consecutiveLate++){
                    if(totalAbsent>=1){
                        prevDp[totalAbsent][0]=dp[totalAbsent][0];
                    }
                    //Fill L
                    //- Điền L thì đằng trước có thể:
                    //  + prev late = cur late - 1
                    //  + Keep absent
                    if(consecutiveLate>=1){
                        dp[totalAbsent][consecutiveLate]=prevDp[totalAbsent][consecutiveLate];
                    }
                    //Fill P
                    //- Điền P thì đằng trước có thể:
                    //  + Sao cũng được.
                    dp[totalAbsent][0] = prevDp[totalAbsent][consecutiveLate];
                }
            }
        }
        int rs=0;

        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                rs=(rs+dp[i][j])%MOD;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (An attendance record) for a student can be represented as a string where (each character) signifies whether the student was
        // absent, late, or present on that day.
        // The record only contains the following three characters:
        //  + 'A': Absent.
        //  + 'L': Late.
        //  + 'P': Present.
        //- Any student is eligible for (an attendance award) if they meet both of the following criteria:
        //  + The student was absent ('A') for strictly fewer than (2 days total).
        //  + The student was never late ('L') for (3 or more consecutive days).
        //Given an integer n,
        //* Return (the number of possible attendance records) of length (n) that make a student eligible for (an attendance award).
        // The answer may be very large, so return it modulo 10^9 + 7.
        //
        //Example 1:
        //Input: n = 2
        //Output: 8
        //Explanation: There are 8 records with length 2 that are eligible for an award:
        //"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        //Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= n <= 10^5
        //==> N khá lớn : O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //- Bài này dạng permutation
        //Example 1:
        //Input: n = 2
        //Output: 8
        //Explanation: There are 8 records with length 2 that are eligible for an award:
        //"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        //Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
        //
        //- Idea nghĩ đến đầu tiên là backtracking?
        //  + List all cases có khả thi không?
        //      + Backtracking rất khó để có thể xử lý trong O(n)
        //
        //- Hint:
        //  + Dynamic programming
        //
        // The record only contains the following three characters:
        //  + 'A': Absent : 0
        //  + 'L': Late : 1
        //  + 'P': Present : 2
        //
        //- Rules:
        //  + Không được late 3 days liên tiếp
        //  + Không được absent >=2 days
        //      + Có thể là 0/1
        //Expression:
        //+ L,L,X,L,X,L
        //--> Ta sẽ thêm 1 direction nữa cho:
        //  + Late
        //  + Absent
        //- dpLate[i][3]
        //  + dpLate[i][j]: là (số lượng case với late liên tiếp = j)
        //      + j=0 : tức là không late
        //- dpAbsent[i][3]
        //  + dpAbsent[i][j]: là số lượng case với (absent = j) khi xét đến (i)
        //
        //- Late 3 days liên tiếp biểu diễn ntn?
        //  + ==> Thêm chiều
        //- Do present chi đứng 1 mình nên ta có thể bỏ qua dp cho nó luôn cũng được
        //
        //- dp[i][j] : lưu số lượng case khi ngày thứ (i) có trạng thái (j)
        //  ==> Do Late, absent quy luật khác nhau
        //  ==> dp[i][j] sẽ thêm 1 chiều nữa
        //- dpLate
        //- dpAbsent
        //* CT:
        //  - dpLate[i][j]
        //      + dpLate[i][0] = dpLate[i-1][0] + dpLate[i-1][1] + dpLate[i-1][2]
        //          + A/P
        //      + dpLate[i][1] = dpLate[i-1][0] :
        //          + L
        //      + dpLate[i][2] = dpLate[i-1][1] :
        //          + L
        //  - dpAbsent[i][j]
        //      + dpAbsent[i][0] = dpAbsent[i-1][0]
        //          + L/P
        //          + Cái (hiện tại !=absent) + (số lần absent==0)
        //      + dpAbsent[i][1] = dpAbsent[i-1][0] + dpAbsent[i-1][1]
        //          + A/L/P
        //          + Cái (số lần absent==1) : Có thể hiện tại absent / trước đó absent
        //==> Vấn đề là nó có thể giao nhau?
        //
        //- Nếu fix (i)==L/P/A thì sao?
        //==> Bỏ đi các cases lúc nãy:
        //* CT:
        //  - dpLate[i][j]
        //      + dpLate[i][1] = dpLate[i-1][0] :
        //          + L
        //      + dpLate[i][2] = dpLate[i-1][1] :
        //          + L
        //  - dpAbsent[i][j]
        //      + dpAbsent[i][1] = dpAbsent[i-1][0]
        //          + A
        //  - dpPresent[i]:
        //      + dpPresent[i] = dpPresent[i-1] + dpLate[i][2] + dpLate[i][1] +  dpAbsent[i][1] + dpAbsent[i][0]
        //
        //- Bottom up khá khoai
        //==> Top down thử xem ntn
        //
        //- memo[index][totalAbsent][consecutiveLate]
        //  ==> Lưu lại vết liên quan
        //
        //* Bottom up map qua
        //* Chú ý:
        //  - Tính chất consecution:
        //      + Nếu điền A/P ==> reset late = 0
        //- Init:
        //+ Fill P:
        //  dp[0][0][0] = 1;
        //+ Fill A:
        //  dp[0][1][0] += 1;
        //+ Fill L:
        //  dp[0][0][1] += 1;
        //
        //- Suy luận như sau:
        //Fill A
        //- Điền A + (RESET LATE) thì đằng trước có thể:
        //  + total absent = cur absent - 1
        //  + late thì tuỳ ý
//        if(totalAbsent>=1){
//            dp[i][totalAbsent][0]=(dp[i][totalAbsent][0] + dp[i-1][totalAbsent-1][consecutiveLate])%MOD;
//        }
        //Fill L
        //- Điền L + (Tăng LATE) thì đằng trước có thể:
        //  + prev late = cur late - 1
        //  + Keep absent
//        if(consecutiveLate>=1){
//            dp[i][totalAbsent][consecutiveLate]=(dp[i][totalAbsent][consecutiveLate] + dp[i-1][totalAbsent][consecutiveLate-1])%MOD;
//        }
        //Fill P
        //- Điền P + (RESET LATE) thì đằng trước có thể:
        //  + Sao cũng được.
//        dp[i][totalAbsent][0] = (dp[i][totalAbsent][0] + dp[i - 1][totalAbsent][consecutiveLate])%MOD;
        //
        //1.1, Optimization
        //- Ta có thể tối ưu space từ O(n) --> O(constant)
        //  + Bằng cách lưu prev array result.
        //
        //1.2, Complexity
        //- Space: O(n*2*3 + n) = O(n)
        //- Time : O(n*2*3) = O(n)
        //
        int n=2;
        System.out.println(checkRecordBottomUpWrongIdea(n));
        System.out.println(checkRecordTopDown(n));
        System.out.println(checkRecordBottomUp(n));
        System.out.println(checkRecordBottomUpOptimizization(n));
    }
}
