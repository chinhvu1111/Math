package interviews;

import java.util.*;

public class E33_SlidingWindowMaximum_queue {

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
        //Bài này lại quay lại tư duy Deque tương tự như bài trước:
        //1,
        // VD:
        //1,-3,-2-4,5
        //Tại i=0:
        //Ta dùng dequeue thì queue có dạng như sau:
        //b1: 1
        //b2: 1, -3 (Add -3 và remove những số nhỏ hơn (-3) đứng đằng trước ===> MỤC ĐÍCH ĐỂ TẠO "DÃY SỐ GIẢM DẦN (KHÔNG TĂNG DẦN)"
        //---> Lúc đó số (lớn nhất/ nhỏ nhất) sẽ xuất hiện ở 2 biên của queue.
        //2, Chú ý bài này cần add element nums[i+k-1] vào last thay vì nums[i]
        //
        //1.1,
        //- Ý tưởng: khi thêm 1 phần tủ vào queue --> Loại bỏ các phần tử nhỏ hơn nó:
        //VD:
        //3,4,5,5, 2
        //add 3: 3
        //add 4: 4
        //add 5: 5
        //add 5: 5,5
        //add 2: 5,5,2
        //==> Queue không tăng.
        //==> Nên add duplicated vì như thế arr và queue sẽ gần giống nhau.
        maxSlidingWindowOptimized(arr, 3);
        System.out.println("");
    }
}
