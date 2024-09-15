package E1_leetcode_medium_dynamic;

public class E173_DiceRollSimulation {

    static int MOD =1_000_000_007;

    public static int dieSimulator(int n, int[] rollMax) {
        //Time: O(n*7)
        //Space: O(n*7)
        long[][] dp=new long[n+1][7];
        long[] sum=new long[n+1];

        for(int i=1;i<=6;i++){
            dp[1][i]=1;
//            dp[0][i]=1;
            sum[1]+=1;
        }
        sum[0]=1;
        //Time: O(n*6*15)
        for(int i=2;i<=n;i++){
            long curSum=0;
            for(int j=1;j<=6;j++){
                long curRs=0;
                //6-1=5
                //- Thằng hiện tại ==6 ==> (trước đó !=6)
                //- Thằng hiện tại ==6 ==> (trước đó ==6)
                //  + Trước của trước !=6
                //curRs=5
//                curRs+=sum[i-1]-dp[i-1][j];
                //1,2
                //- Tức là:
                //  + Cái này sẽ xét khi có ít nhất 1 thằng (value==j) đằng trước
                //h=2,h=1(Đằng tước =j),h
                //rollMax[j]: ký tự liên tiếp
                //0,1,2
                int maxInterval=Math.min(rollMax[j-1]-1, i-1);
                for(int h=0;h<=maxInterval;h++){
                    //0,[1],2(i)
                    //  |-----|
                    //   roll
//                    if(i<=h){
//                        break;
//                    }
                    //3,3:
                    //[{1->6}-{3}],3
                    //a,4: 6
                    //  + a!=4
                    //  + a==4
                    //==> Cần tính theo sum[i=0] ==> dp[i][any]
                    //  + Để ra kết quả cho việc except (j) của (i==0) ra bằng =1 với (a==4) thì:
                    //  + Init:
                    //      + sum[i=0] = 1
                    //      + dp[0][any] = 0
                    curRs=(curRs+(sum[i-h-1]-dp[i-h-1][j]+ MOD))% MOD;
                }
                dp[i][j]=curRs;
//                System.out.printf("i: %s, j:%s, val: %s\n", i, j, dp[i][j]);
                curSum=(curSum+curRs)% MOD;
            }
            sum[i]=curSum;
        }
        return (int) sum[n];
    }

    public static int dieSimulatorOptimization(int n, int[] rollMax) {
        //Time: O(n*7)
        //Space: O(n*7)
        long[][] dp=new long[n+1][7];
        long[] sum=new long[n+1];

        for(int i=1;i<=6;i++){
            dp[1][i]=1;
//            dp[0][i]=1;
            sum[1]+=1;
        }
        sum[0]=1;
        //Time: O(n*6*15)
        for(int i=2;i<=n;i++){
            long curSum=0;
            for(int j=1;j<=6;j++){
                long curRs=0;
                //6-1=5
                //- Thằng hiện tại ==6 ==> (trước đó !=6)
                //- Thằng hiện tại ==6 ==> (trước đó ==6)
                //  + Trước của trước !=6
                //curRs=5
//                curRs+=sum[i-1]-dp[i-1][j];
                //1,2
                //- Tức là:
                //  + Cái này sẽ xét khi có ít nhất 1 thằng (value==j) đằng trước
                //h=2,h=1(Đằng tước =j),h
                //rollMax[j]: ký tự liên tiếp
                //0,1,2
                int maxInterval=Math.min(rollMax[j-1]-1, i-1);
                for(int h=0;h<=maxInterval;h++){
                    //0,[1],2(i)
                    //  |-----|
                    //   roll
//                    if(i<=h){
//                        break;
//                    }
                    //3,3:
                    //[{1->6}-{3}],3
                    //a,4: 6
                    //  + a!=4
                    //  + a==4
                    //==> Cần tính theo sum[i=0] ==> dp[i][any]
                    //  + Để ra kết quả cho việc except (j) của (i==0) ra bằng =1 với (a==4) thì:
                    //  + Init:
                    //      + sum[i=0] = 1
                    //      + dp[0][any] = 0
                    curRs=(curRs+(sum[i-h-1]-dp[i-h-1][j]+ MOD))% MOD;
                }
                dp[i][j]=curRs;
//                System.out.printf("i: %s, j:%s, val: %s\n", i, j, dp[i][j]);
                curSum=(curSum+curRs)% MOD;
            }
            sum[i]=curSum;
        }
        return (int) sum[n];
    }

