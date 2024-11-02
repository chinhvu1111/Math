package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E200_FindMaximumRemovalsFromSourceString {

    public static int maxRemovals(String source, String pattern, int[] targetIndices) {
        HashSet<Integer> removedIndices= new HashSet<>();
        for(int e:targetIndices){
            removedIndices.add(e);
        }
        HashMap<Character, List<Integer>> charToIndex=new HashMap<>();
        int n=source.length();
        for(int i=0;i<n;i++){
            char c=source.charAt(i);
            List<Integer> curList=charToIndex.getOrDefault(c, new ArrayList<>());
            curList.add(i);
            charToIndex.put(c, curList);
        }
        int m=pattern.length();
        HashSet<Integer> rsIndex=new HashSet<>();
        for(int i=0;i<m;i++){
            char c = pattern.charAt(i);
            List<Integer> curList=charToIndex.get(c);
            Integer index=-1;
            for(int e: curList){
                if(!removedIndices.contains(e)){
                    index=e;
                    break;
                }
            }
            if(index==-1){
                rsIndex.add(curList.get(0));
                curList.remove(curList.get(0));
            }else{
                curList.remove(index);
                rsIndex.add(index);
            }
        }
        int rs=0;
        for(int i=0;i<n;i++){
            if(!rsIndex.contains(i)&&removedIndices.contains(i)){
                rs++;
            }
        }
        return rs;
    }

    public static int maxRemovals1(String source, String pattern, int[] targetIndices) {
        int n=source.length();
        HashSet<Integer> removedIndices= new HashSet<>();
        for(int e:targetIndices){
            removedIndices.add(e);
        }
        int numCanRemove=removedIndices.size();
//        for (int i = 0; i < n; i++) {
//            if(removedIndices.contains(i)){
//                numCanRemove++;
//            }
//        }
        int rs=0;
        for(int h=0;h<n;h++){
            int i = 0, j = h;
            int tempNum=numCanRemove;

            while (i < pattern.length() && j < source.length()) {
                if (pattern.charAt(i) == source.charAt(j)) {
                    i++;
                    if(removedIndices.contains(j)){
                        tempNum--;
                    }
                }
                j++;
            }
            if(i==pattern.length()){
                rs=Math.max(rs, tempNum);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string source of size n, a string pattern that is (a subsequence of source),
        // and (a sorted integer array) targetIndices that contains (distinct numbers) in the range [0, n - 1].
        //
        //- We define an operation as (removing a character) at (an index idx) from source such that:
        //  + idx is (an element of targetIndices).
        //  + pattern remains (a subsequence) of source after removing the character.
        //  ==> Pattern vẫn là subsequence của string
        //- Performing an operation does not change the indices of the other characters in source.
        //  + For example, if you remove 'c' from "acb", the character at index 2 would still be 'b'.
        //
        //* Return (the maximum number of operations) that can be performed.
        //- A subsequence is a string that can be derived from another string by ("deleting" some or no characters)
        // without changing the order of the remaining characters.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == source.length <= 3 * 10^3
        //1 <= pattern.length <= n
        //1 <= targetIndices.length <= n
        //targetIndices is sorted in (ascending order).
        //The input is generated such that targetIndices contains distinct elements in the range [0, n - 1].
        //source and pattern consist (only) of (lowercase English letters).
        //  + Chỉ lowercase
        //The input is generated such that pattern appears as (a subsequence) in source.
        //  + N = 3000 cũng không quá lớn
        //  ==> O(n^2) có thể
        //
        //** Brainstorm
        //Example 2:
        //Input: source = "bcda", pattern = "d", targetIndices = [0,3]
        //Output: 2
        //Explanation:
        //We can remove source[0] and source[3] in two operations.
        //- Số lần move chính là:
        //  + Số lần remove liên tiếp
        //
        //- Việc remove đi thì tương ứng với gì?
        //source = "abbaa"
        //pattern = "aba"
        //- Check pattern là subsequence của source ntn?
        //  + 2 pointers
        //
        //- Làm sao có thể remove được nhiều nhất
        //+ 2 pointers kết hợp map được không
        //
        //Ex:
        //source = "abbaa"
        //pattern = "aba"
        //Xét a:
        //  + index = [0,3,4]
        //  ==> Chọn thằng có index min
        //          + Ưu tiên (tồn tại) trong targetIndices
        //
        //(aaabca)ddd(aeebkka)
        //
//        String source = "abbaa", pattern = "aba";
//        int[] targetIndices = {0,1,2};
        String source = "yeyeykyded", pattern = "yeyyd";
        int[] targetIndices = {0,2,3,4};
        System.out.println(maxRemovals1(source, pattern, targetIndices));
    }
}
