package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E3_FreedomTrail {

    public static int[][]dp;

    public static int getDiff(int x, int y, int n){
        if(x>y){
            int temp=x;
            x=y;
            y=temp;
        }
        //1,4 ==> 3
        //0,1,5
        return Math.min(Math.abs(y-x), x+n-y);
    }

    public static int solution(int indexRing, int indexKey, String key, List<Integer>[] indexMap, int n, int m){
        if(indexKey>=m){
            return 0;
        }
        if(dp[indexRing][indexKey]!=-1){
            return dp[indexRing][indexKey];
        }
        char expectedChar=key.charAt(indexKey);
        List<Integer> expectedIndexOfChar=indexMap[expectedChar-'a'];
//        System.out.printf("index=%s, nextNodes: %s\n", indexRing, expectedIndexOfChar);
        int rs=Integer.MAX_VALUE;

        for(int e: expectedIndexOfChar){
            rs=Math.min(rs, solution(e, indexKey+1, key, indexMap, n, m)+getDiff(indexRing, e, n)+1);
        }
//        System.out.printf("indexRing=%s, indexKey: %s, val: %s\n", indexRing, indexKey, rs);
        return dp[indexRing][indexKey]=rs;
    }

    public static int findRotateStepsTopDown(String ring, String key) {
        int n=ring.length();
        int m=key.length();
        List<Integer>[] indexMap=new ArrayList[26];
        dp=new int[n][m];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        for(int i=0;i<26;i++){
            indexMap[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            int curNumChar=ring.charAt(i)-'a';
            indexMap[curNumChar].add(i);
        }
        return solution(0, 0, key, indexMap, n, m);
    }

    public static int findRotateStepsBottomUp(String ring, String key) {
        int n=ring.length();
        int m=key.length();
        List<Integer>[] indexMap=new ArrayList[26];
        int[][] dp=new int[m+1][n];

        for(int i=0;i<=m;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<26;i++){
            indexMap[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            int curNumChar=ring.charAt(i)-'a';
            indexMap[curNumChar].add(i);
        }
        dp[0][0]=0;
        for(int i=1;i<=m;i++){
            int curCharNum=key.charAt(i-1)-'a';
            List<Integer> adj=indexMap[curCharNum];

            for(int j:adj){
                int rs=Integer.MAX_VALUE;
                for(int l=0;l<n;l++){
                    //i==1
                    //dp[0][0]
                    if(dp[i-1][l]!=Integer.MAX_VALUE){
                        rs=Math.min(dp[i-1][l]+getDiff(l, j, n)+1, rs);
                    }
                }
                dp[i][j]=rs;
//                System.out.printf("i=%s, j=%s, val=%s\n", i, j, rs);
            }
        }
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            rs=Math.min(rs, dp[m][i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given ring, key string
        //- Ring được bố trí bằng 1 ring theo chiều kim đồng hồ
        //- Mỗi step ta có thể:
        //  + Quay ngược/ xuôi kim đồng hồ ==> 1 step
        //      + You can rotate the ring clockwise or anticlockwise by one place, which counts as (one step).
        //  + Chỉ khi ký tự hướng 12h --> enter thì ta mới nhận ký tự đó
        //* return the minimum number of steps to spell all the characters in the keyword.
        //  + If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts (as one step).
        //  ==> Spell key.
        //
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= ring.length, key.length <= 100
        //ring and key consist of only lower case English letters.
        //It is guaranteed that key could always be spelled by rotating ring.
        //--> Không quá lớn
        //
        //- Brainstorm
        //- Mỗi lần xuay --> Start index của ring sẽ biến đổi.
        //- Mỗi index có thể đến các index khác với weight = i-j (0 -> 1)
        //--> Cái này làm theo top-down được.
        //- Chú ý là có thể move (tiến/ lùi)
        //  Ex: 0 -> 6 mất 1 thôi
        //
        //- Bottom up:
        //- Simple:
        // + dp[i]: Là số step ít nhất để đến được index=i
        //  dp[i] = min(dp[i-1][all indexes])
        //  ==> dp[i][j]
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(n*m)
        //- Time: O(n^2*m)
        //
        String ring = "godding", key = "godding";
//        String ring = "abcde", key = "ade";
        //0 : count=1
        //0 -> 3/3 -> 0 : count+=4 / count+=3
        //3 -> 4 : count+=2
        System.out.println(findRotateStepsTopDown(ring, key));
        System.out.println(findRotateStepsBottomUp(ring, key));
        System.out.println(getDiff(1,4,6));
        System.out.println(getDiff(0,3,5));
        //#Reference:
        //600. Non-negative Integers without Consecutive Ones
        //639. Decode Ways II
        //1542. Find Longest Awesome Substring
    }
}
