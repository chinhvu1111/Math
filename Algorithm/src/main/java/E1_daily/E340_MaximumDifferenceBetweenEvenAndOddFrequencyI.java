package E1_daily;

public class E340_MaximumDifferenceBetweenEvenAndOddFrequencyI {

    public static int maxDifference(String s) {
        int[] count=new int[26];
        int n=s.length();

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
        }
        int evenCount=Integer.MAX_VALUE;
        int oddCount=0;

        for(int i=0;i<26;i++){
            if(count[i]==0){
                continue;
            }
            if(count[i]%2==0){
                evenCount=Math.min(evenCount, count[i]);
            }else{
                oddCount=Math.max(oddCount, count[i]);
            }
        }
//        System.out.println(evenCount);
//        System.out.println(oddCount);
        return oddCount-evenCount;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting of (lowercase English letters).
        //- Your task is to find (the maximum difference diff = freq(a1) - freq(a2))
        // between the frequency of characters a1 and a2 in the string such that:
        //  + a1 has an (odd) frequency in the string.
        //  + a2 has an (even) frequency in the string.
        //* Return this (maximum difference).
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= s.length <= 100
        //s consists only of lowercase English letters.
        //s contains at least one character with an odd frequency and one with an even frequency.
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)

        String s = "aaaaabbc";
        System.out.println(maxDifference(s));
    }
}
