package E1_daily;

public class E264_NumberOfSubstringsContainingAllThreeCharacters_slice_window_classic {

    public static boolean isValid(int[] count){
//        for(int i=0;i<3;i++){
//            if(count[i]<=0){
//                return false;
//            }
//        }
//        return true;
        return count[0] > 0 && count[1] > 0 && count[2] > 0;
    }

    public static int numberOfSubstrings(String s) {
        int n=s.length();
        int start=0;
        int rs=0;
        int[] count=new int[3];
        count[s.charAt(start)-'a']++;

        for(int i=1;i<n;i++){
            int c=s.charAt(i)-'a';
            int lastStart=start;

            count[c]++;
            while(isValid(count)){
                count[s.charAt(start)-'a']--;
                lastStart=start;
                start++;
            }
            if(start!=lastStart){
                start=lastStart;
                count[s.charAt(start)-'a']++;
            }
            if(isValid(count)){
//                System.out.println(start);
                rs+=lastStart+1;
            }
        }
        return rs;
    }

    public static int numberOfSubstringsRefer(String s) {
        int len = s.length();
        int left = 0, right = 0;
        // Track frequency of a, b, c
        int[] freq = new int[3];
        int total = 0;

        while (right < len) {
            // Add character at right pointer to frequency array
            char curr = s.charAt(right);
            freq[curr - 'a']++;

            // While we have all required characters
            while (hasAllChars(freq)) {
                // All substrings from current window to end are valid
                // Add count of these substrings to result
                total += len - right;

                // Remove leftmost character and move left pointer
                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;
                left++;
            }

            right++;
        }
        return total;
    }

    public static int numberOfSubstringsOptimization(String s) {
        int len = s.length();
        // Track last position of a, b, c
        int[] lastPos = { -1, -1, -1 };
        int total = 0;

        for (int pos = 0; pos < len; pos++) {
            // Update last position of current character
            lastPos[s.charAt(pos) - 'a'] = pos;

            // Add count of valid substrings ending at current position
            // If any character is missing, min will be -1
            // Else min gives leftmost required character position
            total += 1 + Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));
        }

        return total;
    }

    private static boolean hasAllChars(int[] freq) {
        // Check if we have at least one of each character
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s consisting ("only") of (characters a, b and c).
        //* Return (the number of substrings) containing (at least one) occurrence of (all these characters a, b and c).
        //
        //Example 1:
        //
        //Input: s = "abcabc"
        //Output: 10
        //Explanation:
        // The substrings containing at least one occurrence of
        // the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= s.length <= 5 x 10^4
        //s only consists of a, b or c characters.
        //  + Length<=5*10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //- We can use the prefixSum
        //
        //Example 1:
        //Input: s = "abcabc"
        //- Check string end with chars[i]
        //- min length (slice window) that contains (a,b,c) + end at (index=i)
        //- start, end
        //- end++ ==> better
        //- start++
        //  + We increase start when (start+1, end) has enough characters
        //  ==> (Increase) start to 1 until it has not (chars)
        //
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //- All substrings from current window to end are valid
        //- Add count of these substrings to result
        //  + total += len - right;
        //- If abc is valid ==> abcc is valid
        //  + rs+= len-right (Number of chars after right)
        //
        //- Last Position Tracking:
        //- Instead of thinking in terms of a sliding window, we can take a different perspective:
        //  + for (each position) in the string,
        //  (how many valid substrings) (end at) this position?
        //- The key observation is that a substring is valid if it contains (at least one occurrence) of (each required character) (a, b, and c).
        //  + However, instead of tracking (exact counts), we only care about where (the most recent occurrence) of each character is.
        //  ==> Quan tâm đến lần xuất hiện cuối cùng thôi.
        //  + total += 1 + Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //2953. Count Complete Substrings
        //
//        String s = "abcabc";
        String s = "aaacb";
        //
        System.out.println(numberOfSubstrings(s));
        System.out.println(numberOfSubstringsRefer(s));
        System.out.println(numberOfSubstringsOptimization(s));
    }
}
