package leetcode_medium_greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class E3_RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {
        int dp[]=new int[27];
        Arrays.fill(dp, -1);
        int n=s.length();
        ArrayList<Character> rs=new ArrayList<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            int cI=c-'a';

            if(dp[cI]!=-1&&(dp[cI]+1>rs.size()-1||rs.get(dp[cI])>rs.get(dp[cI]+1))){
                rs.remove(dp[cI]);
                rs.add(c);
                int currentIndex=rs.size()-1;
                dp[cI]=currentIndex;
            }else if(dp[cI]==-1){
                rs.add(c);
                int currentIndex=rs.size()-1;
                dp[cI]=currentIndex;
            }
        }
        StringBuilder rsStr=new StringBuilder();

        for(char e: rs){
            rsStr.append(e);
        }
        return rsStr.toString();
    }

    public static void main(String[] args) {
//        String s="bcabc";
        String s="cbacdcbc";
        System.out.println(removeDuplicateLetters(s));

    }
}
