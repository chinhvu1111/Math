package interviews;

import java.util.*;

public class E52_GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        int n=strs.length;
        byte[] buff;

        for(int i=0;i<n;i++){
            buff=new byte[27];
            String currentWord=strs[i];

            for(int j=0;j<currentWord.length();j++){
                buff[currentWord.charAt(j)-'a']++;
            }
            String str= Arrays.toString(buff);
            List<String> currentList=hashMap.get(str);

            if(currentList!=null){
                currentList.add(currentWord);
            }else{
                currentList=new ArrayList<>();
                currentList.add(currentWord);
            }
            hashMap.put(Arrays.toString(buff), currentList);
        }
        rs.addAll(hashMap.values());
        return rs;
    }

    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length==0)
            return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] ch = new char[26];
            for(char c: s.toCharArray()){
                ch[c-'a']++;
            }
            String str = new String(ch);
            map.computeIfAbsent(str, k -> new ArrayList<>());
            map.get(str).add(s);
        }
        res.addAll(map.values());
        return res;
    }

    public static List<List<String>> groupAnagramsOptimized1(String[] strs) {
        HashMap<String, List<String>> hashMap=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        if(strs.length==0)
            return rs;
        int n=strs.length;
        char[] buff;

        for(int i=0;i<n;i++){
            buff=new char[27];
            String currentWord=strs[i];

            for(int j=0;j<currentWord.length();j++){
                buff[currentWord.charAt(j)-'a']++;
            }
            String str= new String(buff);
//            List<String> currentList=hashMap.get(str);
//
//            if(currentList!=null){
//                currentList.add(currentWord);
//            }else{
//                currentList=new ArrayList<>();
//                currentList.add(currentWord);
//            }
            hashMap.computeIfAbsent(str, k -> new ArrayList<>());
            hashMap.get(str).add(currentWord);
        }
        rs.addAll(hashMap.values());
        return rs;
    }

    public static void main(String[] args) {
        String input[]=new String[]{"eat","tea","tan","ate","nat","bat"};

        List<List<String>> rs=groupAnagrams(input);
        List<List<String>> rs1=groupAnagramsOptimized(input);
        List<List<String>> rs2=groupAnagramsOptimized1(input);
        System.out.println("");

        //Bài này tư duy như sau:
        //1, Vì eta, ate --> Có thể chuyển thành cùng 1 chuỗi nhị phân 0001011 GIỐNG NHAU:
        //--> Ta hoàn toàn có thể đẩy group thành 1 hash map --> Để tính toán.
        //2, Tối ưu ở đây là phần :
        //2.1, cast(array) to (String)
        //--> Nếu ta dùng (char to String) thì tốc độ sẽ được cải thiện hơn
    }
}
