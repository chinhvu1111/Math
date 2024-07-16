package E1_daily;

import java.util.*;

public class E41_MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public static int minDifferenceSort(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;

        if(n<=3){
            return 0;
        }
        int allRightDiff=nums[n-4]-nums[0];
        int allLeftDiff=nums[n-1]-nums[3];
        int leftBoth=nums[n-2]-nums[2];
        int rightBoth=nums[n-3]-nums[1];

        return Math.min(allRightDiff, Math.min(allLeftDiff, Math.min(leftBoth, rightBoth)));
    }

    public static int[] finMinMax(HashMap<Integer, Integer> maxVisitedTmp, int[] nums, int n){
        int min=Integer.MAX_VALUE;
        int max=0;

        for(int i=0;i<n;i++){
            if(maxVisitedTmp.containsKey(nums[i])){
                max=Math.max(max, nums[i]);
                min=Math.min(min, nums[i]);
            }else{
                int curVal=maxVisitedTmp.get(nums[i]);

                if(curVal>1){
                    maxVisitedTmp.put(nums[i], maxVisitedTmp.get(nums[i])-1);
                }else{
                    maxVisitedTmp.remove(nums[i]);
                }
            }
        }
        return new int[]{min, max};
    }

    public static int minDifference1(int[] nums) {
        int n=nums.length;
        if(n<=3){
            return 0;
        }
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>();
        PriorityQueue<Integer> minHeap=new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<n;i++){
            minHeap.add(nums[i]);
            maxHeap.add(nums[i]);
            if(maxHeap.size()>3){
                maxHeap.poll();
            }
            if(minHeap.size()>3){
                minHeap.poll();
            }
        }
        List<Integer> changedMaxElements=new ArrayList<>();
        HashMap<Integer, Integer> maxVisited=new HashMap<>();
        HashMap<Integer, Integer> minVisited=new HashMap<>();

        while (!maxHeap.isEmpty()){
            int curMax=maxHeap.poll();
            changedMaxElements.add(curMax);
            maxVisited.put(curMax, maxVisited.getOrDefault(curMax, 0)+1);
        }

        List<Integer> changedMinElements=new ArrayList<>();

        while (!minHeap.isEmpty()){
            int curMin=minHeap.poll();
            changedMinElements.add(curMin);
            minVisited.put(curMin, minVisited.getOrDefault(curMin, 0)+1);
        }
//        System.out.println(changedMaxElements);
//        System.out.println(changedMinElements);
        HashMap<Integer, Integer> maxVisitedTmp=new HashMap<>(maxVisited);
        HashMap<Integer, Integer> minVisitedTmp=new HashMap<>(minVisited);

        int rs=Integer.MAX_VALUE;
        //All Right
        int max=0;
        int min=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            if(!maxVisitedTmp.containsKey(nums[i])){
                max=Math.max(max, nums[i]);
                min=Math.min(min, nums[i]);
            }else{
                int curVal=maxVisitedTmp.get(nums[i]);

                if(curVal>1){
                    maxVisitedTmp.put(nums[i], maxVisitedTmp.get(nums[i])-1);
                }else{
                    maxVisitedTmp.remove(nums[i]);
                }
            }
        }
//        System.out.printf("Max: %s, Min: %s\n", max, min);
        rs=Math.min(rs, max-min);
        max=0;
        min=Integer.MAX_VALUE;
        //All left
        for(int i=0;i<n;i++){
            if(!minVisitedTmp.containsKey(nums[i])){
                max=Math.max(max, nums[i]);
                min=Math.min(min, nums[i]);
            }else{
                int curVal=minVisitedTmp.get(nums[i]);

                if(curVal>1){
                    minVisitedTmp.put(nums[i], minVisitedTmp.get(nums[i])-1);
                }else{
                    minVisitedTmp.remove(nums[i]);
                }
            }
        }
        rs=Math.min(rs, max-min);
//        System.out.printf("Max: %s, Min: %s\n", max, min);
        maxVisitedTmp=new HashMap<>();
        maxVisitedTmp.put(changedMaxElements.get(2), 1);
        maxVisitedTmp.put(changedMaxElements.get(1), 1);
        minVisitedTmp=new HashMap<>();
        minVisitedTmp.put(changedMinElements.get(2), 1);

        max=0;
        min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(!minVisitedTmp.containsKey(nums[i])&&!maxVisitedTmp.containsKey(nums[i])){
                max=Math.max(max, nums[i]);
                min=Math.min(min, nums[i]);
            }else if(minVisitedTmp.containsKey(nums[i])){
                int curVal=minVisitedTmp.get(nums[i]);

                if(curVal>1){
                    minVisitedTmp.put(nums[i], minVisitedTmp.get(nums[i])-1);
                }else{
                    minVisitedTmp.remove(nums[i]);
                }
            }else if(maxVisitedTmp.containsKey(nums[i])){
                int curVal=maxVisitedTmp.get(nums[i]);

                if(curVal>1){
                    maxVisitedTmp.put(nums[i], maxVisitedTmp.get(nums[i])-1);
                }else{
                    maxVisitedTmp.remove(nums[i]);
                }
            }
        }
        rs=Math.min(rs, max-min);
