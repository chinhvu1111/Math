package interviews.bytedance;

import java.util.HashMap;

public class E22_RomanToInteger_undone {

    public static HashMap<String, Integer> hashMap;

    public static int romanToInt(String s) {
        if(hashMap==null){
            hashMap=new HashMap<>();
            hashMap.put("I", 1);
            hashMap.put("V", 5);
            hashMap.put("X", 10);
            hashMap.put("L", 50);
            hashMap.put("C", 100);
            hashMap.put("D", 500);
            hashMap.put("M", 1000);

            hashMap.put("IV", 4);
            hashMap.put("IX", 9);
            hashMap.put("XL", 40);
            hashMap.put("XC", 90);
            hashMap.put("CD", 400);
            hashMap.put("CM", 900);
        }
        int n=s.length();
        int rs=0;
        for(int i=0;i<n;i++){
            Integer value=null;

            if(i+1<n){
                value=hashMap.get(s.charAt(i)+""+s.charAt(i+1)+"");
            }
            if(value==null){
                value=hashMap.get(s.charAt(i)+"");
            }else{
                i++;
            }
            rs+=value;
        }
        return rs;
    }

    public static void main(String[] args) {
//        String s="LVIII";
        String s="MCMXCIV";
        System.out.println(romanToInt(s));
    }
}
