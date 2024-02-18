package contest;

import java.util.*;

public class E41_ApplyOperationsToMakeStringEmpty {

    public static String lastNonEmptyString(String s) {
        int n=s.length();
        HashMap<Character, Integer> mapCount=new HashMap<>();
        int maxCount=Integer.MIN_VALUE;
        HashSet<Character> charAtLastStep=new HashSet<>();

        for(char c: s.toCharArray()){
            mapCount.put(c, mapCount.getOrDefault(c, 0)+1);
        }
        for(Map.Entry<Character, Integer> entry: mapCount.entrySet()){
            maxCount=Math.max(entry.getValue(), maxCount);
        }
        for(Map.Entry<Character, Integer> entry: mapCount.entrySet()){
            if(entry.getValue()==maxCount){
                charAtLastStep.add(entry.getKey());
            }
        }
        StringBuilder rs=new StringBuilder();
        HashSet<Character> visited=new HashSet<>();

        for(int i=n-1;i>=0;i--){
            char c=s.charAt(i);
            if(charAtLastStep.contains(c)&&!visited.contains(c)){
                rs.append(c);
                visited.add(c);
            }
        }
        rs.reverse();
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //You are given a string s.
        //Consider performing the following operation until s becomes empty:
        //For every alphabet character from 'a' to 'z', remove the first occurrence of that character in s (if it exists).
        //* Return the value of the string s right before applying the last operation.
        //-> Return string at last step.
        //Ex:
        //
        //Input: s = "aabcbbca"
        //Output: "ba"
        //Explanation: We do the following operations:
        //- Remove the underlined characters s = "aabcbbca". The resulting string is s = "abbca".
        //- Remove the underlined characters s = "abbca". The resulting string is s = "ba".
        //- Remove the underlined characters s = "ba". The resulting string is s = "".
        //The string just before the last operation is "ba".
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= s.length <= 5 * 105
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //s=aabcbbca
        //- count max nhất + bằng nhau --> Sẽ là string cuối cùng
//        String s = "aabcbbca";
//        String s = "abcd";
//        String s = "";
//        String s = "a";
        String s = "aaaa";
        System.out.println(lastNonEmptyString(s));
    }
}
