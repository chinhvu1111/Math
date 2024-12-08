package E1_daily;

public class E174_AddingSpacesToAString {

    public static String addSpaces(String s, int[] spaces) {
        int n=s.length();
        int m=spaces.length;
        StringBuilder rs=new StringBuilder();
        int j=0;
        for(int i=0;i<n;i++){
            if(j<m&&spaces[j]==i){
                j++;
                rs.append(" ");
            }
            rs.append(s.charAt(i));
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a (0-indexed string s) and (a 0-indexed integer array spaces) that describes
        // (the indices) in (the original string) where (spaces) will be added.
        //- (Each space) should be inserted (before the character) (at the given index).
        //  + For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C',
        // which are (at indices 5 and 9 respectively). Thus, we obtain "Enjoy Your Coffee".
        //* Return the modified string after the spaces have been added.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //Constraints:
        //1 <= s.length <= 3 * 10^5
        //s consists only of lowercase and uppercase English letters.
        //1 <= spaces.length <= 3 * 10^5
        //0 <= spaces[i] <= s.length - 1
        //All the values of spaces are (strictly increasing).
        //
        //- Brainstorm
        //- Two pointers
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //1987. Number of Unique Good Subsequences
        //908. Smallest Range I
        //259. 3Sum Smaller
        String s = "LeetcodeHelpsMeLearn";
        int[] spaces = {8,13,15};
        System.out.println(addSpaces(s, spaces));
    }
}
