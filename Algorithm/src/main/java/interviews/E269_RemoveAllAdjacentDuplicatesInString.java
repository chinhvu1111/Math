package interviews;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class E269_RemoveAllAdjacentDuplicatesInString {

    public static String removeDuplicates(String s) {
        Deque<Character> sChar=new LinkedList<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            if(sChar.isEmpty() || sChar.getLast() != s.charAt(i)){
                sChar.add(s.charAt(i));
                continue;
            }
            while (!sChar.isEmpty()&&sChar.getLast()==s.charAt(i)){
                sChar.removeLast();
            }
        }
        StringBuilder rs=new StringBuilder();

        while (!sChar.isEmpty()){
            rs.append(sChar.removeFirst());
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }
}
