package interviews;

import java.util.*;

public class E33_SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        PriorityQueue<int[]> integers=new PriorityQueue<>((a, b)-> (b[0] - a[0]));
        int rsArr[]=new int[nums.length-k+1];

        for(int i=1;i<=k;i++){
            integers.add(new int[]{nums[i-1], i-1});
        }
        if(nums.length>=1){
            rsArr[0]=integers.peek()[0];
        }

        for(int i=1;i+k-1<n;i++){
            if(i+k-1<n){
                integers.add(new int[]{nums[i+k-1], i+k-1});
            }
            while(!integers.isEmpty()&&integers.peek()[1]<i){
                integers.poll();
            }
            rsArr[i]=integers.peek()[0];
        }
        return rsArr;
    }

    public static int[] maxSlidingWindowOptimized(int[] nums, int k) {
        int n=nums.length;
        Deque<Integer> deque=new LinkedList<>();
        int rsArr[]=new int[nums.length-k+1];

        for(int i=1;i<=k;i++){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i-1]){
                deque.pollLast();
            }
            deque.addLast(i-1);
        }
        if(!deque.isEmpty()){
            rsArr[0]=nums[deque.peekFirst()];
        }
        for(int i=1;i<nums.length-k+1;i++){
            int currentIndex=0;

            if(i+k-1<n){
                currentIndex=i+k-1;
            }else{
                currentIndex=n-1;
            }
            while (!deque.isEmpty()&&nums[deque.peekLast()]<nums[currentIndex]){
                deque.pollLast();
            }
            deque.addLast(currentIndex);
            if(!deque.isEmpty()&&deque.peekFirst()<i){
                deque.pollFirst();
            }
            rsArr[i]=nums[deque.peekFirst()];
        }
        return rsArr;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,-1,-3,5,3,6,7};
//        int arr[]=new int[]{1,3};
//        int arr[]=new int[]{1,-3,-2,-4,5};
        int rs[]=maxSlidingWindow(arr, 3);
        //B??i n??y l???i quay l???i t?? duy Deque t????ng t??? nh?? b??i tr?????c:
        //1,
        // VD:
        //1,-3,-2-4,5
        //T???i i=0:
        //Ta d??ng dequeue th?? queue c?? d???ng nh?? sau:
        //b1: 1
        //b2: 1, -3 (Add -3 v?? remove nh???ng s??? nh??? h??n (-3) ?????ng ?????ng tr?????c ===> M???C ????CH ????? T???O "D??Y S??? GI???M D???N (KH??NG T??NG D???N)"
        //---> L??c ???? s??? (l???n nh???t/ nh??? nh???t) s??? xu???t hi???n ??? 2 bi??n c???a queue.
        //2, Ch?? ?? b??i n??y c???n add element nums[i+k-1] v??o last thay v?? nums[i]
        maxSlidingWindowOptimized(arr, 3);
        System.out.println("");
    }
}
