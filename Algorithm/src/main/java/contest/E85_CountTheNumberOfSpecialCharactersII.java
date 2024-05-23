package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E85_CountTheNumberOfSpecialCharactersII {

    public static int numberOfSpecialChars(String word) {
        HashMap<Character, Integer> firstIndex=new HashMap<>();
        HashMap<Character, Integer> lastIndexLowercase=new HashMap<>();
        int n=word.length();
        int rs=0;

        for(int i=0;i<n;i++){
            char curChar=word.charAt(i);
            if(curChar>='a'&&curChar<='z'){
                lastIndexLowercase.put(curChar, i);
            }
            if(firstIndex.containsKey(curChar)){
                continue;
            }
//            if('A'<=curChar&&curChar<='B'&&firstIndex.contains((char)(curChar+32))){
//                rs++;
//            }
            firstIndex.put(curChar, i);
        }
        for(char c: lastIndexLowercase.keySet()){
            char upperCase= (char)(c-32);
            if(!firstIndex.containsKey(upperCase)){
                continue;
            }
            if(firstIndex.get(upperCase)>lastIndexLowercase.get(c)){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string word. A letter c is called special
        // if it appears both in lowercase and uppercase in word,
        // and every lowercase occurrence of c appears before the first uppercase occurrence of c.
        //Return the number of special letters in word.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        String s="aaAbcBC";
        System.out.println(numberOfSpecialChars(s));
    }
}
