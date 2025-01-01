package contest;

public class E246_FindTheLexicographicallyLargestStringFromTheBoxI {

    public static String answerString(String word, int numFriends) {
        if(numFriends==1){
            return word;
        }
        int n=word.length();
        String rs="";

        for (int i = 0; i < n; i++) {
            int remainingFriends = numFriends-i-1;
            //0,1,2,3
            int nextIndex = n-1-remainingFriends;
            String curStr = "";
            if(remainingFriends>0&&nextIndex>=i){
//                System.out.println(nextIndex);
                curStr=word.substring(i, nextIndex+1);
                if(rs.compareTo(curStr)<0){
                    rs=curStr;
                }
            }else{
                curStr=word.substring(i);
            }
            if(rs.compareTo(curStr)<0){
                rs=curStr;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string word, and an integer numFriends.
        //- Alice is organizing a game for her numFriends friends.
        // There are multiple rounds in the game, where in each round:
        //  + word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
        //  + All the split words are put into a box.
        //* Find the lexicographically largest string from the box after all the rounds are finished.
        //- A string a is lexicographically smaller than a string b if in the first position where a and b differ,
        // string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
        //- If the first min(a.length, b.length) characters do not differ,
        // then the shorter string is the lexicographically smaller one.
        //
        //Example 1:
        //
        //Input: word = "dbca", numFriends = 2
        //Output: "dbc"
        //
        //Explanation:
        //
        //All possible splits are:
        //"d" and "bca".
        //"db" and "ca".
        //"dbc" and "a".
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= word.length <= 5 * 10^3
        //word consists only of lowercase English letters.
        //1 <= numFriends <= word.length
        //  + length<=5000 ==> Time: O(n^2)
        //  + num_char <= 26 ==> Time: O(n*26)
        //Ex:
        //word = "dbca", numFriends = 3
        //- We can cut:
        //  d|b|ca
        //  db|c|a
        //- at index=i:
        //  + We assume we split (i+1) chars before
        //  ==> We get until m-(i+1)
        System.out.println("d".compareTo("ab"));
//        String word = "dbca";
//        int numFriends = 2;
        String word = "gh";
        int numFriends = 1;
        //"gh"
        //1
        System.out.println(answerString(word, numFriends));
    }
}