    public static int dieSimulatorRefer(int n, int[] rollMax) {

        /*
        indices are (swap, endswith, Excess consecutive)
        */
        long[][] counts = new long[6][];
        for (int i = 0; i < 6; i++)
        {
            counts[i] = new long[rollMax[i]];
            counts[i][0] = 1; //we can always use the length 1 sequence
        }

        //there are 6 valid sequences of length 1.
        long totalSeqsFromLastStep = 6;

        for (int step = 1; step < n; step++)
        {
            //keep a count of the sequences we've generated this step
            long totalSeqsFromThisStep = 0;

            for (int endsWith = 0; endsWith < 6; endsWith++)
            {
                long[] validSeqsWithSuffix = counts[endsWith];

                long totalOldSeqsWithSuffix = validSeqsWithSuffix[validSeqsWithSuffix.length - 1];
                for (int i = validSeqsWithSuffix.length - 1; i > 0; i--)
                {
                    long withSuffix = validSeqsWithSuffix[i - 1];
                    validSeqsWithSuffix[i] = withSuffix;

                    totalOldSeqsWithSuffix += withSuffix;
                    totalSeqsFromThisStep += withSuffix;
                }

                long withSuffixSizeOne = (totalSeqsFromLastStep - totalOldSeqsWithSuffix + MOD) % MOD;
                validSeqsWithSuffix[0] = withSuffixSizeOne;
                totalSeqsFromThisStep += withSuffixSizeOne;
            }

            totalSeqsFromLastStep = totalSeqsFromThisStep % MOD;
        }

        return (int) totalSeqsFromLastStep;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A die simulator) generates a random number from 1 to 6 for (each roll).
        //- You introduced (a constraint) to the generator such that
        //  + (it cannot roll the number i) more than (rollMax[i]) (1-indexed) ("consecutive" times).
        //      + Tức là rolls có 6 values + (không được đứng liên tiếp rollMax[i] times)
        //- Given (an array of integers rollMax) and an integer n,
        //* Return (the number of distinct sequences) that can be obtained with (exact n rolls).
        //  + Số lượng dãy có thể build được.
        //+ Since the answer may be too large, return it modulo 10^9 + 7.
        //- Two sequences are considered different if at least (one element) differs (from each other).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //1 <= n <= 5000
        //rollMax.length == 6
        //1 <= rollMax[i] <= 15
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: n = 2, rollMax = [1,1,2,2,2,3]
        //Output: 34
        //Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations.
        // In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively,
        // therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
        //- Có những số không được xuất hiện quá max[i] lần:
        //  + Nó có thể > n luôn
        //- Điền mỗi số thì ta có 6 cách:
        //  + dp[i][6]
        //- Dựa vào rollMax mà ta có thể tính được dp[i][j] có thể tính ntn
        //- Dp[i][j]:
        //  + Số lượng case mà điền đến số thứ (i) và kết thúc bởi value=j
        //- CT:
        //  + dp[i][j]= sum(dp[i-rollMax[j]][j]) + sum(dp[i-1][j1!=j])
        //      + dp[i-rollMax[j]][j]:
        //      Ex:
        //      rollMax[6]=3
        //      + 122666(i!=6)[6]
        //      [122666]6[6]
        //          + Nó sẽ +=sum(dp[i-rollMax[6]]][j1!=6]
        //              + Vì sau (i-rollMax[6]) đã là 6 rồi.
        //- Init:
        //  + dp[0][1->6]=1
//        int n = 2;
//        int[] rollMax = {1,1,2,2,2,3};
        //1,2: 5
        //3,4,5: 6
        //6: 6
        //rs = 5*2+ 6*3 + 5 = 10+18+6  = 34
//        int n = 3;
//        int[] rollMax = {1,1,1,2,2,3};
        //a,b:
        //b=1,2,3: 5
        //b=4,5,6: 6
        //a,b,c
        //c=1,(or 2,3):
        //  + a,{1->6}-{1},1: 5+5+6*3
        //c=4:
        //  + a,{1->6}-{4},4: 5*3+6*2=15+12=27
        //  + {any},4,4: 5
        //      + Nếu tính theo xuất hiện 4 ==> Nó sẽ kéo theo các case 4 xuất hiện phía trước
        //      ==> WRONG
        //      ==> Ta sẽ cần case không xuất hiện 4 ở (i-roll-1)
        //
        //- Case đặc biệt:
        //Ex:
        //rollMax = [1,1,1,2,2,3]
        //n=3
        //- a,4: 6
        //  + a!=4
        //  + a==4
        //==> Cần tính theo sum[i=0] ==> dp[i][any]
        //  + Để ra kết quả cho việc except (j) của (i==0) ra bằng =1 với (a==4) thì:
        //  + Init:
        //      + sum[i=0] = 1
        //      + dp[0][any] = 0
        //
        //* Kinh nghiệm:
        //  - Khi trừ 2 số để ra mod giống nhau:
        //      + ((Số lớn nên cộng thêm mod)- số nhỏ) ==> Để kết quả luôn >=0
        //1.1, Optimization
        //- Có thể giảm được phần space cho sum đi
        //
        //- A 2D array, counts is used to keep track of the count of previous sequences, where the first index denotes the last roll in that sequence,
        // and the second index is how many times in a row that character has been repeated.
        //- On each iteration, the counts are changed as follows:
        //+ Each count of a roll repeated 2 or more times is set equal to the count of a sequence with its last roll being repeated one fewer times.
        //+ Each count of a roll repeated only once is set equal to the total number of sequences from the last,
        // subtracting off all sequences that end in that same roll.
        //  + Tức là ta có thể lưu luôn kết quả except mà không cần lưu sum nữa:
        //      + dp[i][j] = sum[i-h-1]-dp[i-h-1][j]
        //
        //1.2, Complexity
        //- Space: O(n*7)
        //- Time: O(n*6*15)
        //
        int n = 20;
        int[] rollMax = {8,5,10,8,7,2};
        System.out.println(dieSimulator(n, rollMax));
        System.out.println(dieSimulatorRefer(n, rollMax));
        //#Reference:
        //2318. Number of Distinct Roll Sequences
    }
}
