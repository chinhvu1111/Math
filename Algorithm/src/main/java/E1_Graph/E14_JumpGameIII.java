package E1_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class E14_JumpGameIII {

    public static boolean canReach(int[] arr, int start) {
        int n=arr.length;
        if(n==0) {
            return false;
        }
        if(arr[start]==0){
            return true;
        }
        Queue<Integer> indexQueues=new LinkedList<>();
        boolean[] visited=new boolean[n];
        indexQueues.add(start);
        visited[start]=true;

        while (!indexQueues.isEmpty()){
            Integer currentIndex=indexQueues.poll();
            int nodeAdd=currentIndex+arr[currentIndex];
            int nodeSubtract=currentIndex-arr[currentIndex];

            if(nodeAdd<n&&!visited[nodeAdd]){
                if(arr[nodeAdd]==0){
                    return true;
                }
                indexQueues.add(nodeAdd);
                visited[nodeAdd]=true;
            }
            if(nodeSubtract>=0&&!visited[nodeSubtract]){
                if(arr[nodeSubtract]==0){
                    return true;
                }
                indexQueues.add(nodeSubtract);
                visited[nodeSubtract]=true;
            }
        }
        return false;
    }

    public static boolean canReachOptimzation(int[] arr, int start) {
        int n=arr.length;
        if(n==0) {
            return false;
        }
        if(arr[start]==0){
            return true;
        }
        Queue<Integer> indexQueues=new LinkedList<>();
        indexQueues.add(start);

        while (!indexQueues.isEmpty()){
            Integer currentIndex=indexQueues.poll();
            int nodeAdd=currentIndex+arr[currentIndex];
            int nodeSubtract=currentIndex-arr[currentIndex];

            if(nodeAdd>=0&&nodeAdd<n&&arr[nodeAdd]>=0){
                if(arr[nodeAdd]==0){
                    return true;
                }
                indexQueues.add(nodeAdd);
                arr[nodeAdd]=-arr[nodeAdd];
            }
            if(nodeSubtract>=0&&nodeSubtract<n&&arr[nodeSubtract]>=0){
                if(arr[nodeSubtract]==0){
                    return true;
                }
                indexQueues.add(nodeSubtract);
                arr[nodeSubtract]=-arr[nodeSubtract];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Bạn đứng ở vị trí start trong array
        //+ Đứng ở vị trí (i) có thể move đến vị trí (i-arr[i])/ (i+arr[i])
        //+ Kiểm tra xem liệu có thể move đến vị trí có gía trị bằng 0 hay không
        //+ Không đi ra khỏi array được
        //- return true/ false
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= arr.length <= 5 * 10^4
        //+ 0 <= arr[i] < arr.length
        //+ 0 <= start < arr.length
        //--> Không có gì đặc biệt lắm trong th dùng BFS
        //
        //- BFS thuần
        //+ (i) : add node:
        //+ i-arr[i] : khi value >=0
        //+ i+arr[i] : value <n
        //--> Khi đến arr[index]==0: return true <> return false
        //
        //1.1, Special testcases
        //- arr={0}
        //
        //1.2, Optimization
        //-
        //
        //1.3, Complexity
        //- Space complexity : O(n) (n là length của arr)
        //- Time complexity : O(n) : Vì cần phải đi hết node.
        //* Độ phức tạp thuật toán của BFS:
        //- O(V + E) : V là số nodes, E là số edges.
        //
        int[] arr = {4,2,3,0,3,1,2};
        int start = 5;
        System.out.println(canReach(arr, start));
        System.out.println(canReachOptimzation(arr, start));
        //#Reference:
        //542. 01 Matrix
        //1871. Jump Game VII
        //2297. Jump Game VIII
    }
}
