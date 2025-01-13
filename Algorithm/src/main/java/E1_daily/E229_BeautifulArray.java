package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E229_BeautifulArray {

    static Map<Integer, int[]> memo;
    public static int[] beautifulArray(int N) {
        memo = new HashMap<>();
        return solution(N);
    }

    public static int[] solution(int N) {
        //Time: O(N)
        int[] rs=new int[N];
        if(memo.containsKey(N)){
            return memo.get(N);
        }
        if(N==1){
            rs[0]=1;
            return rs;
        }
        int index=0;
        //Time: O(log(N))
        for(int e: solution((N+1)/2)){
            rs[index++]=e*2-1;
        }
        for(int e: solution(N/2)){
            rs[index++]=e*2;
        }
        memo.put(N, rs);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (An array nums of length n) is beautiful if:
        //  + nums is (a permutation of the integers) in the range [1, n].
        //  + For every (0 <= i < j < n),
        //      + there is (no index k) with (i < k < j) where (2 * nums[k] == nums[i] + nums[j]).
        //- Given the integer n,
        //* Return (any beautiful array nums) of length n.
        //  + There will be (at least one valid answer) for the given n.
        //
        //Example 1:
        //Input: n = 4
        //Output: [2,1,4,3]
        //
        // Idea
        //1. Divide and conquer
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 1000
        //
        //- Brainstorm
        //Ex:
        //Input: n = 4
        //Output: [2,1,4,3]
        //
        //- Brute force all of arrays:
        //  + Check (each array) is valid or not
        //  + How to check that:
        //      + The each element is unique
        //      ==> We can use hashmap to store the (unique index) of (each element)
        //      + We check sum of (all of pair of elements) in array
        //* Rule:
        //- If we find the valid array with order-x
        //  ==> If we reverse the array that is still valid
        //
        //- Check 2 elements:
        //  + nums[i], nums[j]
        //  sum = (nums[i] + nums[j])
        //  + nums[k] = x/2
        //      + We need to put nums[k] out of [i,j]
        //  ==> We will put this element on the right
        //+ k>j>i
        //Ex:
        //1,2,3,4
        //[1,3] => 2
        //[2,4] => 3
        //==> Conflict
        //
        //- 2 * nums[k] == nums[i] + nums[j]
        //<=> nums[k] - nums[j] = nums[i]-nums[k]
        //Ex
        //2,3,4
        //  ==> Increase/ Decrease (Subsequence)
        //
        //- x+y = 2*z
        //  ==> 2 sides reflect (the odd or even numbers)
        //
        //- Another way we could arrive at this is to try to place a number in the middle, like 5.
        // We will have 4 and 6 say, to the left of 5, and 7 to the right of 6, etc.
        // We see that in general, odd numbers move towards one direction and even numbers towards another direction.
        //
        //- Looking at the elements 1, 2, ..., N, there are (N+1) / 2 odd numbers and (N / 2) even numbers.
        //- We solve for elements 1, 2, ..., (N+1) / 2 and map these numbers onto 1, 3, 5, ....
        // Similarly, we solve for elements 1, 2, ..., N/2 and map these numbers onto 2, 4, 6, ....
        //- We can compose these solutions by concatenating them,
        // since (an arithmetic sequence) never (starts and ends) with (elements of different parity).
        //- Left và right sẽ nằm về 1 phía
        //
        //* Rule:
        //- n=2 [1,2]
        //- n=3 [1,3,2]
        //- n=4 [1,5,3,2,4]
        //- f(4) calulated by:
        //  + odd: f((4+1)/2)
        //  + even: f(4/2)
        //* Main point:
        //- For (each element) in (the previous subset solution):
        //  + We calculate (new element) and then adding between [i, j]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        int n = 5;
        int[] rs = beautifulArray(n);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
    }
}
