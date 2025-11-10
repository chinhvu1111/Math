package E1_daily;

public class E351_FindTheOriginalTypedStringI {

    public static int possibleStringCount(String word) {
        int n=word.length();
        //eree
        //==>
        int rs=1;
        int i=0;
        while (i<n){
            char curChar = word.charAt(i);
            i++;
            while (i<n&&curChar==word.charAt(i)){
                i++;
                rs++;
            }
        }
        return rs;
    }

    public static int possibleStringCountOptimization(String word) {
        int n = word.length(), ans = 1;
        for (int i = 1; i < n; ++i) {
            if (word.charAt(i - 1) == word.charAt(i)) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Alice is attempting to type a specific string on her computer.
        // However, she tends to be clumsy and may press a key for too long,
        // resulting in a character being typed multiple times.
        //- Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
        //- You are given a string word, which represents the final output displayed on Alice's screen.
        //* Return the total number of possible original strings that Alice might have intended to type.
        //
        //Example 1:
        //Input: word = "abbcccc"
        //Output: 5
        //Explanation:
        //The possible strings are: "abbcccc", "abbccc", "abbcc", "abbc", and "abcccc".
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(1)
        String word = "abbcccc";
        System.out.println(possibleStringCount(word));
        System.out.println(possibleStringCountOptimization(word));
    }
}
