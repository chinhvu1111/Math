package E1_Array;

import java.util.ArrayList;
import java.util.List;

public class E4_ReverseVowelsOfAString {

    public static String reverseVowels(String s) {
        List<Character> listVowels=new ArrayList<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='a'||s.charAt(i)=='A'
                    ||s.charAt(i)=='e'||s.charAt(i)=='E'
                    ||s.charAt(i)=='i'||s.charAt(i)=='I'
                    ||s.charAt(i)=='o'||s.charAt(i)=='O'
                    ||s.charAt(i)=='u'||s.charAt(i)=='U'){
                listVowels.add(s.charAt(i));
            }
        }
        StringBuilder rs=new StringBuilder();
        int index=listVowels.size()-1;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='a'||s.charAt(i)=='A'
                    ||s.charAt(i)=='e'||s.charAt(i)=='E'
                    ||s.charAt(i)=='i'||s.charAt(i)=='I'
                    ||s.charAt(i)=='o'||s.charAt(i)=='O'
                    ||s.charAt(i)=='u'||s.charAt(i)=='U'){
                rs.append(listVowels.get(index));
                index--;
            }else{
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static boolean isVowel(char c){
        return c=='a'||c=='A'
                ||c=='e'||c=='E'
                ||c=='i'||c=='I'
                ||c=='o'||c=='O'
                ||c=='u'||c=='U';
    }

    public static String reverseVowelsOptimization(String s) {
        int n=s.length();
        char[] sArr=s.toCharArray();
        int low=0, high=n-1;

        while(low<=high){
            // char left=s.charAt(low);
            // char right=s.charAt(high);

            while(low<=high&&!isVowel(sArr[low])){
                low++;
            }
            while(low<=high&&!isVowel(sArr[high])){
                high--;
            }
            if(low<high){
                char right=sArr[high];
                sArr[high]=sArr[low];
                sArr[low]=right;
            }
            low++;
            high--;
        }
        StringBuilder rs=new StringBuilder();
        for(char c: sArr){
            rs.append(c);
        }

        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
        //1.1, Optimization
        //- swap dùng char array
        //==> Bên trong thì chỉ swap các vowels thôi
        //
        //1.2. Complexity:
        //- Space : O(N)
        //- Time : O(N)
        //
        //#Reference:
        //151. Reverse Words in a String
        //344. Reverse String
        //1119. Remove Vowels from a String
        //2810. Faulty Keyboard
        String s = "leetcode";
        System.out.println(reverseVowels(s));
        System.out.println(reverseVowelsOptimization(s));
    }
}
