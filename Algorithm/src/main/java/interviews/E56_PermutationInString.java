package interviews;

import java.util.Arrays;

public class E56_PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }

        int left[]=new int[26];
        int right[]=new int[26];

        for(int i=0;i<s1.length();i++){
            left[s1.charAt(i)-'a']++;
            right[s2.charAt(i)-'a']++;
        }
        int start=0;
        int end=s1.length()-1;

        if(Arrays.equals(left, right)){
            return true;
        }
        while (end+1<s2.length()){
            right[s2.charAt(start)-'a']--;
            right[s2.charAt(end+1)-'a']++;

            if(Arrays.equals(left, right)){
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    public static void main(String[] args) {
//        String s1="ab";
//        String s2="eidbaooo";
        String s1="ab";
        String s2="eidbaooo";

        System.out.println(checkInclusion(s1, s2));
    }
}
