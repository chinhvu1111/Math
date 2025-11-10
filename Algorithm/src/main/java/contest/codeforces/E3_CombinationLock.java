package contest.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class E3_CombinationLock {

    public static int solve(int k, Integer[] nums){
        Arrays.sort(nums, Collections.reverseOrder());
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            int curStrong=0;
            int count=0;
            int minVal=Integer.MAX_VALUE;

            while(curStrong<k&&i<n){
                minVal=Math.min(minVal, nums[i]);
                i++;
                count++;
                curStrong=minVal*count;
            }
            if(curStrong>=k){
                rs++;
            }
            i--;
        }
        return rs;
    }

    public static void main(String[] args) throws IOException {
        //- At the IT Campus "NEIMARK", there are several top-secret rooms where problems for major programming competitions are developed.
        //- To enter one of these rooms, you must unlock (a circular lock) by selecting (the correct code).
        //  + This code is updated (every day).
        //- Today's code is a permutation of the numbers from (1 to n), with the property that in (every cyclic shift)
        // of it, there is (exactly one fixed point).
        //- That is, in (every cyclic shift), there exists (exactly one element) whose value is equal to its position in the permutation.
        //- Output any valid permutation that satisfies this condition.
        //  + Keep in mind that a valid permutation might not exist, then output −1
        //
        //- A permutation is defined as a sequence of length 𝑛 consisting of integers from (1 to 𝑛)
        //, where (each number) appears (exactly once).
        //- For example, (2 1 3), (1), (4 3 1 2) are permutations; (1 2 2), (3), (1 3 2 5) are not.
        //- (A cyclic shift of an array) is obtained by moving (the last element) to (the beginning of the array).
        // A permutation of length 𝑛 has "exactly" (𝑛 cyclic shifts).
        //* Tức là tìm permutation + shift nó n lần
        //  + Mỗi lần shift cho 1 intermediate permutation có (element = index của nó)
        //
        //Ex:
        //3
        //4
        //5
        //3
        //
        //-1
        //4 1 3 5 2
        //1 3 2
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //Each test contains multiple test cases. The first line contains the number of test cases 𝑡
        // (1≤𝑡≤104
        //). The description of the test cases follows.
        //
        //The first line of each test case contains two integers 𝑛
        // and 𝑥
        // (1≤𝑛≤2⋅10^5
        //, 1≤𝑥≤10^9
        //) — the number of students in training and the minimum strength of a team to be considered strong.
        //
        //The second line of each test case contains 𝑛
        // integers 𝑎𝑖
        // (1≤𝑎𝑖≤109
        //) — the skill of each student.
        //
        //It is guaranteed that the sum of 𝑛
        // over all test cases does not exceed 2⋅10^5
        //
        //3
        //4
        //5
        //3
        //
        //-1
        //4 1 3 5 2
        //1 3 2
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //A single line of each test case contains a single integer 𝑛
        // (1≤𝑛≤2⋅105
        //).
        //  ==> O(n)
        //
        //It is guaranteed that the sum of 𝑛
        // over all test cases does not exceed 2*10^5
        //.
        //
        //- Brainstorm
        //
        //
        //1,2,3,4,5
        //- Fixed point
        //
        //1,2,3,4,5
        //4,1,3,5,2
        //=>
        //1,2,3,4,5
        //2,4,1,3,5
        //5,2,4,1,3
        //3,5,2,4,1
        //- The main problem is that we (flip the bit) and then getting (the intermediate result) from that
        //  + Flip 1 bit
        //  ==> Getting the fixed point
        //- Stack? queue? ==> Order
        //
        //1,2,3,4,5
        //1, => shift
        //x,1,x,x,x
        //  + x,1,3,x,x
        //x,1,3,x,x
        //  => shift x,x,1,3,x
        //x,2,1,3,x
        //  => shift x,x,2,1,3
        //x,x,2,1,3
        //1,x,2,1,3 ==> Invalid
        //
        //
        System.setIn(new FileInputStream("/Users/chinhvu/Documents/projects/Math/Algorithm/src/main/java/contest/codeforces/data/E2"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        for(int t=0;t<n;t++){
            String input = bufferedReader.readLine().trim();
            String[] str =input.split(" ");
            int k = Integer.parseInt(str[1]);
            String[] s=bufferedReader.readLine().split(" ");
            Integer[] nums=new Integer[s.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i]=Integer.valueOf(s[i]);
            }
            int result = solve(k, nums);
            System.out.println(result);
        }
        bufferedReader.close();
    }
}
