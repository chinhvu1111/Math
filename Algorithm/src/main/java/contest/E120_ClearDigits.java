package contest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E120_ClearDigits {

    public static String clearDigits(String s) {
        int n=s.length();
        boolean[] visited=new boolean[n];
        Stack<Integer> queue=new Stack<>();

        for(int i=0;i<n;i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                visited[i]=true;
//                int j=i+1;
//                while(j<n&&s.charAt(j)>='0'&&s.charAt(j)<='9'){
//                    j++;
//                }
//                if(j<n){
//                    visited[j]=true;
//                }
                if(!queue.isEmpty()){
                    visited[queue.pop()]=true;
                }
            }else{
                queue.add(i);
            }
        }
        StringBuilder rs=new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
//        String s = "abc";
//        String s = "cb34";
        String s = "ag3";
        System.out.println(clearDigits(s));
    }
}
