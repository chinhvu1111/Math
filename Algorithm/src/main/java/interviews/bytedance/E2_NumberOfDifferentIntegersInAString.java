package interviews.bytedance;

import java.util.HashSet;
import java.util.Set;

public class E2_NumberOfDifferentIntegersInAString {

    public static int numDifferentIntegers(String word) {
        int n=word.length();
        StringBuilder prevNum=new StringBuilder();
        HashSet<String> setDistinctStr=new HashSet<>();

        for(int i=0;i<n;i++){
            char ch=word.charAt(i);

            if(ch-'0'>=0&&ch-'0'<=9){
                if(prevNum.length()==1&&prevNum.charAt(0)=='0'){
                    prevNum=new StringBuilder();
                }
                prevNum.append(ch);
            }else if(prevNum.length()!=0){
                setDistinctStr.add(prevNum.toString());
                prevNum=new StringBuilder();
            }
        }
        if(prevNum.length()!=0){
            setDistinctStr.add(prevNum.toString());
        }
        return setDistinctStr.size();
    }

    public static int numDifferentIntegersRefactor(String word) {
        int n=word.length();
        Set<String> hashSet=new HashSet<>();

        for(int i=0;i<n;i++){
            if(Character.isDigit(word.charAt(i))){
                int t=i;
                while (i<n&&Character.isDigit(word.charAt(i))) i++;
                while (t<i-1&&word.charAt(t)=='0') t++;
                hashSet.add(word.substring(t, i));
            }
        }
        return hashSet.size();
    }

    public static void main(String[] args) {
//        String s="a123bc34d8ef34";
//        String s="leet1234code234";
        String s="a1b01c001";
//        String s="0a0";
//        String s="a00";
        System.out.println(numDifferentIntegers(s));
        System.out.println(numDifferentIntegersRefactor(s));
        //
        //#Reference
        //1807. Evaluate the Bracket Pairs of a String
        //2419. Longest Subarray With Maximum Bitwise AND
    }

}
