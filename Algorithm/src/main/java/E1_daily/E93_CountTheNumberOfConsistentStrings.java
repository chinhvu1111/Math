package E1_daily;

public class E93_CountTheNumberOfConsistentStrings {

    public static int countConsistentStrings(String allowed, String[] words) {
        int[] count=new int[26];

        for(int i=0;i<allowed.length();i++){
            count[allowed.charAt(i)-'a']++;
        }
        int rs=0;
        for(String s: words){
            int j;
            for(j=0;j<s.length();j++){
                if(count[s.charAt(j)-'a']==0){
                    break;
                }
            }
            if(j==s.length()){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string allowed (consisting of distinct characters) and (an array of strings words).
        //- A string is consistent if (all characters) in the string appear in the string allowed.
        //* Return (the number of consistent strings) in the array words.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //
        //
        String allowed = "ab";
        String[] words = {"ad","bd","aaab","baa","badab"};
        System.out.println(countConsistentStrings(allowed, words));
    }
}
