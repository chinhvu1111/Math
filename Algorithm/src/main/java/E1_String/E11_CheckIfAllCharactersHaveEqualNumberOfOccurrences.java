package E1_String;

public class E11_CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public boolean areOccurrencesEqual(String s) {
        int[] countC=new int[26];
        for(int i=0;i<s.length();i++){
            countC[s.charAt(i)-'a']++;
        }
        int count=-1;
        for(int i=0;i<26;i++){
            // if(countC[i]>0){
            //     System.out.printf("%s\n", countC[i]);
            // }
            if(count==-1&&countC[i]>0){
                count=countC[i];
            }else if(count>0&&countC[i]>0&&countC[i]!=count){
                // System.out.printf("%s %s\n", count, countC[i]);
                return false;
            }
        }
        return true;
    }
}
