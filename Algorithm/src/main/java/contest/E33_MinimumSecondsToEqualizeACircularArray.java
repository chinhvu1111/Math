package contest;

import java.util.*;

public class E33_MinimumSecondsToEqualizeACircularArray {

    public static int minimumSecondsTimeout(List<Integer> nums) {
        if(nums==null || nums.size()==0){
            return 0;
        }
        //Value ==> list node (changed base on this value)
        HashMap<Integer, HashSet<Integer>> visited=new HashMap<>();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        HashMap<Integer, Integer> mapCountNear=new HashMap<>();

        //{2,1,3,3,2}
        //2 : 2
        //1 : 1
        //3 : 2
        for(int num: nums){
            mapCount.put(num, mapCount.getOrDefault(num, 0)+1);
        }
        int n=nums.size();

        //{2,1,3,3,2}
        //<2,{i=1,i=3}>
        //<3,{i==1,i==4}>
        //<1,{i==0,i==2}>
        for(int i=0;i<n;i++){
            int currentValue=nums.get(i);
            int indexLeft=(i - 1 + n) % n;
            int left=nums.get(indexLeft);
            int indexRight=(i + 1) % n;
            int right=nums.get(indexRight);
            HashSet<Integer> currentNum=visited.get(currentValue);

            if(currentNum==null){
                currentNum=new HashSet<>();
            }
            currentNum.add(i);

            if(currentValue!=left&&!currentNum.contains(indexLeft)){
                mapCountNear.put(currentValue, mapCountNear.getOrDefault(currentValue, 0)+1);
                currentNum.add(indexLeft);
            }
            if(currentValue!=right&&!currentNum.contains(indexRight)){
                mapCountNear.put(currentValue, mapCountNear.getOrDefault(currentValue, 0)+1);
                currentNum.add(indexRight);
            }
            visited.put(currentValue, currentNum);
        }
        int maxValue=0;
        int element=0;

        //element=3/2
        //+ element = 3
        //sum= 2 + 2
        //
        //+ element = 2
        //sum= 2 + 2
        //
        //element = 3
        //maxValue=4
        if(mapCountNear.size()==0){
            return 0;
        }
        for(Integer num:mapCount.keySet()){
            int currentSum=mapCount.get(num)+mapCountNear.get(num);

            if(maxValue<currentSum){
                maxValue=currentSum;
                element=num;
            }
        }
        List<Integer> listElements=new ArrayList<>();

        for(Integer num:mapCount.keySet()){
            int currentSum=mapCount.get(element)+mapCountNear.get(element);
            if(currentSum==maxValue){
                listElements.add(num);
            }
        }
        int rs=Integer.MAX_VALUE;

        for(Integer e: listElements){
            int currentRs=getResult(n, e, nums, visited, maxValue);
            if(rs>currentRs){
                rs=currentRs;
            }
        }

        return rs;
    }

    public static int getResult(int n, int element, List<Integer> nums, HashMap<Integer, HashSet<Integer>> visited, int maxValue){
        HashSet<Integer> adjNodes=visited.get(element);
//        System.out.println(visited);
//        System.out.printf("%s %s\n", element, maxValue);
//        System.out.println(adjNodes);
        Queue<Integer> nodes = new LinkedList<>(adjNodes);
        int numberSeconds=1;

        //n=5
        //<2,{i==1,i==3}>, adjNodes.size()=2
        //{2,2,3,2,2}
        //{2,1,3,3,2}
        //<2,{i==1,i==3,i=2}>, adjNodes.size()=3
        while (adjNodes.size()!=n){
            Queue<Integer> nextNodes=new LinkedList<>();
            while(!nodes.isEmpty()){
                int currentIndex=nodes.poll();
                int indexLeft=(currentIndex - 1 + n) % n;
                int left=nums.get(indexLeft);
                int indexRight=(currentIndex + 1) % n;
                int right=nums.get(indexRight);

                if(left!= element&&!adjNodes.contains(indexLeft)){
                    nextNodes.add(indexLeft);
                    adjNodes.add(indexLeft);
                }
                if(right!=element&&!adjNodes.contains(indexRight)){
                    nextNodes.add(indexRight);
                    adjNodes.add(indexRight);
                }
//                System.out.printf("%s %s %s %s\n", indexLeft, left, indexRight, right);
            }
//            System.out.printf("Queue: %s\n",nodes);
//            System.out.printf("Hashset: %s\n",adjNodes);
            nodes=nextNodes;
            numberSeconds++;
        }
        return numberSeconds;
    }

