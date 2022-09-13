package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E134_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> queue=new LinkedList<>();
        int[] mark =new int[256];
        Arrays.fill(mark, -1);
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            if(mark[s.charAt(i)]!=-1){
                rs=Math.max(rs, queue.size());
//                System.out.printf("%s,", i);
                while (!queue.isEmpty()&&queue.peek()!=s.charAt(i)){
                    mark[queue.poll()]=-1;
                }
                if(!queue.isEmpty()){
                    mark[queue.poll()]=-1;
                }
            }
            mark[s.charAt(i)]=1;
            queue.add(s.charAt(i));
        }
//        queue.forEach(c-> System.out.printf("%s,", c));
        rs=Math.max(rs, queue.size());
        return rs;
    }

    public static int lengthOfLongestSubstringOptimize(String s) {
        char[] queue=new char[s.length()];
        int front=0,rear=0;
        int length=0;
        int[] mark =new int[256];
        Arrays.fill(mark, -1);
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            if(mark[s.charAt(i)]!=-1){
                rs=Math.max(rs, length);
//                System.out.printf("%s,", i);
//                System.out.println(String.valueOf(queue));
                while (length>0&&queue[front]!=s.charAt(i)){
                    mark[queue[front++]]=-1;
                    length--;
                }
                if(length>0){
//                    mark[queue[front++]]=-1;
                    front++;
                    length--;
                }
            }
            mark[s.charAt(i)]=1;
            queue[rear++]=s.charAt(i);
            length++;
        }
//        queue.forEach(c-> System.out.printf("%s,", c));
        rs=Math.max(rs, length);
        return rs;
    }

    public static void main(String[] args) {
//        String s="abcabcbb";
//        String s="bbbbb";
//        String s="pwwkew";
//        String s="dvdf";
        String s="hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        String s="p";
//        String s=" ";
//        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringOptimize(s));
    }
}
