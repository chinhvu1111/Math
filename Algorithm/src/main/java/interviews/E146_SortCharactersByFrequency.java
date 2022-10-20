package interviews;

import java.util.Arrays;
import java.util.Comparator;

public class E146_SortCharactersByFrequency {

    public static String frequencySort(String s) {
        int[] count=new int[123];
        int n=s.length();
        Character[] arr=new Character[s.length()];

        for(int i=0;i<n;i++){
            arr[i]=s.charAt(i);
        }
        for(int i=0;i<n;i++){
            count[s.charAt(i)]++;
        }
        return null;
    }

    public static void main(String[] args) {
//        String s="tree";
        String s="loveleetcode";
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
        System.out.println(frequencySort(s));
    }
}
