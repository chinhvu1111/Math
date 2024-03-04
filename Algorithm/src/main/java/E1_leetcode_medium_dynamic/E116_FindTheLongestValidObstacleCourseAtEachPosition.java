package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class E116_FindTheLongestValidObstacleCourseAtEachPosition {

    public static int[] longestObstacleCourseAtEachPositionWrong(int[] obstacles) {
        int n=obstacles.length;
//        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] rsLeft=new int[n];
        Queue<Integer> queue=new LinkedList<>();

        for(int i=0;i<n;i++){
            int curVal=obstacles[i];
            while(!queue.isEmpty()&&queue.peek()>curVal){
                queue.poll();
            }
            queue.add(curVal);
//            if(!mapCount.containsKey(curVal) || (mapCount.containsKey(curVal)&&mapCount.get(curVal)<queue.size())){
//                mapCount.put(i, queue.size());
//            }
            rsLeft[i]=queue.size();
        }
        for(int i=0;i<n;i++){
            System.out.printf("%s, ", rsLeft[i]);
        }
        return null;
    }

    public static class Node{
        int val;
        int index;
        int length;
        public Node(int val, int index, int length){
            this.val=val;
            this.index=index;
            this.length=length;
        }

        @Override
        public String toString() {
            return val+" "+index+" "+length;
        }
    }

    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n=obstacles.length;
        TreeSet<Node> sortVal=new TreeSet<>((a, b) -> {
            if(a.val!=b.val){
                return a.val-b.val;
            }
            return a.index-b.index;
        });
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            int curVal=obstacles[i];
            Node curNode=new Node(curVal, i, 1);
            //Ex:
            //1,4,5,2,3
            //1
            //1,4
            //1,4,5
            //1,(4<->2),5 = 1,2,5
            //Ex:
            //+ Nếu (4) có length > length(2) + 1 thì sao
            //1,(3),4,5,2,3
            //==> Để 3 thì 2 sẽ thay 3
            //
            //Tìm số < + index max nhất
            //+ Ta sẽ thay số hiện tại vào số đằng sau vì:
            //  + Số đằng sau chỉ có thể >= curVal ==> Số đằng sau sẽ có length = prev + 1
            if(!sortVal.isEmpty()){
                Node replacedNode=sortVal.ceiling(curNode);
                Node prevNode=sortVal.floor(curNode);
                if(replacedNode!=null){
                    sortVal.remove(replacedNode);
                }
                if(prevNode!=null){
                    curNode.length=prevNode.length+1;
                }else{
                    curNode.length=1;
                }
            }
            rs[curNode.index]=curNode.length;
            sortVal.add(curNode);
            System.out.println(sortVal);
        }
        for(int i=0;i<n;i++){
            System.out.printf("%s, ", rs[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.
        //For every index i between 0 and n - 1 (inclusive),
        //- Find (the length of the longest obstacle course) in obstacles such that:
        //+ You choose any number of obstacles between 0 and i inclusive.
        //  + Chọn số giữa (0 -> i-1)
        //+ You must include the (ith obstacle) in the course
        //  + Bắt buộc phải include (ith)
        //+ You must put the chosen obstacles in the same order as they appear in obstacles.
        //  + Giữ nguyên order
        //+ Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
        //  + Mọi obstacle trừ cái đầu tiên --> >= previous element.
        //* Return (an array ans of length n), where ans[i] is (the length of the longest obstacle course) for index i as described above.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //n == obstacles.length
        //1 <= n <= 10^5
        //1 <= obstacles[i] <= 10^7
        //
        //- Brainstorm
        //Ex:
        //obstacles = [3,1,5,6,4,2]
        //- Ta sẽ cần tìm 2 thứ:
        //+ Độ dài lớn nhất chuỗi không giảm end with (i) - last -> first
        //+ Độ dài lớn nhất chuỗi không tăng start with (i) - first -> last
        //  + Ta có thể đổi thành giảm dần từ last -> first
        //
        //arr=
        //[3,1,5,6,4,2]
        //[1,1,2,3,2,2]
        //- Ta có thể làm binary search
        //- Mục đích:
        //  + Ta tìm index lớn nhất + value < x ==> (WRONG IDEA)
        //Ex:
        //[3,1,5,(6),4,2]
        //==> Đứng ở 6 thì ta chọn 3 or 5
        //Ex:
        //[3,1,[5],1,2,3,[4],(6),4,2] : Số lớn hơn chưa chắc đã tốt
        //+ Giữa 5 và 4 chọn 4 --> Dài hơn
        //Ex:
        //[3,1,2,3,5,[7],[4],(8),4,2] : Số gần hơn chưa chắc đã tốt hơn
        //+ Giữa 7 và 4 --> Chọn thằng xa hơn là 7
        //==>
        //- Giả sử:
        //- Dynamic programming + binary search
        //  + Sort dựa trên dp[i]
        //- Sau đó hash<value, Length> --> Update dần nếu lớn hơn.
        //Ex:
        //[3,1,[5],1,2,3,[4],(6),4,2]
        //queue= 3 : 1
        //(pop 3 + add 1) : queue=1 : 1
        //add 5 : queue=2
        //
//        int[] obstacles = {3,1,5,6,4,2};
        int[] obstacles = {1,4,5,2,3};
        //3
        //1
        //1,5
        //1,5,6
        //==> Tư duy này sai vì có thể 4 được tính theo 3 + trước 3 chẳng hạn
        //  + Nó đã được pop ra rồi nên sai
        //
        //* Solution:
        //- Ta sẽ reorder lại để index nó mới đúng được + update lại value
        //- Value nhỏ hơn + update vào vị trí mà value<= matrix[i]
        //Ex:
        //{3,1,5,6,4,2}
        //3(=1)
        //1(=1) ==> Thay cho 3
        //1(=1),5(2)
        //1(=1),5(2),6(3)
        //1(=1),5(2),6(3)
        //
        //- Tree sort theo:
        //  + value
        //  + index
//        longestObstacleCourseAtEachPositionWrong(obstacles);
        longestObstacleCourseAtEachPosition(obstacles);
        //#Reference:
        //1760. Minimum Limit of Balls in a Bag
        //2170. Minimum Operations to Make the Array Alternating
        //2219. Maximum Sum Score of Array
    }
}
