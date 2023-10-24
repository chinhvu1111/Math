package E1_heap_priority_queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class E2_TotalCostToHireKWorkers {

    public static long totalCostLackOfCases(int[] costs, int k, int candidates) {
        int n=costs.length;
        if(k==0||n==0||candidates==0){
            return 0;
        }
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> a[0]-b[0]);
        //Ex:
        //costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
        //n=
        //i : 0 -> 3
        int i;
        int maxLeft=Math.min(candidates, n);

        for(i=0;i<maxLeft;i++){
            queue.add(new int[]{costs[i], i});
            System.out.printf("Add left : %s, ", costs[i]);
        }
        System.out.println();
        //i : 9-4 = 5 -> 9
        i=Math.max(maxLeft, n-candidates);
        for(;i<n;i++){
            queue.add(new int[]{costs[i], i});
            System.out.printf("Add right: %s, ", costs[i]);
        }
//        System.out.println();
        //low=3
        int low=maxLeft;
        //low=9 - 4 -1 = 4
        int high=Math.max(n-candidates-1, maxLeft-1);
        high=Math.min(high, n-1);
        int curSession=1;
        long rs=0;
        long rs1=0;
        //(17,12,10,7),(11,20,8)
        //low=3, high=4
        //- Giả sử hết
        while(low<=high&&curSession<=k){
            if(!queue.isEmpty()){
                int[] popWorker=queue.poll();
//                System.out.printf("Getting %s, size %s\n", popWorker[0], queue.size());
                rs+=popWorker[0];

                if(popWorker[1]<=low){
                    queue.add(new int[]{costs[low], low});
                    low++;
                }else if(popWorker[1]>=high){
                    queue.add(new int[]{costs[high], high});
                    high--;
                }
            }
            curSession++;
        }
//        List<int[]> list=new ArrayList<>(queue);

//        for(int[] e:list){
//            System.out.printf("Element: %s, index: %s\n", e[0], e[1]);
//        }
//        System.out.printf("Current result: %s, CurSession: %s, k: %s\n", rs, curSession, k);

        while(!queue.isEmpty()&&curSession<=k){
            int[] curr=queue.poll();
            rs+=curr[0];
            System.out.printf("Getting-1 %s\n", curr[0]);
            curSession++;
        }
        System.out.printf("Rs: %s\n", rs);
        return Math.min(rs, rs1);
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        int n=costs.length;
        if(k==0||n==0||candidates==0){
            return 0;
        }
        PriorityQueue<Integer> headWorkers=new PriorityQueue<>();
        PriorityQueue<Integer> tailWorkers=new PriorityQueue<>();

        //m=candidates
        //Time : O(m)
        for(int i=0;i<candidates;i++){
            headWorkers.add(costs[i]);
        }
        int rightMin=Math.max(candidates, costs.length-candidates);

        //m=candidates
        //Time : O(m)
        for(int i=rightMin;i<costs.length;i++){
            tailWorkers.add(costs[i]);
        }
        long rs=0;
        int nextHead=candidates;
        int nextTail=costs.length-1-candidates;

        //Time : O(k)
        for(int i=0;i<k;i++){
            if(tailWorkers.isEmpty()|| !headWorkers.isEmpty()&&headWorkers.peek() <= tailWorkers.peek()){
                rs+=headWorkers.poll();

                if(nextHead<=nextTail){
                    //Time : O(log(m))
                    headWorkers.add(costs[nextHead]);
                    nextHead++;
                }
            }else{
                rs+=tailWorkers.poll();
                if(nextHead<=nextTail){
                    //Time : O(log(m))
                    tailWorkers.add(costs[nextTail]);
                    nextTail--;
                }
            }
        }

//        System.out.printf("Rs: %s, rs1: %s\n", rs, rs1);
        return rs;
    }

    public static long totalCostOptimization(int[] costs, int k, int candidates) {
        int n=costs.length;
        if(k==0||n==0||candidates==0){
            return 0;
        }
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b) -> {
            if(a[0]==b[0]){
                return a[1]-b[1];
            }
            return a[0]-b[0];
        });
        for(int i=0;i<candidates;i++){
            queue.add(new int[]{costs[i], 0});
        }
        int minLeft=Math.max(candidates, costs.length-candidates);

        for(int i=minLeft;i<n;i++){
            queue.add(new int[]{costs[i], 1});
        }

        int nextHead=candidates;
        int nextTail=costs.length-1-candidates;
        long rs=0;

        for(int i=0;i<k;i++){
            int[] curWorker=queue.poll();
            int curCost=curWorker[0];
            int curId=curWorker[1];
            rs+=curCost;

            if(nextHead<=nextTail){
                if(curId==0){
                    queue.add(new int[]{costs[nextHead], 0});
                    nextHead++;
                }else{
                    queue.add(new int[]{costs[nextTail], 1});
                    nextTail--;
                }
            }
        }

//        System.out.printf("Rs: %s, rs1: %s\n", rs, rs1);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Costs array, cost[i] là giá để thuê worker thứ ith
        //- Ta muốn thuê k workers theo rule sau:
        //+ You will run k sessions and hire (exactly one worker in each session).
        //+ In each hiring session, choose the worker with the lowest cost from either the (first "candidates" workers) or the (last "candidates" workers).
        //  + Break the tie by the smallest index.
        //For example, if costs = [3,2,7,7,1,2] and candidates = 2,
        // then in the first hiring session, we will choose the (4th worker) because they have the lowest cost [3,2,7,7,1,2].
        //- Nếu số workers còn lại nhỏ hơn số ("candidate" workers) --> Ta chọn worker có giá thuê thấp nhất.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //1 <= costs.length <= 10^5
        //1 <= costs[i] <= 10^5
        //1 <= k, candidates <= costs.length
        //
        //- Brainstorm
        //- Bài này ta dùng priority queue xong sau đó add các phần tử ở first and last
        //+ Nếu ta lấy 1 element ở first or last ==> Sau đó ta sẽ add thêm phần tử ở phía đó để lúc nào ta cũng xét đủ k first and k last
        //- Ta cũng cần chú ý nếu các phần tử còn lại ==k ==> Ta không cần add thêm ==> Mà là poll all từ priority queue ra
        //+ Số phần tử add thêm ta sẽ kiểm soát bằng count.
        //
