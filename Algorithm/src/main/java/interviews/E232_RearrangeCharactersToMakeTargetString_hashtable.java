package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E232_RearrangeCharactersToMakeTargetString_hashtable {

    public static int rearrangeCharactersWrongWithouConsecutively(String s, String target) {
        Deque<Character> deque=new LinkedList<>();
        int[] arr=new int[26];
        int[] check=new int[26];

        for(int i=0;i<target.length();i++){
            arr[target.charAt(i)-'a']++;
            check[target.charAt(i)-'a']++;
        }
        int n=s.length();
        int countRs=0;

        for(int i=0;i<n;i++){
            if(check[s.charAt(i)-'a']==0){
                while (!deque.isEmpty()&&check[s.charAt(i)-'a']==0){
                    check[deque.removeLast()-'a']++;
                }
                if(check[s.charAt(i)-'a']!=0){
                    check[s.charAt(i)-'a']--;
                    deque.add(s.charAt(i));
                }
            }else{
                check[s.charAt(i)-'a']--;
                deque.add(s.charAt(i));
            }
//            System.out.println(deque);
            if(deque.size()==target.length()){
                countRs++;

                System.arraycopy(arr, 0, check, 0, 26);
            }
        }

        return countRs;
    }

    public static int rearrangeCharacters(String s, String target) {
        int[] arr=new int[26];
        for(int i=0;i<target.length();i++){
            arr[target.charAt(i)-'a']++;
        }

        int count[]=new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }

        int countRs=Integer.MAX_VALUE;
        for(int i=0;i<26;i++){
            if(arr[i]!=0){
                countRs=Math.min(countRs, count[i]/arr[i]);
            }
        }

        return countRs;
    }

    public static void main(String[] args) {
        String s = "ilovecodingonleetcode", target = "code";
//        String s = "abcba", target = "abc";
        System.out.println(rearrangeCharacters(s, target));
    }
}
