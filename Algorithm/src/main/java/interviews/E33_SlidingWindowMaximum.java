package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class E33_SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        PriorityQueue<Integer> integers=new PriorityQueue<>((a, b)-> (b - a));
        List<Integer> rs=new ArrayList<>();

        if(n>=1){
            integers.add(nums[0]);
        }

        for(int i=0;i<k;i++){
            integers.add(nums[i]);
        }

        for(int i=0;i<n;i++){
            integers.poll();
            for(int j=0;j<=k;j++){
                rs.add(integers.peek());
            }
            integers.poll();
        }
        int rsArr[]=new int[rs.size()];

        for(int i=0;i<rs.size();i++){
            rsArr[i]=rs.get(i);
        }
        return rsArr;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,-1,-3,5,3,6,7};
        int rs[]=maxSlidingWindow(arr, 3);
        System.out.println("");
    }
}
