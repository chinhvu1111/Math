package interviews;

public class E36_MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int left[]=new int[26];
        int right[]=new int[26];
        int count[]=new int[26];
        int start=0;
        int end=t.length()-1;

        for(int i=0;i<t.length();i++){
            right[t.charAt(i)-'a']++;
            left[s.charAt(i)-'a']++;
            count[i]=left[s.charAt(i)-'a'];
        }
        for(int i=0;i<t.length();i++){

        }
        return null;
    }

    public static void main(String[] args) {
        String s="ADOBECODEBANC";
        String s1="ABC";
    }
}
