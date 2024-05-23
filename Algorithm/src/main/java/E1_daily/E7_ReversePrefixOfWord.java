package E1_daily;

public class E7_ReversePrefixOfWord {

    public static String reversePrefix(String word, char ch) {
        StringBuilder rs=new StringBuilder();
        for(char c: word.toCharArray()){
            rs.append(c);
            if(ch==c){
                rs.reverse();
                ch='1';
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a (0-indexed string word) and (a character ch),
        //- Reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive).
        //- If the character ch does not exist in word, do nothing.
        //For example, if word = "abcdefd" and ch = "d",
        // then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
        //* Return the resulting string.
        //
        //** Idea
        //1.
        //1.0,
        //* Constraint
        //1 <= word.length <= 250
        //word consists of lowercase English letters.
        //ch is a lowercase English letter.
        //
        //* Brainstorm
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- N is size of word
        //- Space: O(n)
        //- Time : O(n)
        //
        String word = "abcdefd";
        char ch = 'd';
        System.out.println(reversePrefix(word, ch));
        //#Reference:
        //2496. Maximum Value of a String in an Array
        //1963. Minimum Number of Swaps to Make the String Balanced
        //1842. Next Palindrome Using Same Digits
    }
}
