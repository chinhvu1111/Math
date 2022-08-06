/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_hard;

import java.util.HashMap;

/**
 *
 * @author chinhvu
 */
public class E5_ScrambleString_hard {
    
    public static boolean dp[][];
    public static HashMap<String,Boolean> map;
    public static String s;
    
    //Nếu ta nghĩ như thế này sẽ bị chết trường hợp:
    //g(rea)t
    //r(et)g(a)
    //--> Tách ra sẽ gây sai
    //Cái ta cần là: a + all_swap(b)
    //Chuỗi kết hợp của: A + kết hợp với (B) --> Có thuộc trong chuỗi s2 không?
    
    //Vấn đề chính ở đây là (order)
//    public static boolean solution(int low, int high){
//        if(dp[low][high]){
//            return true;
//        }
//        if(high-low==1){
//            Boolean rs=map.get(s.substring(low, high+1));
//            return rs==null?false:true;
//        }
//        if(high==low){
//            if(map.get(String.valueOf(s.charAt(low)))!=null){
//                return true;
//            }
//            return false;
//        }
//        boolean rs=false;
//        
//        for(int i=low;i<high;i++){
//            rs=rs||(solution(low, i)&&solution(i+1, high));
//        }
//        return dp[low][high]=rs;
//    }
    
//    public static boolean isScramble(String s1, String s2) {
//        int n=s1.length();
//        int m=s2.length();
//        dp=new boolean[n][m];
//        s=s1;
//        
//        map=new HashMap<>();
//        int rs=0;
//        
////        map.put(String.valueOf(s2.charAt(0)), Boolean.TRUE);
//        for(int i=0;i<m;i++){
//            rs+=s1.charAt(i)-s2.charAt(i);
//            String s=null;
//            StringBuilder s3=new StringBuilder();
//            String single=String.valueOf(s2.charAt(i));
//            
//            if(i<m-1){
//                s=s2.substring(i, i+2);
//                s3.append(s);
//                s3.reverse();
//                map.put(s, Boolean.TRUE);
//                map.put(s3.toString(), Boolean.TRUE);
//            }
//            if(map.get(single)==null){
////                map.put(s, Boolean.TRUE);
////                map.put(s3.toString(), Boolean.TRUE);
//                map.put(single, Boolean.TRUE);
//            }
//        }
//        if(rs!=0){
//            return false;
//        }
//        return solution(0, n-1);
//    }
    
    //Vấn đề chính ở đây là (order)
    //Ta cần đi tìm key point của bài toán:
    //Nếu ta chia 2 chuỗi (s1, s2) cùng rule:
    //(0,i)(i+1,n)
    //Kết quả = check (lần lượt đôi một chuỗi con của s1, s2) có là shamble của nhau hay không
    //NOTET: Ta đã quá chú ý vào 1 chuỗi s1 --> Tìm chuỗi s1 --> Không để ý đến việc ta có thể tận dụng s2
    
    public static HashMap<String, Boolean> mapStr;
    
    public static boolean isScramble(String s1, String s2){
        if(mapStr.get(s1+s2)!=null){
            return true;
        }
        
        int n=s1.length();
        
        if(n==0){
            return true;
        }
        if(n==1){
            return s1.equals(s2);
        }
        boolean rs=false;
        
        for(int i=0;i<n-1;i++){
            Boolean left=isScramble(s1.substring(0,i+1), s2.substring(0,i+1));
            Boolean right=false;
            if(left){
                right=isScramble(s1.substring(i+1,n), s2.substring(i+1,n));
                rs=rs||(left&&right);
            }
            if(rs){
                break;
            }
            
            left=isScramble(s1.substring(i+1,n), s2.substring(0,n-i-1));
            if(left){
                right=isScramble(s1.substring(0,i+1), s2.substring(n-i-1,n));
                rs=rs||(left&&right);
            }
            if(rs){
                break;
            }
        }
        if(rs){
            mapStr.put(s1+s2, Boolean.TRUE);
        }
        return rs;
    }
    
    public static void main(String[] args) {
        String s1="great";
        String s2="rgeat";
//        System.out.println(isScramble(s1, s2));
        s1="a";
        s2="a";
//        System.out.println(isScramble(s1, s2));
        
        s1="abc";
        s2="abc";
//        System.out.println(isScramble(s1, s2));
        
//Sai do chỗ map chưa sửa single
        s1="abb";
        s2="bba";
//        System.out.println(isScramble(s1, s2));
//Sai range index (low, i,i+1, high)
        s1="abc";
        s2="acb";
//        System.out.println(isScramble(s1, s2));
        
        s1="aaccd";
        s2="acaad";
//        System.out.println(isScramble(s1, s2));
        s1="great";
        s2="retga";
//        System.out.println(isScramble(s1, s2));
        
        s1="abcdefghijklmnopq";
        s2="efghijklmnopqcadb";
        mapStr=new HashMap<>();
        System.out.println(isScramble(s1, s2));;
    }
}
