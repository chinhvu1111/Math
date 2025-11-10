package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class E324_PaintingAGridWithThreeDifferentColors {

    public static int colorTheGridWrong(int m, int n) {
        int[][][] dp=new int[n+1][m+1][3];

        for (int i = 0; i <= n; i++) {
            dp[i][0][0]=1;
            dp[i][0][1]=1;
            dp[i][0][2]=1;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i][0]=1;
            dp[0][i][1]=1;
            dp[0][i][2]=1;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i+1][j+1][0]=dp[i][j+1][1]+dp[i+1][j][2];
                dp[i+1][j+1][1]=dp[i][j+1][0]+dp[i+1][j][2];
                dp[i+1][j+1][2]=dp[i][j+1][0]+dp[i+1][j][1];
            }
        }
        return dp[n][m][2]+dp[n][m][0]+dp[n][m][1];
    }

    static final int mod = 1000000007;

    public static int colorTheGrid(int m, int n) {
        int maxBitMask = (int) Math.pow(3, m);
        //Space: O(3^m*n)
        HashMap<Integer, List<Integer>> valToColors= new HashMap<>();

        //Time: O(3^m*m)
        for(int i=0;i<maxBitMask;i++){
            List<Integer> color=new ArrayList<>();
            int temp=i;
            for (int j = 0; j < m; j++) {
                color.add(temp%3);
                temp=temp/3;
            }
            boolean isValid=true;
            for(int j=1;j<m;j++){
                if(color.get(j).equals(color.get(j-1))){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                valToColors.put(i, color);
            }
        }
//        System.out.println(valToColors);
        //Space: O(3^(2*m))
        HashMap<Integer, List<Integer>> tuplesValid=new HashMap<>();

        //Time: 3^(2*m)*m
        for(int key: valToColors.keySet()){
            List<Integer> list=valToColors.get(key);
            for(int key1: valToColors.keySet()){
                List<Integer> list1=valToColors.get(key1);
                boolean isValid=true;
                for(int j=0;j<m;j++){
                    if(list.get(j).equals(list1.get(j))){
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
                    List<Integer> curList=tuplesValid.getOrDefault(key, new ArrayList<>());
                    curList.add(key1);
                    tuplesValid.put(key, curList);
                }
            }
        }
//        System.out.println(tuplesValid);
        HashMap<Integer, Integer> valToCaseNum=new HashMap<>();

        for(int e: valToColors.keySet()){
            valToCaseNum.put(e, 1);
        }
        //Time: O(n*3*(2*m))
        for(int i=1;i<n;i++){
            HashMap<Integer, Integer> curNumCase=new HashMap<>();
            for(int curMask: valToCaseNum.keySet()){
                List<Integer> adj=tuplesValid.getOrDefault(curMask, new ArrayList<>());
                for(int prevMask: adj){
                    curNumCase.put(curMask, (curNumCase.getOrDefault(curMask, 0)+valToCaseNum.getOrDefault(prevMask, 0))%mod);
                }
            }
            valToCaseNum=curNumCase;
//            System.out.println(valToCaseNum);
        }
        int rs=0;

        for (int value: valToCaseNum.values()){
            rs=(rs+value)%mod;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers m and n.
        //- Consider an m x n grid where each cell is initially white.
        //- You can paint each cell red, green, or blue. All cells must be painted.
        //* Return the number of ways to color the grid with no two adjacent cells having the same color.
        //* Since the answer can be very large, return it modulo 10^9 + 7
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= m <= 5
        //1 <= n <= 1000
        //  + 1 <= n <= 1000 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //- Use dynamic programming
        //- dp[i][3]
        //Ex:
        //m = 1, n = 2
        //1  1   (1) 1 1
        //1 (1)  (1) 1 1
        //1  1    1  1 1
        //- dp[i][j] = f(dp[i-1][j], dp[i][j-1])
        //  ==> It is not correct
        //
        //Ex: Make it simpler
        //a(3)    b(2)
        //c(2)    d
        //- Conditions:
        //  + a!=b
        //  + a!=c
        //  + c!=d && b!=d
        //- c==b
        //a b
        //b d
        //  ==>(a,d) = (0,0),(1,1),(2,2)
        //  ==>(a,d) = (0,1),(1,2),(0,2)/(1,0),(2,1),(2,0)
        //  + rs=9
        //- c!=b
        //a b
        //c d
        //  + a!=(b,c)
        //  ==> a = 0,1,2 (d is the same as a)
        //  + rs=3
        //
        //Ex:
        //0 1 1
        //0 1 1
        //0 0 0
        //rs=18
        //
        //0 1(3) 1(6)
        //0 1(6) 1
        //0 0 0
        //* ==> It is difficult to figure out the rule
        //- Signs:
        //  + M is small
        //- We can put col by col
        //Ex:
        //1
        //0
        //1 ==> We can store the previous column value as the bit mask
        //- Using dynamic programming
        //0 1
        //1 0
        //==> How to choose (the current bitmask)
        //- Different cols:
        //1 0
        //2 2
        //==> 2 xor 2 == 0
        //
        //- Different rows:
        //1 0
        //1 2
        //- Find the combinators such that:
        //  + There is not consecutive cells having the same value
        //1 0 1 0 ....
        //
        //1 0 1 2
        //  ==> Check bits by comparing them together
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(3^(2*m))
        //- Time: O(n*3*(2*m))
        //
        //#Reference:
        //1411. Number of Ways to Paint N × 3 Grid
        //
        int m = 2, n = 2;
//        System.out.println(colorTheGridWrong(m, n));
        System.out.println(colorTheGrid(m, n));
    }
}
