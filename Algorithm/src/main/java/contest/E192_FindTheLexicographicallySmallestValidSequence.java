package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class E192_FindTheLexicographicallySmallestValidSequence {

    public static int search(List<Integer> list, int val){
        int low=0;
        int high=list.size()-1;
        int rs=-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if(val<=list.get(mid)){
                rs=list.get(mid);
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }
    public static int[] finalRs;

    public static boolean solution(int indexWord2, int prevIndex, int count, List<Integer>[] indexes, String word2, int[] rs){
//        if(finalRs!=null){
//            return;
//        }
        if(indexWord2==word2.length()){
//            finalRs=rs;
            return true;
        }
        int c = word2.charAt(indexWord2)-'a';
        if(!indexes[c].isEmpty()){
            int curIndex = search(indexes[c], prevIndex+1);
            if(curIndex!=-1){
                if(prevIndex+1!=curIndex&&count==0&&solution(indexWord2+1, prevIndex+1, count+1, indexes, word2, rs)){
                    rs[indexWord2]=prevIndex+1;
                    return true;
                }
                rs[indexWord2]=curIndex;
                prevIndex=curIndex;
            }else{
                count++;
                if(count>=2){
                    return false;
                }
                //Chọn character nào?
                //
                rs[indexWord2]=prevIndex+1;
                prevIndex++;
            }
            if(solution(indexWord2+1, prevIndex, count, indexes, word2, rs)){
                return true;
            }
        }else{
            count++;
            if(count>=2){
                return false;
            }
            //Chọn character nào?
            //
            rs[indexWord2]=prevIndex+1;
            prevIndex++;
            if(solution(indexWord2+1, prevIndex, count, indexes, word2, rs)){
                return true;
            }
        }
        return false;
    }

    public static int[] validSequence(String word1, String word2) {
        List<Integer>[] indexes=new ArrayList[26];
        int n=word1.length();

        for(int i=0;i<26;i++){
            indexes[i]=new ArrayList();
        }
        HashSet<Character> setWord2=new HashSet<>();
        for(char c:word2.toCharArray()){
            setWord2.add(c);
        }
        for(int i=0;i<n;i++){
            if(setWord2.contains(word1.charAt(i))){
                int c = word1.charAt(i)-'a';
                indexes[c].add(i);
            }
        }
        int m = word2.length();
        int[] rs=new int[m];
        int firstC=word2.charAt(0);
        finalRs=null;
        if(!indexes[firstC-'a'].isEmpty()){
            rs[0]=indexes[firstC-'a'].get(0);
            if(solution(1, indexes[firstC-'a'].get(0), 0, indexes, word2, rs)){
                finalRs=rs;
            }
        }
//        if(finalRs==null){
//            rs[0]=0;
//            solution(1, 0, 1, indexes, word2, rs);
//        }
        int[] rs1=new int[m];
        if(solution(1, 0, 1, indexes, word2, rs1)){
            if(finalRs==null){
                return rs1;
            }else{
                for (int i = 0; i < m; i++) {
                    if(finalRs[i]<rs1[i]){
                        return finalRs;
                    }else if(finalRs[i]>rs1[i]){
                        return rs1;
                    }
                }
            }
        }
        return finalRs==null?new int[]{}:finalRs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two strings word1 and word2.
        //- A string x is called almost equal to y if you can change (at most one character) in x to make it identical to y.
        //- A sequence of indices seq is called valid if:
        //  + The indices are sorted (in ascending order).
        //  + Concatenating (the characters at these indices) in word1 in the same order results in a string that is almost equal to word2.
        //
        //* Return an array of size word2.length representing the lexicographically smallest valid sequence of indices.
        // If no such sequence of indices exists, return an empty array.
        //- Note that the answer must represent the lexicographically smallest array, not the corresponding string formed by those indices.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word2.length < word1.length <= 3 * 10^5
        //  + Big length: Time: O(n)
        //  + Lower case : 26
        //
        //** Brainstorm
        //Ex:
        //
        //Input: word1 = "bacdc", word2 = "abc"
        //Output: [1,2,4]
        //Explanation:
        //word1[1] is already 'a'.
        //Change word1[2] to 'b'.
        //word1[4] is already 'c'.
        //
        //- Change duy nhất 1 character, x -> y
        //- Keep order
        //- Để giống nhau mà khác nhiều nhất 1 char thì làm ntn?
        //- Smallest lexicographically:
        //  + Left càng small index thì càng tốt
        //
        //s = b(a)cdc
        //s1= (a)bc
        // Two pointers?
        //s1 = abc
        //a: 1
        //b: 0 ==> Cần bằng 2
        //c: 2,4
        //indexes = [1,2,4]
        //- Nếu không tìm được x=prev+1 ==> Gán cho cái khác
        //- Không tìm được tức là:
        //  + prevVal >= max current value
        //      + Mà prevVal ==> Min rồi nên không thể.
        //  + Gán ntn?
        //      + Ta sẽ gán với
        //
        //s = aaaabc
        //s1 = asadsaaadccadacd
        //
//        String word1 = "bacdc", word2 = "abc";
//        String word1 = "abc", word2 = "ab";
//        String word1 = "aaaaaa", word2 = "aaabc";
//        String word1 = "vbcca", word2 = "abc";
//        String word1 = "vbcca", word2 = "abc";
//        String word1 = "dddddabc", word2 = "abc";
//        String word1 = "ghhgghhhhhh", word2 = "gg";
//        String word1 = "cccbcccbccbcccccccb", word2 = "ccb";
//        String word1 = "cdbbcdddddbdddbcdbdbbbccbbc", word2 = "dbc";
        String word1 = "gkhddkknkkbadga", word2 = "kkjkk";
        //0,6,7
        int [] rs = validSequence(word1, word2);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
    }
}
