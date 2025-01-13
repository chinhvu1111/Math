package E1_weekly;

import java.util.HashSet;

public class E23_UniqueSubstringsWithEqualDigitFrequency {

    public static int equalDigitFrequency(String s) {
        int n=s.length();
        int[][] prefixSum=new int[n+1][26];

        for(int i=1;i<=n;i++){
            for(int j=0;j<26;j++){
                prefixSum[i][j]=prefixSum[i-1][j];
            }
            prefixSum[i][s.charAt(i-1)-'0']++;
        }
//        int rs=0;
        HashSet<String> rsStr=new HashSet<>();
        for(int i=1;i<=n;i++){
            StringBuilder curStr=new StringBuilder();
            for(int j=i-1;j>=0;j--){
                boolean isValid=true;
                HashSet<Integer> set=new HashSet<>();
                curStr.insert(0, s.charAt(j));
                for(int h=0;h<26;h++){
                    if(prefixSum[i][h]!=prefixSum[j][h]){
                        int count = prefixSum[i][h]-prefixSum[j][h];
                        if(set.size()>1){
                            isValid=false;
                            break;
                        }
                        set.add(count);
                    }
                }
//                System.out.println(curStr);
                if(isValid&&set.size()==1){
//                    System.out.printf("%s %s, %s\n",j, i, curStr);
//                    rs++;
                    rsStr.add(curStr.toString());
                }
            }
        }
//        System.out.println(rsStr);
        return rsStr.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a digit string s,
        //* Return (the number of unique substrings of s) where (every digit) appears (the same number of times).
        //
        //Example 1:
        //
        //Input: s = "1212"
        //Output: 5
        //Explanation: The substrings that meet the requirements are "1", "2", "12", "21", "1212".
        //Note that although the substring "12" appears twice, it is only counted once.
        //
        //** Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 1000
        //s consists of digits.
        //
        //- Brainstorm
        //- Prefix sum for each index in the string
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n^2)
        //- Time: O(n^2)
        //
//        String s = "1212";
        String s = "12321";
        System.out.println(equalDigitFrequency(s));
        //
        //#Reference:
        //2067. Number of Equal Count Substrings
    }
}
