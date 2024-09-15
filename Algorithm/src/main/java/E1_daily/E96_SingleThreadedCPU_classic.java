package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E96_SingleThreadedCPU_classic {

    public static int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        PriorityQueue<int[]> sortEnqueueTime = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[2] - o2[2];
            }
        });
        for (int i = 0; i < n; i++) {
            sortEnqueueTime.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        PriorityQueue<int[]> sortProcessingTime = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[2] - o2[2];
            }
        });
        //1 -> 2 -> 3
        //- Cần phải processing task
        //
        int[] rs = new int[n];
//        for(int i=0;i<n;i++){
//            sortProcessingTime.add(sortEnqueueTime.poll());
//            rs[i]=sortProcessingTime.poll()[2];
//        }
        int index = 0;
        int[] firstItem = sortEnqueueTime.poll();
        int pointTime = firstItem[0];

        sortProcessingTime.add(firstItem);
        while (!sortEnqueueTime.isEmpty() && pointTime >= sortEnqueueTime.peek()[0]) {
            sortProcessingTime.add(sortEnqueueTime.poll());
        }
        while (!sortProcessingTime.isEmpty()) {
            int[] curTask = sortProcessingTime.poll();
            pointTime += curTask[1];
            rs[index++] = curTask[2];
            boolean isSameDuration = false;
            while (!sortEnqueueTime.isEmpty() && pointTime >= sortEnqueueTime.peek()[0]) {
                sortProcessingTime.add(sortEnqueueTime.poll());
                isSameDuration = true;
            }
            if (!isSameDuration && !sortEnqueueTime.isEmpty() && sortProcessingTime.isEmpty()) {
                curTask = sortEnqueueTime.poll();
                //Đoạn này nguy hiểm
                //- Gán lại duplicate so với bên trên để có thể gap đến mốc init point mới
                //  + Sau đó sẽ tiếp tục xét tiếp
                //- Special case:
                //  + Nếu sortProcessingTime not empty:
                //      + Tức là vẫn dư task ==> Thì không cần add thêm.
                //
                pointTime = curTask[0];
//                pointTime+=curTask[0];
                sortProcessingTime.add(curTask);
                while (!sortEnqueueTime.isEmpty() && pointTime >= sortEnqueueTime.peek()[0]) {
                    sortProcessingTime.add(sortEnqueueTime.poll());
                }
            }
            //
        }
        return rs;
    }
    public static int[] getOrderOptimization(int[][] tasks) {
        int n = tasks.length;
        int[][] sortedTime=new int[n][3];

        for (int i = 0; i < n; i++) {
            sortedTime[i][0]=tasks[i][0];
            sortedTime[i][1]=tasks[i][1];
            sortedTime[i][2]=i;
        }
        Arrays.sort(sortedTime, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[2] - o2[2];
            }
        });
        PriorityQueue<int[]> sortProcessingTime = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[2] - o2[2];
            }
        });
        //1 -> 2 -> 3
        //- Cần phải processing task
        //
        int[] rs = new int[n];
