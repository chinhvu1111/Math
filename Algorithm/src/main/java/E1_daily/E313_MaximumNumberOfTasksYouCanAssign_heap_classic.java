package E1_daily;

import java.util.*;

public class E313_MaximumNumberOfTasksYouCanAssign_heap_classic {

    public static int findSmallestIndex(int index, int[] workers, int taskValue){
        int low=index, high=workers.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(workers[mid]>=taskValue){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n=tasks.length;
        int m=workers.length;
        Arrays.sort(workers);
        Arrays.sort(tasks);
        int rs=-1;
        int low=0, high=n-1;
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });

        for(int i=0;i<m;i++){
            set.add(new int[]{workers[i], i});
        }

        while(low<=high){
            //1,10,15
            //1,5,30
            int mid=low+(high-low)/2;
            int countPills=pills;

            int i;
            //O(n*log(m))
            for(i=mid;i>=0;i--){
                int[] curSearch=new int[]{tasks[i], -1};
                //Time: O(log(m))
                int[] expectedVal=set.ceiling(curSearch);

                if(expectedVal==null&&countPills<=0){
                    break;
                }else if(expectedVal==null){
                    curSearch=new int[]{tasks[i]-strength, -1};
                    countPills--;
                    expectedVal=set.ceiling(curSearch);
                    if(expectedVal==null){
                        break;
                    }
                }
                set.remove(expectedVal);
//                System.out.printf("Invalid: workerVal: %s, task: %s\n", workers[i], tasks[mid]);
            }
            set.clear();
//            System.out.printf("Mid: %s, curRs: %s\n", mid,  i==-1);
            if(i==-1){
               rs=mid;
               low=mid+1;
            }else{
                high=mid-1;
            }
            for(i=0;i<m;i++){
                set.add(new int[]{workers[i], i});
            }
        }

        return rs+1;
    }

    public static int maxTaskAssignRefer(
            int[] tasks,
            int[] workers,
            int pills,
            int strength
    ) {
        int n = tasks.length, m = workers.length;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 1, right = Math.min(m, n), ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(tasks, workers, pills, strength, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // Check if pills and strength can be used in mid tasks
    private static boolean check(
            int[] tasks,
            int[] workers,
            int pills,
            int strength,
            int mid
    ) {
        int p = pills;
        int m = workers.length;
        Deque<Integer> ws = new ArrayDeque<>();
        int ptr = m - 1;
        // Enumerate each task from largest to smallest
        //
        //- (4,6,6) <> (5,5,8)
        //worker queue = 6,6,4 (Decrease order)
        //
        //- For each task[i]:
        //  + Add all of workers such that:
        //      + worker[j] + strength >= tasks[i]
        //  ==> After that we will choose the smallest valid workers[i]
        //  + If we find the last >= current task[i]:
        //      + Poll last
        //  + Poll first if p>0:
        //      + Because we only add when worker[j] + strength >= tasks[i]
        //
        for (int i = mid - 1; i >= 0; --i) {
            while (ptr >= m - mid && workers[ptr] + strength >= tasks[i]) {
                ws.addFirst(workers[ptr]);
                --ptr;
            }
            if (ws.isEmpty()) {
                return false;
            } else if (ws.getLast() >= tasks[i]) {
                // If the largest element in the deque is greater than or equal to tasks[i]
                ws.pollLast();
            } else {
                if (p == 0) {
                    return false;
                }
                --p;
                ws.pollFirst();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have (n tasks and m workers).
        //- (Each task) has a strength requirement stored in (a 0-indexed integer array tasks),
        // with (the ith task) requiring (tasks[i] strength) to complete.
        //- (The strength of each worker) is stored in (a 0-indexed integer array workers),
        // with (the jth worker) having (workers[j] strength).
        //- (Each worker) can only be assigned to (a single task) and must have (a strength)
        // greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
        //- Additionally, you have pills magical pills that will increase (a worker's strength) by strength.
        //- You can decide which workers receive the magical pills,
        // however, you may only give (each worker) (at most one) magical pill.
        //- Given (the 0-indexed integer arrays tasks) and workers and (the integers pills and strength),
        //* return (the maximum number of tasks) that can be completed.
        //  + We may only give (each worker) (at most one) magical pill.
        //
        //Example 1:
        //
        //Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
        //Output: 3
        //Explanation:
        //We can assign the magical pill and tasks as follows:
        //- Give the magical pill to worker 0.
        //- Assign worker 0 to task 2 (0 + 1 >= 1)
        //- Assign worker 1 to task 1 (3 >= 2)
        //- Assign worker 2 to task 0 (3 >= 3)
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == tasks.length
        //m == workers.length
        //1 <= n, m <= 5 * 10^4
        //0 <= pills <= m
        //0 <= tasks[i], workers[j], strength <= 10^9
        //  + n, m <= 5 * 10^4 ==> Time: O(n*k)
        //  + tasks[i], workers[j], strength <= 10^9 ==> (Binary search + Long)
        //
        //* Brainstorm:
        //- Binary search
        //  + Find (the max number of the tasks) we can finish
        //- We use (the tasks) rather than using worker to do (the binary search)
        //==> Get first m tasks
        //
        //
        //1.1, Case
        //
        //* Main point:
        //- Use treeSet (val, index)
        //
        //1.2, Optimization
        //- Use dequeue + available sorted array
        //- For each task[i]:
        //  + Add all of workers such that:
        //      + worker[j] + strength >= tasks[i]
        //  ==> After that we will choose the smallest valid workers[i]
        //  + If we find the last >= current task[i]:
        //      + Poll last
        //  + Poll first if p>0:
        //      + Because we only add when worker[j] + strength >= tasks[i]
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(max(m,n)*log(m)*log(n))
        //
        int[] tasks = {3,2,1};
        int[] workers = {0,3,3};
        int pills = 1, strength = 1;
//        int[] tasks = {5,9,8,5,9};
        //5,5,8,9
//        int[] workers = {1,6,4,2,6};
//        int pills = 1, strength = 5;
        //1,2,4,6,6
        //Expected: 3
        //=> (5,5,8),9
        //
        //- (4,6,6) <> (5,5,8)
        //  + It is not respectively
        //  Because:
        //  + 4+5>8
        //  + 6>5,6>5
        //  ==> We need to use TreeSet to search this value
        //  + Find the min value such that:
        //      + val + length > current_task_val
        //- (5,5,8) => find (4,6,6)
        //  + 8 ==> find not found
        //      + 8 < 4+5
        //
        //# Reference:
        //2141. Maximum Running Time of N Computers
        //2528. Maximize the Minimum Powered City
        System.out.println(maxTaskAssign(tasks, workers, pills, strength));
        System.out.println(maxTaskAssignRefer(tasks, workers, pills, strength));
    }
}
