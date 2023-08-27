package E1_two_pointers;

import java.util.*;
import java.util.List;

public class E6_CountOfSmallerNumbersAfterSelf {

    public static class Node{
        int index;
        int value;
        public Node(int index, int value){
            this.index=index;
            this.value=value;
        }
        public String toString() {
            return index+" : "+value;
        }
    }

    public static List<Integer> countSmallerStuckedSolution(int[] nums) {
        int n=nums.length;
        TreeSet<Integer> sortedNodes=new TreeSet<Integer>();
        List<Integer> rs=new LinkedList<>();
        HashMap<Integer, Integer> countMap=new HashMap<>();

        for(int i=n-1;i>=0;i--){
            Integer lessNode=sortedNodes.ceiling(nums[i]);
            System.out.printf("Index=%s, value=%s, greater value=%s\n", i, nums[i], lessNode);
            if(lessNode==null){
                rs.add(0, sortedNodes.size());
                sortedNodes.add(nums[i]);
                countMap.put(nums[i], countMap.getOrDefault(nums[i], 0)+1);
                continue;
            }
            int currentRs=0;

            for (Integer currentElement : sortedNodes) {
//                System.out.printf("%s, ", currentElement);
                if (Objects.equals(currentElement, lessNode)) {
                    break;
                }
                if(currentElement==nums[i]){
                    break;
                }
                currentRs+=countMap.get(currentElement);
            }
            if(sortedNodes.size()!=0){
                rs.add(0, currentRs);
            }else{
                rs.add(0, 0);
            }
            sortedNodes.add(nums[i]);
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0)+1);
        }
//        for(Integer e:rs){
//            System.out.printf("%s, ", e);
//        }
//        System.out.println();
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given nums
        //* Return array count[] where counts[i] is the number of smaller element to right of nums[i]
        //Ex:
        //nums = [5,2,6,1]
        //Output: [2,1,1,0]
        //Explanation:
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= nums.length <= 10^5
        //-10^4 <= nums[i] <= 10^4
        //
        //- Brainstorm
        //Ex:
        //nums = [5,2,6,1]
        //Output: [2,1,1,0]
        //- i==0 : count= 2
        //- i==1 : count= 1
        //- i==2 : count= 1
        //+ Sort dần từ phải qua trái
        //5 so sánh với (1,2,6) ==> 2
        //==> Binary search.
        //- Cần 1 max heap để tìm index.
        //
        //- Ta cần tìm được index của phần tử có trong priority queue/ max heap
        //VD:
        //nums= [5,2,6,1]
        //-> i=0: priority queue = {1,2,6} ==> floor = 2 ==> Old index = 1
        //==> Cần phải trả lại vị trí thực của nó trong queue ==> Cái này khi add xong node ==> Cần phải traverse lại để xác định index mới.
        //- Guess complexity : O(n(each element)) * ( O(log(n) - search) + O(log(n) - add)
        //==> Tệ
        //- Dự đoán Complexity : O(n^2)
        //
        //index=3
        //{ (1,i=4), (1,i=2), (2,i=1), (5,i=0),(6,i=3) }
        //index=2
        //index=1
        //index=0
        //==> Quay trở lại việc tìm index của search --> Không tìm được vì tree được sort/reindex liên tục mỗi lần Add new Element.
        //- Muốn tìm được index --> Traverse all array
        //
        //- Làm sao để Count tất cả các Elements < x (Given)
        //+ Không liên quan đến old index
        //+ Chỉ liên quan đến index của phần tử đó trong priority quê ==> Max value < x
        //--> Tìm index của phần tử (max < x) như thế nào? Với cách như bình thường là không thể (Suy luận bên trên)
        //
        //- Sort trước
        //Ex:
        //5,2,1,6,1
        //
        //{ (1,i=4), (1,i=2), (2,i=1), (5,i=0),(6,i=3) }
        //- Đứng ở vị trí (i)(index sau khi loop) ta sẽ loop tìm các (index >= current index) (original index) ==> count++
        //- Có cách nào tính dựa trên được không?
        //Ex:
        //i==3, value=6
        //+ Tính theo i=4
        //--> Tận đầu, index nhỏ hơn chưa chắc value đã nhỏ hơn, value nhỏ hơn chưa chắc index đã nhỏ hơn.
        //
        //{ (1,i=4), (1,i=2), (2,i=1), (5,i=0),(6,i=3) }
        //i=0 --> index>0: (i=1 --> i=4)
        //{ (i=0,5), (i=1, 2), (i=2, 1), (i=3,6) (i=4,1) }
        //
        //
        //#Reference:
        //327. Count of Range Sum
        //406. Queue Reconstruction by Height
        //493. Reverse Pairs
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        //--> Không có ceiling.
        //
        TreeSet<Node> treeSet=new TreeSet<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value-o2.value;
            }
        });
        treeSet.add(new Node(3, 1));
        treeSet.add(new Node(2, 6));
        treeSet.add(new Node(1, 2));
        treeSet.add(new Node(0, 2));
        treeSet.add(new Node(0, 2));
        treeSet.add(new Node(0, 4));
        System.out.printf("Size of tree: %s\n",treeSet.size());
//        treeSet.add(new Node(0, 5));
        System.out.println(treeSet.floor(new Node(1, 5)));
        int[] nums = {5,2,6,1};
        System.out.println(countSmallerStuckedSolution(nums));
    }
}
