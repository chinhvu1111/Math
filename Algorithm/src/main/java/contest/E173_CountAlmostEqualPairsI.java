package contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class E173_CountAlmostEqualPairsI {

    public static int getNumber(char[] curChars){
        int n=curChars.length;
        int rs=0;
        for(int i=0;i<n;i++){
            rs=rs*10+(curChars[i]-'0');
        }
        return rs;
    }

    public static int countPairs(int[] nums) {
        int n=nums.length;
        int rs=0;
//        Map<Integer, Integer> mapCount=new HashMap<>();
        Map<Integer, HashSet<Integer>> mapIndex=new HashMap<>();
        Map<Integer, HashSet<Integer>> swapMapIndexToSet=new HashMap<>();
        for(int i=0;i<n;i++){
//            Map<Integer, HashSet<Integer>> tempCount=new HashMap<>();
            HashSet<Integer> distinctNum=new HashSet<>();
            int curNum=nums[i];
            char[] curChars=String.valueOf(curNum).toCharArray();
//            char[] temp=curChars.clone();
            HashSet<Integer> matchIndexes=new HashSet<>();
            int len=curChars.length;
            if(swapMapIndexToSet.containsKey(curNum)){
//                matchIndexes.addAll(swapMapIndexToSet.get(curNum));
                matchIndexes.addAll(swapMapIndexToSet.get(curNum));
            }
            distinctNum.add(curNum);
            //Swap
            for(int j=0;j<len;j++){
                for(int h=j+1;h<len;h++){
                    char curTemp= curChars[j];
                    curChars[j]=curChars[h];
                    curChars[h]=curTemp;
                    int newNumber=getNumber(curChars);
                    if(newNumber==curNum){
                        curTemp= curChars[j];
                        curChars[j]=curChars[h];
                        curChars[h]=curTemp;
//                        curChars=temp;
                        continue;
                    }
//                    if(swapMapIndexToSet.containsKey(newNumber)){
//                        matchIndexes.addAll(swapMapIndexToSet.get(newNumber));
//                    }
                    if(mapIndex.containsKey(newNumber)){
                        matchIndexes.addAll(mapIndex.get(newNumber));
                    }
                    distinctNum.add(newNumber);
//                    System.out.printf("i: %s, value: %s, rs: %s\n", i , newNumber, rs);
                    curTemp= curChars[j];
                    curChars[j]=curChars[h];
                    curChars[h]=curTemp;
                }
            }
            rs+=matchIndexes.size();
            for(int e: distinctNum){
                HashSet<Integer> oldIndexes=swapMapIndexToSet.getOrDefault(e, new HashSet<>());
                oldIndexes.add(i);
                swapMapIndexToSet.put(e, oldIndexes);
            }
            HashSet<Integer> old=mapIndex.getOrDefault(nums[i], new HashSet<>());
            old.add(i);
            mapIndex.put(nums[i], old);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (an array nums) consisting of ((positive) integers).
        //- We call (two integers x and y) in this problem (almost equal) if (both integers) can (become equal)
        // after performing the following operation (at most once):
        //- Choose (either x or y) and swap any (two digits) within (the chosen number).
        //* Return (the number of indices i and j) in nums where i < j such that (nums[i] and nums[j]) are almost equal.
        //- Note that it is allowed for (an integer) to have (leading zeros) after performing an operation.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 100
        //1 <= nums[i] <= 10^6
        //  + Len small, value of nums[i] lớn
        //
        //** Brainstorm
        //- Tức là swap 2 digits của x ==> x1
        //  + x1 tồn tại trong array ==> 1 pair
        //- Index swap i, j trong 1 digit có thể == nhau.
        //Ex:
        // nums = [1,1,1,1,1]
        //4+3+2+1 = 10
        //
        //5432 <=> 4532
        //+ Check swap 2 phần tử ntn cho nhanh
        //
        //-
//        int[] nums = {3,12,30,17,21};
//        int[] nums = {1,1,1,1,1};
        int[] nums = {123,231};
//        int[] nums = {5,12,8,5,5,1,20,3,10,10,5,5,5,5,1};
        //Output: 25
        //Expect: 27
//        int[] nums = {3,12,30,17,21};
        //Output: 3
        //Expect: 2
        //2 số swap được, khi:
        //  + swap cả 2
        //
        System.out.println(countPairs(nums));
    }
}
