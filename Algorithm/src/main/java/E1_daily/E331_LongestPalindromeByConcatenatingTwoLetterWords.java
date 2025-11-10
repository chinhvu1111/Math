package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class E331_LongestPalindromeByConcatenatingTwoLetterWords {

    public static int longestPalindrome(String[] words) {
        HashMap<String, Integer> mapCount=new HashMap<>();

        for (String word: words){
            mapCount.put(word, mapCount.getOrDefault(word, 0)+1);
        }
        int maxOddCount=0;
        int totalEvenCount=0;
//        System.out.println(mapCount);
        HashSet<String> visited=new HashSet<>();
        for(Map.Entry<String, Integer> e: mapCount.entrySet()){
//            if(reversedStr.equals(e.getKey())){
//                totalEvenCount+=e.getValue();
//            }
            String reversedStr = new StringBuilder(e.getKey()).reverse().toString();
            boolean isEqual = e.getKey().equals(reversedStr);

            if(!mapCount.containsKey(reversedStr)||(isEqual&&e.getValue()==1)){
                if(isEqual){
                    maxOddCount=Math.max(maxOddCount, e.getKey().length());
                }
                continue;
            }
            if(visited.contains(reversedStr)||visited.contains(e.getKey())){
                continue;
            }
            if(isEqual){
                if(e.getValue()%2==0){
                    totalEvenCount+=e.getValue()/2;
                }else{
                    totalEvenCount+=(e.getValue()-1)/2;
                    maxOddCount=Math.max(maxOddCount, e.getKey().length());
                }
            }else{
                int minCount = Math.min(e.getValue(), mapCount.get(reversedStr));
                totalEvenCount+=minCount;
//                System.out.println(e.getKey());
            }
            visited.add(reversedStr);
            visited.add(e.getKey());
        }
//        System.out.println(totalEvenCount);
//        System.out.println(maxOddCount);
        return maxOddCount+totalEvenCount*4;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of strings words.
        //- Each element of words consists of (two lowercase English letters).
        //- Create (the longest possible palindrome) by (selecting some elements from words)
        // and concatenating them in any order.
        //- (Each element) can be selected (at most once).
        //* Return (the length of the longest palindrome) that you can create.
        //- If it is impossible to create any palindrome, return 0.
        //- A palindrome is a string that reads the same forward and backward.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= words.length <= 10^5
        //words[i].length == 2
        //words[i] consists of lowercase English letters.
        //  + words.length <= 10^5 ==> Time: O(n*k)
        //  + words[i].length == 2
        //  ==> It has "only" (2 characters)
        //
        //* Brainstorm:
        //- A palindrome:
        //  + abba
        //  + abcba
        //  ==> Choose the middle string as (the longest string)
        //- We need to create (ab|ba)
        //- We should not create (the palindrome)
        //Ex:
        //a + a = 0
        //a + a + a + a =0
        //- Count of the characters is even
        //  + ==> Add
        //- Odd ==> Get max with the odd count
        //
        //1.1, Case
        //
//        String[] words = {"lc","cl","gg"};
//        String[] words = {"ab","ty","yt","lc","cl","ab"};
//        String[] words = {"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"};
        //dd: 5
        //aa: 3
        //bb: 3
        //cc: 3
        //==> 4+2+2+2 = 10
        //==> rs=10*2+2
        String[] words = {"em","pe","mp","ee","pp","me","ep","em","em","me"};
        //pe, ep
        //em, me
        //- count(em) != count(me)
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(longestPalindrome(words));
    }
}
