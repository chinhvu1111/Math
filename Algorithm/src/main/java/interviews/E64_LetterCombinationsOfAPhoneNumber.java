package interviews;

import java.util.ArrayList;
import java.util.List;

public class E64_LetterCombinationsOfAPhoneNumber {

    public static List<String> rs;
    public static String[] map;

    public static void execute(int index, String digits, String str){

        if(index>digits.length()-1){
            rs.add(str);
            return;
        }
//        System.out.println("==="+index);
//        System.out.println(digits+" "+(digits.charAt(index)-'0'));
        String currentString=map[digits.charAt(index)-'0'];

        for(int i=0;i<currentString.length();i++){
            execute(index+1, digits, str+currentString.charAt(i));
        }
    }

    public static List<String> letterCombinations(String digits) {
        map=new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        rs=new ArrayList<>();
        if("".equals(digits)){
            return rs;
        }
        execute(0, digits, "");
        return rs;
    }

    public static List<String> letterCombinationsOptimized(String digits) {
        List<String> rs=new ArrayList<>();
        return rs;
    }

    public static void main(String[] args) {
//        String digits="23";
        String digits="";
        List<String> rs=letterCombinations(digits);
        System.out.println(rs);
    }
}