//        for(int i=0;i<n;i++){
//            sortProcessingTime.add(sortEnqueueTime.poll());
//            rs[i]=sortProcessingTime.poll()[2];
//        }
        int indexSortedTime=0;
        int[] firstItem = sortedTime[indexSortedTime++];
        int pointTime = firstItem[0];
        sortProcessingTime.add(firstItem);

        while (indexSortedTime<n && pointTime >= sortedTime[indexSortedTime][0]) {
            sortProcessingTime.add(sortedTime[indexSortedTime]);
            indexSortedTime++;
        }
        int i=0;
        //Time: O(n)
        while (i<n) {
            int[] curTask = sortProcessingTime.poll();
            pointTime += curTask[1];
            rs[i] = curTask[2];

            //Với point time mới:
            //+ Add new elements vào
            //- Để giảm duplicated code:
            //  + Đoạn này ta add phần tử mới ta sẽ gom thêm điều kiện:
            //      + sortProcessingTime is empty
            //          + Vì là empty ==> Add thêm element đầu vào + nó cũng cần đi kềm các task có cùng enqueueTime
            //          ==> Ta cần update lại pointTime nếu pointTime <= task[0] (EnqueueTime của task mới)
            while (indexSortedTime<n && (pointTime >= sortedTime[indexSortedTime][0]||sortProcessingTime.isEmpty())) {
                sortProcessingTime.add(sortedTime[indexSortedTime]);
                if(pointTime<=sortedTime[indexSortedTime][0]){
                    pointTime=sortedTime[indexSortedTime][0];
                }
                indexSortedTime++;
            }
            i++;
            //
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
        //  + where tasks[i] = [enqueueTimei, processingTimei]
        // means that (the i task) will be available to process at enqueueTime[i] and will take processingTime[i] to finish processing.
        //- You have (a single-threaded CPU) that can process (at most one task) (at a time) and will act in the following way:
        //  + If the CPU is idle and there are (no available) tasks to process, the CPU remains (idle).
        //  + If the CPU is idle and there are (available tasks), the CPU will choose the one with (the shortest processing time).
        //If multiple tasks have the ("same" shortest processing time), it will choose the task with (the smallest index).
        //  + Once a task is started, the CPU will process (the entire task) without (stopping).
        //  + The CPU can finish a task then start (a new one instantly).
        //* Return (the order) in which the CPU will process (the tasks).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //tasks.length == n
        //1 <= n <= 10^5
        //1 <= enqueueTime[i], processingTime[i] <= 10^9
        //  + Length <= 10^5 ==> Time: O(n)
        //  + processingTime[i] <= 10^9: ==> Long
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
        //Output: [0,2,3,1]
        //Explanation: The events go as follows:
        //- At time = 1, task 0 is available to process. Available tasks = {0}.
        //- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
        //- At time = 2, task 1 is available to process. Available tasks = {1}.
        //- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
        //- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
        //- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
        //- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
        //- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
        //- At time = 10, the CPU finishes task 1 and becomes idle.
        //
        //- Không thể loop (1 -> 10^9)
        //  + Ta sẽ cần phải add vào priorityQueue sort theo (enqueueTime)
        //      + Add thêm cả index để lưu lại thông tin.
        //  Sau đó loop là được
        //- Sau đó tạo 1 priority queue sort theo (processingTime) và (index)
        //
        //- Idea1:
        //- Ta sẽ add từng task vào theo enqueue time vào 1 priorityQueue1
        //- Ban đầu:
        //- Sau đó ta sẽ lấy task ở top vào priorityQueue2:
        //  + Current point time = enqueueTime[i]
        //  + Tuy nhiên ta cần add thêm tất cả các tasks có cùng enqueueTime vào để chọn ưu tiên theo (processingTime và index smallest)
        //- Trong quá trình xử lý task ==> poin time sẽ được update
        //  + Trong quá trính xử lý có những tasks xuất hiện:
        //      + Nên ta cần add thêm tasks vào
        //- Tuy nhiên khi xử lý xong sẽ có trường hợp gap:
        //  + Ta sẽ không có tasks nào mới
        //      + Ta cần check case này + add tasks mới vào nữa
        //          + Tuy nhiên khi add task mới --> Ta lại phải làm giống bên trên add thêm các tasks có cùng enqueueTime[i] = current point time vào
        //
        //1.1, Optimization
        //- Khi nào dùng (priority queue) vs (sort):
        //  + Ta muốn sort liên tục khi add new element vào
        //- Còn nếu ta muốn loop lần lượt sau khi sort
        //  + Nên sort toàn bộ array ==> Loop sau.
        //- Loại bỏ duplication code:
        //Với point time mới:
        //+ Add new elements vào
        //- Để giảm duplicated code:
        //  + Đoạn này ta add phần tử mới ta sẽ gom thêm điều kiện:
        //      + sortProcessingTime is empty
        //          + Vì là empty ==> Add thêm element đầu vào + nó cũng cần đi kềm các task có cùng enqueueTime
        //          ==> Ta cần update lại pointTime nếu pointTime <= task[0] (EnqueueTime của task mới)
        //- Reduce:
        //  + 156 ms -> 107 ms
        //
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time:
        //
//        int[][] tasks = {{1,2},{2,4},{3,2},{4,1}};
//        int[][] tasks = {{7,10},{7,12},{7,5},{7,4},{7,2}};
//        int[][] tasks = {{5,2},{7,2},{9,4},{6,3},{5,10},{1,1}};
        int[][] tasks = {{35, 36}, {11, 7}, {15, 47}, {34, 2}, {47, 19}, {16, 14}, {19, 8}, {7, 34}, {38, 15}, {16, 18}, {27, 22}, {7, 15}, {43, 2}, {10, 5}, {5, 4}, {3, 11}};
//        int[] rs = getOrder(tasks);
        int[] rs = getOrderOptimization(tasks);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
    }
}
