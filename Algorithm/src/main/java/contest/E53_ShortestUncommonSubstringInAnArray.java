package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class E53_ShortestUncommonSubstringInAnArray {

    public static String[] shortestSubstrings(String[] arr) {
        int n=arr.length;
        HashMap<String, HashSet<Integer>> strToIndexArr=new HashMap<>();

        for(int i=0;i<n;i++){
            String curStr=arr[i];
            int m=curStr.length();
            for(int j=0;j<m;j++){
                for(int h=j+1;h<=m;h++){
                    String subStr=curStr.substring(j, h);
//                    System.out.printf("%s, ", subStr);
                    HashSet<Integer> oldIndex=strToIndexArr.get(subStr);
                    if(oldIndex==null){
                        oldIndex=new HashSet<>();
                    }
                    oldIndex.add(i);
                    strToIndexArr.put(subStr, oldIndex);
                }
//                System.out.println();
            }
//            System.out.println(strToIndexArr);
        }
        String[] rs=new String[n];
        for(int i=0;i<n;i++){
            String curStr=arr[i];
            int m=curStr.length();
            TreeSet<String> curRs=new TreeSet<>((a, b) ->{
                if(a.length()!=b.length()){
                    return a.length()-b.length();
                }
                return a.compareTo(b);
            });

            for(int j=0;j<m;j++){
                for(int h=j+1;h<=m;h++){
                    String subStr=curStr.substring(j, h);
                    HashSet<Integer> oldIndex=strToIndexArr.get(subStr);
                    if(oldIndex.size()==1){
                        curRs.add(subStr);
                    }
                }
            }
            if(!curRs.isEmpty()){
                rs[i]=curRs.first();
            }else{
                rs[i]="";
            }
        }
//        for (int i = 0; i < rs.length; i++) {
//            System.out.println(rs[i]);
//        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an array arr of (size n consisting of non-empty strings).
        //- Find a string array answer of size n such that:
        //+ answer[i] is the shortest substring of arr[i] that does not occur as a substring in any other string in arr.
        //+ If multiple such substrings exist, answer[i] should be the (lexicographically smallest).
        //+ And if no such substring exists, answer[i] should be (an empty string).
        //* Return the array answer.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == arr.length
        //2 <= n <= 100
        //1 <= arr[i].length <= 20
        //arr[i] consists only of lowercase English letters.
        //==> Số khá nhỏ ==> Nên có thể cache được
        //
        //- Brainstorm
        //- prefix search?
        //
//        String[] arr = {"cab","ad","bad","c"};
//        String[] arr = {"abc","bcd","abcd"};
        String[] arr = {"gfnt","xn","mdz","yfmr","fi","wwncn","hkdy"};
        shortestSubstrings(arr);
    }
}
