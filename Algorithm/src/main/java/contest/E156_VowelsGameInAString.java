package contest;

import java.io.StringReader;
import java.util.HashSet;

public class E156_VowelsGameInAString {

    public static boolean doesAliceWin(String s) {
        int countVowels=0;
        int n=s.length();

        char[] vowels={'a','e','i','o','u'};
        HashSet<Character> set=new HashSet<>();
        for(char c: vowels){
            set.add(c);
        }
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(set.contains(c)){
                countVowels++;
            }
        }
        return countVowels!=0;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Alice and Bob are playing a game on a string.
        //- You are given a string s, Alice and Bob will take turns playing the following game where (Alice starts first):
        //  + On Alice's turn, she has to remove (any non-empty substring) from s that contains (an ("odd") number of vowels).
        //  + On Bob's turn, he has to remove (any non-empty substring) from s that contains (an ("even") number of vowels).
        //- The first player who cannot make a move on their turn loses the game. We assume that both Alice and Bob play optimally.
        //* Return true if (Alice wins the game), and false otherwise.
        //The English vowels are: a, e, i, o, and u.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s consists only of lowercase English letters.
        //
        //** Brainstorm
        //- Count số lượng vowels
        //  + Xem nó lẻ hay chẵn
        //- Lẻ:
        //  + Alice
        //- Chẵn:
        //Ex:
        //6 --> 5
        //  + Bob
        //
    }
}
