package E1_slide_window;

import java.util.HashSet;

public class E6_LengthOfTheLongestAlphabeticalContinuousSubstring {

    public static int longestContinuousSubstring(String s) {
        int low=0, high=0;
        int n=s.length();
        int rs=0;
        HashSet<Character> setChars=new HashSet<>();

        while(high<n){
            if(high!=0&&s.charAt(high)-s.charAt(high-1)!=1){
                low=high;
                high++;
                setChars.clear();
                setChars.add(s.charAt(low));
            }else{
                setChars.add(s.charAt(high));
                high++;
                rs=Math.max(rs, setChars.size());
            }
            // System.out.printf("Low: %s, high: %s, %s\n", low, high, setChars);
        }
        return rs;
    }

    public static int longestContinuousSubstringOptimization(String s) {
        int n=s.length();
        if(n==1){
            return 1;
        }
        int low=0, high=1;
        int rs=1;

        while(high<n){
            if(s.charAt(high)-s.charAt(high-1)==1){
                high++;
                rs=Math.max(rs, high-low);
            }else{
                low=high;
                high++;
            }
            // System.out.printf("Low: %s, high: %s, %s\n", low, high, setChars);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //* Return độ dài nhất của các phần tử liên tiếp theo alphabet (a,b,c) --> length=3
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 105
        //s consists of only English lowercase letters.
        //
        //- Slide window
        //- Dùng hashset để check distinct các element liên tiếp theo thứ tự "abcdefghijklmnopqrstuvwxyz"
        //+ s[high]-s[high-1]==1
        //
        //1.1, Optimization
        //- Clear() method is lower than using new method
        //+ hashset.clear() < hashset=new HashSet()
        //* Nhầm đã theo alpha rồi thì cần gì hashset ==> NGÁO
        //- Ta sẽ dùng array thay cho hashset
        //
        //1.2, Complexity
        //- Space : O(n) --> O(1)
        //- Time : O(n)
        //
        String s="yrpjofyzubfsiypfre";
        System.out.println(longestContinuousSubstring(s));
        System.out.println(longestContinuousSubstringOptimization(s));
        //#Reference
        //128. Longest Consecutive Sequence
        //2348. Number of Zero-Filled Subarrays
    }
}
