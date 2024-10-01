package contest;

import java.util.HashSet;

public class E194_CountOfSubstringsContainingEveryVowelAndKConsonantsI {

    public static int countOfSubstrings(String word, int k) {
        int n=word.length();
        int[][] prefixSum=new int[n+1][26];
        int[] prefixCount=new int[26];

        for(int i=0;i<n;i++){
            prefixCount[word.charAt(i)-'a']++;
            for(int j=0;j<26;j++){
                prefixSum[i+1][j]=prefixCount[j];
            }
        }
        int[] vowels={0,'e'-'a','i'-'a','o'-'a','u'-'a'};
        HashSet<Integer> cons=new HashSet<>();
        HashSet<Integer> vowelsSet=new HashSet<>();

        for(int c: vowels){
            vowelsSet.add(c);
        }
        for(int i=0;i<26;i++){
            if(!vowelsSet.contains(i)){
                cons.add(i);
            }
        }
        //j=0,i=n-1
        //index:0,1,2,3
        //i:  0,1,2,3,4
        //
        int rs=0;
        for(int i=1;i<=n;i++){
            //i==1,j==0
            //
            for(int j=i-1;j>=0;j--){
                boolean isInvalid=false;
                for(int h:vowels){
                    if(prefixSum[i][h]-prefixSum[j][h]<=0){
                        isInvalid=true;
                        break;
                    }
                }
                if(isInvalid){
                    continue;
                }

                int countCons=0;
                for(int h: cons){
                    countCons+=prefixSum[i][h]-prefixSum[j][h];
                }
                if(countCons==k){
                    rs++;
//                    if(j>=1){
//                        System.out.println(word.substring(j+1, i-1));
//                    }else{
//                        System.out.println(word.substring(i-1));
//                    }
                    //[j,i-1]
                    System.out.println(word.substring(j, i));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string word and (a non-negative integer k).
        //* Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u')
        // at least once and exactly k consonants.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //5 <= word.length <= 250
        //word consists only of lowercase English letters.
        //0 <= k <= word.length - 5
        //  + Length nhỏ ==> O(n^2)
        //
        //** Brainstorm
        //
        //- Làm O(n^2) cũng được
        //- Dùng prefix sum dp[i][26] là được
//        String word = "aeiou";
//        int k = 0;
        String word = "ieaouqqieaouqq";
        int k = 1;
        System.out.println(countOfSubstrings(word, k));
    }
}
