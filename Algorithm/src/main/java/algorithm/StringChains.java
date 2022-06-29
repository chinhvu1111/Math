package algorithm;

import java.util.*;

public class StringChains {

    public static int longestChain(List<String> words) {
        // Write your code here
        HashMap<String, Integer> hashMap=new HashMap<>();
        Collections.sort(words, (t, t1) -> {
            return t.length()-t1.length();
        });
        int n=words.size();
        int rs=0;

        for(int i=0;i<n;i++){
            String word=words.get(i);
            Integer currentValue = null;

            if(word.length()>1){
                for(int j=0;j<word.length();j++){
                    String tranformedWord=removeCharAt(word, j);
                    currentValue=hashMap.get(tranformedWord);

                    if(currentValue!=null){
                        currentValue++;
                        break;
                    }
                }
                if(currentValue==null){
                    currentValue=1;
                }
            }else{
                currentValue=1;
            }
            hashMap.put(word, currentValue);
            if(currentValue!=null){
                rs=Math.max(rs, currentValue);
            }
        }
        return rs;
    }

    public static String removeCharAt(String s,int index){
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<s.length();i++){
            if(i!=index){
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
//        String[] input=new String[]{"a", "b", "ba", "bca", "bda", "bdca"};
//        String[] input=new String[]{"a", "b", "ba", "bca"};
//        String[] input=new String[]{"a", "and", "an", "bear"};
//        String[] input=new String[]{"a"};
        String[] input=new String[]{"zxb", "bca", "bda", "bdca", "zxbe"};
//        String[] input=new String[]{};
        List<String> list=new ArrayList<>();

        for(int i=0;i<input.length;i++){
            list.add(input[i]);
        }
        System.out.println(longestChain(list));
    }

}
