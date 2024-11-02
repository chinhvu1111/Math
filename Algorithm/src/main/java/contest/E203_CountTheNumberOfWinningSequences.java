package contest;

import sun.print.CUPSPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E203_CountTheNumberOfWinningSequences {

    public static int countWinningSequencesDynamic(String s) {
        int n=s.length();
        int[][] dp=new int[n][3];

        //F:0
        //W:1
        //E:2
        //+ F(0), E(2)
        //+ W(1), F(0)
        //+ E(2), W(1)
        if(s.charAt(0)=='F'){
            dp[0][1]=1;
            dp[0][2]=-1;
        }
        if(s.charAt(0)=='W'){
            dp[0][2]=1;
            dp[0][0]=-1;
        }
        if(s.charAt(0)=='E'){
            dp[0][0]=1;
            dp[0][1]=-1;
        }
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(dp[i], Integer.MIN_VALUE);
//        }
        for(int i=1;i<n;i++){
            for(int j=0;j<3;j++){
//                int curMax=Integer.MIN_VALUE;
                for(int h=0;h<3;h++){
                    if(j==h){
                        dp[i][j]=0;
                        continue;
                    }
                    if((j==0&&h==2)||(j==1&&h==0)||(j==2&&h==1)){
                        dp[i][j]=Math.max(dp[i][j], dp[i-1][h]+1);
                    }else if((j==2&&h==0)||(j==0&&h==1)||(j==1&&h==2)){
                        dp[i][j]=Math.max(dp[i][j], dp[i-1][h]-1);
                    }
                }
            }
        }
        return 1;
    }

//    public static long solution(int index, int alicePoint, int bobPoint, String s, int prevChoice){
//        if(index==s.length()){
//            return alicePoint<bobPoint?1:0;
//        }
//        long curRS=0;
//        //F:0
//        //W:1
//        //E:2
//        //+ F(0), E(2)
//        //+ W(1), F(0)
//        //+ E(2), W(1)
//        if(s.charAt(index)=='F'){
//            if(prevChoice!=2){
//                curRS+=solution(index+1, alicePoint+1, bobPoint, s, 2);
//            }
//            if(prevChoice!=1){
//                curRS+=solution(index+1, alicePoint, bobPoint+1, s, 1);
//            }
//            if(prevChoice!=0){
//                curRS+=solution(index+1, alicePoint, bobPoint, s, 0);
//            }
//        }
//        if(s.charAt(index)=='W'){
//            if(prevChoice!=0){
//                curRS+=solution(index+1, alicePoint+1, bobPoint, s, 0);
//            }
//            if(prevChoice!=2){
//                curRS+=solution(index+1, alicePoint, bobPoint+1, s, 2);
//            }
//            if(prevChoice!=1){
//                curRS+=solution(index+1, alicePoint, bobPoint, s, 1);
//            }
//        }
//        if(s.charAt(index)=='E'){
//            if(prevChoice!=1){
//                curRS+=solution(index+1, alicePoint+1, bobPoint, s, 1);
//            }
//            if(prevChoice!=0){
//                curRS+=solution(index+1, alicePoint, bobPoint+1, s, 0);
//            }
//            if(prevChoice!=2){
//                curRS+=solution(index+1, alicePoint, bobPoint, s, 2);
//            }
//        }
//        return curRS;
//    }

    private static final int MOD = 1000000007;
    public static Map<String, Long> memo;
    public static long solution(int index, int alicePoint, int bobPoint, String s, int prevChoice){
        int n=s.length();
        //0,(1),2
        if(n-index<alicePoint-bobPoint){
            String key = index + "," + prevChoice + "," + bobPoint + "," + alicePoint;
            memo.put(key, 0L);
            return 0;
        }
        if(index==s.length()){
            return alicePoint<bobPoint?1:0;
        }
        long curRS=0;
        //F:0
        //W:1
        //E:2
        //+ F(0), E(2)
        //+ W(1), F(0)
        //+ E(2), W(1)
        String key = index + "," + prevChoice + "," + bobPoint + "," + alicePoint;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if(s.charAt(index)=='F'){
            if(prevChoice!=2){
                curRS=(curRS+solution(index+1, alicePoint+1, bobPoint, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint+1, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint, s, 0))%MOD;
            }
        }else if(s.charAt(index)=='W'){
            if(prevChoice!=0){
                curRS=(curRS+solution(index+1, alicePoint+1, bobPoint, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint+1, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint, s, 1))%MOD;
            }
        }else if(s.charAt(index)=='E'){
            if(prevChoice!=1){
                curRS=(curRS+solution(index+1, alicePoint+1, bobPoint, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint+1, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solution(index+1, alicePoint, bobPoint, s, 2))%MOD;
            }
        }
        memo.put(key, curRS);
        return curRS;
    }

    public static int countWinningSequences(String s) {
        memo=new HashMap<>();
        long rs = solution(0, 0, 0, s, -1);
        return (int) rs;
    }

//    private static final int MOD = 1000000007;
//
//    public static int countBobWinningSequences(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][3]; // 0: F, 1: W, 2: E
//        int[] points = new int[n + 1];
//
//        // Initialize for the first round
//        for (int j = 0; j < 3; j++) {
//            dp[0][j] = 1;
//        }
//
//        // Fill the dp table
//        for (int i = 1; i < n; i++) {
//            char aliceMove = s.charAt(i);
//            for (int j = 0; j < 3; j++) { // Bob's previous move
//                for (int k = 0; k < 3; k++) { // Bob's current move
//                    if (j != k) {
//                        int bobPoints = bobWins(aliceMove, k);
//                        dp[i][k] = (dp[i][k] + dp[i - 1][j]) % MOD;
//                        points[i] = (points[i] + bobPoints * dp[i - 1][j]) % MOD;
//                    }
//                }
//            }
//        }
//
//        // Count the sequences where Bob has more points than Alice
//        int totalWinningSequences = 0;
//        int totalRounds = s.length();
//
//        for (int j = 0; j < 3; j++) {
//            if (points[totalRounds - 1] > totalRounds / 2) {
//                totalWinningSequences = (totalWinningSequences + dp[totalRounds - 1][j]) % MOD;
//            }
//        }
//
//        return totalWinningSequences;
//    }
//
//    private static int bobWins(char aliceMove, int bobMove) {
//        if (aliceMove == 'F' && bobMove == 1) return 1; // Bob wins with Water Serpent
//        if (aliceMove == 'W' && bobMove == 2) return 1; // Bob wins with Earth Golem
//        if (aliceMove == 'E' && bobMove == 0) return 1; // Bob wins with Fire Dragon
//        return 0;
//    }

//    private static final int MOD = 1000000007;
//    private static String aliceMoves;
//    private static Map<String, Integer> memo;
//
//    public static int countBobWinningSequences(String s) {
//        aliceMoves = s;
//        memo = new HashMap<>();
//        int n = s.length();
//        return countWays(0, -1, 0, 0, n);
//    }
//
//    private static int countWays(int round, int prevBob, int bobPoints, int alicePoints, int n) {
//        if (round == n) {
//            return bobPoints > alicePoints ? 1 : 0;
//        }
//
//        String key = round + "," + prevBob + "," + bobPoints + "," + alicePoints;
//        if (memo.containsKey(key)) {
//            return memo.get(key);
//        }
//
//        int totalWays = 0;
//        char aliceMove = aliceMoves.charAt(round);
//
//        for (int bobMove = 0; bobMove < 3; bobMove++) {
//            if (bobMove == prevBob) continue;
//
//            int bobGain = bobWins(aliceMove, bobMove);
//            totalWays = (totalWays + countWays(round + 1, bobMove, bobPoints + bobGain, alicePoints + (bobGain == 0 ? 1 : 0), n)) % MOD;
//        }
//
//        memo.put(key, totalWays);
//        return totalWays;
//    }
//
//    private static int bobWins(char aliceMove, int bobMove) {
//        if (aliceMove == 'F' && bobMove == 1) return 1; // Bob wins with Water Serpent
//        if (aliceMove == 'W' && bobMove == 2) return 1; // Bob wins with Earth Golem
//        if (aliceMove == 'E' && bobMove == 0) return 1; // Bob wins with Fire Dragon
//        return 0;
//    }

    public static long solutionOptimizationTLEMAP(int index, int bobPoint, String s, int prevChoice){
//        int n=s.length();
        //0,(1),2
//        if(n-index<n-bobPoint){
//            String key = index + "," + prevChoice + "," + bobPoint;
//            memo.put(key, 0L);
//            return 0;
//        }
        if(index==s.length()){
            return bobPoint>0?1:0;
        }
        long curRS=0;
        //F:0
        //W:1
        //E:2
        //+ F(0), E(2)
        //+ W(1), F(0)
        //+ E(2), W(1)
        String key = index + "," + prevChoice + "," + bobPoint;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if(s.charAt(index)=='F'){
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint-1, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint+1, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint, s, 0))%MOD;
            }
        }else if(s.charAt(index)=='W'){
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint-1, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint+1, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint, s, 1))%MOD;
            }
        }else if(s.charAt(index)=='E'){
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint-1, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1,bobPoint+1, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimizationTLEMAP(index+1, bobPoint, s, 2))%MOD;
            }
        }
        memo.put(key, curRS);
        return curRS;
    }

    public static long[][][] memoArr;

    public static long solutionOptimization(int index, int bobPoint, String s, int prevChoice){
//        int n=s.length();
        //0,(1),2
//        if(n-index<n-bobPoint){
//            String key = index + "," + prevChoice + "," + bobPoint;
//            memo.put(key, 0L);
//            return 0;
//        }
        if(index==s.length()){
            return bobPoint>0?1:0;
        }
        long curRS=0;
        //F:0
        //W:1
        //E:2
        //+ F(0), E(2)
        //+ W(1), F(0)
        //+ E(2), W(1)
        if(memoArr[index][bobPoint+1000][prevChoice]!=-1){
            return memoArr[index][bobPoint+1000][prevChoice];
        }
        if(s.charAt(index)=='F'){
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimization(index+1, bobPoint-1, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimization(index+1, bobPoint+1, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimization(index+1, bobPoint, s, 0))%MOD;
            }
        }else if(s.charAt(index)=='W'){
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimization(index+1, bobPoint-1, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimization(index+1, bobPoint+1, s, 2))%MOD;
            }
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimization(index+1, bobPoint, s, 1))%MOD;
            }
        }else if(s.charAt(index)=='E'){
            if(prevChoice!=1){
                curRS=(curRS+solutionOptimization(index+1, bobPoint-1, s, 1))%MOD;
            }
            if(prevChoice!=0){
                curRS=(curRS+solutionOptimization(index+1,bobPoint+1, s, 0))%MOD;
            }
            if(prevChoice!=2){
                curRS=(curRS+solutionOptimization(index+1, bobPoint, s, 2))%MOD;
            }
        }
        memoArr[index][bobPoint+1000][prevChoice] = curRS;
        return curRS;
    }

    public static int countWinningSequencesOptimization(String s) {
        int n=s.length();
        memoArr=new long[n][2001][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2001; j++) {
                Arrays.fill(memoArr[i][j], -1);
            }
        }
