package E1_heap_priority_queue;

import java.util.*;

public class E9_AvoidFloodInTheCity {

    public static class Node{
        int val;
        TreeSet<Integer> currentIndexes;
        public Node(int val, TreeSet<Integer> currentIndexes){
            this.val=val;
            this.currentIndexes=currentIndexes;
        }

        @Override
        public String toString() {
            return val+ ", "+ currentIndexes;
        }
    }

    public static int[] avoidFlood(int[] rains) {
        HashMap<Integer, TreeSet<Integer>> setHashMap=new HashMap<>();
        int n=rains.length;

        for(int i=0;i<n;i++){
            int curVal=rains[i];
            if(curVal==0){
                continue;
            }
            TreeSet<Integer> currentIndexes = setHashMap.computeIfAbsent(curVal, k -> new TreeSet<>());
            currentIndexes.add(i);
        }
        PriorityQueue<Node> queue=new PriorityQueue<>((a, b) ->
        {
            if(a.currentIndexes.size()==0&&b.currentIndexes.size()==0){
                return 0;
            }
            if(a.currentIndexes.size()!=0&&b.currentIndexes.size()==0){
                return -1;
            }else if(a.currentIndexes.size() == 0){
                return 1;
            }
            return a.currentIndexes.first()-b.currentIndexes.first();
        }
        );
        List<Integer> rs=new ArrayList<>();
        HashSet<Integer> exists=new HashSet<>();
        int[] rsArr=new int[n];

        for(int i=0;i<n;i++){
            int curVal=rains[i];
            if(exists.contains(curVal)){
                rsArr= new int[]{};
                break;
            }
//            System.out.printf("Queue: %s\n",queue);
            if(curVal!=0){
                TreeSet<Integer> currentIndexes=setHashMap.get(curVal);
                currentIndexes.remove(i);
//                System.out.printf("val: %s, indexes: %s\n", curVal, currentIndexes);
                queue.add(new Node(curVal, currentIndexes));
                rs.add(-1);
                exists.add(curVal);
//                System.out.printf("Add %s\n", curVal);
            }else if(!queue.isEmpty()){
                Node driedNode=queue.poll();
                rs.add(driedNode.val);
                exists.remove(driedNode.val);
//                System.out.printf("Remove %s\n", driedNode.val);
            }else{
                rs.add(1);
            }
        }
//        System.out.println(rs);
//        System.out.println(rsArr.length);
//        System.out.println(queue.size());

        for(int i=0;i<rsArr.length;i++){
            rsArr[i]=rs.get(i);
//            System.out.printf("%s, ", rsArr[i]);
        }
//        System.out.println();
        return rsArr;
    }

