package contest.codeforces;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class E2_TeamTraining {

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
        //- At the IT Campus "NEIMARK", there are training sessions in competitive programming — both individual and team-based!
        //- For the next team training session, 𝑛 students will attend, and the skill of (the 𝑖-th student) is given by (a positive integer 𝑎𝑖)
        //- The coach considers a team strong if its strength is (at least 𝑥)
        //- The strength of a team is calculated as (the number of team members) multiplied by (the minimum skill) among the team members.
        //
        //+ For example, if a team consists of 4 members with skills [5,3,6,8], then the team's strength is 4*𝑚𝑖𝑛([5,3,6,8])=12
        //* Output (the maximum possible number of strong teams),
        // given that
        //  + (each team) must have (at least one participant) and
        //  + (every participant) must belong to (exactly one team).
        //
        //Ex:
        //5
        //6 4
        //4 5 3 3 2 6
        //4 10
        //4 2 1 3
        //5 3
        //5 3 2 3 2
        //3 6
        //9 1 7
        //6 10
        //6 1 3 6 3 2
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
        //.
        //
        //- Brainstorm
        //
        //6 4
        //4 5 3 3 2 6
        //rs=4
        //[4],[5],[6], [3,3,2]
        //output = [2,3,3,4,5,6]
        //[2,3,3],[4],...
        //- Sort + calculate
        //
        //3 6
        //9 1 7
        //6 10
        //6 1 3 6 3 2
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
