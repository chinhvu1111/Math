package E1_hashmap;

import java.util.HashMap;
import java.util.Objects;

public class E14_WordPattern {

    public static boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> charToLetter=new HashMap<>();
        HashMap<String, Character> letterToChar=new HashMap<>();
        String[] letters=s.split(" ");
        if(pattern.length()!=letters.length){
            return false;
        }

        for(int i=0;i<pattern.length();i++){
            char curChar=pattern.charAt(i);

            if(!charToLetter.containsKey(curChar)){
                if(letterToChar.containsKey(letters[i])&&!letterToChar.get(letters[i]).equals(curChar)){
                    return false;
                }
                charToLetter.put(pattern.charAt(i), letters[i]);
                letterToChar.put(letters[i], curChar);
            }else if(!charToLetter.get(curChar).equals(letters[i])){
                return false;
            }
        }
        return true;
    }

    public static boolean wordPatternSingleHashIndex(String pattern, String s) {
        HashMap<Object, Integer> charToIndex=new HashMap<>();
        String[] letters=s.split(" ");
        if(pattern.length()!=letters.length){
            return false;
        }

        for(int i=0;i<pattern.length();i++){
            if(!charToIndex.containsKey(letters[i])){
                charToIndex.put(letters[i], i);
            }
            //a, a
            //word, word1
            //+ map['a']=0
            //+ map['a']=1
            //a, b
            //word, word
            //+ map['a']=0
            //+ map['b']=1 ==> Nhưng word giống nhau
            //
            if(!charToIndex.containsKey(pattern.charAt(i))){
                charToIndex.put(pattern.charAt(i), i);
            }
            if(!Objects.equals(charToIndex.get(pattern.charAt(i)), charToIndex.get(letters[i]))){
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba", s = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s));
        //** Requirement
        //- Given a pattern and a string s,
        //* Return true: if s follows the same pattern.
        //Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
        //* character -> letter : 1:1
        //==> Thế nên cần check 2 directions
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= pattern.length <= 300
        //pattern contains only lower-case English letters.
        //1 <= s.length <= 3000
        //s contains only lowercase English letters and spaces ' '.
        //s does not contain any leading or trailing spaces.
        //All the words in s are separated by a single space.
        //
        //- Brainstorm
        //- Sử dụng 2 hashmaps để check 2 directions
        //+ Split string ra thành n phần
        //  + Put dần + check là được.
        //
        //-
        // a   b   b   a
        // dog cat cat dog
        //
        //- Idea 2
        //- Dùng 1 hashmap để lưu thông tin
        //  + hashMap sẽ lưu cả word và character cùng trong 1 hashMap : hashMap<Object, Integer>()
        //- Ta sẽ chỉ lưu index đầu tiên khi map
        // 2 cases chủ yếu:
        //Ex:
        //a, a
        //word, word1
        //+ map['a']=0
        //+ map['a']=1
        //a, b
        //word, word
        //+ map['a']=0
        //+ map['b']=1 ==> Nhưng word giống nhau
        //
        //- Nếu index của char[i] và letter[i] lấy ra khác nhau --> return false.
        //
        System.out.println(wordPatternSingleHashIndex(pattern, s));
        //#Reference:
        //205. Isomorphic Strings
        //291. Word Pattern II
    }
}
