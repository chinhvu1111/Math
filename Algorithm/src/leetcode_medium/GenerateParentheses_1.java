/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author chinhvu
 */
public class GenerateParentheses_1 {
    
    public static Stack<Character> s;
    
    //Đôi khi code for sẽ chậm hơn vì số bước mình đi sẽ nhiều hơn
    //So với backtrack
//    public static List<String> generateParenthesis(int n) {
//        List<String> rs=new LinkedList<String>();
//        HashSet<String> finalRs=new HashSet<>();
//        
//        rs.add("()");
//        for(int i=1;i<n;i++){
//            for(int l=0;l<rs.size();l++){
//                String currentStr=rs.get(l);
////                rs.remove(l);
////                String nextStr=currentStr;
////                String tmp="";
//                List<Integer> changes=new ArrayList<>();
//                changes.add(0);
//                
//                for(int j=0;j<currentStr.length();j++){
////                    tmp+=currentStr.charAt(j);
//                    
//                    if(j+1<currentStr.length()
//                            &&currentStr.charAt(j)==')'
//                            &&currentStr.charAt(j+1)=='('||j==currentStr.length()-1){
//                        changes.add(j);
//                    }
//                }
//                finalRs.add("()"+currentStr);
//                
//                for(int k=0;k<changes.size();k++){
//                    for(int h=k+1;h<changes.size();h++){
//                        String s=currentStr.substring(0,changes.get(k))+"("
//                                +currentStr.substring(changes.get(k),changes.get(h)+1)+")"
//                                +currentStr.substring(changes.get(h)+1);
//                        finalRs.add(s);
//                    }
//                }
//            }
//            rs.clear();
//            rs.addAll(finalRs);
//            finalRs.clear();
//        }
//        rs.forEach((t) -> {
//            System.out.println(t);
//        });
//        return rs;
//    }
    
    public static List<String> generateParenthesis(int n) {
        List<String> ans=new ArrayList<>();
        
        if(n==0){
            return ans;
        }
        
        getParenthesis(n,n,ans,"");
        return ans;
    }
    
    private static void getParenthesis(int n, int n0, List<String> ans, String string) {
        if(n==0&&n0==0){
            ans.add(string);
        }
        if(n>0){
            getParenthesis(n-1, n0, ans, string+"(");
        }
//        if(n0>0){
//            getParenthesis(n, n0-1, ans, string+")");
//        }

        //Đoạn này thú vị đây (Về bài toán dấu ngoặc)
        //Bài xảy ra lỗi khi: )( : Tức là sẽ có trường hợp số lượng "(">")"
        //--> Solution: ta luôn cho số lượng "(">")" + (số lần "("==")")
        //Ta có công thức như sau:
        
        // Mở "(" thì cần đóng ")"
        if(n0>n){
            getParenthesis(n, n0-1, ans, string+")");
        }
    }
    
    public static void main(String[] args) {
        int n=2;
        generateParenthesis(n);
    }
}