//        System.out.printf("Max: %s, Min: %s\n", max, min);
        maxVisitedTmp=new HashMap<>();
        maxVisitedTmp.put(changedMaxElements.get(2), 1);
        minVisitedTmp=new HashMap<>();
        minVisitedTmp.put(changedMinElements.get(2), 1);
        minVisitedTmp.put(changedMinElements.get(1), 1);

        max=0;
        min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(!minVisitedTmp.containsKey(nums[i])&&!maxVisitedTmp.containsKey(nums[i])){
                max=Math.max(max, nums[i]);
                min=Math.min(min, nums[i]);
            }else if(minVisitedTmp.containsKey(nums[i])){
                int curVal=minVisitedTmp.get(nums[i]);

                if(curVal>1){
                    minVisitedTmp.put(nums[i], minVisitedTmp.get(nums[i])-1);
                }else{
                    minVisitedTmp.remove(nums[i]);
                }
            }else if(maxVisitedTmp.containsKey(nums[i])){
                int curVal=maxVisitedTmp.get(nums[i]);

                if(curVal>1){
                    maxVisitedTmp.put(nums[i], maxVisitedTmp.get(nums[i])-1);
                }else{
                    maxVisitedTmp.remove(nums[i]);
                }
            }
        }
        rs=Math.min(rs, max-min);
//        System.out.printf("Max: %s, Min: %s\n", max, min);
        return rs;
    }

    public static int minDifferenceRefer(int[] nums) {
        int n = nums.length;
        if (n <= 4) return 0;

        Arrays.sort(nums);
        int left=3;
        int rs=Integer.MAX_VALUE;
        int right=n-1;

        while(left>=0){
//            System.out.printf("Max: %s, min: %s\n", nums[right], nums[left]);
            rs=Math.min(rs, nums[right]-nums[left]);
            left--;
            right--;
        }
        return rs;
    }
    public static int minDifferenceReferOptimization(int[] nums) {
        int n = nums.length;
        if (n <= 4) return 0;
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>();
        PriorityQueue<Integer> minHeap=new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<n;i++){
            minHeap.add(nums[i]);
            maxHeap.add(nums[i]);
            if(maxHeap.size()>4){
                maxHeap.poll();
            }
            if(minHeap.size()>4){
                minHeap.poll();
            }
        }
        List<Integer> changedMaxElements=new ArrayList<>();

        while (!maxHeap.isEmpty()){
            int curMax=maxHeap.poll();
            changedMaxElements.add(curMax);
        }

        List<Integer> changedMinElements=new ArrayList<>();

        while (!minHeap.isEmpty()){
            int curMin=minHeap.poll();
            changedMinElements.add(curMin);
        }
//        System.out.println(changedMaxElements);
//        System.out.println(changedMinElements);
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<4;i++){
            rs=Math.min(changedMaxElements.get(i)-changedMinElements.get(3-i), rs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums.
        //  + In one move, you can choose (one element of nums) and change it to (any value).
        //* Return the (minimum difference) between the (largest) and (smallest) value of nums after performing at most (three moves).
        //  - Chọn any number để change to any value
        //      + Được thực hiện nhiều nhất 3 lần
        //  * Return min diff between max and min after that.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //
        //- Brainstorm
        //Example 1:
        //Input: nums = [5,3,2,4]
        //Output: 0
        //- Mỗi lần mình chọn là 1 số:
        //  + Số đó có thể là min/ max ==> Để giảm diff giữa (min) và (max) đi
        //
        //- Vì là số lẻ lần nên có thể có case nào:
        //  + 2 lần xuất phát từ 1 bên thì sẽ tối ưu hơn không?
        //Ex:
        //1,1,2,3,6,100
        //  + Change 100,6,3 ==> 2
        //      ==> return 1
        //  + Change 1,100,6 ==> 3
        //      ==> Return 2
        //- Xu hướng là change 3 các giá trị
        //  (== min/ max) các phần tử còn lại
        //  --> Cho kết quả tốt nhất = (max - min) remaining values
        //- Bài toán thành get (max,min) nhất của (n-3) phần tử
        //
        //- Cách đơn giản nhất là sort
        //  + Sau đó xét các cases đầu và cuối
        //- Có 4 cases:
        //  + First (2 elements consecutively), last 1 element
        //  + First 1 element, last (2 elements consecutively)
        //  + First 1 element, last 1 element
        //      + First 1 element
        //      + Last 1 element
        //
        //- Nếu xét bình thường:
        //  + Chia case ra là được
        //- Dùng loop để xử lý:
        //Ex:
        //1,3,4,6,8,9,13,15
        //- Loop all cases thay vì chia 4 cases:
        //+ Thứ tự loop:
        //1,3,4,6,8,9,13,15
        //1,3,4,(6,8,9,13,15)
        //1,3,(4,6,8,9,13),15
        //1,(3,4,6,8,9),13,15
        //(1,3,4,6,8),9,13,15
        //
        //* Point ở đây là phần tử lấy sẽ là:
        //  + Từ index=4 ==> 0
        //
        //* Kinh nghiệm:
        //  + Code priority queue với size=n
        //      + Luôn add trước
        //      + Size>n: poll ra là được
        //
        //1.1, Optimization
        //- Priority Queue --> O(n) time
        //- Chọn ra 4 số max nhất, 4 số min nhất là được.
        //  + Sau đó loop all từ 2 phía là được.
        //
        //
        //1.2, Complexity
        //- Space: O(4)
        //- Time : O(n)
        //
//        int[] nums = {1,5,0,10,14};
        //nums = [0,1,5,10,14]
//        int[] nums = {20,66,68,57,45,18,42,34,37,58};
//        int[] nums = {82,81,95,75,20};
//        int[] nums = {3,100,20};
        int[] nums = {1,5,0,10,14};
        //20,75,81,82,95
        System.out.println(minDifferenceSort(nums));
        System.out.println(minDifference1(nums));
        System.out.println(minDifferenceRefer(nums));
        System.out.println(minDifferenceReferOptimization(nums));
        //#Reference:
        //2616. Minimize the Maximum Difference of Pairs
    }
}
