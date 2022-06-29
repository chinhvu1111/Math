package interviews;

import java.util.*;

public class E55_FindAllAnagramsInAString {

    public static List<Integer> findAnagrams(String s, String p) {
        int arr[]=new int[27];
        int temp[]=new int[27];
        List<Integer> rs=new ArrayList<>();
        Deque<Character> characters=new LinkedList<>();

        for(int i=0;i<p.length();i++){
            arr[p.charAt(i)-'a']++;
            temp[s.charAt(i)-'a']++;
            characters.addFirst(s.charAt(i));
        }
        if(Arrays.equals(temp, arr)){
            rs.add(0);
        }
        for(int i=p.length();i<s.length();i++){
            if(!characters.isEmpty()){
                temp[characters.pollLast()-'a']=0;
            }
            characters.addFirst(s.charAt(i));
            temp[characters.getFirst()-'a']++;
            if(Arrays.equals(arr, temp)){
                rs.add(i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        String s="cbaebabacd";
        String p="abc";
        System.out.println(findAnagrams(s, p));
    }
}
