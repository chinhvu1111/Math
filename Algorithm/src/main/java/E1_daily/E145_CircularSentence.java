package E1_daily;

public class E145_CircularSentence {

    public static boolean isCircularSentence(String sentence) {
        int n= sentence.length();
        boolean isCircular = sentence.charAt(0) == sentence.charAt(n-1);
        if(!isCircular){
            return false;
        }
        char prevC = '-';
        for(int i=0;i<n;i++){
            if(sentence.charAt(i)==' ') {
                if(i+1<n&&sentence.charAt(i+1)!=prevC){
                    return false;
                }
                continue;
            }
            prevC=sentence.charAt(i);
        }
        return true;
    }

    public static boolean isCircularSentenceRefer(String sentence) {
        for (int i = 0; i < sentence.length(); ++i) if (
                sentence.charAt(i) == ' ' &&
                        sentence.charAt(i - 1) != sentence.charAt(i + 1)
        ) return false;
        return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- A sentence is (a list of words) that are separated by (a single space) with (no leading or trailing spaces).
        //  + For example,
        //  "Hello World", "HELLO", "hello world hello world" are all sentences.
        //- Words consist of (only) (uppercase and lowercase) English letters.
        //  (Uppercase and lowercase) English letters are considered (different).
        //- A sentence is circular if:
        //  + The (last character) of a word is equal to the (first character) of the next word.
        //  + The (last character) of the (last word) is equal to the (first character) of the first word.
        //  ==>
        //  + Mối quan hệ circular sẽ nằm trong từng word trong sentence hoặc chính sentence đó.
        //- For example,
        //  "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences.
        // However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.
        //- Given a string sentence,
        //  + return true if it is circular. Otherwise, return false.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= sentence.length <= 500
        //sentence consist of only lowercase and uppercase English letters and spaces.
        //The words in sentence are separated by a single space.
        //There are no leading or trailing spaces.
        //
        //- Brainstorm
        //
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //-
        String sentence = "leetcode exercises sound delightful";
        System.out.println(isCircularSentence(sentence));
        System.out.println(isCircularSentenceRefer(sentence));
    }
}
