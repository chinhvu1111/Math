package E1_hashmap;

import java.util.HashMap;
import java.util.Objects;

public class E15_WordPatternII {

    public static boolean solution(
            int index, int curIndex, String pattern, String s,
            HashMap<Character, Integer> charToIndex, HashMap<String, Integer> wordToIndex){
        StringBuilder str=new StringBuilder();
        char curChar=pattern.charAt(curIndex);
        int remainingPart= pattern.length()-curIndex;

        if(curIndex==pattern.length()-1){
            String curString=s.substring(index);
//            System.out.printf("Index: %s, str: %s\n", index, curString);
            boolean isPush=false;
            if(!wordToIndex.containsKey(curString)){
                wordToIndex.put(curString, curIndex);
                isPush=true;
            }
            boolean curRs=Objects.equals(charToIndex.get(curChar), wordToIndex.get(curString));
//            System.out.printf("Char to Index: %s\n",charToIndex);
//            System.out.printf("Word to Index: %s\n",wordToIndex);
            if(isPush){
                wordToIndex.remove(curString);
            }
            return curRs;
        }
        //map: letter[curIndex] = word
        //map: word=letter[curIndex]
        //i=n-1
        //abab(index=n-1)
        //as|da|sd|as
        boolean curRs=false;

        //Ex:
        //String pattern = "aba", s = "aaaa";
        //
        //i=0, remainingPart=3: i <= 0 to 1
        //i=1, remainingPart=2: i <= index+1 (1/2) to 2
        //i=3, remainingPart=1: i <= index+1 (2/3) to 3
//        System.out.printf("CurIndex: %s, Range: %s to %s\n", curIndex, index, s.length()-remainingPart);
        for(int i=index;i<=s.length()-remainingPart;i++){
            str.append(s.charAt(i));
            String curString=str.toString();
//            System.out.println(curString);
            boolean isPush=false;
            if(!wordToIndex.containsKey(curString)){
                wordToIndex.put(curString, curIndex);
                isPush=true;
            }
//            System.out.printf("CurIndex: %s, sub: %s to %s, valid: %s\n", curIndex, index, i, Objects.equals(charToIndex.get(curChar), wordToIndex.get(curString)));
//            System.out.printf("Char to Index: %s\n",charToIndex);
//            System.out.printf("Word to Index: %s\n",wordToIndex);
//            System.out.println();
            if(Objects.equals(charToIndex.get(curChar), wordToIndex.get(curString))){
                curRs=solution(i+1, curIndex+1, pattern, s, charToIndex, wordToIndex);
            }
            if(curRs){
                return true;
            }
            if(isPush){
                wordToIndex.remove(curString);
            }
        }
        return false;
    }

    public static boolean wordPatternMatch(String pattern, String s) {
        HashMap<Character, Integer> charToIndex=new HashMap<>();
        HashMap<String, Integer> wordToIndex=new HashMap<>();

        for(int i=0;i<pattern.length();i++){
            if(!charToIndex.containsKey(pattern.charAt(i))){
                charToIndex.put(pattern.charAt(i), i);
            }
        }
        return solution(0, 0, pattern, s, charToIndex, wordToIndex);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a pattern and a string s,
        //* Return true: if s follows the same pattern.
        //Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
        //* character -> letter : 1:1
        //==> Thế nên cần check 2 directions
        //- Bài này khác 1 chỗ là các letters được tuỳ ý lựa chọn thay vì cách nhau bằng space như bài trước
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= pattern.length, s.length <= 20
        //pattern and s consist of only lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //Input: pattern = "abab", s = "redblueredblue"
        //Output: true
        //Explanation: One possible mapping is as follows:
        //'a' -> "red"
        //'b' -> "blue"
        //- Ở đây nếu ta có các letters thì sẽ mất time = O(n) để check xem nó có valid không
        //
        //- Chung chung là ta sẽ chia s thành nhiều nhất 20 parts ==> Sau đó đi check valid
        //Ex: red|blue|red|blue
        //- Mình có thể sử dụng hashmap index của pattern để có thể cut branch được không?
        //==> Hoàn toàn được khi nếu khác cái là return false.
        //Ex:
        //Input: pattern = "abab", s = "redblueredblue"
        //hash['a']=0
        //hash['b']=1 ==> Chỉ lưu giá trị đầu tiên
        //- Cái này ta sẽ làm backtrack + cut branch
        //
        //- curIndex sẽ thể hiện số lượng parts đã có.
        //- length of pattern : là số lượng parts ta cần
        //Ex:
        //pattern=abab
        //s = "|redblu|eredbl|ue"
        //0|(curIndex=1)|2|3
        //length=x-(curIndex)
        //==> Số lượng char còn lại phải >= length pattern/ số lượng parts cần chia còn lại còn lại
        //- Chặt khoảng như thế nào?
        //Ex:
        //pattern=a (len=1)
        //s=asdas
        //Ex:
        //pattern=ab (len=2)
        //s=asdas
        //
        //** Kinh nghiệm:
        //- Để debug thì:
        //+ Chuẩn bị rõ cases
        //+ Viết cho việc nó đi dần dần trong code là được.
        //Ex:
        //String pattern = "aba", s = "aaaa";
        //
        //i=0, remainingPart=3: i <= 0 to 1
        //i=1, remainingPart=2: i <= index+1 (1/2) to 2
        //i=3, remainingPart=1: i <= index+1 (2/3) to 3
        //
        //- Mỗi step mình sẽ đặt 1 character map với 1 word
//        String pattern = "abab", s = "redblueredblue";
//        String pattern = "aaaa", s = "asdasdasdasd";
//        String pattern = "ab", s = "cd";
        String pattern = "aba", s = "aaaa";
        //pattern: abab
        //red|blue|red|blue
        //r|edblue|r|edblue
        System.out.println(wordPatternMatch(pattern, s));
        //#Reference:
        //804. Unique Morse Code Words
        //1396. Design Underground System
        //1796. Second Largest Digit in a String
    }
}