    public static int minimumSecondsTimeout1(List<Integer> nums) {
        if(nums==null || nums.size()==0){
            return 0;
        }
        //Value ==> list node (changed base on this value)
        HashMap<Integer, HashSet<Integer>> visited=new HashMap<>();

        //{2,1,3,3,2}
        //2 : 2
        //1 : 1
        //3 : 2
        int n=nums.size();
        int seconds=0;
        int maxCount=0;

        while (maxCount!=n){
            for(int i=0;i<n;i++){
                int currentValue=nums.get(i);
                HashSet<Integer> currentSet=visited.get(currentValue);
                int indexLeft=(i - 1 + n) % n;
                int left=nums.get(indexLeft);
                int indexRight=(i + 1) % n;
                int right=nums.get(indexRight);
                if(currentSet==null){
                    currentSet=new HashSet<>();
                }
                currentSet.add(i);

                if(left!= currentValue){
                    currentSet.add(indexLeft);
                }
                if(right!=currentValue){
                    currentSet.add(indexRight);
                }
                maxCount=Math.max(maxCount, currentSet.size());
                visited.put(currentValue, currentSet);
            }
            System.out.println(visited);
            seconds++;
        }

        return maxCount;
    }

    public static int minimumSeconds(List<Integer> nums) {
        //Space : O(N*3)
        HashMap<Integer, Integer> previousPos=new HashMap<>();
        HashMap<Integer, Integer> firstPos=new HashMap<>();
        HashMap<Integer, Integer> maxValue=new HashMap<>();
        int n=nums.size();

        //Time : O(N)
        for(int i=0;i<n;i++){
            int currentValue=nums.get(i);
            if(!firstPos.containsKey(currentValue)){
                firstPos.put(currentValue, i);
            }
            if(!maxValue.containsKey(currentValue)){
                maxValue.put(currentValue, 0);
            }
            if(previousPos.containsKey(currentValue)){
                int prevPosition=previousPos.get(currentValue);
                int currentMax=maxValue.get(currentValue);
                currentMax=Math.max(currentMax, (i-prevPosition)/2);
                previousPos.put(currentValue, i);
                maxValue.put(currentValue, currentMax);
            }else{
                previousPos.put(currentValue, i);
            }
        }
        int rs=Integer.MAX_VALUE;

        //Max(Time) = O(N)
        for(int key: firstPos.keySet()){
            int currentMax=maxValue.get(key);
            int firstElementPos=firstPos.get(key);
            Integer lastPos=previousPos.get(key);
            // System.out.printf("%s: %s %s\n", key, firstElementPos, n-1-lastPos);
            currentMax=Math.max(currentMax, (firstElementPos+n-lastPos)/2);
            rs=Math.min(rs, currentMax);
        }
        return rs;
    }

