package E1_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E36_CriticalConnectionsInANetwork {

    public static class Node{
        int currentNode;
        HashSet<Node> adjNodes;

        public Node(int currentNode) {
            this.currentNode = currentNode;
            adjNodes=new HashSet<>();
        }
    }

    public static HashSet<String> nonCycleEdges;
    public static Node[] nodes;
    public static HashMap<Integer, Integer> rank;

    public static int findCycle(int currentNode, int currentValue){
        if(rank.containsKey(currentNode)){
            return rank.get(currentNode);
        }
        rank.put(currentNode, currentValue);
        if(nodes[currentNode].adjNodes.size()!=0){
            int minNextValue=Integer.MAX_VALUE;

            for(Node nextNode:nodes[currentNode].adjNodes){
                Integer neighRank=rank.get(nextNode.currentNode);
                //Bỏ node đã đi trước rồi VD: A(1) --> B (2) ==> Ở B bỏ đi (value-1) ở A đi
                if(neighRank!=null&&neighRank==currentValue-1){
                    continue;
                }
                int nextCurrentValue=findCycle(nextNode.currentNode, currentValue+1);
                if(nextCurrentValue<=currentValue){
                    int x=Math.min(currentNode, nextNode.currentNode);
                    int y=Math.max(currentNode, nextNode.currentNode);
                    nonCycleEdges.remove(x+" "+y);
//                    System.out.println(x+" "+y);
                }
                minNextValue=Integer.min(minNextValue, nextCurrentValue);
            }
            return minNextValue;
        }
        return currentValue+1;
    }

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        nodes=new Node[n];
        nonCycleEdges =new HashSet<>();

        for(int i=0;i<n;i++){
            nodes[i]=new Node(i);
        }
        for(List<Integer> currentConnection:connections){
            int x=currentConnection.get(0);
            int y=currentConnection.get(1);
            nodes[x].adjNodes.add(nodes[y]);
            nodes[y].adjNodes.add(nodes[x]);
            int x1=Math.min(x, y);
            int y1=Math.max(x, y);
            nonCycleEdges.add(x1+" "+y1);
        }
//        System.out.println(nonCycleEdges);

        rank=new HashMap<>();
        findCycle(0, 0);
        List<List<Integer>> rs=new ArrayList<>();

        for(String s: nonCycleEdges){
            String[] currentEdgeInfor=s.split(" ");
            int x=Integer.parseInt(currentEdgeInfor[0]);
            int y=Integer.parseInt(currentEdgeInfor[1]);
            List<Integer> currentList=new ArrayList<>();
            currentList.add(x);
            currentList.add(y);
            rs.add(currentList);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Ta có 0 -> n-1 servers, mỗi server có thể connect đến các server khác 1 cách directly or indirectly
        //- Critical connection là connection mà nếu remove nó ta không thể connect đến các server khác.
        //* Return lại toàn bộ critical connections.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 2 <= n <= 10^5
        //+ n - 1 <= connections.length <= 10^5
        //+ 0 <= ai, bi <= n - 1
        //+ ai != bi
        //--> Số lượng edge khác nhiều ==> Nếu chạy từng edge thì sẽ slow.
        //
        //- Brainstorm
        //- Ta có thể xác định số lượng connect giữa 2 nodes ==> [i][j] (Độ phức tạp O(n^2))
        //==> Slow
        //* Để giảm độ phức tạp những bài như thế này --> Dùng HashMap để giảm time complexity từ O(N^2) --> O(E) (E là số lượng edges)
        //
        //- Dấu hiệu nhận biết critical connection:
        //+ Tại 1 điểm --> Đi mà có xuất hiện cycle
        //+ 1 cạnh chỉ đi qua duy nhất 1 lần.
        //
        //- Check cycle:
        //+ Đồ thị vô hướng : Nếu chỉ check circle khi đứng tại 1 node --> Ta sẽ không biết edge nào là luôn cần thiết.
        //VD
        // 3 -> 1 -> 2 -> 0 -> 1
        //+ 3 không có cycle nên cạnh kết nối với 3 chính là critical connections (Tức là nếu removed đi thì không thể connect với 3)
        //** ---> SAI, 3 vẫn có cycle vì 3 đi đển (1) ==> Lại vòng về (1)
        //
        //* Đồ thị vô hướng --> Cần thay đổi visited ==> hashMap
        //+ Khi dùng HashMap --> space= O(E) vì bỏ được đi những cases visited[i][j] (i==j)
        //
        //VD:
        // 1 -> (2) -> 3 -> 1
        // 1 -> (2) -> 3 -> 2 (sub graph đi từ (2) có cycle) ==> graph đi từ (1) có cycle
        //
        //VD:
        // 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
        //   \---/               \---/
        //+ Critical connections:
        // (3, 4), (4, 5), (5, 6)
        //+ Những subgraph mà có cycle thì sẽ không có critical connections trong đó
        //<> ==> 1 các connections trong 1 cycle nào đó --> không phải là critical connection
        //** Paraphase 1 chút về yêu cầu:
        //- Loại bỏ all edges thuộc chu trình ==> Các remaining edges chính là các critical connections.
        //
        //- How to find a cycle?
        //- Cycle :  Node --> quay lại trở lại chính nó (Trên các edge chưa visited)
        //VD
        // 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
        //   \---9---/           \---/
        //- Để tìm được cycle --> Mà có thể dùng lại kết quả của việc traverse --> Tránh mỗi node traverse 1 lần riêng rẽ
        //** Thuật toán traverse theo rank để tìm cycle
        //- Khi traverse 1 node
        //VD:
        // A --> C --> B --> D --> E
        //              \--------/
        //- Giả sử ta đi từ A --> Ta sẽ đánh ranking tăng dần:
        // A (1) --> C (2) --> B (3) --> D (4) --> E (5)
        //              \--------\--------------/
        //+ Đến vị trí E --> B ta thấy (2<5)/ (3<5) chứng tỏ:
        //  + Quay lại node cũ --> Có thể có cycle
        //  + Ta sẽ chọn value nhỏ nhất (2) --> Từ 2 có thể có cycle
        //+ Vì là recursion --> Ta sẽ revert lại node cũ --> so sánh với (2: mỉn rank) nếu nodes nào > 2 ==> Node đó đang trong cycle
        //  --> Tức các edge đang trong cycle.
        //
        //- Sử dụng lại kết quả khi traverse all nodes như thế nào?
        //+ Test case 1:
        // F --> A (1) --> C (2) --> B (3) --> D (4) --> E (5)
        //                     \--------\--------------/
        // --> Với dạng như này thì không cần traverse all nodes --> coi node nào đó là root + traverse là được
        //+ Test case 2:
        // F(2) -- A (1) -- C (2) -- B (3) -- D (4) -- E (5)
        //                       \--------\------------/
        //  \---------------/
        //--> Ta thấy ở đây dù có thêm F có connect với C vào phía kia + 2 nodes có chung rank
        //==> Vì nó đối xứng nên nó vẫn đúng vì nếu traverse đến C -> Có thể đến F
        //+ Test case 3:
        // F(2) -- A (1) -- C (2) -- B (3) -- D (4) -- E (5)
        //                       \--------\------------/
        //  \-----------------------/
        //--> Ta thấy ở đây dù có thêm F có connect với B vào phía kia + rank(F) < rank(B)
        //==> Vì nó đối xứng nên nó vẫn đúng vì nếu traverse đến B -> Có thể đến F
        //
        //+ Test case 4:
        // H(3) -> F(2) -- A (1) -- C (2) -- B (3) -- D (4) -- E (5)
        //                       \--------\------------/
        //   \----------------------/
        //
        //+ Test case 5:
        // H(3) -> F(2) -- A (1) -- C (2) -- B (3) -- D (4) -- E (5)
        //                       \--------\-----------/
        //   \----------------------/
        //
        //- Việc loại bỏ các cycle edges sẽ làm như thế nào?
        //+ Ta add all edges có thể vào 1 hashset --> Sau đó cái nào trong cycle thì loại đi
        //  + Space: O(E)
        //+ Ta return lại all cycle edges ==> Sau đó traverse all edges và check loại bỏ từ từ
        //  + Space: O(K) (K<E)
        //
        //- Để làm dạng hashset như này thì các edge được add vào phải có rule:
        // + x<y --> như thế mới remove bình thường được.
        //
        //1.1, Implementation
        //-
        //
        int n = 4;
        int[][] connections = {{0,1},{1,2},{2,0},{1,3}};
        List<List<Integer>> connectionList=new ArrayList<>();
        for(int[] conn:connections){
            List<Integer> currentList=new ArrayList<>();
            currentList.add(conn[0]);
            currentList.add(conn[1]);
            connectionList.add(currentList);
        }
        System.out.println(criticalConnections(n, connectionList));
        //#Reference:
        //1926. Nearest Exit from Entrance in Maze
        //211. Design Add and Search Words Data Structure
        //687. Longest Univalue Path
        //1993. Operations on Tree
    }
}
