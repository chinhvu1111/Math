package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E282_ShortestCommonSupersequence_hard_classic {

    public static int solution(int index, int maxIndex, int maxLength, String str1, List<Integer>[] str2CharIndicesMap, int[] mappingIndices, int[] rs, int[] maxMatchingLength){
        if(index==str1.length()){
            if(maxMatchingLength[0]<maxLength){
                maxMatchingLength[0]=maxLength;
                for(int i=0;i<rs.length;i++){
                    rs[i]=mappingIndices[i];
                }
            }
            return maxLength;
        }
        int c = str1.charAt(index)-'a';
        List<Integer> matchingIndices=str2CharIndicesMap[c];
        int curMaxLength=0;

        //Optimize this loop
        for(Integer e: matchingIndices){
            if(e>maxIndex){
                mappingIndices[index]=e;
                int maxLen = solution(index+1, e, maxLength+1, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
                mappingIndices[index]=-1;
                curMaxLength=Math.max(curMaxLength, maxLen);
            }
        }
        int maxLen = solution(index+1, maxIndex, maxLength, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
        curMaxLength=Math.max(maxLen, curMaxLength);
        return curMaxLength;
    }

    public static int findUpperBound(List<Integer> mappingIndices, int key){
        int low=0, high=mappingIndices.size()-1;
        int rs=-1;

        if(mappingIndices.isEmpty()){
            return -1;
        }
        if(key==-1){
            return mappingIndices.get(0);
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(mappingIndices.get(mid)>=key){
                rs=mappingIndices.get(mid);
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int solutionBinarySearch(int index, int maxIndex, int maxLength, String str1, List<Integer>[] str2CharIndicesMap, int[] mappingIndices, int[] rs, int[] maxMatchingLength){
//        if(str1.length()-index-1+maxLength<maxMatchingLength[0]){
//            return -1;
//        }
        if(index==str1.length()){
            if(maxMatchingLength[0]<maxLength){
                maxMatchingLength[0]=maxLength;
                for(int i=0;i<rs.length;i++){
                    rs[i]=mappingIndices[i];
                }
            }
            return maxLength;
        }
        int c = str1.charAt(index)-'a';
        List<Integer> matchingIndices=str2CharIndicesMap[c];
        int curMaxLength=0;
        int searchIndex = findUpperBound(matchingIndices, maxIndex);
        if(searchIndex!=-1){
            mappingIndices[index]=searchIndex;
            int maxLen = solutionBinarySearch(index+1, searchIndex, maxLength+1, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
            mappingIndices[index]=-1;
            curMaxLength=Math.max(curMaxLength, maxLen);
        }
        int maxLen = solutionBinarySearch(index+1, maxIndex, maxLength, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
        curMaxLength=Math.max(maxLen, curMaxLength);
        return curMaxLength;
    }

    public static String shortestCommonSupersequence(String str1, String str2) {
        int m=str2.length();
        List<Integer>[] str2CharIndicesMap=new List[26];

        for(int i=0;i<26;i++){
            str2CharIndicesMap[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int c = str2.charAt(i)-'a';
            str2CharIndicesMap[c].add(i);
        }
        int[] mappingIndices=new int[str1.length()];
        Arrays.fill(mappingIndices, -1);
        int[] rs=new int[str1.length()];
        Arrays.fill(rs, -1);
        int[] maxMatchingLength=new int[1];
        solutionBinarySearch(0, -1, 0, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
        StringBuilder rsStr=new StringBuilder();
        int prevIndex=0;

        for (int i = 0; i < rs.length; i++) {
            if(rs[i]==-1){
                rsStr.append(str1.charAt(i));
            }else{
                for(int j=prevIndex;j<=rs[i];j++){
                    rsStr.append(str2.charAt(j));
                }
                prevIndex=rs[i]+1;
            }
        }
        while (prevIndex<str2.length()){
            rsStr.append(str2.charAt(prevIndex++));
        }
//        System.out.println();
        return rsStr.toString();
    }

//    public static String shortestCommonSupersequenceWrong(String str1, String str2) {
//        int m=str2.length();
//        int[] str2CharIndicesMap=new int[26];
//        Arrays.fill(str2CharIndicesMap, -1);
//
//        for(int i=0;i<m;i++){
//            int c = str2.charAt(i)-'a';
//            if(str2CharIndicesMap[c]==-1){
//                str2CharIndicesMap[c]=i;
//            }
//        }
//        int[] mappingIndices=new int[str1.length()];
//        Arrays.fill(mappingIndices, -1);
//        int[] rs=new int[str1.length()];
//        Arrays.fill(rs, -1);
//        int[] maxMatchingLength=new int[1];
//        solution(0, -1, 0, str1, str2CharIndicesMap, mappingIndices, rs, maxMatchingLength);
//        StringBuilder rsStr=new StringBuilder();
//        int prevIndex=0;
//
//        for (int i = 0; i < rs.length; i++) {
//            if(rs[i]==-1){
//                rsStr.append(str1.charAt(i));
//            }else{
//                for(int j=prevIndex;j<=rs[i];j++){
//                    rsStr.append(str2.charAt(j));
//                }
//                prevIndex=rs[i]+1;
//            }
//        }
//        while (prevIndex<str2.length()){
//            rsStr.append(str2.charAt(prevIndex++));
//        }
////        System.out.println();
//        return rsStr.toString();
//    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two strings str1 and str2,
        //* return the ("shortest") string that has (both str1 and str2 as subsequences).
        //- If there are multiple valid strings, return any of them.
        //- A string s is (a subsequence of string t) if deleting some number of characters from t (possibly 0) results in the string s.
        //
        //Example 1:
        //
        //Input: str1 = "abac", str2 = "cab"
        //Output: "cabac"
        //Explanation:
        //str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
        //str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
        //The answer provided is the shortest such string that satisfies these properties.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= str1.length, str2.length <= 1000
        //str1 and str2 consist of lowercase English letters.
        //  + Time: O(n^2)/O(n*26)
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: str1 = "abac", str2 = "cab"
        //Output: "cabac"
        //- Combination of str1 and str2
        //- Find (the longest common string)
        //str1 = "abac"
        //str2 = "cab"
        //- If str2 contains str1 (multiple times)
        //  + We need to choose the best case
        //
        //Ex:
        //str1 = "abac"
        //str2 = "(aebhc)xxxx(acbh)"
        //- Match the smallest index that is optimal:
        //  + Because between x and y ==> It could be existed the (matching characters)
        //
        //- In the matching process:
        //  + There are (some characters) that could be matched to (any characters)
        //
        //Ex:
        //Input: str1 = "abac", str2 = "cab"
        //Let's match the strings
        //str1 = "abac"
        //str2 = "cab"
        //str1 -> str2
        //str1 matches = [1,2,1,0]
        //  + Find the longest increase subsequence array
        //
        //- Each character could be matched with (multiple indices)
        //- What is better if we choose the valid index as soon as possible
        //Ex:
        //[0,1,[4,6,7],2,3]
        //  + [0,1,4]
        //  + [0,1,2,3] is better
        //- We can use recursion method to solve:
        //  + Depth = 1000
        //
        //- Construct the string:
        //str1 = "abac", str2 = "cab"
        //[1,2]
        //  + ==> Not match + add(c) to string
        //
//        String str1 = "abac", str2 = "cab";
//        String str1 = "bbbaaaba", str2 = "bbababbb";
        //Expected: bbbaaababbb
        //TLE
        String str1 = "bcaaacbbbcbdcaddadcacbdddcdcccdadadcbabaccbccdcdcbcaccacbbdcbabb",
                str2 = "dddbbdcbccaccbababaacbcbacdddcdabadcacddbacadabdabcdbaaabaccbdaa";
        //[0, 1, 3, 4, -1, -1, 5, -1]
        //Output: bbabaaaba
        //Expected: bbbaaababbb
        //TLE
//        System.out.println(shortestCommonSupersequence(str1, str2));
        System.out.println(shortestCommonSupersequence(str1, str2));
    }
}
