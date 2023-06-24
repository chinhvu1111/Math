package E1_Graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E6_TimeNeededToInformAllEmployees {
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] graph=new ArrayList[n];

        for(int i=0;i<n;i++){
            int managerId=manager[i];
            if(managerId==-1){
                continue;
            }
            List<Integer> currentList=graph[managerId];
            if(currentList==null){
                currentList=new ArrayList<>();
            }
            currentList.add(i);
            graph[managerId]=currentList;
//            System.out.printf("%s %s\n", managerId, currentList);
        }
        Deque<Integer> nodes=new LinkedList<>();
        nodes.add(headID);
        Integer[] timeConsumed=new Integer[n];
        timeConsumed[headID]=informTime[headID];
        int rs=0;

        while (!nodes.isEmpty()){
            Integer currentNode=nodes.removeLast();
            List<Integer> currentList=graph[currentNode];
            if(currentList==null){
                continue;
            }
            for(Integer node:currentList){
                timeConsumed[node]=timeConsumed[currentNode]+informTime[node];
                rs=Math.max(rs, timeConsumed[node]);
                nodes.add(node);
            }
//            System.out.println(nodes);
        }
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Head thông báo 1 news cho các employees của anh ta.
        //- Cho head id là người đứng đầu công ty
        //- manager[] : manager[i] là manager của employee thứ (i)
        //- informTime[] : informTime[i] là thời gian để employee (i) thông báo cho all (direct) employees của người đó.
        //==> Tính thời gian hết để thông báo cho all employees
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= n <= 10^5
        //+ 0 <= headID < n
        //+ manager.length == n
        //+ 0 <= manager[i] < n
        //
        //- Thời gian để thông báo hết cho all employees
        //= chính time mà mất nhiều nhất ở mỗi branch
        //- Bài này dùng bfs:
        //+ Root= head_id
        //+ Cần build graph để traverse
        //
        //1.1, Implementation
        //- Graph dạng array với : arr[i] = list(direct subordinates)
        //+ Time thì có thể truy cập trực tiếp informationTime
        //- BFS thì ta cộng dần ==> Tính max kết quả.
        //
        //1.2, Complexity
        //- Time complexity : O(n * h) --> h : Chiều cao của tree
        //- Space complexity : O(n * h)
        //--> Có thể cải tiến bằng cách dựng tree truyền thống dạng NODE.
        //#Reference:
        //1466. Reorder Routes to Make All Paths Lead to the City Zero
        //124. Binary Tree Maximum Path Sum
//        int n=6;
//        int headID=2;
//        int[] manager={2,2,-1,2,2,2};
//        int[] informTime={0,0,1,0,0,0};
        int n=7;
        int headID=6;
        int[] manager={1,2,3,4,5,6,-1};
        int[] informTime={0,6,5,4,3,2,1};
        System.out.println(numOfMinutes(n, headID, manager, informTime));
    }
}
