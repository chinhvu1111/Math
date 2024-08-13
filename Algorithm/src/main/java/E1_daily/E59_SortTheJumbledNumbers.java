package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class E59_SortTheJumbledNumbers {

    public static int[] sortJumbled(int[] mapping, int[] nums) {
        int n=nums.length;
        int[][] temp=new int[n][3];

        for(int i=0;i<n;i++){
            temp[i][0]=nums[i];
            temp[i][1]=i;
            int curNum=nums[i];
            int mappingVal=0;
            int c=1;

            //Nếu curNum==0 ==> Không đi qua loop (Break bên dưới)
            if(curNum==0){
                temp[i][2]=mapping[0];
                continue;
            }
            //Đoạn này có thể sẽ bị biến thành:
            //- 3333388 => 0000077
            while (curNum!=0){
                int curDigit=curNum%10;
                mappingVal+=c*mapping[curDigit];
                curNum=curNum/10;
                c=c*10;
            }
            temp[i][2]=mappingVal;
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("%s ", temp[i][2]);
//        }
//        System.out.println();
        Arrays.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2]!=o2[2]){
                    return o1[2]-o2[2];
                }
                return o1[1]-o2[1];
            }
        });
        for (int i = 0; i < n; i++) {
            nums[i]=temp[i][0];
        }
        return nums;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a (0-indexed integer array) mapping which represents (the mapping rule of a shuffled decimal system).
        // mapping[i] = j means digit (i) should be mapped to (digit j) in this system.
        //- (The mapped value of an integer) is (the new integer) obtained by ("replacing") (each occurrence of digit (i))
        // in the integer with mapping[i] for all 0 <= i <= 9.
        //- You are also given (another integer array nums).
        //* Return the array nums sorted in (non-decreasing order) based on (the mapped values of its elements).
        //Notes:
        //- Elements with (the same mapped values) should appear in (the same relative order) as in the input.
        //  + Những value có cùng mapping value ==> (Keep order) as the (input order)
        //- (The elements of nums) should only be (sorted) based on (their mapped values) and (not be replaced) by them.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //mapping.length == 10
        //0 <= mapping[i] <= 9
        //+ All the values of mapping[i] are (unique).
        //1 <= nums.length <= 3 * 10^4
        //0 <= nums[i] < 10^9
        //
        //- Brainstorm
        //- Dùng hashMap để lưu mapping trước đã.
        //  + Giá trị sau khi mapping
        //- Dùng hashmap để lưu index của nó trong array ==> Để sort được theo old order nếu cần.
        //  + Do value của nums không unique
        //      + Dùng thêm list để lưu index ==> Sort sau
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //#Reference:
        //2212. Maximum Points in an Archery Competition
        //1232. Check If It Is a Straight Line
        //3002. Maximum Size of a Set After Removals
//        int[] mapping = {8,9,4,0,2,1,3,5,7,6}, nums = {991,338,38};
        int[] mapping = {8,9,4,0,2,1,3,5,7,6}, nums = {991,3333388,38, 0};
        int[] rs = sortJumbled(mapping, nums);

        for (int i = 0; i < rs.length; i++) {
            System.out.println(rs[i]);
        }
    }
}
