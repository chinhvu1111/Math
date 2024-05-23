package contest;

import java.util.Arrays;

public class E101_PermutationDifferenceBetweenTwoStrings {

    public static int findPermutationDifference(String s, String t) {
        int n=s.length();
        int[] indexS=new int[26];
        int[] indexT=new int[26];
        Arrays.fill(indexS, -1);
        Arrays.fill(indexT, -1);

        for(int i=0;i<n;i++){
            indexS[s.charAt(i)-'a']=i;
            indexT[t.charAt(i)-'a']=i;
        }
        int rs=0;
        for(int i=0;i<26;i++){
            if(indexS[i]!=-1){
                rs+=Math.abs(indexS[i]-indexT[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
