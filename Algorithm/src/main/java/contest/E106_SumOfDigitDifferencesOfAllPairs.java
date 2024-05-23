package contest;

import java.util.*;

public class E106_SumOfDigitDifferencesOfAllPairs {

    public static int getLength(int num){
        int countDigit=0;

        while(num!=0){
            countDigit++;
            num=num/10;
        }
        return countDigit;
    }

    public static long sumDigitDifferences(int[] nums) {
        int n=nums.length;
        if(n==1){
            return 0;
        }
        int m=getLength(nums[0]);
//        System.out.println(m);
        int pow=1;
        long rs=0;

        for(int i=0;i<m;i++){
            HashMap<Integer, Long> mapCount=new HashMap<>();
            HashSet<Integer> distinctNum=new HashSet<>();

            for(int j=0;j<n;j++){
                //1(2)31
                //1(2)31/100 = 12
                //12 % 10 = 2
                int curNum = (nums[j]/pow)%10;
                mapCount.put(curNum, mapCount.getOrDefault(curNum, 0L)+1);
                distinctNum.add(curNum);
            }
            List<Integer> sortList= new ArrayList<>(distinctNum);
            sortList.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (int) (mapCount.get(o2) - mapCount.get(o1));
                }
            });
            int sumCount=0;
            for(int e: sortList){
                sumCount+=mapCount.get(e);
            }
            long initVal=sumCount;
//            System.out.println(sortList);
//            System.out.println(mapCount);

            for(int e: sortList){
                initVal=initVal-mapCount.get(e);
                rs+=mapCount.get(e) * initVal;
//                initVal= initVal-mapCount.get(e);
            }
            pow=pow*10;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums consisting of (positive integers) where (all integers) have the same (number of digits).
        //The (digit difference) between two integers is (the count of different digits) that are in (the same position) in the (two integers).
        //* Return the sum of (the digit differences) between (all pairs of integers) in nums.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 10^5
        //1 <= nums[i] < 10^9
        //All integers in nums have the same number of digits.
        //==> Khá lớn nên O(N) time
        //
        //- Brainstorm
        //- Bài này là sum all diff count của all pairs
        //Ex:
        //Input: nums = [13,23,12]
        //Output: 4
        //Explanation:
        //We have the following:
        //- The digit difference between 13 and 23 is 1.
        //- The digit difference between 13 and 12 is 1.
        //- The digit difference between 23 and 12 is 2.
        //So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.
        //- Tìm diff count ntn?
        //Ex:
        //13
        //23 ==> diff count=1
        //=> Compare bình thg mất O(9)
        //
        //- Sum của tất cả các pair nên tính ntn để k take O(N) time:
        //Ex:
        //diff(13,23) == 1
        //diff(23,12) == 2
        //- Các số có cùng chữ số?
        //Ex:
        //[13,23,12]
        //1 3
        //2 3
        //1 2
        //2|2 = 4
        //
        //- Quay về bài toán con
        //- Tính sum diff giữa các số có 1 digit
        //[1,2,5,1,6]
        //1:3
        //2:4
        //5:4
        //6:4
        //Count các cặp khác nhau?
        //1,2,5,6 = 3+2+1 (Bỏ cái cuối đi)
        //(1,2)
        //(1,5)
        //(1,6): (1,count=3) ==> Nhưng có 2 số 1 ==> (1, count*2) = (1,6)
        //(2,5)
        //(2,6): (2: count=2)
        //(5,6): (5: count=1)
        //rs=6
        //
        //Ex:
        //[13,13,23,12]
        //1 3
        //1 3
        //2 3
        //1 2
        //- Nếu thêm số đằng trước (giống/ khác)
        //  + count diff tăng lên --> Cộng dồn lên
        //- Duplication thì sao?
        //
        //1 3
        //2 3
        //1 2
        //3,3,2 = 2
        //1,2,1 -> 1,1,2 = 2
        //
        //- Nhiều duplications:
        //1,1,2,2,3
        //1: 2
        //2: 2
        //3: 1
        //1:
        // + 5-2 = 3 ==> 3*2
        //2: 5-2-2
        //
//        int[] nums= {13,23,12};
//        int[] nums= {10,10,10,10};
//        int[] nums= {50,28,48};
        int[] nums= {824,843,837,620,836,234,276,859};
        //5 0
        //2 8
        //4 8
        //0,8,8 : 2
        //2,4,5 = 2+1
        //output:   4
        //expected: 5
        System.out.println(sumDigitDifferences(nums));
    }
}