//        long rs = solutionOptimization(0, 0, s, 0)%MOD;
//        rs= (rs+ solutionOptimization(0, 0, s, 1))%MOD;
//        rs = (rs + solutionOptimization(0, 0, s, 2))%MOD;
        long rs = solutionOptimization(0, 0, s, 3);
        return (int) (rs % MOD);
    }

    public static void main(String[] args) {
        //* Requirement
        //- Alice and Bob are playing a fantasy battle game consisting of (n rounds) where they summon (one of three magical creatures) each round:
        //  + a Fire Dragon, a Water Serpent, or an Earth Golem.
        //- In each round, players simultaneously summon their creature and are awarded points as follows:
        //  + If one player summons a (Fire Dragon) and the other summons an (Earth Golem), the player who summoned the (Fire Dragon) is awarded a point.
        //      + F, E
        //  + If one player summons a (Water Serpent) and the other summons a (Fire Dragon), the player who summoned the Water Serpent is awarded a point.
        //      + W, F
        //  + If one player summons an (Earth Golem) and the other summons a (Water Serpent), the player who summoned the Earth Golem is awarded a point.
        //      + E, W
        //  + If both players summon the same creature, (no player) is awarded a point.
        //- You are given a string s consisting of n characters 'F', 'W', and 'E', representing the sequence of (creatures Alice) will summon in each round:
        //  + If s[i] == 'F', Alice summons a Fire Dragon.
        //  + If s[i] == 'W', Alice summons a Water Serpent.
        //  + If s[i] == 'E', Alice summons an Earth Golem.
        //
        //- Bob’s sequence of moves is (unknown), but it is guaranteed that Bob will (never) summon the (same creature) in (two consecutive) rounds.
        //  + Không (2 cái liên tiếp giống nhau)
        //- Bob beats Alice if (the total number of points) awarded to Bob after (n rounds) is strictly greater than the points awarded to Alice.
        //* Return (the number of distinct sequences Bob) can use to (beat) Alice.
        //  + Bob win
        //- Since the answer may be very large, return it modulo 10^9 + 7.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 1000
        //s[i] is either 'F', 'W', or 'E'.
        //  + Số không lớn ==> Recursion + Memo được
        //
        //** Brainstorm
        //
        //Example 2:
        //Input: s = "FWEFW"
        //Output: 18
        //
        //- Count + không giống nhau:
        //  ==> Dynamic được
        //
        //Ex:
        //FWEFW
        //FWFWF
        //- Nếu dp[i][j]:
        //  + i là index
        //  + j là lưa chọn
        //  => Là point của thằng bob thì chưa tốt lắm
        //      + Cần tính point của 2 người ==> Mới biết được ai thắng ai thua
        //
        //1.1, Optimization
        //- If Else condition 3 lần:
        //  + Sẽ tốt hơn là 3 cái if
        //
//        String s = "FWEFW";
//        String s = "EEFWEWWFFFWEEWEFFEEEFEFEEFWFEWEFEWWWFFEEEFFFE";
//        String s = "FEWFEEFWEWFFFWWFFEFEWEEEWWWWWWFEFFEWF";
//        String s = "EEFWWFFFFFFWFWFFEEFFFEFEFFFEEWWFWWFWEEEEWWWWFWEFFEFEFFWFWWFEWFWEWFEFEWFWWWWWWWWFWWWFEEFEFFEEFEFFEWFEWEFWFFFEWFWFEEWFFEWEWEFWWWEFFWWEFWFFFFFEW";
//        String s = "WWFFEWEEEWEFFEFWWEEWFFFEWFEWFWFEEFEFWFEFWFFWFEFEFEEEEEEFEWFEWWEEEWEFEWWFFFFEWEWWEFWFEFWFWEEFFWWWEWFFEEWEFEWEF";
        String s = "EEFFFWWWWFWFFEFFFWEWEEWEWFEEEFFW";
        //637884933
        //
        //- Nếu dùng aliceScore và bobScore cùng 1 lúc:
        //  + Sẽ bị TLE
        //  ==> Ở đây chỉ cần dùng score của Bob là được
        //* Kinh nghiệm:
        //- Chỉ cần lưu lại bob score là được.
        //FWEFW
        //EWFWE
//        System.out.println(countWinningSequences(s));
//        System.out.println(countBobWinningSequences(s));
        System.out.println(countWinningSequences(s));
        System.out.println(countWinningSequencesOptimization(s));
    }
}
