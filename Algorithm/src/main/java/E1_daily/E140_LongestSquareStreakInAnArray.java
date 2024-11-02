package E1_daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class E140_LongestSquareStreakInAnArray {

    public static int longestSquareStreak(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> valToSize=new HashMap<>();
        Arrays.sort(nums);
        int rs=0;

        for(int i=0;i<n;i++){
            if(!valToSize.containsKey(nums[i])){
                int prevNum = (int) Math.sqrt(nums[i]);
                int curSize = 1;
                if(prevNum*prevNum==nums[i]&&valToSize.containsKey(prevNum)){
                    curSize = valToSize.get(prevNum)+1;
                }
                valToSize.put(nums[i], curSize);
                rs=Math.max(curSize, rs);
            }
        }
        return rs==1?-1:rs;
    }

    public static boolean binarySearch(int[] nums, int searchVal){
        int low=0,high=nums.length-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]>searchVal){
                high--;
            }else if(nums[mid]==searchVal){
                return true;
            }else{
                low++;
            }
        }
        return false;
    }

    public static int longestSquareStreakBinarySearch(int[] nums) {
        int n=nums.length;
        HashSet<Integer> visited=new HashSet<>();
        Arrays.sort(nums);
        int rs=0;

        for(int i=0;i<n;i++){
            int curNum=nums[i];
            if(visited.contains(curNum)){
                continue;
            }
            int curSize=1;
            visited.add(curNum);
            while((long)(curNum)*(long)(curNum)<=100_000&&binarySearch(nums, curNum*curNum)){
                visited.add(curNum*curNum);
                curNum=curNum*curNum;
                curSize+=1;
            }
//            System.out.printf("val:%s, size:%s\n", nums[i], curSize);
            rs=Math.max(rs, curSize);
        }
        return rs==1?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums. A subsequence of nums is called (a square streak) if:
        //  + (The length of the subsequence) is at least 2, and
        //  + after (sorting the subsequence), each element (except the first element) is (the square of the previous number).
        //* Return (the length of the longest square streak) in nums, or return -1 if there is (no square streak).
        //  + Tức là return longest square streak là độ dài array dài nhất mà (current number) bằng square của (previous number).
        //  + A subsequence is sorted.
        //- A subsequence is an array that can be derived from another array by deleting some
        // or no elements without changing the order of the remaining elements.
        //
        // Idea
        //1.
        //1.0, HashMap
        // Method-1:
        //- Constraint
        //2 <= nums.length <= 10^5
        //2 <= nums[i] <= 10^5
        //
        //- Brainstorm
        //- Sort array
        //- Sau đó dựa trên array ta sẽ tìm chuỗi dài nhất called a square streak
        //Ex:
        //nums = [4,3,6,16,8,2]
        //sorted:[(2),3,(4),6,8,(16)]
        //Ex:
        //sorted:[2,3,(4),6,8,(2,4),[16]]
        //- Ta thấy rằng đã reach đến 16 rồi
        //  + Tính theo 4 nào cũng được
        //  ==> Vì size[4] = 2
        //
        //Ex:
        //nums = [4,3,6,16,8,2]
        //sorted:[(2),3,(4),6,8,(16)]
        //valToPos[2] = 1
        //- Những thằng không tồn tại ==> Size =1
//        int[] nums = {4,3,6,16,8,2};
//        int[] nums = {2,3,5,6,7};
        //- Exceptional cases:
        int[] nums = {4,2,4,6,16,8,2};
        //sorted nums = [2,4,4,6,8,16]
        //  + Ta thấy khi sort thì 4,4 đứng ngay cạnh nhau
        //  ==> Đều tính dựa trên 2 đằng trước nên không sao
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        //2.
        //2.0, Binary search
        //- Để dùng binary search:
        //  + Cần sort
        //- Từ (current number) ta có thể tìm (next number)
        //  + (current number)=x -> x*x=y -> y*y
        //  ==> Ta sẽ dùng binary search tìm liên tục
        //  + Để tránh over size of the int:
        //      + Cần check x*x<=10^5
        //- Mỗi lần check ta sẽ add thêm vào set:
        //  + Để mỗi lần loop qua ta sẽ bỏ qua.
        //
        //#Reference:
        //555. Split Concatenated Strings
        //3113. Find the Number of Subarrays Where Boundary Elements Are Maximum
        //905. Sort Array By Parity
        //
        System.out.println(longestSquareStreak(nums));
        System.out.println(longestSquareStreakBinarySearch(nums));
    }
}
