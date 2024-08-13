package E1_weekly;

import java.util.Arrays;

public class E8_TossStrangeCoins {

    public static double solution(int index, double[] prob, double[][] memo, double[] probSuffix, int curTarget, int target) {
        int n=prob.length;

        //index=0, target = 1
        //index=1, target = 2
        //1 --> 99
        //- Cut branch
        if(target-curTarget>n-index){
            return 0;
        }
        if (curTarget == target) {
            return memo[index][curTarget]=index<n?probSuffix[index]:1;
        }
        if(index>=n){
            return 0;
        }
        if (memo[index][curTarget] != -1) {
            return memo[index][curTarget];
        }
//        double curRs = 0;
//        double curProb = 1;
//        System.out.printf("Index=%s, current target: %s\n",index, curTarget);

//        for (int i = index; i < prob.length; i++) {
//            double headVal = solution(i+1, prob, memo, probSuffix, curTarget + 1, target)*prob[i];
//            double nonHeadVal = solution(i+1, prob, memo, probSuffix, curTarget, target)*(1-prob[i]);
//            curRs += curProb * headVal;
//            curRs += curProb * nonHeadVal;
//            System.out.printf("Index: %s, Prev prob: %s, head val: %s, non head val: %s\n", index, curProb, headVal, nonHeadVal);
//            curProb *= 1 - prob[i];
//        }
        double headVal = solution(index+1, prob, memo, probSuffix, curTarget + 1, target)*prob[index];
        double nonHeadVal = solution(index+1, prob, memo, probSuffix, curTarget, target)*(1-prob[index]);
        return memo[index][curTarget] = headVal+nonHeadVal;
    }

    public static double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double rs = 1D;

        if (n == target) {
            for (int i = 0; i < n; i++) {
                rs *= prob[i];
            }
        }else if(target==0){
            for (int i = 0; i < n; i++) {
                rs *= 1-prob[i];
            }
        }else {
            double[][] memo = new double[n+1][target+1];
            double[] probSuffix = new double[n];
            double curProb = 1;

            for(int i=0;i<=n;i++){
                Arrays.fill(memo[i], -1);
            }

            for (int i = n - 1; i >= 0; i--) {
                probSuffix[i] = (1-prob[i]) * curProb;
                curProb = probSuffix[i];
//                System.out.println(probSuffix[i]);
            }
            rs=0;
            rs += solution(1, prob, memo, probSuffix, 1, target)*prob[0];
            rs += solution(1, prob, memo, probSuffix, 0, target)*(1-prob[0]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have some coins.
        //- The (i-th coin) has a probability (prob[i] of facing heads) when tossed.
        //* Return the probability that (the number of coins) facing (heads) equals target if you toss every coin (exactly once).
        //* Return the prob sao cho số lượng coins có mặt head == target nếu (toss mỗi coin) 1 lần.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= prob.length <= 1000
        //0 <= prob[i] <= 1
        //0 <= target <= prob.length
        //Answers will be accepted as correct if they are within 10^-5 of the correct answer.
        //- Length của prob không lớn lắm:
        //  ==> Ta có thể xử lý được trong O(n^2)
        //
        //- Brainstorm
        //- Nếu target >= n
        //  + return product of all of elements
        //- target<n:
        //  + Ta chọn tổ hợp target trong n elements
        //- Bài này ta có thể làm memo được không?
        //  + memo[index][target]
        //- Nếu ta ignore index nào:
        //  ==> rs*=1-prob[i]
        //- Errors:
        //+ Lỗi TLE liên quan đến cut branch:
        //  + Nếu remaining(target)>= remaing(length) : return 0
        //+ Nếu để memo[index][curTarget]!=0:
        //  + => Sẽ có trường hợp memo[index][target]==0 ==> Nó sẽ không return (SAI)
        //** KINH NGHIỆM:
        //  + Gán memo[i][j]=-1 ==> Ngay từ đầu.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*target+target)
        //- Time: O(n*target+n)
        //
//        double[] prob = {0.5,0.5,0.5,0.5,0.5};
//        int target = 0;
//        double[] prob = {0.4};
//        int target = 1;
        double[] prob = {0.4, 0.1, 0.6};
        int target = 2;
//        double[] prob = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//        int target = 99;
        //
        //#Reference:
        //2088. Count Fertile Pyramids in a Land
        //3082. Find the Sum of the Power of All Subsequences
        //3024. Type of Triangle
        System.out.println(probabilityOfHeads(prob, target));
    }
}