    public static int minimumSecondsOptimization(List<Integer> nums) {
        HashMap<Integer, List<Integer>> valueToIndexs=new HashMap();
        int n=nums.size();

        for(int i=0;i<n;i++){
            List<Integer> listIndexs=valueToIndexs.get(nums.get(i));

            if(listIndexs==null){
                listIndexs=new ArrayList();
            }
            listIndexs.add(i);
            valueToIndexs.put(nums.get(i), listIndexs);
        }
        // System.out.println(valueToIndexs);
        int rs=Integer.MAX_VALUE;

        for(int key: valueToIndexs.keySet()){
            List<Integer> listIndexs=valueToIndexs.get(key);
            int m=listIndexs.size();
            int currentRs=0;
            int prevIndex=listIndexs.get(0);

            for(int j=1;j<m;j++){
                currentRs=Math.max(currentRs, (listIndexs.get(j)-prevIndex)/2);
                prevIndex=listIndexs.get(j);
            }
            currentRs=Math.max(currentRs, (listIndexs.get(0)+n-listIndexs.get(m-1))/2);
            // System.out.printf("%s %s\n", key, currentRs);
            rs=Math.min(rs, currentRs);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement:
        //- Make nums[i] become:
        // arr={1,2,3,4}
        //+ i = 1, n = 4
        //+ nums[i]
        //+ nums[(i - 1 + n) % n] = 0
        //+ nums[(i + 1) % n] = 2
        //
        //+ i = 0, n = 4
        //+ nums[i]
        //+ nums[(i - 1 + n) % n] = 3
        //+ nums[(i + 1) % n] = 1
        //* quy luật : Rotate index.
        //
        //* Note that all the elements get replaced simultaneously.
        //* Return (the minimum number of seconds) needed to make all elements in the array nums (equal).
        //
        // Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //+ Constraints:
        //+ 1 <= n == nums.length <= 105
        //+ 1 <= nums[i] <= 109
        //
        //- Brainstorm
        //- Change tất cả cùng 1 lúc ==> Không thế nào a --> b, b --> c được
        //+ Chỉ có thể gán theo kiểu những giá trị được sử dụng --> Static trong turn đó
        //+ Không thể a=b, b=c ==> Cùng lúc được (Vì không thể biết cái nào thực hiện được)
        //
        //VD:
        //nums = [2,1,3,3,2]
        //--> 2 steps
        //
        //nums = [3,3,3,3,3]
        //
        //nums = [2,2,3,1,3,3,2]
        //-> nums = [2,2,2,2,2,2,2]
        //
        //+ nums = [2,2,2,1,3,2,2]
        //+ nums = [2,2,2,1,3,2,2]
        //+ nums = [2,2,2,2,2,2,2]
        //rs= 3
        //
        //nums = [2,2,3,1,3,3,2]
        //-> nums = [3,3,3,3,3,3,3]
        //+ nums = [2,3,3,3,3,3,3]
        //+ nums = [3,3,3,3,3,3,3]
        //
        //nums = [2,3,2,3,3]
        // 2 near: 2
        // 3 near : 2
        //==> 2/3
        //-> Greedy
        //
        //nums = [2,3,2,3,3]
        //+ Số count + số lượng kề ==> Cái ta chọn thay đổi.
        //
        //- Tìm số steps như thế nào?
        //nums = [2,3,2,3,3]
        //+ Scan theo layers
        //
        //- Special cases:
        //- Các cases mà có cùng value= count + the number of adjent nodes
        //{13,1,12,14,8,11,3,13,15,3,4}
        //- element=3
        //+ {13,1,12,14,8,3,3,3,3,3,3}
        //+ {3,1,12,14,3,3,3,3,3,3,3}
        //+ {3,3,12,3,3,3,3,3,3,3,3}
        //+ {3,3,3,3,3,3,3,3,3,3,3}
        //
        //{13,1,12,14,8,11,3,13,15,3,4}
        //- element=13
        //+ {13,13,12,14,8,11,13,13,13,3,13}
        //+ {13,13,13,14,8,13,13,13,13,13,13}
        //+ {13,13,13,13,13,13,13,13,13,13,13}
        //
        //--> Thử loop số seconds xem có được không
        //+ Each second we need to reach all element ==> Timeout
        //
        //- {13,1,12,14,8,11,3,13,15,3,4}
        //-->
        //{13,X,X,X,X,X,X,13,X,X,X}
        //{X,X,X,X,X,X,3,X,X,3,X}
        //
        //{13,1,12,14,8,11,3,13,15,3,4}
        //+ second=1
        //{0,1,2,3,4,5,6,7,8,9,10}
        //+ i=0: 0,1,10
        //+ i=1: 1,0,10
        //--> Khá là khó vì đang muốn chạy cùng 1 lúc.
        //
        //* Result : Failed
        //
        //* Reference:
        //- We have (i and j)
        //+ nums[i]==nums[j]==x
        //+ The number of elements we need to change from nums[k] to X
        //  + Example: 2,1,(3),4,5,(3),6
        //  i=2, j=5 ==> Take 1 seconds to change
        //  + Example: 2,1,(3),4,(3),6,7
        //  i=2, j=4 ==> Take 1 seconds to change
        //  + Example: 2,1,(3),4,6,5,(3),6,7
        //  i=2, j=6 ==> Take 2 seconds to change
        //  ** Rule:
        //  Time = (j-i)/2
        //- If we have more than 2 values
        //Example:
        //2,1,(3),4,5,(3),6,(3),7
        //indexs= 2, 5, 7
        //+ ranges = (2,5), (5,7), (7,2) ==> Seconds= Max of all ranges.
        //
        //+ Tính Max của từng values
        //+ Result chính là Min trong các giá trị max đó.
        //
        //1.1, Implementation
        //- HashMap<Integer, Integer> prevPosition
        //  + To calculate current range
        //- HashMap<Integer, Integer> firstPosition
        //  + To calculate from last to first range (Rotation)
        //- HashMap<Int, int> max:
        //  + Max value of each element
        //
        //1.2, Optimization
        //- Để tối ưu ta sẽ dùng Map dạng:
        //- < Int, List<Int>
        //+ key : value
        //+ values : List of index such that nums[i]==key
        //
        //- After that we will loop all keys ==> calculate the result for each key
        //
        //1.3, Complexity :
        //- N is the number of element
        //- K is the number of unique values
        //- Time complexity : O(N+K)
        //- Space complexity : O(N*3)
        //
        //#Reference:
        //359. Logger Rate Limiter
        //364. Nested List Weight Sum II
        //1944. Number of Visible People in a Queue
        //929. Unique Email Addresses
        //1324. Print Words Vertically
        //360. Sort Transformed Array
        //1656. Design an Ordered Stream
        //540. Single Element in a Sorted Array
        //303. Range Sum Query - Immutable
//        Integer[] nums={2,1,3,3,2};
//        Integer[] nums={1,2,1,2};
//        Integer[] nums={};
//        Integer[] nums={5,5,5,5};
//        Integer[] nums={18,9,15,4,17,10};
        Integer[] nums={13,1,12,14,8,11,3,13,15,3,4};
        System.out.println(minimumSeconds(Arrays.asList(nums)));
    }
}
