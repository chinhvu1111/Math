package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class E17_FindScoreOfAnArrayAfterMarkingAllElements {

    public static long findScore(int[] nums) {
        int n=nums.length;
        boolean[] visited=new boolean[n];
        HashMap<Integer, TreeSet<Integer>> hashIndex=new HashMap<>();

        for(int i=0;i<n;i++){
            TreeSet<Integer> indexTree=hashIndex.get(nums[i]);
            if(indexTree==null){
                indexTree=new TreeSet<>();
            }
            indexTree.add(i);
            hashIndex.put(nums[i], indexTree);
        }
        int[] nums1=new int[n];

        for(int i=0;i<n;i++){
            nums1[i]=nums[i];
        }
        Arrays.sort(nums1);
        long rs=0;

        for(int i=0;i<n;i++){
            TreeSet<Integer> indexs=hashIndex.get(nums1[i]);
            //2,1,5,2 ==> 2 đã được đánh dấu rồi thì loại ra
            while (!indexs.isEmpty()&&visited[indexs.first()]) indexs.pollFirst();
            if(!indexs.isEmpty()){
                int index=indexs.pollFirst();
                visited[index]=true;
                if(index>=1){
                    visited[index-1]=true;
                }
                if(index<n-1){
                    visited[index+1]=true;
                }
                rs+=nums1[i];
                System.out.printf("Index: %s, value %s\n", index, nums1[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{2,1,3,4,5,2};
        int[] arr=new int[]{2,3,5,1,3,2};
        System.out.println(findScore(arr));
        //** Đề bài:
        //- You are given (an array nums) consisting of (positive integers).
        //- Starting with score = 0, apply the following algorithm:
        //  + Choose ((the smallest integer) of the array) that is (not marked).
        //      + If there is (a tie), choose (the one) with (the smallest index).
        //  + Add the value of the chosen integer to score.
        //  + Mark (the chosen element) and (its two adjacent elements) if they exist.
        //- Repeat until all the array elements are marked.
        //* Return (the score) you get after applying (the above algorithm).
        //
        //- Bài này tìm số lần đánh dấu từ phẩn tử min --> max
        //- Nếu index của phần tử đánh dấu là (i) --> thì ta sẽ đánh dấu thêm 2 phần tử bên cạnh là (i-1), (i+1)
        //- Nếu có nhiều phần tử value=min --> lấy smallest index.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Xây dựng data structure
        //- Nếu muốn đánh dấu phần tử đã traverse ta dùng boolean visited[]
        //- Nếu muốn tìm smallest index --> HashMap<Value, TreeSet<Index>> ==> Các index sẽ được sắp xếp tương ứng với từng value
        //+ Nếu muốn dùng value nào ta sẽ lấy index đó ra + remove + mark nó.
        //1.2, Complexity:
        //- Time complexity: O(n)log(n)
        //- Space complexity : O(n)
    }
}
