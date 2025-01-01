package contest;

import java.math.BigDecimal;
import java.util.*;

public class E211_CountNumberOfBalancedPermutations {

    public static int getNumberDistinctCase(String nums, int target, int length){
        //Ex:
        //nums=[1,2,3]
        //target=6
        //
        //- For each element, we use (only once time).
        //
        int[][] dp=new int[target+1][length+1];
        int[][] prevDp=new int[target+1][length+1];
        int n=nums.length();
        dp[0][0]=1;
        prevDp[0][0]=1;

        for(int i=0;i<n;i++){
            dp[nums.charAt(i)-'0'][1]=1;
            prevDp[nums.charAt(i)-'0'][1]=1;
        }
        //3 = 1+2
        //3 = 2+1
        //- We only use (part of the nums):
        //  + If we loop following the all of the elements that means we use (all of them).
        //
        //- dp[i][len]:
        //  + count the number of distinct nums such that:
        //      + length=len
        //      + sum=i
        //- dp[i][len] = dp[i-nums[i]][len-1]
        //1,2
        //2,1 ==> They are different
        //  + Loop base on length
        //- Misunderstanding:
        //  + Mình hiểu lầm rằng nếu flexiable trong order:
        //  ==> Thì cần thêm loop theo length
        //
        for(int i=0;i<n;i++){
            int curDigit=nums.charAt(i)-'0';

            for(int j=1;j<=target;j++){
                for(int l=2;l<=length;l++){
                    if(j>=curDigit){
                        //dp[3][2]+=prev[1][1]
                        dp[j][l]+=prevDp[j-curDigit][l-1];
                    }
                }
            }
            for(int j=1;j<=target;j++){
                for(int l=2;l<=length;l++){
                    prevDp[j][l]=dp[j][l];
                }
            }
        }
        return dp[target][length];
    }

    static int MOD = 1_000_000_007;

    public static HashMap<String, BigDecimal> memo;
    public static Integer[] indexToVal;

    public static BigDecimal solution(int odd, int even, long odd_remain, int n){
        if(odd==even&&even==0){
            if(odd_remain==0){
                return new BigDecimal(1);
            }
            return new BigDecimal(0);
        }
        String curKey = odd+"_"+even+"_"+odd_remain;
        if(memo.containsKey(curKey)){
            return memo.get(curKey);
        }
        long a=indexToVal[n-even-odd];
        BigDecimal rs=new BigDecimal(0);
        if(odd>0&&odd_remain-a>=0){
            rs=rs.add(solution(odd - 1, even, odd_remain - a, n).multiply(new BigDecimal(odd)));
//            System.out.println(rs);
        }
        if(even>0){
            rs=rs.add(solution(odd, even - 1, odd_remain, n).multiply(new BigDecimal(even)));
//            System.out.println(rs);
        }
//        rs=rs.remainder(new BigDecimal(MOD));
//        System.out.println(rs);
        memo.put(curKey, rs);
        return rs;
    }

    static BigDecimal factorial(long n){
        if (n == 0)
            return new BigDecimal(1);
        else
            return new BigDecimal(n).multiply(factorial(n-1));
    }

    public static int countBalancedPermutations(String num) {
        memo=new HashMap<>();
        int sum=0;
        int n=num.length();
        indexToVal=new Integer[n];

        for(int i=0;i<n;i++){
            int curVal=num.charAt(i)-'0';
            sum+=curVal;
            indexToVal[i]= curVal;
        }
        if(sum%2==1){
            return 0;
        }
        int target=sum/2;
        int even=n/2, odd=(n+1)/2;
        Arrays.sort(indexToVal, Collections.reverseOrder());
        int[] freq=new int[10];

        for(int i=0;i<n;i++){
            freq[indexToVal[i]]++;
        }
        BigDecimal fac=new BigDecimal(1);

        for(int i=0;i<10;i++){
            if(freq[i]!=0){
                fac=fac.multiply(factorial(freq[i]));
//                System.out.println(fac);
            }
        }
        //207360
//        System.out.println(fac);
        BigDecimal rs= solution(odd, even, target, n);
        long curRs=rs.divide(fac).remainder(new BigDecimal(MOD)).longValue();
        return (int) curRs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (a string num).
        //- A string of digits is called (balanced)
        //  + if the (sum of the digits at (even) indices) is equal to (the sum of the digits at (odd) indices).
        //* Return (the number of distinct permutations) of num that are balanced.
        //- Since the answer may be very large, return it modulo 10^9 + 7.
        //- A permutation is (a rearrangement of all the characters) of a string.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= num.length <= 80
        //num consists of digits '0' to '9' only.
        //  + length<=80 --> Time:
        //
        //** Brainstorm
        //- Sum of even indices == Sum of odd indices
        //- If length%2==0:
        //  + abcd
        //  + "even" indices number = "odd" indices number
        //- If length%2==1:
        //  + abc
        //  + "even" indices number + 1 = "odd" indices number
        //
        //- x+y = sum
        //  + x=sum/2
        //- Given num=[1,2,3], count the number of distinct number such that:
        //  + sum==x
//        String num = "123";
//        //1,2
//        //2,1
//        System.out.println(getNumberDistinctCase(num, 3, 2));
        //
        //* Approach
        //- DP(odd, even, odd_remain)
        //
        //- Total valid permutation if we have odd position count, even position count, odd_remain remaining odd sum
        //- How to index element from A
        //  + idx = n - (odd + even)
        //
        //- Two Choices:
        //- odd_remain: sum of odd elements
        //  + DP(odd - 1, even, odd_remain - A[idx]) * odd: Put A[idx] in odd partition.
        //      + We (multiply by odd) since we have odd positions from (odd partition)
        //          + Put A[idx] to the odd partition ==> odd ways
        //  + DP(odd, even - 1, odd_remain) * even: Put A[idx] in even partition.
        //      + (multiply by even) since we have even positions from (even partition)
        //
        //- Original Problem
        //  DP(odd, even, target)
        //
        //Note:
        //
        //If A = 122333
        //fac = 1! * 2! * 3!
        //
        //* Tips:
        //- Pick element from A (in decreasing order) could reduce redundant recursive stack
        // since we could pick bigger digit to arrive at target earlier
        //
//        String num = "112";
//        String num = "42661847847036";
//        String num = "3527368360898414880870";
//        String num = "910503651275324420311674487";
        String num = "7307648318310204023043476980826538374303700";
//        System.out.println(getNumberDistinctCase(num, 2, 2));
        System.out.println(countBalancedPermutations(num));
        //==> 1 1 are the same --> It is hard to combine regularly
        //
        //#Reference:
        //Combination Sum IV
        //Partition Equal Subset Sum
    }
}
