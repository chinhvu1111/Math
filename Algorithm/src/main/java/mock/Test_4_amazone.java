package mock;

import java.util.Arrays;

public class Test_4_amazone {

    public static int firstUniqChar(String s) {
        int[] chars=new int[26];
        Arrays.fill(chars, -1);
        int n=s.length();

        for(int i=0;i<n;i++){
            if(chars[s.charAt(i)-'a']==-1){
                chars[s.charAt(i)-'a']=0;
            }
            chars[s.charAt(i)-'a']++;
        }
        println(chars);

        for(int i=0;i<n;i++){
            if(chars[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }

    public static void println(int[] arr){
        int n= arr.length;
        for(int i=0;i<n;i++){
            System.out.printf("%s, ",arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        String s="aabb";
        String s="leetcode";
        System.out.println(firstUniqChar(s));
    }
}
