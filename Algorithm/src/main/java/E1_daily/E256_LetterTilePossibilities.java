package E1_daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class E256_LetterTilePossibilities {

    public static int solution(int[] countChar, HashSet<Character> listChars){
        int totalCount=0;
//        if(index==length){
////            set.add(s);
//            return;
//        }
        //Time: 26
        for(Character c: listChars){
            if(countChar[c-'A']==0){
                continue;
            }
            totalCount++;
            countChar[c-'A']--;
            totalCount+=solution(countChar, listChars);
            countChar[c-'A']++;
        }
        return totalCount;
    }

    public static int numTilePossibilities(String tiles) {
        int[] charCount=new int[26];
        int n=tiles.length();
        HashSet<Character> listChars=new HashSet<>();

        for(int i=0;i<n;i++){
            charCount[tiles.charAt(i)-'A']++;
            listChars.add(tiles.charAt(i));
        }
        return solution(charCount, listChars);
    }

    public static void main(String[] args) {
        //** Requirement
        //You have n tiles, where each tile has (one letter tiles[i]) printed on it.
        //* Return (the number of possible non-empty sequences of letters) you can make using (the letters) printed on (those tiles).
        //
        //Example 1:
        //
        //Input: tiles = "AAB"
        //Output: 8
        //Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= tiles.length <= 7
        //tiles consists of (uppercase) English letters.
        //
        //* Brainstorm:
        //- Backtrack ==> count[chars]
        //- Count chars => backtrack + hashtable
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //- Count ==> Luôn distinct + create new distinct string
        //- For each loop ==> count++ (with the depth == n)
        //
        //1.3, Complexity
        //- Time: O(n!)
        //- Space: O(n)
        //
        //#Reference:
        //1904. The Number of Full Rounds You Have Played
        //2748. Number of Beautiful Pairs
        //2653. Sliding Subarray Beauty
        //
        String tiles = "AAB";
        System.out.println(numTilePossibilities(tiles));
    }
}