    public static int[] avoidFloodOptimization(int[] rains) {
        HashMap<Integer, TreeSet<Integer>> setHashMap=new HashMap<>();
        int n=rains.length;

        for(int i=0;i<n;i++){
            int curVal=rains[i];
            if(curVal==0){
                continue;
            }
            TreeSet<Integer> currentIndexes = setHashMap.computeIfAbsent(curVal, k -> new TreeSet<>());
            currentIndexes.add(i);
        }
        PriorityQueue<Node> queue=new PriorityQueue<>((a, b) ->
        {
            if(a.currentIndexes.size()==0&&b.currentIndexes.size()==0){
                return 0;
            }
            if(a.currentIndexes.size()!=0&&b.currentIndexes.size()==0){
                return -1;
            }else if(a.currentIndexes.size() == 0){
                return 1;
            }
            return a.currentIndexes.first()-b.currentIndexes.first();
        }
        );
        List<Integer> rs=new ArrayList<>();
        HashSet<Integer> exists=new HashSet<>();
        int[] rsArr=null;

        for(int i=0;i<n;i++){
            int curVal=rains[i];
            if(exists.contains(curVal)){
                rsArr= new int[]{};
                break;
            }
//            System.out.printf("Queue: %s\n",queue);
            if(curVal!=0){
                TreeSet<Integer> currentIndexes=setHashMap.get(curVal);
                currentIndexes.remove(i);
//                System.out.printf("val: %s, indexes: %s\n", curVal, currentIndexes);
                queue.add(new Node(curVal, currentIndexes));
                rs.add(-1);
                exists.add(curVal);
//                System.out.printf("Add %s\n", curVal);
            }else if(!queue.isEmpty()){
                Node driedNode=queue.poll();
                rs.add(driedNode.val);
                exists.remove(driedNode.val);
//                System.out.printf("Remove %s\n", driedNode.val);
            }else{
                rs.add(1);
            }
        }

        if(rsArr==null){
            rsArr=new int[n];
        }
        for(int i=0;i<rsArr.length;i++){
            rsArr[i]=rs.get(i);
//            System.out.printf("%s, ", rsArr[i]);
        }
//        System.out.println();
        return rsArr;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Đất nước của bạn có vô số hồ. Ban đầu, tất cả các hồ đều trống, nhưng khi trời mưa trên hồ thứ n thì hồ thứ n đầy nước.
        //- Nếu trời mưa trên một hồ nước đầy nước thì sẽ có lũ lụt.
        //- Mục tiêu của bạn là tránh lũ lụt ở bất kỳ hồ nào.
        //- Your goal is to avoid floods in any lake.
        //
        //Given an integer array rains where:
        //- rains[i] > 0 means there will be rains over the rains[i] lake.
        //- rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
        //
        //* Return an array ans where:
        //- ans.length == rains.length
        //- ans[i] == -1 if rains[i] > 0.
        //- ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= rains.length <= 10^5
        //0 <= rains[i] <= 10^9
        //
        //- Brainstorm
        //Ex:
        //Input: rains = [1,2,0,0,2,1]
        //Output: [-1,-1,2,1,-1,-1]
        //Explanation: After the first day full lakes are [1]
        //After the second day full lakes are [1,2]
        //After the third day, we dry lake 2. Full lakes are [1]
        //After the fourth day, we dry lake 1. There is no full lakes.
        //After the fifth day, full lakes are [2].
        //After the sixth day, full lakes are [1,2].
        //It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
        //
        //Ex:
        //Input: rains =
        //[1,2,0,0,2,1]
        //[0,1,2,3,4,5]
        //+ Các bước rain
        //+ index=0: full 1
        //+ index=1: full 1,2
        //+ index=2: dry 1 or 2
        //+ index=3: dry 1 or 2
        //+ index=4: full 2
        //+ index=5: full 1
        //
        //Ex:
        //Input: rains =
        //[1,2,0,0,3,1]
        //[0,1,2,3,4,5]
        //- Ta sẽ chọn dry 2 thay vì 1
        //
        //Ex:
        //Input: rains =
        //[0,3,0,4,1]
        //[0,1,2,3,4]
        //--> dry 3 trước -> sau đó mới đến 4
        //
        //Ex:
        //Input: rains =
        //[1,2,0,0,0,3,0,4,4,0,6,1]
        //[0,1,2,3,4,5,6,7,8,9]
        //+ Index=2
        //  + 2 không xuất hiện sau --> dry 2
        //  + 1 xuất hiện phía sau ==> Thì có nên xoá 1 không?
        //Ex:
        //[1,2,0,0,(0,1,0),1,0,2,4]
        //- HashVal -> tree set (index)
        //- priority queue --> sort by (the first index)
        //
        //- Để chọn được dry cái gì cần:
        //+ Xem before đã full cái gì
        //+ Và xem chọn nên dry cái gì trong những cái full --> Tốt cho đằng sau
        //
        //- Flood khi nào?
        //Ex:
        //[1,2,0,2,1]
        //
        //* Special cases:
//        int[] rains = {1,2,0,0,2,1};
//        int[] rains = {1,2,0,1,2};
//        int[] rains = {1,2,3,4};
//        int[] rains = {1};
//        int[] rains = {1,0,1,0,0,1};
//        int[] rains = {};
//        int[] rains = {1,2,0,2,3,0,1};
        //i=0: 1
        //i=1: 1,2
        //i=2: dry 2, 1
        //i=3: 1,2
        //i=4: 1,2,3
        //i=5: dry 1 -> 2,3
        //i=6: 1,2,3
        //Ex:
        //- Ở đây mặc dù 1 còn nhưng sẽ ưu tiên dry 2/3 để tránh flood
        int[] rains = {1,0,2,3,0,2};
//        int[] rains = {1,0,2,3,0,3};
        //+ i=0: 1
        //+ i=1: dry 1 (Vì chỉ có 1 element)
        //+ i=2: 2
        //+ i=3: 2,3
        //+ i=4: dry 3 --> Vì ta thấy rằng sau 0 thì 3 là số gần nhất
        //+ i=5 : 2,3
//        int[] rains = {1,0,2,3,0,3,3};
        //+ Xoá 3 cả 2 lần ==> Tư duy này "SAI" nhé vì trước 0 đầu tiên chưa xuất hiện 3
        //+ Xoá lần lượt 1, 3
        //
        //- 1 chỉ xuất hiện 1 lần trước đó --> Không nhất thiết là xoá ngay ==> trừ khi nó xuất hiện lại sau.
        //* Problem:
        //- Vấn đề các point xuất hiện 1 lần ta có thể fix bằng ưu tiên những thằng còn index lên trước.
        //
        //1.2, Optimization
        //- Phần return rs --> Nên tối ưu size
        //- Tối ưu liên quan đến priority queue:
        //Ex:
        //int[] rains = {1,0,2,3,0,2};
        //
        //
        //1.3, Complexity
        //- Space :
        //- Time :
        avoidFlood(rains);
        //#Reference:
        //1953. Maximum Number of Weeks for Which You Can Work
        //2590. Design a Todo List
        //2601. Prime Subtraction Operation
        avoidFloodOptimization(rains);
    }
}
