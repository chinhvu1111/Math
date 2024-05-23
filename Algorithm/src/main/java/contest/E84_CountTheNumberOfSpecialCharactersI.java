package contest;

import java.util.HashSet;

public class E84_CountTheNumberOfSpecialCharactersI {

    public static int numberOfSpecialChars(String word) {
        HashSet<Character> set=new HashSet<>();
        HashSet<Character> visited=new HashSet<>();

        for(char c: word.toCharArray()){
            set.add(c);
        }
        int rs=0;
        for(char c: word.toCharArray()){
            if(visited.contains(c)){
               continue;
            }
            if(c>='a'&&c<='z'&&set.contains((char)(c-32))){
                rs++;
            }
            visited.add(c);
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
        //* Return the number of special letters in word.
        System.out.println('a'-'A');
        String s="aaAbcBC";
        System.out.println(numberOfSpecialChars(s));
    }
}
