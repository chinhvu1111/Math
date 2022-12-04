package interviews;

import java.util.*;

public class E213_CourseScheduleIV_topo {

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
//        HashMap<Integer, HashSet<Integer>> hashPrerequisites=new HashMap<>();
        //1, Để tránh dùng hash --> slow hơn
        //==> Dùng list, với index phải là số thì mới có thể truy cập trực tiếp được
        List<List<Integer>> adjList=new ArrayList<>();
        //2, Là danh sách các điểm là parent của node index (i) <=> Các điểm là prerequisites của (i)
        List<Set<Integer>> parentNodeList=new ArrayList<>();
        int[] degree=new int[numCourses+1];
        int n=prerequisites.length;


        for(int i=0;i<numCourses;i++){
            adjList.add(new ArrayList<>());
            parentNodeList.add(new HashSet<>());
        }

        for(int i=0;i<n;i++){
            int[]edges=prerequisites[i];

            int u=edges[0];
            int v=edges[1];
            degree[v]++;
            adjList.get(u).add(v);
        }

        Deque<Integer> deque=new LinkedList<>();

        for(int i=0;i<numCourses;i++){
            if(degree[i]==0){
                deque.add(i);
            }
        }
        //O(N) TIME
        while (!deque.isEmpty()){
            int currentNode=deque.pop();
            List<Integer> nextNodes=adjList.get(currentNode);
//            System.out.printf("Parent node: %s", currentNode);

            for(Integer adjNode: nextNodes){
//                System.out.printf(", adjacent node %s ", adjNode);
                degree[adjNode]--;
                if(degree[adjNode]==0){
                    //Adding to traverse continously
                    deque.add(adjNode);
                }
                //O(N) TIME
                for(Integer prerequiParent: parentNodeList.get(currentNode)){
                    parentNodeList.get(adjNode).add(prerequiParent);
                }
                //Ngoài các prerequisite --> Cần phải add cả th current parent node nữa
                parentNodeList.get(adjNode).add(currentNode);
            }
//            System.out.println();
        }
        List<Boolean> result=new ArrayList<>();
        int qLength=queries.length;
//        System.out.println(parentNodeList);

        for(int i=0;i<qLength;i++){
            int u=queries[i][0];
            int v=queries[i][1];
            if(parentNodeList.get(v).contains(u)){
                result.add(true);
            }else{
                result.add(false);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int numberCourses=8;
        //1 --> 2
        //1 --> 0
        //2 --> 0
//        int[][] prerequisites=new int[][]{{1,2},{1,0},{2,0}};
        int[][] prerequisites=new int[][]{{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        int[][] queries=new int[][]{{0,7}};
        //
        //** Đề bài
        //- Trả lại kết quả của list of queries (u,v) xem (u có phải học phần kiến quyết của v hay không)
        //- length queries > 10^4
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1,
        //Để tránh dùng hash dạng <Index, List<Integer>> --> slow hơn
        //==> Dùng list, với index phải là số thì mới có thể truy cập trực tiếp được
        //--> Ta chỉ cần init all node các cạnh next tiếp là new ArrayList là được.
        //
        //1.2,
        //Là danh sách các điểm là parent của node index (i) <=> Các điểm là prerequisites của (i)
        //+ List<Set<Integer>> parentNodeList=new ArrayList<>();
        //--> Nếu duyệt đến đỉnh nào --> add all điểm của parent vào là xong
        //** Chú ý : Cần add cả parent node vào nữa (Không phải chỉ các node kiên quết của parent node/ prerequisites)
        //
        //1.3,
        //- Khi đã quay về bài toán cover all parents --> Query lúc này ta chỉ cần đảo ngược lại
        //-VD: thay vì query (u --> v) --> xem có thể query (v -> u) không là được.
        //+ Dấu hiệu quan hệ ở đây là 1 : 1 giữa u và v.
        //2,
        //- Time complexity : O(N * N)
        //- Space complexity : O(N)
        //- Bài này thuộc dạng reverse query, tức là dạng query ngược
        //## Reference
        //- The Maze III
        //- Construct String from Binary Tree
        //- Find All Possible Recipes from Given Supplies
        System.out.println(checkIfPrerequisite(numberCourses, prerequisites, queries));
        //
    }
}
