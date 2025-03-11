package contest;

import java.util.List;

public class E267_CountSubstringsDivisibleByLastDigit {

    public static long solution(String s, int k, List<Integer> lastIndices){
        int n=s.length();
        long rs=0;
        if(k==2){
            for(int i=0;i<n;i++){
                if(s.charAt(i)=='2'){
                    rs+=(i+1);
                }
            }
        }
        if(k==3){
            int[] remainderCount=new int[3];
            remainderCount[0]=1;
            int sum=0;

            for(int i=0;i<n;i++){
                int curRemainder=(sum+s.charAt(i)-'0')%3;
                if(s.charAt(i)=='3'){
                    rs+=remainderCount[curRemainder];
                }
                remainderCount[curRemainder]++;
            }
        }
        if(k==4){

        }
        return 0;
    }

    public static long countSubstrings(String s) {
        for(int i=1;i<=9;i++){

        }
        return 0L;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting of digits.
        //* Return the number of substrings of s divisible by their non-zero last digit.
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //Note: A substring may contain leading zeros.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of digits only.
        //  + Length<=10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Dynamic programming???
        //
        //
    }
}
