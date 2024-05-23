package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E94_valid_word {

    public static boolean isValid(String word) {
        //A word is considered valid if:
        //
        //It contains a minimum of 3 characters.
        //It consists of the digits 0-9, and the uppercase and lowercase English letters. (Not necessary to have all of them.)
        //It includes at least one vowel.
        //It includes at least one consonant.
        if(word.length()<3){
            return false;
        }
        int countVowel=0;
        int countConsonant=0;
        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('A');
        vowels.add('e');
        vowels.add('E');
        vowels.add('i');
        vowels.add('I');
        vowels.add('o');
        vowels.add('O');
        vowels.add('u');
        vowels.add('U');
        for(char c: word.toCharArray()){
            if(!('0'<=c && c<='9') && !('a'<=c && c<='z') && !('A'<=c && c<='Z')){
                return false;
            }
            if(vowels.contains(c)){
                countVowel++;
            }else if(c>'9'||c<'0'){
                countConsonant++;
            }
        }
        return countVowel>=1&&countConsonant>=1;
    }

    public static void main(String[] args) {
        String s= "AhI";
//        String s= "UuE6";
        System.out.println(isValid(s));
    }
}
