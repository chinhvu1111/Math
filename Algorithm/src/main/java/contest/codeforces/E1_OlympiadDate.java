package contest.codeforces;

import java.io.*;

public class E1_OlympiadDate {

    public static int solution(int[] nums){
        String s = "01.03.2025";
        int[] count=new int[10];
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='.'){
                count[s.charAt(i)-'0']++;
            }
        }
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(count[nums[i]]>0){
                count[nums[i]]--;
                boolean isValid=true;

                for (int j = 0; j < 10; j++) {
                    if(count[j]!=0){
                        isValid=false;
                    }
                }
                if(isValid){
                    return i+1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        //** Requirement
        //- The final of the first Olympiad by IT Campus "NEIMARK" is scheduled for March 1, 2025.
        // A nameless intern was tasked with forming the date of the Olympiad using digits — 01.03.2025.
        //- To accomplish this, the intern took a large bag of digits and began drawing them (one by one).
        //- In total, he drew 𝑛 digits — the (digit 𝑎𝑖) was drawn in the (𝑖th turn).
        //- You suspect that the intern did extra work.
        //- Determine at which step the intern could have first assembled the digits
        // to form the date of the Olympiad (the separating dots can be ignored), or report that it is impossible to form this date
        // from (the drawn digits).
        //* Note that (leading zeros) must be displayed.
        //
        //Output
        //For (each test case), output (the minimum number of digits) that the intern could (pull out).
        //- If (all the digits) cannot be used to make a date, output the number (0)
        //
        //Ex:
        //4
        //10
        //2 0 1 2 3 2 5 0 0 1
        //8
        //2 0 1 2 3 2 5 0
        //8
        //2 0 1 0 3 2 5 0
        //16
        //2 3 1 2 3 0 1 9 2 1 0 3 5 4 0 3
        //
        //Output
        //9
        //0
        //8
        //15
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //
        System.setIn(new FileInputStream("/Users/chinhvu/Documents/projects/Math/Algorithm/src/main/java/contest/codeforces/data/E1_OlympiadDate"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        for(int t=0;t<n;t++){
            int k = Integer.parseInt(bufferedReader.readLine().trim());
            String[] s=bufferedReader.readLine().split(" ");
            int[] nums=new int[s.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i]=Integer.valueOf(s[i]);
            }
            int result = solution(nums);
            System.out.println(result);
        }

        bufferedReader.close();
    }
}