//        int[] cost={17,12,10,2,7,2,11,20,8};
//        int k=3;
//        int candidate=4;
//        int[] cost={2};
//        int k=3;
//        int candidate=4;
//        int[] cost={2, 3, 4, 5, 1};
//        int k=4;
//        int candidate=4;
//        int[] cost={2, 3, 4, 5, 1};
//        int k=1;
//        int candidate=4;
//        int[] cost={31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        //+ k=1
        //31,25
        //17,58
        //+ k=2
        //31,25,72
        //58,33
//        int k=11;
//        int candidate=2;
//        int[] cost={1,2,4,1};
//        int k=3;
//        int candidate=3;

//        int[] cost={19,29,88,2,5,24,60,26,76,24,96,82,97,97,72,35,21,77,82,30,94,55,76,94,51,82,3,89,52,96,72,27,59,57,97,6,46,88,41,52,46,4,17};
//        int k=1;
//        int candidate=3;

//        int[] cost={60,66,17,17,35,46,77,7,15,38,35,61,90,34,29,68,35,9,18,82,78,40,8,71,2,59,70,12,88,73,12,55,88,59,71,49,47,46,65,37,
//                24,75,81,54,39,70};
//        int k=37;
//        int candidate=40;
//        int[] cost={
//                2,2,2,2,2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,2,2,2
//        };
        int[] cost={
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 5, 5, 5, 4, 1, 2, 2, 2, 2, 2, 2
        };
        //- Case mà chọn left or right thì sẽ ảnh hưởng đến kết quả
        //2,2,2,2,2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,2,2,2
        //k=1 :(2,2,2),2,2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //k=2 :(2,2,2),2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //k=3 :(2,2,2),2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //k=4 :(2,2,2),1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //k=5 :(2,2,1),4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 1
        //k=6 :(2,2,4),5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //k=7 :(2,2,4),5,5,5,5,5,2,2,2,2,2,2,2,2,2,(2,2,2) + 2
        //+ Như thế này thì chọn bên left thì sẽ có lợi hơn vì ta sẽ meet value=1 nếu ta chọn left liên tục
        //* Solution:
        //- Ta sẽ ưu tiên 1 trong 2 nếu xét ==> Để có thể lấy result đúng.
        //--> Với case trên thì nếu làm như cũ thì không xử lý được.
        //- Ta sẽ dùng thêm 1 queue ==> Dùng 2 queue để sort theo:
        //+ Value + index first : Ta sẽ sort theo value + ưu tiên index đầu tiên
        //+ Value + index last : Ta sẽ sort theo value + ưu tiên index ở cuối
        //
        //1.1, Optimization
        //1.2, Complexity
        //+ m=candidates
        //- Space : O(m)
        //- Time : O((k+m)*log(m))
        //
        //2. Cùng dùng priority queue nhưng tối ưu hơn so với phương pháp cũ
        //2.0,
        //- Ở đây ta chỉ cần add all elements vào 2 queue
        //- Xong sau đó ta sẽ lấy k phần tử ra từ 2 queues đos
        //+ Vì việc lấy được chia đều cho 2 queue nên ta sẽ lấy phần tử nhỏ nhất từ 2 queue đó dần dần
        //+ Ta sẽ loop k lần để lấy phần tử min ra.
        //- Câu hỏi:
        //- Mình add các phần tử vào từng queue như thế nào?
        //+ Ta sẽ add bình thường như cách 1:
        //  + add "candidates" phần từ vào queue 1
        //  + add nhiều nhất k phần tử vào queue 2
        //
        //- Ở đây ta sẽ chia ra 2 indexes
        //+ index của left = candidates
        //  + Đi sang right (index++)
        //+ index của right = n-candidates-1
        //  + Đi sang left (index--)
        //==> Với cách này có vẻ nó sẽ chỉ simpler trong implementation
        //+ Với test case chọn 2 đầu nếu cả 2 đều có min như nhau
        //--> Có ở đây nó đang ưu tiên head ==> Nếu số lớn hơn ở cuối ==> Thì cách này sẽ bị sai.
        //
        //2.1, Optimization
        //2.2, Complexity
        //+ m=candidates
        //- Space : O(m)
        //- Time : O((k+m)*log(m))
        //
        //3.
        //3.0,
        //- Cách này phân group --> Vẫn là ưu tiên first hơn (0/1)
        //- Nó chính là cách 1 + refactor thành 1 queue
        //==> Tưu tưởng vẫn sẽ không khác gì.
        //
        int k=7;
        int candidate=3;
        int n=cost.length;
        System.out.printf("n=%s, k=%s\n",n, k);
//        System.out.println(totalCostLackOfCases(cost, k, candidate));
        System.out.println(totalCost(cost, k, candidate));
        System.out.println(totalCostOptimization(cost, k, candidate));
        //#Reference:
        //253. Meeting Rooms II
        //2532. Time to Cross a Bridge
    }
}
