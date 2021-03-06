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

        //B??i n??y t?? duy nh?? sau:
        //1, V?? eta, ate --> C?? th??? chuy???n th??nh c??ng 1 chu???i nh??? ph??n 0001011 GI???NG NHAU:
        //--> Ta ho??n to??n c?? th??? ?????y group th??nh 1 hash map --> ????? t??nh to??n.
        //2, T???i ??u ??? ????y l?? ph???n :
        //2.1, cast(array) to (String)
        //--> N???u ta d??ng (char to String) th?? t???c ????? s??? ???????c c???i thi???n h??n
    }
}
