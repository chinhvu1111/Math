package E1_Graph;

import java.util.ArrayList;
import java.util.List;

public class E32_CourseSchedule {

    public static boolean[] visited;
    public static boolean[] stack;

    public static boolean isCycleSlow(int first, int u, List<Integer>[] adjs){
//        System.out.println(u);
        if(adjs[u]!=null){
            visited[u]=true;
            for(int v:adjs[u]){
                if(!visited[v]){
//                    visited[v]=true;
                    if(!isCycleSlow(first, v, adjs)){
                        return false;
                    }
                }else if(first==v){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCycle(int u, List<Integer>[] adjs){
        if(stack[u]){
            return true;
        }
        if(visited[u]){
            return false;
        }
//        System.out.println(u);
        if(adjs[u]!=null){
            visited[u]=true;
            stack[u]=true;
            for(int v:adjs[u]){
                if(isCycle(v, adjs)){
                    return true;
                }
            }
        }
        stack[u]=false;
        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjNodes=new ArrayList[numCourses];
        //O(N)
        visited=new boolean[numCourses];
        stack=new boolean[numCourses];
        //O(M)
        for(int[] pre:prerequisites){
            int x=pre[0];
            int y=pre[1];
            if(adjNodes[x]==null){
                adjNodes[x]=new ArrayList<>();
            }
            adjNodes[x].add(y);
        }
        boolean isCycle;

        for(int i=0;i<numCourses;i++){
            if(adjNodes[i]==null){
                continue;
            }
            isCycle=isCycle(i, adjNodes);
//            System.out.println();
            if(isCycle){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course (bi) first if you want to take course (ai).
        //--> Bắt buộc phải lấy course (bi) trước (ai)
        //* Return true nếu có thể lấy all courses.
        //VD:
        //numCourses = 2, prerequisites = [[1,0],[0,1], [2,3]]
        // (0) --> (1), (1) --> (0)
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
//        int numCourses = 2;
//        int[][] prerequisites = {{1,0},{0,1}};
        //
        //- Các cases có thể xảy ra:
        // 0 --> 1
        // 1 --> 0
        //--> 2 thằng này chạy quay lại liên tục
        //
        //- Case 2:
        // (1) -->  (4)
        //  ^        ^
        //  |        |
        // (3) -->  (2)
        //--> Tức là đồ thị 2 chiều --> việc chạy lại visited ---> Không phải việc chech cycle
        //
        //- Case 3:
        //1 --> (0)
        //1 --> 7 --> (0) --> 5
        //2 --> 6 --> 4
        //--> Cùng qua (0) nhưng không có cycle
        //
        //* Ở đây ta dùng 2 cache:
        //+ Visited[n] để lưu xem course đó đã đến chưa
        //+ stack[n] : Để lặp lại bên trong 1 quá trình chạy
        //+ if(stack[i]) return true (Có cycle) ==> Stack sẽ cần phải reset về false từng node ngay trong lúc recursively.
        //+ if(visited[i]) return false (Vì đến đây chưa có cycle mà node --> Chỉ có thể đi từ loop khác + bên trên đã check stack rồi).
        //
        //1.1, Complexity:
        //- Time complexity : O(N+M)
        //+ M is the number of edges
        //+ N is number of course
        //+ DFS() :
        //  + Sẽ thực hiện mỗi node một lần traverse --> O(N)
        //  + Với mỗi nodes chúng ta sẽ duyệt all edges --> Mất O(M) tổng cộng (in total)
        // --> O(N+M)
        //
        //- Space complexity : O(N+M)
        //+ Add cạnh kề sẽ mất O(M) space
        //+ Visit or stack mất O(N) space
        //+ Recursion --> stack O(N) space
        //
        int numCourses = 8;
        int[][] prerequisites = {{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}};
        //1 --> 0
        //1 --> 7 --> 0 --> 5
        //2 --> 6 --> 4
        //
        //
//        int[][] prerequisites = {{1,0}};
        // (1 --> 0)
//        int numCourses = 5;
//        int[][] prerequisites = {{1,0},{0,1}};
//        int[][] prerequisites = {{1,4}, {2,4}, {3,1}, {3,2}};
        // (1) -->  (4)
        //  ^        ^
        //  |        |
        // (3) -->  (2)
        //
        System.out.println(canFinish(numCourses, prerequisites));
        //#Reference:
        //62. Unique Paths
        //261. Graph Valid Tree
        //310. Minimum Height Trees
        //630. Course Schedule III
    }
}
