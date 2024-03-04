package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E123_RestoreTheArray {

    public static HashMap<Integer, Integer> memo;
    public static int mod=1_000_000_007;

    public static int solutionTopDown(int index, String s, int k){
        if(index>=s.length()){
            return 1;
        }
        if(memo.containsKey(index)){
            return memo.get(index);
        }
        long val=0;
        int curRs=0;
        for(int i=index;i<s.length();i++){
            val=val*10+s.charAt(i)-'0';

            while (i+1<s.length()&&s.charAt(i+1)=='0'){
                val=val*10;
                if(val>k){
                    break;
                }
                i++;
            }
            if(val>k){
                break;
            }
            curRs=(curRs+solutionTopDown(i+1, s, k))%mod;
//            System.out.printf("Index=%s, %s %s\n", index, val, curRs);
        }
        memo.put(index, curRs);
        return curRs;
    }

    public static int numberOfArrays(String s, int k) {
        memo=new HashMap<>();
        return solutionTopDown(0, s, k);
    }

    public static int numberOfArraysBottomUp(String s, int k) {
        int n=s.length();
        int[] dp =new int[n+1];
//        int sum=1;
        dp[1]=1;
        int rs=1;

        //s=12|34
        //k=15
        //==> Sai case nối 3 với 12 ==> (Lớn hơn k)
        for(int i=2;i<=n;i++){
            while(i-1<n&&s.charAt(i-1)=='0'){
                i++;
//                System.out.println(sum);
            }
            if(i<=n){
//                dp[i]=(sum+1)%mod;
//                sum=(sum+dp[i])%mod;
                long val=s.charAt(i-1)-'0';
                int j=i-1;
                long mul=10;
                int sum=0;

                while(j>=1&&val+(s.charAt(j-1)-'0')*mul<=k){
                    val=val+(s.charAt(j-1)-'0')*mul;
                    mul=mul*10;
                    sum=(sum+dp[j])%mod;
//                    System.out.printf("i=%s, j=%s, %s, %s",i, j, val, dp[j]);
                    j--;
                }
//                System.out.println();
                dp[i]=(sum+1)%mod;
                rs=Math.max(rs, dp[i]);
            }
        }
//        for(int i=1;i<=n;i++){
//            System.out.printf("%s, ", dp[i]);
//        }
//        System.out.println();
        //Ex:
        //317 -> add 1
        //  + 1 có thể add vào 7/1/3
        //+ dp[index=3] = sum(dp[0],dp[1],dp[2])
        //+ 3: 1
        //+ 31: 1+1 ( 1 chính nó + còn lại là tách)
        //+ 31|7: [31]+ length(31) (7 kết hợp với 1/13) = 4
        //+ 317|1: (1 + 7/17/317) dp[i-2]+dp[i-3]+dp[i-4] + 1 (count của chính nó)
        //s=10000
        //k=100
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- A program was supposed to print an array of integers.
        // The program forgot to print whitespaces and the array is printed as a string of digits s and all
        // we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.
        //- Given the string s and the integer k,
        //* Return the number of the possible arrays that can be printed as s using the mentioned program.
        // Since the answer may be very large, return it modulo 10^9 + 7.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of only digits and does not contain leading zeros.
        //1 <= k <= 10^9
        //
        //- Brainstorm
        //Ex:
        //Input: s = "1317", k = 2000
        //Output: 8
        //Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
        //- Bài này thì có thể làm top down được dễ hơn chút
        //1317
        //+ 1|317
        //  317 có:
        //      + 3,17
        //      + 317
        //      + 31,7
        //      + 3,1,7
        //+ 13|17
        //+ 131|7
        //==> Số đượng cộng dần, nếu >k ==> break
        //
        //- Bottom up approach:
        //-
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space:
        //- Time:
        //
//        String s = "1317";
//        int k = 2000;
//        String s = "1000";
//        int k = 10000;
//        String s = "1000";
//        int k = 10;
//        String s = "600342244431311113256628376226052681059918526204";
//        int k = 703;
//        String s = "13172";
//        int k = 200033;
//        String s = "407780786171321121429620765476840275495357129574174233567552131453038760763182952432348422252546559691171161181440370120987634895458140447952079749439961325982629462531738374032416182281868731817661954890417245087359968833257550123324827240537957646002494771036413572415";
//        int k = 823924906;
//        String s = "317";
//        int k = 12222;
//        String s = "1234";
//        int k = 15;
        String s="60034227767";
        int k=703;
        System.out.println(numberOfArrays(s, k));
        System.out.println(numberOfArraysBottomUp(s, k));
        //#Reference:
        //1977. Number of Ways to Separate Numbers
        //2478. Number of Beautiful Partitions
    }
}
