package interviews;

import java.util.HashMap;

public class E250_RomanToInteger {
    public static int romanToInt(String s) {
        HashMap<Character, Integer> map=new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int number=0;
        int n=s.length();

        for(int i=0;i<n;i++){

        }
        return 1;
    }

    public static void main(String[] args) {
        String s="MCMXCIV";

    }
}
